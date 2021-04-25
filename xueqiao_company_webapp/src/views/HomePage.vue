<template>
  <div class="page">
    <div class="title-container">
      <div class="title-text">
        <div>
          雪橇 ——
        </div>
        <div>
          科学高效的投资工作流。
        </div>
      </div>
      <div class="title-button">
        <button
          class="download-button"
          @click="gotoDownloadPCClient"
        >下载雪橇</button>
        <button
          class="try-button"
          @click="applyTrial"
        >申请试用</button>
      </div>
    </div>
    <!-- <div class="touyan-container" v-if="touyanAppDownloadUrl">
      <div class="touyan-text">
        <div>
          雪橇投研
        </div>
        <div>
          科学可靠的投研分析工具。
        </div>
      </div>
      <div class="touyan-button">
        <button
          class="download-button"
          @click="gotoDownloadTouyanWinClient"
        >免费体验</button>
      </div>
    </div> -->
    <div class="description-container">
      <div class="description-content">
        <div class="divider-container margin-top60">
          <div class="divider"></div>
        </div>
        <div class="description-title">
          <span>更好的技术与更智能的算法</span>
        </div>
        <div class="description-sub-title">
          <span>——帮助用户建立科学高效的投资工作流。</span>
        </div>
        <div class="description-text">
          <span class="description-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;雪橇投资管理系统是一体化的投资工作流解决方案。基于24小时云端托管服务和智能订单执行程序，为专业的投资机构和投资者提供期货内外盘套利、跨产品对冲交易、大订单自动拆单执行等专业交易服务；以及多交易员协同操作交易账户、交易员授信额度管理、可交易品种管理、持仓风险分析、业绩分析与复盘；同时支持投研数据的多维度图表展示及交易计划的持续动态跟踪等一系列投资管理功能。</span>
        </div>
        <div class="description-text">
          <span class="description-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;雪橇资金管理系统由来自武汉大学、香港理工大学、工行、腾讯、香港券商等知名学府及机构的资深金融从业人员、资深工程师、机器学习学界翘楚共同打造，为用户提供更全面、更科学、更严谨、更高效的投资管理服务。</span>
        </div>
        <div class="divider-container margin-top60">
          <div class="divider"></div>
        </div>
        <div class="description-title">
          <span>雪橇核心能力</span>
        </div>
        <div class="description-sub-content">
          <div class="description-sub-content-item">
            <img
              class="description-sub-content-item-icoin"
              src='../assets/icon1.png'
            >
            <div class="description-sub-content-item-title">
              <span>24小时托管服务</span>
            </div>
            <div class="description-sub-content-item-content">
              <span>不同于国内传统客户端交易产品，雪橇为每一个专业用户都配置了24小时托管服务，提供更稳定、更高效的交易支持。</span>
            </div>
          </div>
          <div class="description-sub-content-item">
            <img
              class="description-sub-content-item-icoin"
              src='../assets/icon2.png'
            >
            <div class="description-sub-content-item-title">
              <span>内外盘支持</span>
            </div>
            <div class="description-sub-content-item-content">
              <span>雪橇系统支持内外盘所有主流交易所，用户添加对应券商账户，即可进行专业的内外盘套利交易。</span>
            </div>
          </div>
          <div class="description-sub-content-item">
            <img
              class="description-sub-content-item-icoin"
              src='../assets/icon3.png'
            >
            <div class="description-sub-content-item-title">
              <span>多账户系统</span>
            </div>
            <div class="description-sub-content-item-content">
              <span>通过设置多个操作帐号，可以将一个操作帐号分配给多人操作，通过限制授信额度和可交易合约来有效管理风险。</span>
            </div>
          </div>
          <div class="description-sub-content-item">
            <img
              class="description-sub-content-item-icoin"
              src='../assets/icon4.png'
            >
            <div class="description-sub-content-item-title">
              <span>智能订单执行</span>
            </div>
            <div class="description-sub-content-item-content">
              <span>通过机器学习对5万个真实订单反复学习，雪橇目前可以提供国内唯一的智能订单执行程序，极大降低了复杂交易的交易成本。</span>
            </div>
          </div>
        </div>
        <div class="divider-container margin-top60 margin-bottom60">
          <div class="divider"></div>
        </div>
      </div>
    </div>
    <v-footer></v-footer>
  </div>
</template>

<script>
import footer from "./common/Footer.vue";
export default {
  components: {
    "v-footer": footer
  },
  data() {
    return {
      downloadInfo: null,
      touyanAppDownloadUrl: null
    };
  },
  mounted() {
    // this.getTouyanWinDownloadInfo();
  },
  methods: {
    applyTrial() {
      this.$router.push("/register");
    },
    gotoDownloadPCClient() {
      this.$router.push({
        path: "/userverify"
      });
    },
    getTouyanWinDownloadInfo() {
      var that = this;
      this.errorType = 0;
      this.errorMsg = null;
      // var params = new URLSearchParams();
      // params.append("method", "GetXQClientAppDownload");
      // params.append("companyId", this.companyId);
      var params = {};
      this.$http.post("GetXQTouyanWinAppDownload", params, function(response) {
        if (!response) {
          // that.errorType = 1;
          // that.errorMsg = "服务繁忙，请稍后再试";
          // that.showTips();
        } else if (response.errorMsg) {
          // that.errorType = 2;
          // that.errorMsg = response.errorMsg;
          // that.showTips();
        } else if (response.errorInfo) {
          // that.errorType = 2;
          // that.errorMsg = response.errorInfo.errMsg;
          // that.showTips();
        } else {
          that.downloadInfo = response.value;
          if (that.downloadInfo && that.downloadInfo.downloadUrlx32) {
            that.touyanAppDownloadUrl = that.downloadInfo.downloadUrlx32;
          }

          // if (!that.downloadInfo) {
          // that.errorType = 1;
          // that.errorMsg = "服务繁忙，请稍后再试";
          // } else if (!that.downloadInfo.downloadUrlx32) {
          // that.errorType = 2;
          // that.errorMsg =
          //   "内部配置异常，请联系客服。客服电话：{{$XQConfig.config.CUSTOM_SERVICE_TEL}}";
          // }
        }
      });
    },
    gotoDownloadTouyanWinClient() {
      if (this.touyanAppDownloadUrl) {
        window.open(this.touyanAppDownloadUrl);
      }
    }
  }
};
</script>

<style scoped>
.page {
  overflow-y: hidden;
}
.title-container {
  left: 0px;
  top: 0px;
  min-width: 1920px;
  height: 900px;
  background-image: url("../assets/main.png");
}
.touyan-container {
  left: 0px;
  top: 0px;
  min-width: 1920px;
  height: 900px;
  background-image: url("../assets/touyan.jpg");
}
.title-text {
  padding-left: 60vw;
  padding-top: 341px;
  width: 616px;
  height: 140px;
  line-height: 72px;
  color: rgba(255, 255, 255, 1);
  font-size: 48px;
  text-align: left;
  font-family: STHeitiSC-Medium;
}
.title-button {
  padding-left: 60vw;
  padding-top: 20px;
  text-align: left;
}
.touyan-text {
  padding-left: 10vw;
  padding-top: 341px;
  width: 616px;
  height: 140px;
  line-height: 72px;
  color: rgba(255, 255, 255, 1);
  font-size: 48px;
  text-align: left;
  font-family: STHeitiSC-Medium;
}
.touyan-button {
  padding-left: 10vw;
  padding-top: 20px;
  text-align: left;
}
.download-button {
  width: 220px;
  height: 48px;
  border-radius: 4px;
  border-width: 0px;
  color: rgba(255, 255, 255, 1);
  font-size: 24px;
  font-family: STHeitiSC-Medium;

  background-color: #0075ff;

  background: liner-gradient(to right, #00acff, #0075ff);
  filter: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#00ACFF,endColorStr=#0075FF); /* IE 6 7 8 */
  background: -ms-linear-gradient(top, #00acff, #0075ff); /* IE 10 */
  background: -moz-linear-gradient(top, #00acff, #0075ff); /* 火狐 */
  background: -webkit-gradient(
    linear,
    0% 0%,
    100% 100%,
    from(#00acff),
    to(#0075ff)
  ); /* 谷歌 */
  background: -webkit-gradient(
    linear,
    0% 0%,
    0% 100%,
    from(#00acff),
    to(#0075ff)
  ); /* Safari 4-5, Chrome 1-9 */
  background: -webkit-linear-gradient(
    top,
    #00acff,
    #0075ff
  ); /* Safari5.1 Chrome 10+ */
}
.try-button {
  width: 220px;
  height: 48px;
  margin-left: 10px;
  border-radius: 4px;
  border-width: 2px;
  color: rgba(255, 255, 255, 1);
  font-size: 24px;
  font-family: STHeitiSC-Medium;
  background: transparent;
}
.margin-top60 {
  padding-top: 60px;
}
.margin-bottom60 {
  padding-bottom: 60px;
}
.description-container {
  /* height: 1000px; */
  background: white;
  text-align: center;
}
.description-content {
  margin-left: 465px;
  margin-right: 465px;
}
.divider-container {
  text-align: center;
}
.divider {
  /* width: 990px; */
  height: 2px;
  display: block;
  text-align: center;
  background-color: rgba(200, 231, 255, 1);
}
.description-title {
  margin-top: 30px;
  line-height: 33px;
  color: rgba(48, 48, 48, 0.87);
  font-size: 24px;
  text-align: center;
  font-family: PingFangSC-Medium;
}
.description-sub-title {
  margin-top: 10px;
  line-height: 25px;
  color: rgba(80, 80, 80, 0.87);
  font-size: 18px;
  text-align: center;
  font-family: PingFangSC-Light;
}
.description-text {
  margin-top: 30px;
  text-align: left;
}
.description-sub-content {
  margin-top: 40px;
  display: flex;
  direction: column; /* orientation: landscape; */
  /* align-content: space-between; */
  justify-content: space-between;
  /* align-items: center; */
}
.description-sub-content-item {
  width: 162px;
}
.description-sub-content-item-title {
  height: 25px;
  line-height: 25px;
  color: rgba(80, 80, 80, 0.87);
  font-size: 18px;
  font-weight: bold;
  text-align: center;
  font-family: PingFangSC-Medium;
}
.description-sub-content-item-icoin {
  width: 128px;
  height: 128px;
}
.description-sub-content-item-content {
  line-height: 25px;
  margin-top: 30px;
  color: rgba(80, 80, 80, 0.87);
  font-size: 18px;
  text-align: center;
  font-family: PingFangSC-Light;
}
.company-info-container {
  height: 175px;
  background-color: rgba(39, 49, 84, 1);
}
.company-info-content {
  /* padding-top: 30px; */
  margin-left: 465px;
  margin-right: 465px;
}
.authority-info-container {
  height: 50px;
  background-color: rgba(27, 37, 70, 1);
}
.company-info-content-item {
  text-align: left;
  display: flex;
  padding-top: 30px; /* align-items: center; */
}
.company-info-content-sub-item {
  margin-right: 60px;
}
.company-info-content-key {
  width: 90px;
  height: 25px;
  line-height: 25px;
  color: rgba(255, 255, 255, 0.5);
  font-size: 18px;
  text-align: left;
  font-family: PingFangSC-Light;
}
.company-info-content-value {
  margin-left: 30px;
  line-height: 25px;
  color: rgba(255, 255, 255, 0.5);
  font-size: 16px;
  text-align: left;
  font-family: PingFangSC-Light;
}
.authority-info-content {
  padding-top: 15px;
  line-height: 25px;
  color: rgba(255, 255, 255, 0.5);
  font-size: 16px;
  text-align: center;
  font-family: PingFangSC-Light;
}
</style>
