<template>
  <div class="page">
    <v-header></v-header>
    <div class="touyan-container">
      <div class="touyan-text">
        <div>
          雪橇投研
        </div>
        <div>
          科学可靠的投研分析工具。
        </div>
      </div>
      <div
        class="touyan-button"
        v-if="touyanAppDownloadUrl"
      >
        <button
          class="download-button"
          @click="gotoDownloadTouyanWinClient"
        >免费体验</button>
      </div>
    </div>
    <v-footer></v-footer>
  </div>
</template>

<script>
import VHeader from "./common/TouyanHeader";
import footer from "./common/Footer.vue";
export default {
  components: {
    "v-header": VHeader,
    "v-footer": footer
  },
  data() {
    return {
      downloadInfo: null,
      touyanAppDownloadUrl: null
    };
  },
  mounted() {
    this.getTouyanWinDownloadInfo();
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
</style>
