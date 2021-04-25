var app = new Vue({
	el: '#common-left-side-bar',
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
		getLeftSideItemSytle: function(index) {
			if(index == this.leftSideIndex) {
				return "left_side_item_active";
			} else {
				return "left_side_item_normal";
			}
		},
	}
})