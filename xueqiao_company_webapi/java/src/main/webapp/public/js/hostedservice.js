var HOSTING_MACHINE_SPEC_LIST = [{
		inteface: '支持的交易接口',
		basic: 'CTP',
		common: 'CTP&易盛',
		enhanced: 'CTP&易盛',
	},
	{
		inteface: '支持同时启用资金账户数量',
		basic: '3',
		common: '5',
		enhanced: '8',
	},
	{
		inteface: '支持同时启用子账户数量',
		basic: '5',
		common: '10',
		enhanced: '15',
	},
	{
		inteface: '每个子账户可分配用户数量',
		basic: '5',
		common: '10',
		enhanced: '15',
	},
	{
		inteface: '支持用户订阅合约行情数量',
		basic: '50',
		common: '80',
		enhanced: '100',
	},
	{
		inteface: '支持用户订阅套利行情数量',
		basic: '20',
		common: '50',
		enhanced: '80',
	},
];

var SERVICE_TYPE_LIST = [
	'REAL',
	'SIM',
];

var SERVICE_TYPE_CN_LIST = [
	'实盘',
	'模拟盘',
];

var PRODUCT_PURPOSE_LIST = [
	'NEW',
	'UPGRADE',
	'RECHARGE_TIME',
];

var DEFAULT_PAYTYPE = 'INNER';

var app = new Vue({
	el: '#hostedservice-vue-app',
	data: {
		totalNum: 0,
		totalPage: 1,
		currentPage: 1,

		hosting_machine_spec_list: HOSTING_MACHINE_SPEC_LIST,

		isAddHostedServiceDialogShow: false,
		isConfirmOrderDialogShow: false,
		isManageHostedServiceDialogShow: false,
		isManageGroupUserDialogShow: false,
		isSetGroupUserRoleDialogShow: false,
		isUpdateSpecDialogShow: false,
		isRechargeDialogShow: false,
		isWaitingForPayDialogShow: false,
		newHostedService: { // duplicated
			hostedServiceName: null,
			companyGroupCode: null,

			typeTabIndex: 0,
			specTabIndex: 0,
		},
		createHMOrderInfo: {
			hostedServiceName: null,
			orderType: null,
			productId: 0,
			typeTabIndex: 0,
			specTabIndex: 0,
			serviceTypeStr: null,
			specStr: null,
			timeIncrement: 0,
			price: 0,
		},
		
		// 确认订单信息
		orderConfirmInfo: {
			payType: DEFAULT_PAYTYPE,
			productId: 0,
			groupId: 0,
			hostedServiceName: null,
			productPurpose: null,
			orderTypeCN: null,
			serviceType: null,
			serviceTypeStr: null,
			serviceSpecStr: null,
			serviceTime: 0,
			totalAmount: 0,
			price: 0,
			quantity: 1,
		},
		
		hostedServicePage: null,
		selectedHostedService: null,
		selectedHostedServiceGroupUserPage: null,
		selectedHostedServiceGroupUserIdList: [],
		//		selectedHostedServiace: {
		//			groupId: 1234,
		//			groupName: 'dp test',
		//			serviceType: 'real',
		//			specName: 'basic',
		//			expiredTimestamp: '20180702'
		//		},
		modifyGroupUser: null,

		companyUserPage: null,
		hostedServiceProductList: null,

		/*hostedServiceProductList: [{
			type: "BASIC_SPEC",
			name: "基本类型",
			description: "开通基本类型，最大支持接入2个上手资金账户，创建5个资金子账户",
			productNewDetailList: [{
				productId: 4,
				price: "500.0",
				currencyType: "RMB",
				timeIncrement: 90
			}]
		}, {
			type: "COMMON_SPEC",
			name: "通用型",
			description: "通用型托管机，最大支持接入4个上手资金账户，创建10个资金子账户",
			productNewDetailList: [{
				productId: 6,
				price: "88888.0",
				currencyType: "RMB",
				timeIncrement: 0
			}]
		}, {
			type: "ENHANCEMENT_SPEC",
			name: "增强型",
			description: "增强型托管机，最大支持接入6个上手资金账户，创建15个资金子账户",
			productNewDetailList: [{
				productId: 7,
				price: "128888.0",
				currencyType: "RMB",
				timeIncrement: 0
			}]
		}],*/
		// 升级商品列表（动态获取）
		productUpgrateList: null,
		upgradeSpecTabIndex: 0,
		selectedProductUpgrade: null,
		
		// 续费商品列表 （动态获取）
		productRechargeTimeList: null,
		selectedProductRechargeTime: null,
		
		selectedProductDetailList: null,
		selectedProductDetail: {
			productId: null,
			price: null,
			currencyType: null,
			timeIncrement: null,
		},
		submitOrderId: null,
	},
	mounted: function() {
		this.queryHostedServiceList();
		this.queryNewHostedServiceProductList();
		this.queryCompanyUsers();
	},
	methods: {
		queryHostedServiceList: function() {
			var that = this;
			var data = {
				pageIndex: this.currentPage - 1,
			}
			postMsg(
				'GetHostedServices', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						//						alert(that.telephone);
						that.totalNum = json.value.total;
						that.totalPage = Math.floor((that.totalNum + 9) / 10);
						if(that.totalPage < 1) {
							that.totalPage = 1;
						}
						that.hostedServicePage = json.value.page;
					}
				}
			)
		},
		queryNewHostedServiceProductList: function() {
			var that = this;
			postMsg(
				'GetNewHostedServiceProducts', {},
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						that.hostedServiceProductList = json.results;
					}
				}
			)
		},
		queryCompanyUsers: function() {
			var that = this;
			var data = {
                pageIndex: -1, //不分页
                withGroupUser: -1, //不获取groupUser信息
                allUser: -1,  // 只获取有效用户（未被禁用）
            }
			postMsg(
				'GetCompanyUsers', {},
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						that.companyUserPage = json.value.page;
					}
				}
			)
		},
		queryCompanyGroupUsers: function(groupId) {
			var that = this;
			var data = {
				groupId: groupId,
			}
			postMsg(
				'GetCompanyGroupUsers', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						that.selectedHostedServiceGroupUserPage = json.value.page;

						if(that.selectedHostedServiceGroupUserPage.length > 0) {
							that.selectedHostedServiceGroupUserPage.forEach(function(item, index) {
								that.selectedHostedServiceGroupUserIdList[index] = item.userId;
							});
						} else {
							that.selectedHostedServiceGroupUserIdList = [];
						}

					}
				}
			)
		},
		gotoFirstPage: function() {
			this.currentPage = 1;
			this.queryHostedServiceList(1);
		},
		gotoPreviousPage: function() {
			this.currentPage -= 1;
			if(this.currentPage < 1) {
				this.currentPage = 1;
			}
			this.queryHostedServiceList(this.currentPage);
		},
		gotoGivenPage: function() {
			this.queryHostedServiceList(this.currentPage);
		},
		gotoNextPage: function() {
			this.currentPage += 1;
			if(this.currentPage > this.totalPage) {
				this.currentPage = this.totalPage;
			}
			this.queryHostedServiceList(this.currentPage);
		},
		gotoLastPage: function() {
			this.currentPage = this.totalPage;
			this.queryHostedServiceList(this.totalPage);
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
		showAddHostedServiceDialog: function() {
			this.createHMOrderInfo.hostedServiceName = null,

			this.createHMOrderInfo.typeTabIndex = 0;
			this.createHMOrderInfo.specTabIndex = 0;

			this.selectedProductDetailList = this.hostedServiceProductList[0].productNewDetailList;
			this.selectedProductDetail = this.selectedProductDetailList[0];

			this.isAddHostedServiceDialogShow = true;

		},
		cancelAddHostedService: function() {
			this.isAddHostedServiceDialogShow = false;
		},
		submitOrder: function() {
			this.createHMOrderInfo.orderTypeCN = '开通服务';
//			this.createHMOrderInfo.hostedServiceName = "";
			this.createHMOrderInfo.serviceTypeStr = SERVICE_TYPE_CN_LIST[this.createHMOrderInfo.typeTabIndex];
			this.createHMOrderInfo.specStr = this.hostedServiceProductList[this.createHMOrderInfo.specTabIndex].name;

			this.createHMOrderInfo.productId = this.selectedProductDetail.productId;
			this.createHMOrderInfo.timeIncrement = this.selectedProductDetail.timeIncrement;
			this.createHMOrderInfo.price = this.selectedProductDetail.price;
			if(!this.createHMOrderInfo.hostedServiceName) {
				alert('请设置服务名称');
				return;
			}

			// set order info for create hosted service
			this.orderConfirmInfo.payType = DEFAULT_PAYTYPE;
			this.orderConfirmInfo.productId = this.selectedProductDetail.productId;
			this.orderConfirmInfo.hostedServiceName = this.createHMOrderInfo.hostedServiceName;
			this.orderConfirmInfo.orderTypeCN = '开通服务';
			this.orderConfirmInfo.productPurpose = PRODUCT_PURPOSE_LIST[0];
			this.orderConfirmInfo.serviceType = SERVICE_TYPE_LIST[this.createHMOrderInfo.typeTabIndex];
			this.orderConfirmInfo.serviceTypeStr = SERVICE_TYPE_CN_LIST[this.createHMOrderInfo.typeTabIndex];
			this.orderConfirmInfo.serviceSpecStr = this.hostedServiceProductList[this.createHMOrderInfo.specTabIndex].name;
			this.orderConfirmInfo.serviceTime = this.selectedProductDetail.timeIncrement;
			this.orderConfirmInfo.totalAmount = this.selectedProductDetail.price;
			this.orderConfirmInfo.price = this.selectedProductDetail.price;
			this.orderConfirmInfo.quantity = 1; //开通服务的商品数量为1
			
			this.showConfirmOrderDialog();
		},
		typeTabToggle: function(typeTabIndex) {
			this.createHMOrderInfo.typeTabIndex = typeTabIndex;
		},
		specTabToggle: function(specTabIndex) {
			//		    alert('specTabIndex : ' + specTabIndex);
			this.createHMOrderInfo.specTabIndex = specTabIndex;
			if(specTabIndex < this.hostedServiceProductList.length) {
				this.selectedProductDetailList = this.hostedServiceProductList[specTabIndex].productNewDetailList;
				this.selectedProductDetail = this.selectedProductDetailList[0];
				//				alert('selectedProductDetailList length : ' + this.selectedProductDetailList.length);
			} else {
				alert('specTabToggle : ' + specTabIndex);
			}
		},
		toggleCss: function(isActive) {
			if(isActive) {
				return 'item_active';
			} else {
				return 'item_inactive';
			}
		},
		showHostedServiceManagerDailog: function(hostedService) {
			if (hostedService.hostingServiceStatus == 'EXPIRED') {
				alert('托管服务已过期，不能再操作');
				return;
			}
			if (hostedService.hostingServiceStatus == 'RELEASED') {
				alert('托管服务已释放，不能再操作');
				return;
			}
			this.selectedHostedService = hostedService;
			this.selectedHostedServiceGroupUserPage = null;
			this.selectedHostedServiceGroupUserIdList = [];
			this.queryCompanyGroupUsers(hostedService.groupId);
			this.isManageHostedServiceDialogShow = true;
		},
		cancelManageHostedService: function() {
			this.isManageHostedServiceDialogShow = false;
		},
		showManageGroupUserDialog: function() {
			var that = this;
			if(this.selectedHostedServiceGroupUserPage && this.selectedHostedServiceGroupUserPage.length > 0) {
				this.selectedHostedServiceGroupUserPage.forEach(function(item, index) {
					that.selectedHostedServiceGroupUserIdList[index] = item.userId;
				});
			} else {
				this.selectedHostedServiceGroupUserIdList = [];
			}

			this.isManageGroupUserDialogShow = true;
		},
		cancelManageGroupUser: function() {
			this.isManageGroupUserDialogShow = false;
		},
		confirmManageGroupUser: function() {
			this.isManageGroupUserDialogShow = false;

			var that = this;
			var data = {
				groupId: this.selectedHostedService.groupId,
				groupUserIdList: JSON.stringify(this.selectedHostedServiceGroupUserIdList),
			};
			postMsg(
				'UpdateCompanyGroupUsers', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						alert('设置成功');
						that.queryCompanyGroupUsers(that.selectedHostedService.groupId);
					}
				}
			)

		},
		showSetGroupUserRoleDialog: function(groupUser) {
			this.modifyGroupUser = groupUser;
			this.isSetGroupUserRoleDialogShow = true;
		},
		cancelSetGroupUserRole: function() {
			this.isSetGroupUserRoleDialogShow = false;
		},
		modifyGroupUserRole: function() {

			var that = this;
			var data = {
				groupId: this.modifyGroupUser.groupId,
				groupUserId: this.modifyGroupUser.userId,
				role: this.modifyGroupUser.role,
			};

			postMsg(
				'UpdateCompanyGroupUser', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						alert('设置成功');
						that.queryCompanyGroupUsers(that.selectedHostedService.groupId);
					}
				}
			)
			this.isSetGroupUserRoleDialogShow = false;
		},
		showUpdateSpecDialog: function() {
			
			this.upgradeSpecTabIndex = 0;
			
			var that = this;
			var data = {
				groupId: this.selectedHostedService.groupId,
			};

			postMsg(
				'GetUpgradeSpecInfo', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						that.productUpgrateList = json.results;
						if (that.productUpgrateList) {
							that.selectedProductUpgrade = that.productUpgrateList[0];
							that.isUpdateSpecDialogShow = true;
						} else {
							alert("没有更高的规格可升级")	;
						}
					}
				}
			)
		},
		upgradeSpecTabToggle: function(index) {
			this.upgradeSpecTabIndex = index;
			if (index < this.productUpgrateList.length) {
				this.selectedProductUpgrade = this.productUpgrateList[index]
			} else {
				this.selectedProductUpgrade = this.productUpgrateList[0];
			}
		},
		cancelUpdateSpec: function() {
			this.isUpdateSpecDialogShow = false;
		},
		submitUpdateSpecOrder: function() {

			// set order info for upgrade spec
			this.orderConfirmInfo.payType = DEFAULT_PAYTYPE;
			this.orderConfirmInfo.productId = this.selectedProductUpgrade.productId;
			this.orderConfirmInfo.groupId = this.selectedHostedService.groupId;
			this.orderConfirmInfo.hostedServiceName = this.selectedHostedService.groupName;
			this.orderConfirmInfo.orderTypeCN = '升级服务';
			this.orderConfirmInfo.productPurpose = PRODUCT_PURPOSE_LIST[1];
			this.orderConfirmInfo.serviceType = this.selectedHostedService.serviceType;
			this.orderConfirmInfo.serviceTypeStr = this.selectedHostedService.serviceTypeCn;
			this.orderConfirmInfo.serviceSpecStr = this.selectedProductUpgrade.productTypeCnName;
			this.orderConfirmInfo.serviceTime = this.selectedProductUpgrade.day;
			this.orderConfirmInfo.totalAmount = this.selectedProductUpgrade.totalAmountStr;
			this.orderConfirmInfo.price = this.selectedProductUpgrade.price;
			this.orderConfirmInfo.quantity = this.selectedProductUpgrade.day;
			
			this.showConfirmOrderDialog();
//			this.isUpdateSpecDialogShow = false;
		},
		showRechargeDialog: function() {
			var that = this;
			var data = {
				groupId: this.selectedHostedService.groupId,
			};

			postMsg(
				'GetRechargeTimeProducts', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						that.productRechargeTimeList = json.results;
						if (that.productRechargeTimeList) {
							that.selectedProductRechargeTime = that.productRechargeTimeList[0];
							that.isRechargeDialogShow = true;
						} else {
							alert("没有续费服务，请联系客服")	;
						}
						
					}
				}
			)
			
		},
		cancelRecharge: function() {
			this.isRechargeDialogShow = false;
		},
		submitRechargeTimeOrder: function() {
			
			// set order info for recharge time
			this.orderConfirmInfo.payType = DEFAULT_PAYTYPE;
			this.orderConfirmInfo.productId = this.selectedProductRechargeTime.productId;
			this.orderConfirmInfo.groupId = this.selectedHostedService.groupId;
			this.orderConfirmInfo.hostedServiceName = this.selectedHostedService.groupName;
			this.orderConfirmInfo.orderTypeCN = '续费服务';
			this.orderConfirmInfo.productPurpose = PRODUCT_PURPOSE_LIST[2];
			this.orderConfirmInfo.serviceType = this.selectedHostedService.serviceType;
			this.orderConfirmInfo.serviceTypeStr = this.selectedHostedService.serviceTypeCn;
			this.orderConfirmInfo.serviceSpecStr = this.selectedHostedService.specNameCn;
			this.orderConfirmInfo.serviceTime = this.selectedProductRechargeTime.dayIncrement;
			this.orderConfirmInfo.totalAmount = this.selectedProductRechargeTime.price;
			this.orderConfirmInfo.price = this.selectedProductRechargeTime.price;
			this.orderConfirmInfo.quantity = 1;
			
			this.showConfirmOrderDialog();
			
//			this.isRechargeDialogShow = false;
		},
		showConfirmOrderDialog: function() {
			this.isConfirmOrderDialogShow = true;
		},
		cancelConfirmOrder: function() {
			this.isConfirmOrderDialogShow = false;
		},
		confirmOrder: function() {

			var that = this;
			var data = {
				payType: this.orderConfirmInfo.payType,
				productPurpose: this.orderConfirmInfo.productPurpose,
				groupName: this.orderConfirmInfo.hostedServiceName,
				tradeType: this.orderConfirmInfo.serviceType,
				productId: this.orderConfirmInfo.productId,
				groupId: this.orderConfirmInfo.groupId,
				totalAmount: this.orderConfirmInfo.totalAmount,
//				price: this.orderConfirmInfo.price;
				quantity: this.orderConfirmInfo.quantity,
			};

			postMsg(
				'SubmitOrder', data,
				function(json) {
					if(json.errorInfo) {
						alert(json.errorInfo.errMsg);
					} else {
						that.isConfirmOrderDialogShow = false;
						that.isAddHostedServiceDialogShow = false;
						that.isUpdateSpecDialogShow = false;
						that.isRechargeDialogShow = false;

						if (that.orderConfirmInfo.payType == 'INNER') {
							if (json.value < 1) {
								alert('支付失败，请联系客服');
							} else {
								alert('支付成功');
							}
						} else {
							// goto pay page
                            that.submitOrderId = json.value;
                            payOrder(json.value);

                            // show waiting for pay dialog
                            that.isWaitingForPayDialogShow = true;
						}

					}
				}
			)
		},
		cancelWaitingForPayDialog: function() {
			this.isWaitingForPayDialogShow = false;
		},
		payOrderAgain: function() {
			payOrder(this.submitOrderId);
		},
		confirmWaitingForPayDialog: function() {
			this.isWaitingForPayDialogShow = false;
			gotoHostedServicePage();
		},
		jumptoOrderPage: function() {
			this.isWaitingForPayDialogShow = false;
			gotoOrderPage();
		}
	}
})