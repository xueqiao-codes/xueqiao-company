<template>
  <div class="page">
    <div :class="getContainerBg()">
      <div
        class="title"
        v-if="errorType == 0 && userName != null"
      >验证成功</div>
      <div class="content">
        <div class="big-item">
          <div
            class="value"
            v-if="errorType == 2"
          >
            <div class="error-item">
              <span>{{$string.serverCOnfigErrorText}}。</span>
            </div>
            <div class="error-item">
              错误信息：{{errorMsg}}
            </div>
            <div class="error-item">
              <span>{{$string.serviceQQ}}。</span>
            </div>
          </div>
          <div class="value"
            v-else-if="errorType == 1">
            <span>{{errorMsg}}</span>
          </div>
          <div
            class="value"
            v-else
          >
            <span>{{userNameDescription}}已经为您准备好合适版本的安装包{{downloadInfo.versionKey}}，欢迎下载使用。</span>
          </div>
        </div>
        <div v-if="downloadInfo && downloadInfo.downloadUrlx64">
          <button
            class="g-button button-item"
            @click="download32"
          >下载</button>
          <!-- <a class="g-button button-item" :href="downloadInfo.downloadUrlx32">下载</a> -->
          <button
            v-if="false"
            class="g-button button-item"
            :href="downloadInfo.downloadUrlx64"
            @click="download64"
          >64位</button>
        </div>
      </div>
    </div>
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
export default {
  components: {
    "d-tips": DTips
  },
  data() {
    return {
      // dialog flag
      isShowTips: false,
      tipMsg: "",

      companyId: null,
      userName: null,
      userNameDescription: null,
      downloadInfo: {},
      errorMsg: null,
      errorType: 0,
    };
  },
  mounted() {
    this.companyId = this.$route.params.companyId;
    this.userName = this.$route.params.userName;
    if (this.userName) {
      this.userNameDescription = this.userName + " 您好，";
    }
    if (!this.companyId) {
      this.$router.push({
        name: "UserVerifyForDownloadPage"
      });
      return;
    }
    this.queryDownloadInfo();
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
    queryDownloadInfo() {
      var that = this;
      this.errorType = 0;
      this.errorMsg = null;
      // var params = new URLSearchParams();
      // params.append("method", "GetXQClientAppDownload");
      // params.append("companyId", this.companyId);
      var params = {
        companyId: this.companyId
      };
      this.$http.post("GetXQClientAppDownload", params, function(response) {
        if (!response) {
          that.errorType = 1;
          that.errorMsg = "服务繁忙，请稍后再试";
          // that.showTips();
        } else if (response.errorMsg) {
          that.errorType = 2;
          that.errorMsg = response.errorMsg;
          // that.showTips();
        } else if (response.errorInfo) {
          that.errorType = 2;
          that.errorMsg = response.errorInfo.errMsg;
          // that.showTips();
        } else {
          that.downloadInfo = response.value;
          if (!that.downloadInfo) {
            that.errorType = 1;
            that.errorMsg = "服务繁忙，请稍后再试";
          } else if (!that.downloadInfo.downloadUrlx32) {
            that.errorType = 2;
            // that.errorMsg =
            //   "内部配置异常，请联系客服。客服电话：{{$XQConfig.config.CUSTOM_SERVICE_TEL}}";
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
    download64() {
      if (this.downloadInfo && this.downloadInfo.downloadUrlx64) {
        window.open(this.downloadInfo.downloadUrlx64);
      } else {
        this.tipMsg = "页面过期过，请刷新后重试";
        this.showTips();
      }
    },
    getContainerBg(){
      if (this.errorType == 0) {
        return "container"
      } else {
        return "container-error"
      }
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
.container-error {
  margin-top: 30px;
  width: 700px;
  height: 290px;
  background-color: rgba(255, 230, 230, 1);
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
.big-item {
  text-align: left;
  display: flex;
  align-items: center;
  margin-top: 60px;
  margin-bottom: 60px;
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
  line-height: 22px;
  color: rgba(80, 80, 80, 0.7);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.button-item {
  /* display: inline-block;
  width: 220px;
  height: 100px; */
  text-decoration: none;
  /* margin: 10px 50px; */
  padding-left: 80px;
  padding-right: 80px;
  padding-top: 5px;
  padding-bottom: 5px;
  /* float: left; */
}
.error-item {
  font-size: 20px;
  padding: 5px;
}
</style>
