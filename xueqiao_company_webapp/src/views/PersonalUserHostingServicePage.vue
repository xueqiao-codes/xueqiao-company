<template>
  <div class="personal-user-manager">
    <div class="manager-title">
      <span class="manager-title-space"></span>
      <span class="manager-title-text">交易云服务</span>
    </div>

    <div class="hosting-service">
      <div>
        <div class="item">
          <div class="item-key">服务状态</div>
          <div class="item-value">{{hostingServiceStatus}}</div>
        </div>
        <div class="item">
          <div class="item-key">到期时间</div>
          <div class="item-value">{{hostingServiceExpiredDate}}</div>
        </div>
      </div>
      <div class="hosting-operation">
        <a
          class="operation-item"
          v-if="hostingServiceBuyStatus == 1"
          href="#"
          @click="buyHostingService"
        >购买</a>
        <a
          class="operation-item"
          v-else-if="hostingServiceBuyStatus == 2"
          href="#"
          @click="rechargeHostingService"
        >续费</a>
      </div>
    </div>
    <div class="download">
      <button
        class="g-button button-item"
        @click="download32"
      >下载客户端</button>
    </div>
    <d-buy
      v-bind:isShow="isShowBuyHostingService"
      v-bind:userId="tipMsg"
      v-bind:type="buyType"
      @close="closeBuyHostingServiceDialog"
      @confirm="confirmBuyHostingService"
    ></d-buy>
    <d-submit-order-success
      v-bind:isShow="isShowSubmitOrderSuccessDialog"
      v-bind:orderId="confirmOrderId"
      @close="closeSubmitOrderSuccessDialog"
      @confirm="confirmSubmitOrderSuccess"
    ></d-submit-order-success>
    <d-tips
      v-bind:isShow="isShowTips"
      v-bind:msg="tipMsg"
      @close="closeTips"
      @confirm="confirmTips"
    ></d-tips>
  </div>
</template>

<script>
import DTips from "../components/dialog/TipsDialog";
import DBuyHostingService from "../components/dialog/BuyPersonalUserHostingServiceDialog";
import PersonalUserSubmitOrderSuccessDialog from "../components/dialog/PersonalUserSubmitOrderSuccessDialog";

export default {
  components: {
    "d-tips": DTips,
    "d-buy": DBuyHostingService,
    "d-submit-order-success": PersonalUserSubmitOrderSuccessDialog
  },
  data() {
    return {
      // dialog flag
      isShowTips: false,
      tipMsg: "",

      buyType: "create",

      isShowBuyHostingService: false,

      isShowSubmitOrderSuccessDialog: false,
      confirmOrderId: null,

      // company user
      personalUser: null,
      userId: null,
      hostingServiceStatus: "--",
      hostingServiceExpiredDate: "--",
      hostingServiceBuyStatus: 0,

      // download info
      downloadInfo: {},
    };
  },
  mounted() {
    if (this.$util.checkLoginState("/personal/hosting") != "personal") {
      return;
    }
    this.$storeUtil.setCurrentPath("/personal/hosting");
    this.queryPersonalUserInfo();
    this.queryXQClientAppDownload();
  },
  methods: {
    showTips() {
      this.isShowTips = true;
    },
    closeTips() {
      this.isShowTips = false;
    },
    confirmTips() {
      this.isShowTips = false;
    },
    queryPersonalUserInfo() {
      var that = this;
      var params = {};
      this.$http.postByPersonalUser("GetPersonalUser", params, function(
        response
      ) {
        // alert('GetPersonalUser : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          if (response.value) {
            // that.$store.commit("setCompanyInfo", response.value);
            that.personalUser = response.value;
            that.userId = that.personalUser.userId;
            that.getHostingServiceStatus();
          } else {
            alert("获取用户信息失败 : " + JSON.stringify(response));
          }
        }
      });
    },
    getHostingServiceStatus() {
      if (this.personalUser) {
        if (this.personalUser.expiredTimeMillis == 0) {
          this.hostingServiceStatus = "未购买";
          this.hostingServiceExpiredDate = "--";
          this.hostingServiceBuyStatus = 1;
          return;
        }
        this.hostingServiceBuyStatus = 2;
        var timestamp = Date.parse(new Date());
        this.hostingServiceExpiredDate = this.$timeUtil.getYYYY_MM_DD_HH_MM_SS(
          this.personalUser.expiredTimeMillis
        );
        if (timestamp >= this.personalUser.expiredTimeMillis) {
          this.hostingServiceStatus = "已过期";
          return;
        }
        var offsetDays =
          (this.personalUser.expiredTimeMillis - timestamp) / 86400000;
        if (offsetDays < 7) {
          this.hostingServiceStatus = "即将到期";
          return;
        } else {
          this.hostingServiceStatus = "已购买";
          return;
        }
      } else {
        this.hostingServiceStatus = "--";
        this.hostingServiceExpiredDate = "--";
        this.hostingServiceBuyStatus = 0;
      }
    },
    buyHostingService() {
      // alert("buyHostingService");
      this.buyType = "create"
      this.showBuyHostingServiceDialog();
    },
    rechargeHostingService() {
      this.buyType = "recharge"
      this.showBuyHostingServiceDialog();
    },
    showBuyHostingServiceDialog() {
      this.isShowBuyHostingService = true;
    },
    closeBuyHostingServiceDialog() {
      this.isShowBuyHostingService = false;
    },
    confirmBuyHostingService() {
      this.isShowBuyHostingService = false;
      this.queryOrderStatus();
    },
    queryOrderStatus() {
      var time = 10;
      var that = this;
      var timer = setInterval(function() {
        if (time === 0) {
          clearInterval(timer);
        } else {
          time--;
          var orderId = that.$XQLocalStorage.getTempOrderId();
          if (orderId) {
            clearInterval(timer);
            that.showSubmitOrderSuccessDialog(orderId);
          }
        }
      }, 1000);
    },
    showSubmitOrderSuccessDialog(orderId) {
      this.confirmOrderId = orderId;
      this.isShowSubmitOrderSuccessDialog = true;
    },
    closeSubmitOrderSuccessDialog() {
      this.isShowSubmitOrderSuccessDialog = false;
      this.queryPersonalUserInfo();
    },
    confirmSubmitOrderSuccess() {
      this.isShowSubmitOrderSuccessDialog = false;
      this.queryPersonalUserInfo();
    },
    queryXQClientAppDownload() {
      var that = this;
      var params = {};
      this.$http.postByPersonalUser("GetXQClientAppDownloadForPersonalUser", params, function(
        response
      ) {
        // alert('GetXQClientAppDownloadForPersonalUser : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          if (response.value) {
            that.downloadInfo = response.value;
          } else {
            alert("获取下载信息失败 : " + JSON.stringify(response));
          }
        }
      });
    },
    download32() {
      if (this.downloadInfo && this.downloadInfo.downloadUrlx32) {
        window.open(this.downloadInfo.downloadUrlx32);
      } else {
        this.tipMsg = "页面过期过，请刷新后重试";
        this.showTips();
      }
    },
  }
};
</script>

<style scoped>
.personal-user-manager {
  background-color: rgba(255, 255, 255, 1);
  min-width: 1660px;
  text-align: left;
}
.hosting-service {
  margin-left: 30px;
  display: flex;
}
.hosting-operation {
  margin-top: 15px;
  margin-left: 60px;
}
.item {
  display: flex;
  margin-top: 15px;
  margin-bottom: 30px;
}
.item-key {
  width: 150px;
  height: 18px;
  color: rgba(80, 80, 80, 1);
  font-size: 18px;
  text-align: left;
  font-family: STHeitiSC-Medium;
}
.item-value {
  width: 200px;
  height: 16px;
  color: rgba(144, 144, 144, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.download {
  margin-left: 30px;
  margin-top: 30px;
}
.button-item {
  width: 133px;
  height: 32px;
  border-radius: 4px;
}
.operation-item {
  width: 32px;
  height: 16px;
  color: rgba(54, 158, 239, 1);
  font-size: 16px;
  text-align: center;
  font-family: STHeitiSC-Light;
}
</style>
