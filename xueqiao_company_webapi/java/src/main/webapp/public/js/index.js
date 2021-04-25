//var host = 'http://devwww.sledtrader.cn/';
//
//// post数据通用函数
//var postMsg = function(method_name, data, result) {
//	var requestUrl = host + 'service?method=' + method_name;
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
//	if($.inArray(method_name, tokenMethod) != -1) {
//		var token = Cookies.get('token');
//		var userId = Cookies.get('userId');
//		requestUrl += ('&token=' + token + '&userId=' + userId);
//	}
//	$.post(requestUrl, data, function(json) {
//			//            alert(json.errorInfo.errMsg);
//			result(json);
//		},
//		"json"
//	)
//}
//
//// get数据通用函数
//var getMsg = function(method_name, data, result) {
//	var requestUrl = host + 'service?method=' + method_name;
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
//
//	if($.inArray(method_name, tokenMethod) != -1) {
//		var token = Cookies.get('token');
//		var userId = Cookies.get('userId');
//		requestUrl += ('&token=' + token + '&userId=' + userId);
//	}
//
//	$.getJSON(requestUrl, data, function(json) {
//		//		if(json.errorInfo) {
//		////			alert(json.errorInfo.errMsg);
//		//			error && error(json);
//		//		} else {
//		//			success && success(json)
//		//		}
//		result(json);
//	})
//}

var app = new Vue({
	el: '#vue-app',
	data: {
		isLogin: false,
		isSignupFragmentShow: false,
		isLoginDialogShow: false,
		vbtnDisabled: false,
		sbtnDisabled: false,
		lbtnDisabled: false,

		loginCompanyCode: null,
		loginPassword: null,

		companyCode: null,
		companyName: null,
		email: null,
		telephone: null,
		password: null,
		contact: null,
		companySignupVerifyCode: null,

		companySignupPasswdDoubleCheck: null,
		getVerifyBtnText: '获取验证码',
		telValid: false,
		telValidMsg: '',

	},
	mounted: function() {
		var token = Cookies.get('token');
		var userId = Cookies.get('userId');
		if(token && userId) {
			this.isLogin = true;
		} else {
			this.isLogin = false;
		}
	},
	methods: {
		gotoMainPage: function() {
			this.isSignupFragmentShow = false;
			this.isLoginDialogShow = false;
		},
		gotoSignupFragment: function() {
			this.isSignupFragmentShow = !this.isSignupFragmentShow;
			this.isLoginDialogShow = false;
		},
		toggleLeftSideItem: function(index) {
			gotoGivenPage(index);
		},
		getUserName: function() {
//			var userName = Cookies.get('userName');
//			if(userName) {
//				return userName;
//			}
//			return "";
			return getUserName();

		},
		getVerifyCode: function() {

			this.checkTel();
			if(!this.telValid) {
				return;
			}
			if(this.vbtnDisabled) return;

			var data = {
				tel: this.telephone
			};

			var that = this,
				time = 60;
			that.vbtnDisabled = true;
			that.time = '(' + time + '\")';
			that.getVerifyBtnText = that.time;

			var timer = setInterval(function() {
				if(time == '0') {
					that.time = '';
					clearInterval(timer);
					that.vbtnDisabled = false;
					that.getVerifyBtnText = '获取验证码';
				} else {
					time--;
					that.time = '(' + time + '\")';
					that.getVerifyBtnText = that.time;
				}
			}, 1000)

			postMsg(
				'GetTelVerifyCode', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {

					}
				}
			)
		},
		signup: function() {
			var data = {
				companyCode: this.companyCode,
				companyName: this.companyName,
				email: this.email,
				telephone: this.telephone,
				password: this.password,
				contact: this.contact,
				companySignupVerifyCode: this.companySignupVerifyCode,
			};

			if(this.sbtnDisabled) return;

			var that = this;

			if(!data.companyCode || !data.companyName || !data.email || !data.telephone || !data.contact || !data.password || !data.companySignupVerifyCode) {
				that.signUpTips = true;
				that.signUpTipsMsg = '请完善资料信息';
				alert(that.signUpTipsMsg);
				return;
			} else {
				that.signUpTips = false;
				that.signUpTipsMsg = '';
//				alert('注册成功');
			}

			that.sbtnDisabled = true;

			//			var requestUrl = host + 'service?method=SignUp';
			//			$.post(requestUrl, data, function(json) {
			postMsg("SignUp", data, function(json) {
					if(json.errorInfo) {
						that.sbtnDisabled = false;
						that.signUpTipsMsg = json.errorInfo.errMsg;
						that.signUpTips = true;
						alert(json.errorInfo.errMsg);
					} else {
						that.sbtnDisabled = false;
						that.showLoginDialog();
						//						location.href = '/login.html';
					}
				},
				"json"
			)
		},
		// 邮箱验证
		checkEmail: function() {
			this.emailValid = true;
			this.email_reg = (/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(this.email));

			if(this.email_reg) {
				this.validImgEmail = 'right';
				this.emailValidMsg = '该邮箱可以使用';
			} else {
				this.validImgEmail = 'wrong';
				this.emailValidMsg = '该邮箱格式不正确';
			}
		},

		// 密码验证
		cheakPwd: function() {
			this.pwdValid = true;
			this.pwd_reg = (/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test(this.pwd));
			if(this.pwd_reg) {
				this.validImgPwd = 'right';
				this.pwdValidMsg = '密码可以使用';
			} else {
				this.validImgPwd = 'wrong';
				this.pwdValidMsg = '密码格式不正确';
			}
		},

		// 确认密码验证
		verifyPwd: function() {
			this.vpwdValid = true;
			this.vpwd_reg = (this.pwd === this.vpwd);
			if(this.vpwd_reg && this.pwd_reg) {
				this.validImgVpwd = 'right';
				this.vpwdValidMsg = '验证通过';
			} else {
				this.validImgVpwd = 'wrong';
				this.vpwdValidMsg = '密码不一致';
			}
		},

		// 联系人输入验证
		checkContact: function() {
			this.contactValid = true;
			this.contact_reg = (/^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/.test(this.contact));
			if(this.contact_reg) {
				this.validImgContact = 'right';
				this.contactValidMsg = '';
			} else {
				this.validImgContact = 'wrong';
				this.contactValidMsg = '请输入正确的人名';
			}
		},

		// 手机号输入验证
		checkTel: function() {
			//			alert('checkTel : ' + this.telephone);
			if(this.telephone == '' || this.telephone == undefined || this.telephone == ' ') {
				this.telValid = false;
				this.telValidMsg = '请输入手机号码';
				return;
			}
			this.telValid = (/^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/.test(this.telephone));
			//			alert('checkTel : ' + this.telValid);
			if(this.telValid) {
				this.telValidMsg = '';
			} else {
				this.telValidMsg = '手机号码不正确';
			}
		},
		login: function() {
			if(this.lbtnDisabled) return;

			var that = this;

			var data = {
				account: this.loginCompanyCode,
				password: this.loginPassword
			};

			if(!data.account || !data.password) {
				that.loginError = true;
				that.loginErrorMsg = '请输入完整信息';
				alert('请输入完整信息');
				return;
			}

			that.lbtnDisabled = true;
			//            $.post(host + 'service?method=Login', data, function(json){
			postMsg("Login", data, function(json) {
				that.lbtnDisabled = false;

				if(json.errorInfo) {
					console.log(json);
					that.loginError = true;
					that.loginErrorMsg = json.errorInfo.errMsg;
					alert(json.errorInfo.errMsg);
				} else {
					//              	alert('login success');
					that.isLoginDialogShow = false;
					setCookies(json.value.token, json.value.userId, json.value.userName);
					that.isLogin = true;
					gotoSettingsPage();
					//					location.href = '/main.html';
				}
			}, 'json')
		},
		logout: function() {
			var that = this;
			getMsg(
				'Logout', {},
				function(json) {
					clearCookies();
					that.isLogin = false;
				}
			)
		},
		showLoginDialog: function() {
			this.isLoginDialogShow = !this.isLoginDialogShow;
			this.isSignupFragmentShow = false;
		},
		cancelLogin: function() {
			this.isLoginDialogShow = false;
		},
		gotoForgetPasswdPage: function() {
//			location.href = '/main.html';
		},
		gotoUserCenterPage: function() {
			location.href = '/main.html';
		},
		gotoHostingServicePage: function() {
			location.href = '/hostedservice.html';
		},
		gotoOrderPage: function() {
			location.href = '/order.html';
		},
		gotoUserListPage: function() {
			location.href = '/users.html';
		}
	}
})