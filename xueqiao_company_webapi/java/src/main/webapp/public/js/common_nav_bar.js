var app = new Vue({
	el: '#navbar-app',
	data: {
		leftSideIndex: 0,
	},
	mounted: function() {
		//		this.queryCompanyInfo();
	},
	methods: {
		toggleLeftSideItem: function(index) {
			gotoGivenPage(index);
		},
		getUserName: function() {
			return getUserName();
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
	}
})