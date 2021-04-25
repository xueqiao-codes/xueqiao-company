var app = new Vue({
	el: '#user-center-vue-app',
	data: {
		totalNum : 0,
		totalPage : 1,
		currentPage : 1,
		
		isAddUserDialogShow: false,
		isModifyUserDialogShow: false,
		
		addUserName: null,
		addPassword: null,
		addTel: null,
		addCnName: null,
		modifyUserId: null,
		
		companyUserPage: null,
	},
	mounted: function() {
		this.queryCompanyUserList();
	},
	methods: {
		queryCompanyUserList: function() {
			var that = this;
			var data = {
				pageIndex: this.currentPage - 1,
			}
			postMsg(
				'GetCompanyUsers', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
//						alert(that.telephone);
						that.totalNum = json.value.total;
						that.totalPage = Math.floor((that.totalNum + 9)/10);
						if (that.totalPage < 1) {
							that.totalPage = 1;
						}
						that.companyUserPage = json.value.page;
					}
				}
			)
		},
		gotoFirstPage : function() {
			this.queryCompanyUserList(1);
		},
		gotoPreviousPage : function() {
			this.currentPage -=1;
			if (this.currentPage < 1) {
				this.currentPage = 1;
			}
			this.queryCompanyUserList(this.currentPage);
		},
		gotoGivenPage : function() {
			this.queryCompanyUserList(this.currentPage);
		},
		gotoNextPage : function() {
			this.currentPage +=1;
			if (this.currentPage > this.totalPage) {
				this.currentPage = this.totalPage;
			}
			this.queryCompanyUserList(this.currentPage);
		},
		gotoLastPage : function() {
			this.queryCompanyUserList(this.totalPage);
		},
		gotoMainPage: function() {
			location.href = '/index.html';
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
					location.href = '/index.html';
				}
			)
		},
		showAddCompanyUserDialog: function() {
			this.addUserName = null;
			this.addPassword = null;
			this.addTel = null;
			this.addCnName = null;
			this.isAddUserDialogShow = true;
		},
		addCompanyUser: function() {
			var that = this;
			var data = {
				userName: this.addUserName,
				password: this.addPassword,
				tel: this.addTel,
				cnName: this.addCnName,
			};
			
			if(data.userName == '' || data.userName == undefined || data.userName == ' ') {
//				this.telValid = false;
//				this.telValidMsg = '请输入手机号码';
				alert('请输入用户名');
				return;
			}
			if(data.password == '' || data.password == undefined || data.password == ' ') {
				alert('请输入密码');
				return;
			}
			if(data.tel == '' || data.tel == undefined || data.tel == ' ') {
				alert('请输入手机号');
				return;
			}
			if(data.cnName == '' || data.cnName == undefined || data.cnName == ' ') {
				alert('请输入中文名');
				return;
			}
			
			postMsg(
				'AddCompanyUser', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						alert('添加成功');
						that.queryCompanyUserList();
					}
				}
			)
			this.isAddUserDialogShow = false;
		},
		cancelAddCompanyUser: function() {
			this.isAddUserDialogShow = false;
		},
		showModifyCompanyUserDialog: function(companyUser) {
			this.modifyUserId = companyUser.userId;
			this.addUserName = companyUser.userName;
			this.addPassword = companyUser.password;
			this.addTel = companyUser.tel;
			this.addCnName = companyUser.cnName;
			this.isModifyUserDialogShow = true;
		},
		cancelModifyCompanyUser: function() {
			this.isModifyUserDialogShow = false;
		},
		modifyCompanyUser: function() {
			var that = this;
			var data = {
				modifyUserId: this.modifyUserId,
				userName: this.addUserName,
				password: this.addPassword,
				tel: this.addTel,
				cnName: this.addCnName,
			};
			
			if(data.userName == '' || data.userName == undefined || data.userName == ' ') {
//				this.telValid = false;
//				this.telValidMsg = '请输入手机号码';
				alert('请输入用户名');
				return;
			}
			if(data.password == '' || data.password == undefined || data.password == ' ') {
				alert('请输入密码');
				return;
			}
			if(data.tel == '' || data.tel == undefined || data.tel == ' ') {
				alert('请输入手机号');
				return;
			}
			if(data.cnName == '' || data.cnName == undefined || data.cnName == ' ') {
				alert('请输入中文名');
				return;
			}
			
			postMsg(
				'UpdateCompanyUser', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						alert('编辑成功');
						that.queryCompanyUserList();
					}
				}
			)
			this.isModifyUserDialogShow = false;
		}
	}
})