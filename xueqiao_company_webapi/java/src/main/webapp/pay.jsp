<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@page import="com.longsheng.trader.utils.PayUtil"%>
<%@page import="com.longsheng.trader.bean.order.PayOrderQRCodeResult"%>
<%@page import="com.longsheng.trader.error.PaymentErrorInfo"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>雪橇交易系统</title>
	</head>

	<body>
		<%
			String orderIdStr = request.getParameter("orderId");
			int orderId = Integer.parseInt(orderIdStr);
			
			PayOrderQRCodeResult payOrderQRCodeResult = PayUtil.pay(orderId);

			String ret = "";
	        if (payOrderQRCodeResult.getErrorCode() == 0) {
	            ret = payOrderQRCodeResult.getHtmlPage();
	        } else {
	        	ret = PaymentErrorInfo.getErrorInfo(payOrderQRCodeResult.getErrorCode());
	        	if (ret == null) {
	        	    ret = payOrderQRCodeResult.getErrorMsg();
	        	}
	        }
		%>
		<%=ret%>
	</body>

</html>