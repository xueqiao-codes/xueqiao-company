<template>
  <div class="fragment-sub-bg">
    <div class="fragment-sub-bg">
      <!-- <div class="fragment-title">注册</div> -->
      <div class="fragment-hint">
        <span class="signup_hint">已有账号？</span>
        <router-link to="/login/personal">立即登录</router-link>
      </div>
      <div style="height:21px;"></div>
      <div class="fragment-info-item-with-errorinfo">
        <div class="fragment-info-key">
          <span>手机号码</span>
        </div>
        <input
          class="g-form-value fragment-info-value"
          type="text"
          v-model="telephone"
        >
      </div>
      <div class="fragment-info-error-item">
        {{telephoneErrorInfo}}
      </div>
      <div class="fragment-info-item-with-errorinfo">
        <div class="fragment-info-key">
          <span>密码</span>
        </div>
        <passwdinput
          style="width: 280px;"
          v-model="passwd"
          @focus="onPasswordFocus"
          @blur="onPasswordBlur"
        ></passwdinput>
        <div
          class="fragment-info-description"
          style="margin-left: 68px;"
          v-if="showPasswordDescription"
        >
          <span>6-20位字符，密码不能和公司登录名相同；<br>只能包含字母、数字、标点符号（空格除外）;<br>字母、数字、标点符号至少包含两种，区别大小写。</span>
        </div>
      </div>
      <div class="fragment-info-error-item">
        {{passwdErrorInfo}}
      </div>
      <div class="fragment-info-item-with-errorinfo">
        <div class="fragment-info-key">
          <span>确认密码</span>
        </div>
        <passwdinput
          style="width: 280px;"
          v-model="passwdDoubleCheck"
        ></passwdinput>
      </div>
      <div class="fragment-info-error-item">
        {{passwdDoubleCheckErrorInfo}}
      </div>

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

      <div class="fragment-hint-with-errorinfo">
        <input
          type="checkbox"
          v-model="termsOfServiceAgreement"
        >
        <span class="register-hint">我已阅读并同意相关服务条款和隐私政策，</span>
        <a
          href="#"
          @click="gotoServiceAgreement"
        >
          <span class="agreement-item">《雪橇服务协议》</span>
        </a>
        <a href="#" @click="gotoPrivacyAgreement">
          <span class="agreement-item">《隐私政策》</span>
        </a>
      </div>
      <div class="fragment-info-error-item">
        {{termsOfServiceAgreementErrorInfo}}
      </div>
      <div class="fragment-button-wrapper">
        <button
          class="g-button fragment-button"
          @click="doRegister"
        >注册</button>
      </div>
    </div>
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
    <d-autoredirect-tips
      v-bind:isShow="isShowAutoRedirectTips"
      title="注册完成"
      msg1="您已完成注册，"
      msg2="秒后自动跳转到登录页面"
      v-bind:currentTime="autoRedirectTipsShowTime"
      @close="closeAutoRedirectTips"
      @confirm="confirmAutoRedirectTips"
    ></d-autoredirect-tips>
  </div>
</template>

<script>
import DCustomService from "../components/dialog/CustomServiceDialog";
import DTips from "../components/dialog/TipsDialog";
import AutoRedirectTipsDialog from "../components/dialog/AutoRedirectTipsDialog";
export default {
  components: {
    "d-custom-service": DCustomService,
    "d-tips": DTips,
    "d-autoredirect-tips": AutoRedirectTipsDialog
  },
  data() {
    return {
      showPasswordDescription: false,

      // register field start
      passwd: null,
      passwdDoubleCheck: null,
      telephone: null,
      verifyCode: null,
      termsOfServiceAgreement: null,
      // register field end

      // error info begin
      passwdErrorInfo: "",
      passwdDoubleCheckErrorInfo: "",
      telephoneErrorInfo: "",
      verifyCodeErrorInfo: "",
      termsOfServiceAgreementErrorInfo: "",
      // error info end

      verifyCodeBtnDisabled: false,
      verifyCodeBtnText: "获取验证码",

      // dialog flag
      isShowCustomServiceDialog: false,
      isShowTips: false,
      isShowAutoRedirectTips: false,
      autoRedirectTipsShowTime: null,
      tipMsg: ""
    };
  },
  mounted() {
    this.$storeUtil.setRegisterTabIndex(1);
    var loginInfo = this.$XQLocalStorage.getLoginInfo();
    this.isLogin = loginInfo.isLogin === "true";
    // 如果已经处于登录状态，则跳转到主页
    if (
      this.isLogin &&
      this.$storeUtil.isUserTypePersonal(loginInfo.userType)
    ) {
      this.$commonFunc.sendBackendCallEvent();
      this.$router.push("/home");
    }
  },
  watch: {
    passwd() {
      this.checkPasswd();
      this.checkPasswdDoubleCheck();
    },
    passwdDoubleCheck() {
      this.checkPasswd();
      this.checkPasswdDoubleCheck();
    },
    telephone() {
      this.checkTelephone();
    },
    verifyCode() {
      this.checkVerifyCode();
    },
    termsOfServiceAgreement() {
      this.checkTermsOfServiceAgreement();
    }
  },
  methods: {
    onPasswordFocus() {
      this.showPasswordDescription = true;
    },
    onPasswordBlur() {
      this.showPasswordDescription = false;
    },
    showCustomServiceDialog() {
      this.isShowCustomServiceDialog = true;
    },
    closeCustomServiceDialog() {
      this.isShowCustomServiceDialog = false;
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
    showAutoRedirectTips() {
      var date = new Date();
      this.autoRedirectTipsShowTime = date.toLocaleTimeString();
      this.isShowAutoRedirectTips = true;
    },
    closeAutoRedirectTips() {
      this.isShowAutoRedirectTips = false;
    },
    confirmAutoRedirectTips() {
      this.isShowAutoRedirectTips = false;
      this.$router.push("/login/personal");
    },
    checkPasswd() {
      this.passwdErrorInfo = "";
      if (this.passwd == null || this.passwd.length < 1) {
        this.passwdErrorInfo = "请输入密码";
        return 1;
      }
      let length = this.passwd.length;
      // 不能包含空格
      let regContainWhiteSpace = /(^\s+)|(\s+$)|\s+/g;
      if (regContainWhiteSpace.test(this.passwd)) {
        this.passwdErrorInfo = "密码不能包含空格";
        return 1;
      }
      // 字母、数字和标点符号，至少包含两种
      let passwdCharacterTypeNum = 0;
      let regContainNum = /\d+/;
      if (regContainNum.test(this.passwd)) {
        // this.passwdErrorInfo = '密码包含数字'
        passwdCharacterTypeNum++;
      }
      let regContainCharacter = /[a-zA-Z]+/;
      if (regContainCharacter.test(this.passwd)) {
        // this.passwdErrorInfo = '密码包含字母'
        passwdCharacterTypeNum++;
      }
      let regContainSpecialSymbol = /[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+/;
      if (regContainSpecialSymbol.test(this.passwd)) {
        // this.passwdErrorInfo = '密码包含特殊字符'
        passwdCharacterTypeNum++;
      }
      if (passwdCharacterTypeNum < 2) {
        this.passwdErrorInfo = "字母、数字和特殊符号，至少包含两种";
        return 1;
      }
      // 密码长度在6-20位字符之间
      if (length < 6 || length > 20) {
        this.passwdErrorInfo = "密码长度不满足要求";
        return 1;
      }
      return 0;
    },
    checkPasswdDoubleCheck() {
      this.passwdDoubleCheckErrorInfo = "";
      if (this.passwdDoubleCheck == null || this.passwdDoubleCheck.length < 1) {
        this.passwdDoubleCheckErrorInfo = "请输入确认密码";
        return 1;
      }
      if (this.passwd !== this.passwdDoubleCheck) {
        this.passwdDoubleCheckErrorInfo = "两次密码输入不一致";
        return 1;
      }
      return 0;
    },
    checkTelephone() {
      this.telephoneErrorInfo = "";
      if (this.telephone == null || this.telephone.length < 1) {
        this.telephoneErrorInfo = "请输入手机号码";
        return 1;
      }
      let regTelephone = /^0?(13[0-9]|(14[5,7])|15[012356789]|17[013678]|18[0-9]|166|198|199)[0-9]{8}$/;
      if (!regTelephone.test(this.telephone)) {
        this.telephoneErrorInfo = "手机号码不正确";
        return 1;
      }
      return 0;
    },
    checkVerifyCode() {
      this.verifyCodeErrorInfo = "";
      if (this.verifyCode == null || this.verifyCode.length < 1) {
        // this.tipMsg = '验证码输入错误'
        // this.showTips()
        this.verifyCodeErrorInfo = "请输入验证码";
        return 1;
      }
      return 0;
    },
    checkTermsOfServiceAgreement() {
      this.termsOfServiceAgreementErrorInfo = "";
      if (
        this.termsOfServiceAgreement == null ||
        !this.termsOfServiceAgreement
      ) {
        this.termsOfServiceAgreementErrorInfo =
          "请确认已阅读并同意相关服务条款与隐私政策";
        return 1;
      }
      return 0;
    },
    getVerifyCode() {
      if (this.verifyCodeBtnDisabled) return;
      if (this.checkTelephone()) return;

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
        tel: this.telephone
      };
      this.$http.postByPersonalUser("GetTelVerifyCode", params, function(
        response
      ) {
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
    doRegister() {
      var checkResult = 0;
      checkResult += this.checkPasswd();
      checkResult += this.checkPasswdDoubleCheck();
      checkResult += this.checkTelephone();
      checkResult += this.checkTermsOfServiceAgreement();
      checkResult += this.checkVerifyCode();
      if (checkResult > 0) {
        return;
      }

      var that = this;
      var params = {
        password: this.passwd,
        telephone: this.telephone,
        verifyCode: this.verifyCode
      };
      this.$http.postByPersonalUser("PersonalUserSignUp", params, function(
        response
      ) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          that.showAutoRedirectTips();
        }
      });
    },
    gotoServiceAgreement() {
      this.$router.push("/serviceAgreement");
    },
    gotoPrivacyAgreement() {
      this.$router.push("/privacyAgreement");
    }
  }
};
</script>

<style scoped>
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
.register-hint {
  color: rgba(80, 80, 80, 1);
  font-size: 15px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.agreement-item {
  font-size: 15px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
</style>
