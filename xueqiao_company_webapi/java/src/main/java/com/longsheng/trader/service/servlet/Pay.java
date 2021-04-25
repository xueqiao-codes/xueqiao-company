package com.longsheng.trader.service.servlet;

import com.longsheng.trader.bean.order.PayOrderQRCodeResult;
import com.longsheng.trader.error.PaymentErrorInfo;
import com.longsheng.trader.utils.PayUtil;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Pay extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderIdStr = req.getParameter("orderId");
        int orderId = Integer.parseInt(orderIdStr);
        AppLog.d("PayServlet ---- orderId : " + orderId);

        String ret = "";

        PayOrderQRCodeResult payOrderQRCodeResult = null;
        try {
            payOrderQRCodeResult = PayUtil.pay(orderId);
        } catch (TException e) {
            AppLog.e("PayServlet ---- PayUtil.pay ---- error", e);
        }
        if (payOrderQRCodeResult == null) {
            ret = "内部错误，支持失败";
        }

        if (payOrderQRCodeResult.getErrorCode() == 0) {
            ret = payOrderQRCodeResult.getHtmlPage();
        } else {
            ret = PaymentErrorInfo.getErrorInfo(payOrderQRCodeResult.getErrorCode());
            if (ret == null) {
                ret = payOrderQRCodeResult.getErrorMsg();
            }
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type","text/html;charset=UTF-8");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>雪橇投资管理系统</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(ret);
        out.println("</body>");
        out.println("</html>");
    }
}
