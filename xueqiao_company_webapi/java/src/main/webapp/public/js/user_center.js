var app = new Vue({
	el: '#app-content',
	data: {
		companyCode: null,
		companyName: null,
		email: null,
		telephone: null,
		contact: null,
		leftSideIndex: 0,
	},
	mounted: function() {
		this.queryCompanyInfo();
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
					clearCookies();
					gotoMainPage();
				}
			)
		},
		toggleLeftSideItem: function(index) {
			this.leftSideIndex = index;
			gotoGivenPage(index);
		},
		getLeftSideItemSytle: function(index) {
			if(index == this.leftSideIndex) {
				return "left_side_item_active";
			} else {
				return "left_side_item_normal";
			}
		},
	}
})