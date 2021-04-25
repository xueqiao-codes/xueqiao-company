<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.longsheng.owl.module.usercenter.UserCenterOrder" %>
<%
	UserCenterOrder.alipayNotify(request.getParameterMap());
%>