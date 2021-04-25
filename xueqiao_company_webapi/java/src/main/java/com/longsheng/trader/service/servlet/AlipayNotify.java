package com.longsheng.trader.service.servlet;

import com.longsheng.trader.controller.UserCenterOrder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AlipayNotify extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        AppLog.i("AlipayNotify ---- param map : " + new Gson().toJson(req.getParameterMap()));
        String ret = UserCenterOrder.alipayNotify(req.getParameterMap());
        resp.setHeader("Content-Type", "application/json;charset=UTF-8");
        resp.getWriter().write(ret);
    }

    /**
     * 支付宝回调需要post方法
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
