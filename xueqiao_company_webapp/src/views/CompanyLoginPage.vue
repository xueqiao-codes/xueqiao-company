<template>
  <div class="fragment-sub-bg">
    <div class="fragment-sub-bg">
      <div class="fragment-title"></div>
      <div class="fragment-info-item">
        <div class="fragment-info-key">
          <span>账号</span>
        </div>
        <input
          class="g-form-value fragment-info-value"
          type="text"
          placeholder="请输入公司登录名/手机号码"
          v-model="account"
        >
      </div>
      <div class="fragment-info-error-item">
        {{accountErrorInfo}}
      </div>
      <div class="fragment-info-item-with-errorinfo">
        <div class="fragment-info-key">
          <span>密码</span>
        </div>
        <!-- <input class="g-form-value fragment-info-value" type="password" placeholder="密码" v-model="passwd"> -->
        <passwdinput
          style="width: 280px;"
          v-model="passwd"
        ></passwdinput>
      </div>
      <div class="fragment-info-error-item">
        {{passwdErrorInfo}}
      </div>
      <div class="fragment-button-wrapper">
        <button
          class="g-button fragment-button"
          @click="doLogin"
        >立即登录</button>
      </div>
      <div class="register-or-forget-passwd">
        <router-link to="/register">注册</router-link>
        <router-link to="/modifypasswd">忘记密码</router-link>
      </div>
    </div>
    <d-login-success
      v-bind:isShow="isShowLoginSuccessDialog"
      title="提示"
      msg1="登录成功，"
      msg2="秒后跳转到交易托管服务"
      v-bind:currentTime="loginSuccessTime"
      @close="closeLoginSuccessDialog"
      @confirm="confirmLoginSuccess"
    ></d-login-success>
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
import DAutoRedirectTips from "../components/dialog/AutoRedirectTipsDialog";
// import WPasswdInput from '../widget/PasswdInput'
export default {
  components: {
    "d-tips": DTips,
    "d-login-success": DAutoRedirectTips
    // 'w-passwd-input': WPasswdInput
  },
  data() {
    return {
      // login input field
      account: null,
      passwd: null,
      // error info
      accountErrorInfo: "",
      passwdErrorInfo: "",
      // dialog flag
      isShowTips: false,
      tipMsg: "",
      logginSuccess: false,
      isShowLoginSuccessDialog: false,
      loginSuccessTime: null
    };
  },
  mounted() {
      this.$storeUtil.setLoginTabIndex(0);
    // if (this.$store.state.companyInfo.companyCode) {
    //   this.account = this.$store.state.companyInfo.companyCode
    // }
    // 如果已经处于登录状态，则跳转到主页
    var loginInfo = this.$XQLocalStorage.getLoginInfo()
    this.isLogin = loginInfo.isLogin === 'true'
    if (this.isLogin) {
      this.$commonFunc.sendBackendCallEvent();
      this.$router.push("/home");
      return;
    }
    if (this.$route.query.timeout === "true") {
      this.tipMsg = "登录信息过期，请重新登录";
      this.showTips();
    }
  },
  destroyed() {
    // alert('leave login')
  },
  watch: {
    account() {
      this.checkAccount();
    },
    passwd() {
      this.checkPasswd();
    }
  },
  methods: {
    checkAccount() {
      this.accountErrorInfo = "";
      if (this.account == null || this.account.length < 1) {
        this.accountErrorInfo = "请输入公司登录名或手机号码";
        return 1;
      }
      return 0;
    },
    checkPasswd() {
      this.passwdErrorInfo = "";
      if (this.passwd == null || this.passwd.length < 1) {
        this.passwdErrorInfo = "请输入密码";
        return 1;
      }
      return 0;
    },
    doLogin() {
      // alert('env : ')
      // alert('env : ' + this.$XQEnv.baseUrl)
      // return

      var checkResult = 0;
      checkResult += this.checkAccount();
      checkResult += this.checkPasswd();
      if (checkResult > 0) {
        return;
      }
      // login
      var that = this;
      // var params = new URLSearchParams()
      // params.append('method', 'Login')
      // params.append('account', this.account)
      // params.append('password', this.passwd)
      var params = {
        account: this.account,
        password: this.passwd
      };
      this.$http.post("Login", params, function(response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          if (response.value) {
            // that.$store.commit('setLoginInfo', response.value)
            that.$XQLocalStorage.setLoginInfo(response.value);
            // that.$XQCookie.setLoginInfo(response.value)
            // that.tipMsg = '登录成功'
            // that.showTips()
            that.logginSuccess = true;
            // get company info
            that.getCompanyInfo();
            that.showLoginSuccessDialog();
            that.$commonFunc.sendLoginSuccessEvent();
          } else {
            alert("Login fail : " + JSON.stringify(response))
          }
        }
      });
    },
    getCompanyInfo() {
      var that = this;
      // var params = new URLSearchParams()
      // params.append('method', 'GetCompany')
      var params = {};
      this.$http.post("GetCompany", params, function(response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          if (response.value) {
            that.$store.commit("setCompanyInfo", response.value);
          } else {
            alert("GetCompany fail : " + JSON.stringify(response));
          }
        }
      });
    },
    showTips() {
      this.isShowTips = true;
      this.logginSuccess = false;
    },
    closeTips() {
      this.isShowTips = false;
      // go to home page after login
      //   this.$router.push('/home')
    },
    confirmTips() {
      this.isShowTips = false;
      // go to home page after login
      if (this.logginSuccess) {
        this.$router.push("/home");
      }
    },
    showLoginSuccessDialog() {
      var date = new Date();
      this.loginSuccessTime = date.toLocaleTimeString();
      this.isShowLoginSuccessDialog = true;
    },
    closeLoginSuccessDialog() {
      this.isShowLoginSuccessDialog = false;
    },
    confirmLoginSuccess() {
      this.isShowLoginSuccessDialog = false;
      this.$router.push("/manager/hosting");
    }
  }
};
</script>

<style scoped>
.register-or-forget-passwd {
  width: 320px;
  padding-top: 5px;
  display: flex;
  justify-content: space-between;
  margin-left: 440px;
}
</style>
