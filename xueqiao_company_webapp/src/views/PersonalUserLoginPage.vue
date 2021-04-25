<template>
  <div class="fragment-sub-bg">
    <div class="fragment-sub-bg">
      <div class="fragment-title"></div>

      <div class="login-tab">
        <div class="login-tab-item"><a
            :class="getLoginTabClass(0)"
            href="#"
            @click="loginTabClick(0)"
          >密码登录</a></div>
        <div class="login-tab-divider"></div>
        <div class="login-tab-item"><a
            :class="getLoginTabClass(1)"
            href="#"
            @click="loginTabClick(1)"
          >验证码登录</a></div>
      </div>

      <div class="fragment-info-item">
        <div class="fragment-info-key">
          <span>手机号码</span>
        </div>
        <input
          class="g-form-value fragment-info-value"
          type="text"
          placeholder="手机号码"
          v-model="account"
        >
      </div>
      <div class="fragment-info-error-item">
        {{accountErrorInfo}}
      </div>
      <div v-if="loginActiveTabIndex == 0">
        <div class="fragment-info-item-with-errorinfo">
          <div class="fragment-info-key">
            <span>密码</span>
          </div>
          <passwdinput
            style="width: 280px;"
            v-model="passwd"
          ></passwdinput>
        </div>
        <div class="fragment-info-error-item">
          {{passwdErrorInfo}}
        </div>
      </div>
      <div v-else>
        <div class="fragment-info-item-with-errorinfo">
          <div class="fragment-info-key">
            <span>验证码</span>
          </div>
          <input
            class="g-form-value verifycode-value"
            type="text"
            v-model="verifyCode"
          >
          <button
            class="g-button verifycode-button"
            v-bind:disabled="verifyCodeBtnDisabled"
            @click="getVerifyCode"
          >{{verifyCodeBtnText}}</button>
        </div>
        <div class="verifycode-hint">
          <span class="signup_hint">收不到验证短信？</span>
          <a
            href="#"
            @click="showCustomServiceDialog"
          >联系客服</a>
        </div>
        <div class="fragment-info-error-item">
          {{verifyCodeErrorInfo}}
        </div>
      </div>
      <div class="fragment-button-wrapper">
        <button
          class="g-button fragment-button"
          @click="doLogin"
        >立即登录</button>
      </div>
      <div class="register-or-forget-passwd">
        <router-link to="/register/personal">注册</router-link>
        <router-link
          v-if="loginActiveTabIndex == 0"
          to="/personalUserForgetPassword"
        >忘记密码</router-link>
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
    <d-custom-service
      v-bind:isShow="isShowCustomServiceDialog"
      @close="closeCustomServiceDialog"
    ></d-custom-service>
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
import DCustomService from "../components/dialog/CustomServiceDialog";
// import WPasswdInput from '../widget/PasswdInput'
export default {
  components: {
    "d-custom-service": DCustomService,
    "d-tips": DTips,
    "d-login-success": DAutoRedirectTips
    // 'w-passwd-input': WPasswdInput
  },
  data() {
    return {
      // login tab index
      loginActiveTabIndex: 0,

      // verify code
      verifyCode: null,
      verifyCodeBtnDisabled: false,
      verifyCodeBtnText: "获取验证码",

      // login input field
      account: null,
      passwd: null,
      // error info
      accountErrorInfo: "",
      passwdErrorInfo: "",
      verifyCodeErrorInfo: "",

      // dialog flag
      isShowTips: false,
      tipMsg: "",
      logginSuccess: false,
      isShowLoginSuccessDialog: false,
      loginSuccessTime: null,

      isShowCustomServiceDialog: false
    };
  },
  mounted() {
    this.$storeUtil.setLoginTabIndex(1);
    // if (this.$store.state.companyInfo.companyCode) {
    //   this.account = this.$store.state.companyInfo.companyCode
    // }
    // 如果已经处于登录状态，则跳转到主页
    var loginInfo = this.$XQLocalStorage.getLoginInfo();
    this.isLogin = loginInfo.isLogin === "true";
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
    getLoginTabClass(tabIndex) {
      if (tabIndex == this.loginActiveTabIndex) {
        return "login-tab-item-text-active";
      } else {
        return "login-tab-item-text";
      }
    },
    loginTabClick(tabIndex) {
      this.loginActiveTabIndex = tabIndex;
    },
    checkAccount() {
      this.accountErrorInfo = "";
      if (this.account == null || this.account.length < 1) {
        this.accountErrorInfo = "请输入手机号码";
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
    checkVerifyCode () {
      this.verifyCodeErrorInfo = ''
      if (this.verifyCode == null || this.verifyCode.length < 1) {
        this.verifyCodeErrorInfo = '请输入验证码'
        return 1
      }
      return 0
    },
    doLogin() {
      if (this.loginActiveTabIndex == 0) {
        this.doLoginWithPassword();
      } else {
        this.doLoginWithVerifyCode();
      }
    },
    doLoginWithPassword() {
      var checkResult = 0;
      checkResult += this.checkAccount();
      checkResult += this.checkPasswd();
      if (checkResult > 0) {
        return;
      }
      var that = this;
      var params = {
        telephone: this.account,
        password: this.passwd
      };
      this.$http.postByPersonalUser(
        "PersonalUserLoginWithPasswd",
        params,
        function(response) {
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
              that.$XQLocalStorage.setPersonalUserLoginInfo(response.value);
              // that.$XQCookie.setLoginInfo(response.value)
              // that.tipMsg = '登录成功'
              // that.showTips()
              that.logginSuccess = true;
              // get company info
              // that.getCompanyInfo();
              that.showLoginSuccessDialog();
              that.$commonFunc.sendLoginSuccessEvent();
            } else {
              alert("Login fail : " + JSON.stringify(response));
            }
          }
        }
      );
    },
    doLoginWithVerifyCode() {
      var checkResult = 0;
      checkResult += this.checkAccount();
      checkResult += this.checkVerifyCode();
      if (checkResult > 0) {
        return;
      }
      var that = this;
      var params = {
        telephone: this.account,
        verifyCode: this.verifyCode
      };
      this.$http.postByPersonalUser(
        "PersonalUserLoginWithVerifyCode",
        params,
        function(response) {
          // alert('login : ' + JSON.stringify(response))
          if (response.errorMsg) {
            that.tipMsg = response.errorMsg;
            that.showTips();
          } else if (response.errorInfo) {
            that.tipMsg = response.errorInfo.errMsg;
            that.showTips();
          } else {
            if (response.value) {
              that.$XQLocalStorage.setPersonalUserLoginInfo(response.value);
              that.logginSuccess = true;
              that.showLoginSuccessDialog();
              that.$commonFunc.sendLoginSuccessEvent();
            } else {
              alert("Login fail : " + JSON.stringify(response));
            }
          }
        }
      );
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
      this.$router.push("/personal/hosting");
    },
    getVerifyCode() {
      if (this.verifyCodeBtnDisabled) return;
      if (this.checkAccount()) return;

      var that = this;
      var time = 120;

      that.time = "(" + time + '")';
      that.verifyCodeBtnText = that.time;
      this.verifyCodeBtnDisabled = true;

      var timer = setInterval(function() {
        if (time === 0) {
          that.time = "";
          clearInterval(timer);
          that.verifyCodeBtnDisabled = false;
          that.verifyCodeBtnText = "获取验证码";
        } else {
          time--;
          that.time = "(" + time + '")';
          that.verifyCodeBtnText = that.time;
        }
      }, 1000);
      var params = {
        tel: this.account
      };
      this.$http.post("GetTelVerifyCode", params, function(response) {
        if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
          clearInterval(timer);
        } else {
          that.tipMsg = "验证码已发送成功";
          that.showTips();
        }
      });
    },
    showCustomServiceDialog() {
      this.isShowCustomServiceDialog = true;
    },
    closeCustomServiceDialog() {
      this.isShowCustomServiceDialog = false;
    }
  }
};
</script>

<style scoped>
.login-tab {
  display: flex;
  justify-content: center;
}
.login-tab-divider {
  width: 1px;
  height: 20px;
  margin-left: 20px;
  margin-right: 20px;
  background-color: rgba(0, 0, 0, 0.2);
}
.login-tab-item {
  width: 80px;
}
.login-tab-item-text {
  width: 50px;
  color: rgba(0, 0, 0, 0.2);
  font-size: 16px;
}
.login-tab-item-text-active {
  width: 50px;
  color: rgba(0, 0, 0, 0.8);
  font-size: 16px;
}
.register-or-forget-passwd {
  width: 320px;
  padding-top: 5px;
  display: flex;
  justify-content: space-between;
  margin-left: 440px;
}
.verifycode-value {
  width: 140px;
}
.verifycode-button {
  width: 130px;
  margin-left: 30px;
}
.verifycode-hint {
  width: 320px;
  margin-left: 440px;
  text-align: left;
}
</style>
