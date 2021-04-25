

var app = new Vue({
	el: '#main-app',
	data: {
		leftSideIndex: 0,
		iframeUrl: WEB_URLS[0],
		isLogin: false,
		isSignupFragmentShow: false,
		isLoginDialogShow: false,
		isMainContainerShow: false,

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
		//		this.queryCompanyInfo();
	},
	methods: {
		queryCompanyInfo: function() {
			var that = this;
			postMsg(
				'GetCompany', {},
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						//						alert(that.telephone);
						that.companyCode = json.value.companyCode;
						that.companyName = json.value.companyName;
						that.email = json.value.email;
						that.telephone = json.value.telephone;
						that.contact = json.value.contact;
					}
				}
			)
		},
		getUserName: function() {
			var userName = Cookies.get('userName');
			if(userName) {
				return userName;
			}
			return "dapaul";

		},
		toggleLeftSideItem: function(index) {
			this.leftSideIndex = index;
			if(index < WEB_URLS.length) {
				this.iframeUrl = WEB_URLS[index];
			} else {
				this.iframeUrl = WEB_URLS[0];
			}
			this.isMainContainerShow = true;
		},
		getLeftSideItemSytle: function(index) {
			if(index == this.leftSideIndex) {
				return "left_side_item_active";
			} else {
				return "left_side_item_normal";
			}
		},
		showLoginDialog: function() {
			//       	alert('show login');
			this.isLoginDialogShow = !this.isLoginDialogShow;
			this.isSignupFragmentShow = false;
		},
		gotoSignupFragment: function() {
			//			alert("show signup fragment");
			this.isSignupFragmentShow = !this.isSignupFragmentShow;
			this.isLoginDialogShow = false;
		},
		gotoMainPage: function() {
			this.isMainContainerShow = false;
			//			location.href = '/index.html';
		},
		gotoUserCenterPage: function() {
			//			location.href = '/main.html';
		},
		gotoHostingServicePage: function() {
			//			location.href = '/hostedservice.html';
		},
		gotoOrderPage: function() {
			//			location.href = '/order.html';
		},
		gotoUserListPage: function() {
			//			location.href = '/users.html';
		},
		gotoSignupFragment: function() {
			//			alert("show signup fragment");
			this.isSignupFragmentShow = !this.isSignupFragmentShow;
			this.isLoginDialogShow = false;
		},
		gotoForgetPasswdPage: function() {
//			location.href = '/main.html';
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
				companyCode: this.loginCompanyCode,
				password: this.loginPassword
			};

			if(!data.companyCode || !data.password) {
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
					Cookies.set('token', json.value.token, {
						expires: 1,
						path: '/'
					});
					Cookies.set('userId', json.value.userId, {
						expires: 1,
						path: '/'
					});
					Cookies.set('userName', json.value.userName, {
						expires: 1,
						path: '/'
					});
					that.isLogin = true;
					that.isMainContainerShow = true;
					//					location.href = '/main.html';
				}
			}, 'json')
		},
		logout: function() {
			var that = this;
			getMsg(
				'Logout', {},
				function(json) {
					Cookies.set('token', '', {
						expires: 1,
						path: '/'
					});
					Cookies.set('userId', '', {
						expires: 1,
						path: '/'
					});
					that.isLogin = false;
					that.isMainContainerShow = false;
				}
			)
		},
		showLoginDialog: function() {
			//       	alert('show login');
			this.isLoginDialogShow = !this.isLoginDialogShow;
			this.isSignupFragmentShow = false;
		},
		cancelLogin: function() {
			//			alert('cancel login');
			this.isLoginDialogShow = false;
		},
	}
})