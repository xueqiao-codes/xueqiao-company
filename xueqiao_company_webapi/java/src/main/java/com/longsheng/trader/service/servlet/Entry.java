package com.longsheng.trader.service.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.longsheng.trader.ErrorInfo;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.vendor.alipay.AlipayConfig;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.config.ErrorConfig;
import com.longsheng.trader.webmethod.base.ResponseContentType;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import org.soldier.base.logger.AppLog;

public class Entry extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Entry() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        AlipayConfig.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            entryProcess(request, response);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            entryProcess(request, response);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private synchronized WebMethod GetMethod(final String methodName) {
        try {
            Object instance = Class.forName("com.longsheng.trader.webmethod." + methodName).newInstance();
            if (instance instanceof WebMethod) {
                return (WebMethod) instance;
            }
        } catch (ClassNotFoundException e) {
            // ClassNotFound means that method is not exist, so let it gone
        } catch (Exception e) {

        }

        return null;
    }


    @SuppressWarnings("unchecked")
    private void entryProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException {


        Object result = null;

        long startTimestamp = System.currentTimeMillis();
        WebMethod method = null;
        do {
            String methodName = request.getParameter("method");

            if (methodName == null) {
                result = new ErrorInfo(ErrorConfig.METHODNAME_EMPTY, ErrorConfig.METHODNAME_EMPTY_MSG);
                break;
            }
            method = GetMethod(methodName);
            if (method == null) {
                result = new ErrorInfo(ErrorConfig.METHOD_NOT_FOUND, ErrorConfig.METHOD_NOT_FOUND_MSG);
                break;
            }

            try {
                result = method.runMethod(fillWebRequest(request));
            } catch (Exception e) {

                throw new ServletException(e.getMessage(), e);
            }
        } while (false);

        try {
            StringBuffer logBuffer = null;

            logBuffer = new StringBuffer(512);
            logBuffer.append("[timeEscape=");
            logBuffer.append((System.currentTimeMillis() - startTimestamp));
            logBuffer.append("ms]{");
            Enumeration<String> en = request.getParameterNames();
            while (en.hasMoreElements()) {
                String paramName = en.nextElement();
                logBuffer.append(paramName);
                logBuffer.append("=");
                String paramValue = request.getParameter(paramName);
                if (paramValue.length() > 512) {
                    logBuffer.append(paramValue.substring(0, 64));
                    logBuffer.append("......");
                    logBuffer.append(paramValue.substring(paramValue.length() - 64));
                } else {
                    logBuffer.append(paramValue);
                }
                logBuffer.append(" ");
            }
            logBuffer.append("} return ");


            boolean isJsonHeader = true;
            if (method != null) {
                AppLog.d("getContentType: " + method.getContentType());
                if (method.getContentType() == ResponseContentType.TEXT_XML) {
                    response.setHeader("Content-Type", "text/xml;charset=UTF-8");
                    isJsonHeader = false;
                } else if (method.getContentType() == ResponseContentType.TEXT_HTML) {
                    response.setHeader("Content-Type", "text/html;charset=utf-8");
                    isJsonHeader = false;
                } else {
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                }
            }


            StringBuffer jsonBuffer = new StringBuffer(512);
            new Gson().toJson(result, jsonBuffer);
            if (jsonBuffer.length() < 512) {
                logBuffer.append(jsonBuffer);
            } else {
                logBuffer.append(jsonBuffer.subSequence(0, 64));
                logBuffer.append("......");
                logBuffer.append(jsonBuffer.substring(jsonBuffer.length() - 64));
            }


            if (isJsonHeader) {
                new Gson().toJson(result, response.getWriter());
            } else {
                if (result instanceof ValueResult) {
                    String form = ((ValueResult) result).getValue().toString();
                    AppLog.d("form: " + form);
                    response.getWriter().write(form);
                } else if (result instanceof BaseResult) {
                    new Gson().toJson(result, response.getWriter());
                } else {
                    response.getWriter().write(result.toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private WebRequest fillWebRequest(HttpServletRequest request) throws Exception {

        WebRequest webRequest = new WebRequest();
        webRequest.setMultiParameters(request.getParameterMap());

        String realIP = request.getHeader("X-Forwarded-For");


        realIP = realIP != null ? realIP : request.getHeader("X-Real-IP");

        if (realIP != null) {
            int index = realIP.indexOf(',');
            if (index != -1) {
                realIP = realIP.substring(0, index);
            }
        }
        webRequest.setIpAddress(realIP != null ? realIP : request.getRemoteAddr());
        AppLog.d("realIP: " + realIP);

        if (request.getMethod().equalsIgnoreCase("POST")) {
            if (request.getContentType() != null) {


                if (request.getContentType().startsWith("application/json")) {
                    webRequest.setContent(IOUtils.toByteArray(request.getInputStream()));
                } else if (request.getContentType().startsWith("multipart/form-data")) {

                } else if (request.getContentType().startsWith("text/xml")) {
                    webRequest.setContent(IOUtils.toByteArray(request.getInputStream()));
                }

            }
        }

        return webRequest;
    }
}
