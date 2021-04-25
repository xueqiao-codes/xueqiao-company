var app = new Vue({
	el: '#user-center-vue-app',
	data: {
		totalNum : 0,
		totalPage : 1,
		currentPage : 1,
		
		orderPage: null,
		payOrderQRCodeHtml: null,
		showPayOrderQRCode: false,
		isWaitingForPayDialogShow: false,
		submitOrderId: null,
	},
	mounted: function() {
		this.queryOrderList();
	},
	methods: {
		queryOrderList: function() {
			var that = this;
			var data = {
				pageIndex: this.currentPage - 1,
			}
			postMsg(
				'GetOrders', data,
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
						that.orderPage = json.value.page;
					}
				}
			)
		},
		gotoFirstPage : function() {
			this.currentPage = 1;
			this.queryOrderList(1);
		},
		gotoPreviousPage : function() {
			this.currentPage -=1;
			if (this.currentPage < 1) {
				this.currentPage = 1;
			}
			this.queryOrderList(this.currentPage);
		},
		gotoGivenPage : function() {
			this.queryOrderList(this.currentPage);
		},
		gotoNextPage : function() {
			this.currentPage +=1;
			if (this.currentPage > this.totalPage) {
				this.currentPage = this.totalPage;
			}
			this.queryOrderList(this.currentPage);
		},
		gotoLastPage : function() {
			this.currentPage = this.totalPage;
			this.queryOrderList(this.totalPage);
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
		showOrderDetail: function(order) {
			alert('界面规划中...')
		},
		payOrder: function(order) {
			if (confirm('确认支付？')) {
				if (order.payType == 'INNER') {
					// 雪橇币
					var that = this;
					var data = {
						orderId: order.orderId,
					}
					postMsg(
						'PayOrderWIthXCoin', data,
						function(json) {
							if(json.errorInfo) {
								alert(json.errorInfo.errMsg);
							} else {
								alert('支付成功');
								that.queryOrderList();
							}
						}
					)
				} else {
					// 支付宝
					this.submitOrderId = order.orderId;
					payOrder(order.orderId);
					this.isWaitingForPayDialogShow = true;
				}
			}
		},
		isShowPayOrderButton: function(status) {
			return status == "CREATED";
//			return true;
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