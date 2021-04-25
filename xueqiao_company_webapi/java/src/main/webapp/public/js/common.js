
//var host = 'http://devtcompany.sledtrader.cn/';  // dev
//var host = 'http://gatcompany.sledtrader.cn/';  // gamma
var host = "/"

var debug = false;
var localhost = debug ? '/webapp/' : '/';

var WEB_URLS = [
	'/settings.html',
	'/hostedservice.html',
	'/order.html',
];

var clearCookies = function() {
	Cookies.set('token', '', {
		expires: 1,
		path: '/'
	});
	Cookies.set('userId', '', {
		expires: 1,
		path: '/'
	});
	Cookies.set('userName', '', {
		expires: 1,
		path: '/'
	});
}

var tokenMethod = [
	'Apply',
	'BuyNow',
	'ChangePassword',
	'GetCompany',
	'SetEmail',
	'SetInfo',
	'SetTel',
	'SetCompanyCode',
	'Logout',
	'GetCompanyUsers',
	'AddCompanyUser',
	'UpdateCompanyUser',
	'GetHostedServices',
	'GetOrders',
	'AddNewHostedService',
	'GetCompanyGroupUsers',
	'UpdateCompanyGroupUsers',
	'UpdateCompanyGroupUser',
	'SubmitOrder',
	'GetUpgradeSpecInfo',
	'GetRechargeTimeProducts',
	'GetCompanyGroups',
	'UpdateCompanyUserStatus',
	'PayOrderWIthXCoin'
];

// post数据通用函数
var postMsg = function(method_name, data, result) {
	var requestUrl = host + 'service?method=' + method_name;

	if($.inArray(method_name, tokenMethod) != -1) {
		var token = Cookies.get('token');
		var userId = Cookies.get('userId');
		if(!token || !userId) {
			location.href = localhost + 'index.html';
			return;
		}
		requestUrl += ('&token=' + token + '&userId=' + userId);
	}
	$.post(requestUrl, data, function(json) {
			//            alert(json.errorInfo.errMsg);
			if(json.errorInfo && json.errorInfo.errorCode == 1000) {
				//				alert(json.errorInfo.errorCode)
				clearCookies();
				location.href = localhost + 'index.html';
			} else {
				result(json);
			}
		},
		"json"
	)
}

// get数据通用函数
var getMsg = function(method_name, data, result) {
	var requestUrl = host + 'service?method=' + method_name;
	//	var tokenMethod = [
	//		'Apply',
	//		'BuyNow',
	//		'ChangePassword',
	//		'GetCompany',
	//		'SetEmail',
	//		'SetInfo',
	//		'SetTel',
	//		'SetCompanyCode',
	//		'Logout'
	//	];

	if($.inArray(method_name, tokenMethod) != -1) {
		var token = Cookies.get('token');
		var userId = Cookies.get('userId');
		if(!token || !userId) {
			location.href = localhost + 'index.html';
			return;
		}
		requestUrl += ('&token=' + token + '&userId=' + userId);
	}

	$.getJSON(requestUrl, data, function(json) {
		//		if(json.errorInfo) {
		////			alert(json.errorInfo.errMsg);
		//			error && error(json);
		//		} else {
		//			success && success(json)
		//		}
		if(json.errorInfo && json.errorInfo.errorCode == 1000) {
			//				alert(json.errorInfo.errorCode)
			clearCookies();
			location.href = localhost + 'index.html';
		} else {
			result(json);
		}
		//		result(json);
	})
}

var gotoSettingsPage = function() {
	location.href = WEB_URLS[0];
}
var gotoHostedServicePage = function() {
	location.href = WEB_URLS[1];
}
var gotoOrderPage = function() {
	location.href = WEB_URLS[2];
}
var gotoGivenPage = function(index) {
	if(index < WEB_URLS.length) {
		location.href = WEB_URLS[index];
	} else {
		location.href = WEB_URLS[0];
	}
}
var gotoMainPage = function() {
	location.href = '/index.html'
}

var getUserName = function() {
	var userName = Cookies.get('userName');
	if(userName) {
		return userName;
	}
	return "";
}

var setCookies = function(token, userId, userName) {
	Cookies.set('token', token, {
		expires: 1,
		path: '/'
	});
	Cookies.set('userId', userId, {
		expires: 1,
		path: '/'
	});
	Cookies.set('userName', userName, {
		expires: 1,
		path: '/'
	});
}

var clearCookies = function() {
	Cookies.set('token', '', {
		expires: 1,
		path: '/'
	});
	Cookies.set('userId', '', {
		expires: 1,
		path: '/'
	});
	Cookies.set('userName', '', {
		expires: 1,
		path: '/'
	});
}

var payOrder = function(orderId) {
	// 直接页跳转
//	location.href = '/pay.jsp?orderId=' + orderId;

	// 另起一页
	window.open('/pay.jsp?orderId=' + orderId, '_blank');
}

var isInArray = function(groupId) {
	
}
