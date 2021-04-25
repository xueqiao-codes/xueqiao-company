<template>
  <div class="fragment-sub-bg">
    <div class="fragment-sub-bg">
      <div class="fragment-title"></div>
      <div class="fragment-info-item description">
        <span>请完成用户验证，我们将为您匹配合适的软件版本。</span>
      </div>
      <div class="fragment-info-item">
        <div class="fragment-info-key">
          <span>公司登录名</span>
        </div>
        <input
          class="g-form-value fragment-info-value"
          type="text"
          placeholder="公司登录名"
          v-model="companyCode"
        >
      </div>
      <div class="fragment-info-item">
        <div class="fragment-info-key">
          <span>用户名</span>
        </div>
        <input
          class="g-form-value fragment-info-value"
          type="text"
          placeholder="用户名"
          v-model="userName"
        >
      </div>
      <div class="fragment-info-item">
        <div class="fragment-info-key">
          <span>密码</span>
        </div>
        <!-- <input class="g-form-value fragment-info-value" type="password" placeholder="密码" v-model="passwd"> -->
        <passwdinput
          style="width: 280px;"
          v-model="passwd"
        ></passwdinput>
      </div>
      <div class="fragment-button-wrapper">
        <button
          class="g-button fragment-button"
          @click="doLogin"
        >验证</button>
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
// import DAutoRedirectTips from "../dialog/AutoRedirectTipsDialog";
// import WPasswdInput from '../widget/PasswdInput'
export default {
  components: {
    "d-tips": DTips,
    // "d-login-success": DAutoRedirectTips
    // 'w-passwd-input': WPasswdInput
  },
  data() {
    return {
      // login input field
      companyCode: null,
      userName: null,
      passwd: null,

      // dialog flag
      isShowTips: false,
      tipMsg: "",

      // data
      verifyResult: {}
    };
  },
  mounted() {
    this.$storeUtil.setClientAppDownloadTabIndex(0);
    var loginInfo = this.$XQLocalStorage.getLoginInfo();
    this.isLogin = loginInfo.isLogin === "true";
    // 如果已经处于登录状态，则跳转到主页
    if (this.isLogin) {
      this.$commonFunc.sendBackendCallEvent();
      // this.$router.push("/download");
      var companyId = this.$XQLocalStorage.getLocalStorage('userId')
      if (companyId && companyId > 0) {
        this.gotoClientAppDownloadPage(companyId);
      }
    }
  },
  methods: {
    checkParams() {
      if (this.companyCode == null || this.companyCode.length < 1) {
        this.tipMsg = "请输入公司登录名";
        this.showTips();
        return 1;
      }
      if (this.userName == null || this.userName.length < 1) {
        this.tipMsg = "请输入用户名";
        this.showTips();
        return 1;
      }
      if (this.passwd == null || this.passwd.length < 1) {
        this.tipMsg = "请输入密码";
        this.showTips();
        return 1;
      }
      return 0;
    },
    doLogin() {
      if (this.checkParams()) {
        return;
      }

      // login
      var that = this;
      var params = {
        "companyCode" : this.companyCode,
        "username" : this.userName,
        "passwd" : this.passwd
      }
      this.$http.post("VerifyCompanyUser", params, function(response) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          that.verifyResult = response.value;
          that.gotoClientAppDownloadPage(that.verifyResult.companyId);
        }
      });
    },
    showTips() {
      this.isShowTips = true;
    },
    closeTips() {
      this.isShowTips = false;
    },
    confirmTips() {
      this.isShowTips = false;
    },
    gotoClientAppDownloadPage(companyId) {
      if (!companyId || companyId < 1) {
        this.tipMsg = "验证出错，请尝试刷新页面后重试";
        this.showTips();
        return;
      }
      this.$router.push({
        name: "clientAppDownloadPage",
        params: {
          companyId: companyId,
          userName: this.verifyResult.userName
        }
      });
    }
  }
};
</script>

<style scoped>
.description {
  margin-top: 40px;
  margin-bottom: 60px;
  color: rgba(80, 80, 80, 1);
  font-size: 16px;
  text-align: left;
  font-family: PingFangSC-Light;
}
.register-or-forget-passwd {
  width: 320px;
  padding-top: 5px;
  display: flex;
  justify-content: space-between;
  margin-left: 440px;
}
</style>
