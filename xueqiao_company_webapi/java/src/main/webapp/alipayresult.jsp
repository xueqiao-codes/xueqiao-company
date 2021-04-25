<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<!doctype html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>雪橇金融交易系统</title>

		<!-- Bootstrap core CSS -->
		<link href="public/css/bootstrap.min.css" rel="stylesheet">
		<link href="public/css/sled_common.css" rel="stylesheet">
		<link href="./public/img/logo60.png" rel="shortcut icon" />

		<!-- Custom styles for this template -->
		<link href="public/css/navbar.css" rel="stylesheet">
		<link href="public/css/page_nav.css" rel="stylesheet">

		<style>
			.container {
				width: 1200px;
			}
		</style>
	</head>

	<body>
		<%
			String charset = request.getParameter("charset");
			String outTradeNo = request.getParameter("out_trade_no");
			String method = request.getParameter("method");
			String totalAmount = request.getParameter("total_amount");
			String sign = request.getParameter("sign");
			String tradeNo = request.getParameter("trade_no");
			String authAppId = request.getParameter("auth_app_id");
			String version = request.getParameter("version");
			String appId = request.getParameter("app_id");
			String signType = request.getParameter("sign_type");
			String sellerId = request.getParameter("seller_id");
			String timestamp = request.getParameter("timestamp");
		%>

		<nav v-cloak class="navbar navbar-default fixed-top" role="navigation" id="navbar-app">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"><img src="public/img/logo60.png" width="30" height="30" style="display: inline;" /><span class="nav_title">雪橇金融交易系统</span></a>
				</div>
				<div>
					<ul class="nav navbar-nav pull-right">
						<li>
							<a href="#" @click="gotoMainPage"><span class="nav_tab">首页</span></a>
						</li>
						<li>
							<a href="#"><span class="nav_tab">产品</span></a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<span class="nav_tab">{{getUserName()}}
									<b class="caret"></b></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="#" @click="toggleLeftSideItem(0)"><span class="nav_drop_down_item">公司资料</span></a>
								</li>
								<li>
									<a href="#" @click="toggleLeftSideItem(1)"><span class="nav_drop_down_item">交易托管服务</span></a>
								</li>
								<li>
									<a href="#" @click="toggleLeftSideItem(2)"><span class="nav_drop_down_item">订单列表</span></a>
								</li>
							</ul>
						</li>

						<li>
							<a href="#" @click="logout"><span class="nav_tab">退出登录</span></a>
						</li>

					</ul>
				</div>
			</div>
		</nav>

		<div align="center">
			
			<div style="width: 400px;">

				<div align="left" style="padding: 20px;">
					<div style="margin-bottom: 20px;">
						<span><b>支付完成</b></span>
					</div>
					<div class="dialog_content">
						<div>
							<span>成功支付：</span>
							<span><%=totalAmount%>元</span>
						</div>
						<div>
							<span>订单号：</span>
							<span><%=outTradeNo%></span>
						</div>
						<div>
							<span>您已完成支付，5秒后自动跳转到交易托管服务页面。</span>
						</div>
						<div>
							<span>您需要为该服务分配一名管理员，确保该交易托管服务可以正常使用。</span>
						</div>
					</div>
					<div align="center">
						<button id="btn">确认并返回</button>
					</div>
				</div>
			</div>	
		</div>

		<!--<div>
			<span>charset : </span> <span><%=charset%></span>
		</div>
		<div>
			<span>outTradeNo : </span> <span><%=outTradeNo%></span>
		</div>
		<div>
			<span>method : </span> <span><%=method%></span>
		</div>
		<div>
			<span>totalAmount : </span> <span><%=totalAmount%></span>
		</div>
		<div>
			<span>sign : </span> <span><%=sign%></span>
		</div>
		<div>
			<span>tradeNo : </span> <span><%=tradeNo%></span>
		</div>
		<div>
			<span>authAppId : </span> <span><%=authAppId%></span>
		</div>
		<div>
			<span>version : </span> <span><%=version%></span>
		</div>
		<div>
			<span>appId : </span> <span><%=appId%></span>
		</div>
		<div>
			<span>signType : </span> <span><%=signType%></span>
		</div>
		<div>
			<span>sellerId : </span> <span><%=sellerId%></span>
		</div>
		<div>
			<span>timestamp : </span> <span><%=timestamp%></span>
		</div>

		<div>
			<button id="btn">确认并返回</button>
		</div>-->
		
		<script src="public/js/jquery.min.js"></script>
		<script src="public/js/popper.min.js"></script>
		<script src="public/js/bootstrap.min.js"></script>
		<script src="public/js/tether.min.js"></script>
		<script src="public/js/vue.min.js"></script>
		<script src="public/js/js.cookie.js"></script>
		<script src="public/js/common.js"></script>
		<script src="public/js/common_nav_bar.js"></script>
		<script>
			document.getElementById("btn").onclick = function() {
				location.href = '/hostedservice.html';
			};
		</script>
	</body>

</html>