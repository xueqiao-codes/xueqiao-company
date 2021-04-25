<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@page import="com.longsheng.trader.controller.UserCenterOrder"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>雪橇交易系统</title>
	</head>

	<body>
		<%
			UserCenterOrder.alipayNotify(request.getParameterMap());
		%>
	</body>

</html>