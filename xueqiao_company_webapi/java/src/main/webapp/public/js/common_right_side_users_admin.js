var app = new Vue({
	el: '#right-side-users-admin',
	data: {
		totalNum: 0,
		totalPage: 1,
		currentPage: 1,

		isAddUserDialogShow: false,
		isModifyUserDialogShow: false,

		//		modifyUserId: null,

		companyUserPage: null,
		//		companyGroupPage: null,
		companyGroupPage: [{
				"groupId": 1142,
				"groupName": "大二号"
			},
			{
				"groupId": 1141,
				"groupName": "大一号"
			},
			{
				"groupId": 1140,
				"groupName": "墨九号"
			}
		],

		newCompanyUser: {
			userName: null,
			password: null,
			tel: null,
			cnName: null,
			groupUserList: null,
		},

		modifiedCompanyGroupList: null,
		modifiedCompanyUser: {
			userId: 0,
			userName: null,
			password: null,
			tel: null,
			cnName: null,
			groupUserList: null,
		},

	},
	mounted: function() {
		this.queryCompanyUserList();
		this.queryCompanyGroupList();
	},
	methods: {
		queryCompanyUserList: function() {
			var that = this;
			var data = {
				pageIndex: -1, //不分页
				withGroupUser: 1, //获取groupUser信息
				allUser: 1, // 获取所有用户
			}
			postMsg(
				'GetCompanyUsers', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						that.totalNum = json.value.total;
						that.totalPage = Math.floor((that.totalNum + 9) / 10);
						if(that.totalPage < 1) {
							that.totalPage = 1;
						}
						that.companyUserPage = json.value.page;
						//						alert("user status : " + that.companyUserPage[0].status)
					}
				}
			)
		},
		queryCompanyGroupList: function() {
			var that = this;
			postMsg(
				'GetCompanyGroups', {},
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						that.companyGroupPage = json.results;
					}
				}
			)
		},
		gotoFirstPage: function() {
			this.queryCompanyUserList(1);
		},
		gotoPreviousPage: function() {
			this.currentPage -= 1;
			if(this.currentPage < 1) {
				this.currentPage = 1;
			}
			this.queryCompanyUserList(this.currentPage);
		},
		gotoGivenPage: function() {
			this.queryCompanyUserList(this.currentPage);
		},
		gotoNextPage: function() {
			this.currentPage += 1;
			if(this.currentPage > this.totalPage) {
				this.currentPage = this.totalPage;
			}
			this.queryCompanyUserList(this.currentPage);
		},
		gotoLastPage: function() {
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
//			this.addUserName = null;
//			this.addPassword = null;
//			this.addTel = null;
//			this.addCnName = null;
//			this.addCompanyUserSelectedGroups = null;
			this.newCompanyUser.userName = null;
			this.newCompanyUser.password = null;
			this.newCompanyUser.tel = null;
			this.newCompanyUser.cnName = null;
			this.newCompanyUser.groupUserList = null;
			
			this.isAddUserDialogShow = true;
		},
		addCompanyUser: function() {
			var that = this;
			//			var data = {
			//				userName: this.addUserName,
			//				password: this.addPassword,
			//				tel: this.addTel,
			//				cnName: this.addCnName,
			//			};

			if(this.newCompanyUser.userName == '' || this.newCompanyUser.userName == undefined || this.newCompanyUser.userName == ' ') {
				//				this.telValid = false;
				//				this.telValidMsg = '请输入手机号码';
				alert('请输入用户名');
				return;
			}
			if(this.newCompanyUser.password == '' || this.newCompanyUser.password == undefined || this.newCompanyUser.password == ' ') {
				alert('请输入密码');
				return;
			}
			if(this.newCompanyUser.tel == '' || this.newCompanyUser.tel == undefined || this.newCompanyUser.tel == ' ') {
				alert('请输入手机号');
				return;
			}
			if(this.newCompanyUser.cnName == '' || this.newCompanyUser.cnName == undefined || this.newCompanyUser.cnName == ' ') {
				alert('请输入中文名');
				return;
			}

			var data = {
				newCompanyUser: JSON.stringify(this.newCompanyUser),
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
			this.modifiedCompanyUser.userId = companyUser.userId;
			this.modifiedCompanyUser.userName = companyUser.userName;
			this.modifiedCompanyUser.password = companyUser.password;
			this.modifiedCompanyUser.tel = companyUser.tel;
			this.modifiedCompanyUser.cnName = companyUser.cnName;
			this.modifiedCompanyUser.groupUserList = companyUser.groupUserList;
			this.modifiedCompanyGroupList = this.modifiedCompanyUser.groupUserList.map(function(groupUser) {
				return {
					groupId: groupUser.groupId,
					groupName: groupUser.groupName,
				};
			});
			this.isModifyUserDialogShow = true;
		},
		cancelModifyCompanyUser: function() {
			this.isModifyUserDialogShow = false;
		},
		onModifyGroupChanged: function(modifiedCompanyGroupList) {
			var that = this;
			//			alert('onModifyGroupChanged : ' + modifyCompanyGroupList.length);
			var temGroupUserList = modifiedCompanyGroupList.map(function(group) {
				var originalItem = null;
				if(that.modifiedCompanyUser.groupUserList) {
					that.modifiedCompanyUser.groupUserList.forEach(function(item) {
						if(item.groupId == group.groupId) {
							originalItem = item;
							return;
						}
					});
				}
				if(originalItem) {
					return originalItem;
				} else {
					return {
						groupId: group.groupId,
						groupName: group.groupName,
						role: 'TRADER',
						cnRole: '交易员',
					};
				}
			});
			that.modifiedCompanyUser.groupUserList = temGroupUserList;

			//			this.modifyCompanyGroupList
		},
		modifyCompanyUser: function() {
			var that = this;

			if(this.modifiedCompanyUser.userName == '' || this.modifiedCompanyUser.userName == undefined || this.modifiedCompanyUser.userName == ' ') {
				//				this.telValid = false;
				//				this.telValidMsg = '请输入手机号码';
				alert('请输入用户名');
				return;
			}
			if(this.modifiedCompanyUser.password == '' || this.modifiedCompanyUser.password == undefined || this.modifiedCompanyUser.password == ' ') {
				alert('请输入密码');
				return;
			}
			if(this.modifiedCompanyUser.tel == '' || this.modifiedCompanyUser.tel == undefined || this.modifiedCompanyUser.tel == ' ') {
				alert('请输入手机号');
				return;
			}
			if(this.modifiedCompanyUser.cnName == '' || this.modifiedCompanyUser.cnName == undefined || this.modifiedCompanyUser.cnName == ' ') {
				alert('请输入中文名');
				return;
			}

			var data = {
				modifyCompanyUser: JSON.stringify(this.modifiedCompanyUser),
				//				modifyUserId: this.modifyCompanyUser.userId,
				//				userName: this.modifyCompanyUser.userNam,
				//				password: this.modifyCompanyUser.password,
				//				tel: this.modifyCompanyUser.tel,
				//				cnName: this.modifyCompanyUser.cnName,
			};

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
		},
		disableCompanyUser: function(companyUser) {
			var that = this;
			companyUser.status = 'DISABLED';
			if(confirm("确认停用吗？")) {
				var data = {
					modifyCompanyUser: JSON.stringify(companyUser),
				};

				postMsg(
					'UpdateCompanyUserStatus', data,
					function(json) {
						if(json.errorInfo) {
							alert(json.errorInfo.errMsg);
						} else {
							alert('已停用');
							that.queryCompanyUserList();
						}
					}
				)
			}
		},
		enableCompanyUser: function(companyUser) {
			var that = this;
			companyUser.status = 'NORMAL';
			if(confirm("确认启用吗？")) {
				var data = {
					modifyCompanyUser: JSON.stringify(companyUser),
				};

				postMsg(
					'UpdateCompanyUserStatus', data,
					function(json) {
						if(json.errorInfo) {
							alert(json.errorInfo.errMsg);
						} else {
							alert('已启用');
							that.queryCompanyUserList();
						}
					}
				)
			}
		}
	}
})