<template>
  <div class="page">
    <div class="container">
      <div class="title">支付完成</div>
      <div class="content">
        <div class="small-item">
          <div class="key">成功支付</div>
          <div class="value">{{totalAmount}}元</div>
        </div>
        <div class="small-item">
          <div class="key">订单号</div>
          <div class="value">{{tradeNo}}</div>
        </div>
        <div class="big-item">
          <div class="value">您已完成支付，{{counter}}秒后自动跳转到交易云服务页面。</div>
        </div>
        <div class="big-item">
          <div
            class="value"
            v-if="isCompanyUser"
          ><span class="attention-text">您需要为该服务分配一名管理员，保证该交易托管服务可以正常使用。</span></div>
          <div
            class="value"
            v-if="isShowPersonalNewHint"
          ><span class="attention-text">服务购买成功，系统正在配置您的交易云服务，配置成功会以短信的方式通知您，收到通知后即可登录客户端进行体验，请稍候。</span></div>
        </div>
        <div>
          <button
            class="g-button button-item"
            @click="gotoHostingPage"
          >立即跳转({{counter}}")</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      counter: 5,
      totalAmount: null,
      tradeNo: null,
      isLogin: false,
      isCompanyUser: false,
      isShowPersonalNewHint: false
    };
  },
  mounted() {
    this.totalAmount = this.$route.query.total_amount;
    this.tradeNo = this.$route.query.out_trade_no;
    this.startTimeCounter();
    this.isShowPersonalNewHint = false;
    var loginInfo = this.$XQLocalStorage.getLoginInfo();
    this.isLogin = loginInfo.isLogin === "true";
    if (this.isLogin) {
      var userType = this.$XQLocalStorage.getLocalStorage("userType");
      if (userType == "personal") {
        this.isCompanyUser = false;
      } else {
        this.isCompanyUser = true;
      }
    } else {
      this.isCompanyUser = false;
    }
    if (!this.isCompanyUser) {
      this.getOrderDetail();
    }
  },
  methods: {
    gotoHostingPage() {
      if (this.isLogin) {
        if (this.isCompanyUser) {
          this.$router.push("/manager/hosting");
        } else {
          this.$router.push("/personal/hosting");
        }
        return;
      }
      this.$router.push("/home");
    },
    startTimeCounter() {
      var that = this;
      that.counter = 5;
      this.timer = setInterval(function() {
        if (that.counter === 0) {
          that.counter = 0;
          clearInterval(that.timer);
          that.gotoHostingPage();
        } else {
          that.counter--;
        }
      }, 1000);
    },
    getOrderDetail() {
      this.isShowPersonalNewHint = false;
      var that = this;
      var params = {
        orderId: this.tradeNo
      };
      this.$http.postByPersonalUser("getOrderDetail", params, function(
        response
      ) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
        } else if (response.errorInfo) {
        } else {
          that.orderDetail = response.value;
          if (
            that.orderDetail &&
            that.orderDetail.productPropose == "PERSONAL_NEW"
          ) {
            that.isShowPersonalNewHint = true;
          } else {
            that.isShowPersonalNewHint = false;
          }
        }
      });
    }
  }
};
</script>

<style scoped>
.page {
  left: 0;
  top: 0;
  height: 1020px;
  width: 100vw;
  min-width: 1920px;
  min-height: 900px;
  background-color: rgba(255, 255, 255, 1);
  display: flex;
  align-items: center;
  flex-direction: column;
}
.container {
  /* left: 610px;
  top: 90px; */
  margin-top: 30px;
  width: 700px;
  height: 290px;
  background-color: rgba(229, 255, 229, 1);
}
.title {
  margin-left: 30px;
  margin-top: 30px;
  line-height: 25px;
  color: rgba(80, 80, 80, 1);
  font-size: 24px;
  text-align: left;
  font-family: STHeitiSC-Medium;
}
.content {
  margin-left: 100px;
  margin-right: 100px;
  margin-top: 30px;
  margin-bottom: 30px;
  /* background: green; */
}
.small-item {
  text-align: left;
  display: flex;
  align-items: center;
  margin-top: 5px;
  margin-bottom: 5px;
}
.big-item {
  text-align: left;
  display: flex;
  align-items: center;
  margin-top: 20px;
  margin-bottom: 20px;
}
.key {
  width: 120px;
  line-height: 16px;
  color: rgba(70, 70, 70, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.value {
  line-height: 16px;
  color: rgba(80, 80, 80, 0.7);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.button-item {
  width: 220px;
}
</style>
