<template>
  <div class="fragment-bg">
    <div class="fragment-sub-bg">
      <div class="fragment-title">修改密码</div>
      <div class="fragment-info-item">
        <div class="fragment-info-key">
          <span>手机号码</span>
        </div>
        <input class="g-form-value fragment-info-value" type="text" v-model="telephone">
        <!-- <div class="g-form-value fragment-info-value">{{$store.state.companyInfo.telephone}}</div> -->
      </div>
      <div class="fragment-info-error-item">
        {{telephoneErrorInfo}}
      </div>
      <div class="fragment-info-item-with-errorinfo">
        <div class="fragment-info-key">
          <span>验证码</span>
        </div>
        <input class="g-form-value verifycode-value" type="text" v-model="verifyCode">
        <button class="g-button verifycode-button" v-bind:disabled="verifyCodeBtnDisabled" @click="getVerifyCode">{{verifyCodeBtnText}}</button>
      </div>
      <div class="verifycode-hint">
        <span class="signup_hint">收不到验证短信？</span>
        <a href="#" @click="showCustomServiceDialog">联系客服</a>
      </div>
      <div class="fragment-info-error-item">
        {{verifyCodeErrorInfo}}
      </div>
      <div class="fragment-info-item-with-errorinfo">
        <div class="fragment-info-key">
          <span>新密码</span>
        </div>
        <input class="g-form-value fragment-info-value" type="password" v-model="passwd" @focus="onPasswordFocus" @blur="onPasswordBlur">
        <div class="fragment-info-description" v-if="showPasswordDescription">
          <span>6-20位字符，密码不能和公司账号相同；<br>只能包含字母、数字、标点符号（空格除外）;<br>字母、数字、标点符号至少包含两种。</span>
        </div>
      </div>
      <div class="fragment-info-error-item">
        {{passwdErrorInfo}}
      </div>
      <div class="fragment-info-item-with-errorinfo">
        <div class="fragment-info-key">
          <span>确认新密码</span>
        </div>
        <input class="g-form-value fragment-info-value" type="password" v-model="passwdDoubleCheck">
      </div>
      <div class="fragment-info-error-item">
        {{passwdDoubleCheckErrorInfo}}
      </div>
      <div class="fragment-button-wrapper">
        <button class="g-button fragment-button" @click="doModifyPasswd">确定修改</button>
      </div>
    </div>
    <d-custom-service v-bind:isShow="isShowCustomServiceDialog" @close="closeCustomServiceDialog"></d-custom-service>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
    <d-autoredirect-tips v-bind:isShow="isShowAutoRedirectTips" title="修改完成" msg1="您已完成修改，" msg2="秒后自动跳转到登录页面" v-bind:currentTime="autoRedirectTipsShowTime" @close="closeAutoRedirectTips" @confirm="confirmAutoRedirectTips"></d-autoredirect-tips>
  </div>
</template>

<script>
import DCustomService from '../components/dialog/CustomServiceDialog'
import DTips from '../components/dialog/TipsDialog'
import AutoRedirectTipsDialog from '../components/dialog/AutoRedirectTipsDialog'
export default {
  components: {
    'd-custom-service': DCustomService,
    'd-tips': DTips,
    'd-autoredirect-tips': AutoRedirectTipsDialog
  },
  data () {
    return {
      showCompanyCodeDescription: false,
      showPasswordDescription: false,
      // input field
      telephone: null,
      passwd: null,
      passwdDoubleCheck: null,
      verifyCode: null,
      // error info
      telephoneErrorInfo: '',
      verifyCodeErrorInfo: '',
      passwdErrorInfo: '',
      passwdDoubleCheckErrorInfo: '',
      //
      verifyCodeBtnDisabled: false,
      verifyCodeBtnText: '获取验证码',
      isShowCustomServiceDialog: false,
      // dialog
      isShowTips: false,
      isShowAutoRedirectTips: false,
      autoRedirectTipsShowTime: null,
      tipMsg: ''
    }
  },
  watch: {
    passwd () {
      this.checkPasswd()
      this.checkPasswdDoubleCheck()
    },
    passwdDoubleCheck () {
      this.checkPasswd()
      this.checkPasswdDoubleCheck()
    },
    telephone () {
      this.checkTelephone()
    },
    verifyCode () {
      this.checkVerifyCode()
    }
  },
  // mounted () {
  //   // try token
  //   this.$commonFunc.tryToken()
  // },
  methods: {
    onPasswordFocus () {
      this.showPasswordDescription = true
    },
    onPasswordBlur () {
      this.showPasswordDescription = false
    },
    // dialog
    showCustomServiceDialog () {
      this.isShowCustomServiceDialog = true
    },
    closeCustomServiceDialog () {
      this.isShowCustomServiceDialog = false
    },
    showTips () {
      this.isShowTips = true
    },
    closeTips () {
      this.isShowTips = false
    },
    confirmTips () {
      this.isShowTips = false
    },
    showAutoRedirectTips () {
      var date = new Date()
      this.autoRedirectTipsShowTime = date.toLocaleTimeString()
      this.isShowAutoRedirectTips = true
    },
    closeAutoRedirectTips () {
      this.isShowAutoRedirectTips = false
    },
    confirmAutoRedirectTips () {
      this.isShowAutoRedirectTips = false
      this.$router.push('/login/personal')
    },
    // check input info
    checkPasswd () {
      this.passwdErrorInfo = ''
      if (this.passwd == null || this.passwd.length < 1) {
        this.passwdErrorInfo = '请输入密码'
        return 1
      }
      let length = this.passwd.length
      // 不能和公司账号相同
      if (this.passwd === this.companyCode) {
        this.passwdErrorInfo = '密码不能与公司账号相同'
        return 1
      }
      // 不能包含空格
      let regContainWhiteSpace = /(^\s+)|(\s+$)|\s+/g
      if (regContainWhiteSpace.test(this.passwd)) {
        this.passwdErrorInfo = '密码不能包含空格'
        return 1
      }
      // 字母、数字和标点符号，至少包含两种
      let passwdCharacterTypeNum = 0
      let regContainNum = /\d+/
      if (regContainNum.test(this.passwd)) {
        // this.passwdErrorInfo = '密码包含数字'
        passwdCharacterTypeNum++
      }
      let regContainCharacter = /[a-zA-Z]+/
      if (regContainCharacter.test(this.passwd)) {
        // this.passwdErrorInfo = '密码包含字母'
        passwdCharacterTypeNum++
      }
      let regContainSpecialSymbol = /[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+/
      if (regContainSpecialSymbol.test(this.passwd)) {
        // this.passwdErrorInfo = '密码包含特殊字符'
        passwdCharacterTypeNum++
      }
      if (passwdCharacterTypeNum < 2) {
        this.passwdErrorInfo = '字母、数字和特殊符号，至少包含两种'
        return 1
      }
      // 密码长度在6-20位字符之间
      if (length < 6 || length > 20) {
        this.passwdErrorInfo = '密码长度不满足要求'
        return 1
      }
      return 0
    },
    checkPasswdDoubleCheck () {
      this.passwdDoubleCheckErrorInfo = ''
      if (this.passwdDoubleCheck == null || this.passwdDoubleCheck.length < 1) {
        this.passwdDoubleCheckErrorInfo = '请输入确认密码'
        return 1
      }
      if (this.passwd !== this.passwdDoubleCheck) {
        this.passwdDoubleCheckErrorInfo = '两次密码输入不一致'
        return 1
      }
      return 0
    },
    checkTelephone () {
      this.telephoneErrorInfo = ''
      if (this.telephone == null || this.telephone.length < 1) {
        this.telephoneErrorInfo = '请输入手机号码'
        return 1
      }
      let regTelephone = /^0?(13[0-9]|(14[5,7])|15[012356789]|17[013678]|18[0-9]|166|198|199)[0-9]{8}$/
      if (!regTelephone.test(this.telephone)) {
        this.telephoneErrorInfo = '手机号码不正确'
        return 1
      }
      return 0
    },
    checkVerifyCode () {
      this.verifyCodeErrorInfo = ''
      if (this.verifyCode == null || this.verifyCode.length < 1) {
        // this.tipMsg = '验证码输入错误'
        // this.showTips()
        this.verifyCodeErrorInfo = '请输入验证码'
        return 1
      }
      return 0
    },
    getVerifyCode () {
      if (this.verifyCodeBtnDisabled) return

      var that = this
      var time = 120

      that.time = '(' + time + '")'
      that.verifyCodeBtnText = that.time
      this.verifyCodeBtnDisabled = true

      var timer = setInterval(function () {
        if (time === 0) {
          that.time = ''
          clearInterval(timer)
          that.verifyCodeBtnDisabled = false
          that.verifyCodeBtnText = '获取验证码'
        } else {
          time--
          that.time = '(' + time + '")'
          that.verifyCodeBtnText = that.time
        }
      }, 1000)

      var params = {
        'tel' : this.telephone
      }
      this.$http.postByPersonalUser('GetTelVerifyCode', params, function (response) {
        if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
          clearInterval(timer)
        } else {
          that.tipMsg = '验证码已发送成功'
          that.showTips()
        }
      })
    },
    doModifyPasswd () {
      var checkResult = 0
      checkResult += this.checkPasswd()
      checkResult += this.checkPasswdDoubleCheck()
      checkResult += this.checkTelephone()
      checkResult += this.checkVerifyCode()
      if (checkResult > 0) {
        return
      }

      // modify passwd
      var that = this
      var params = {
        'password' : this.passwd,
        'telephone' : this.telephone,
        'verifyCode' : this.verifyCode
      }
      this.$http.postByPersonalUser('PersonalUserForgetPassword', params, function (response) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          // that.tipMsg = '注册成功'
          // that.showTips()
          that.showAutoRedirectTips()
        }
      })
    }
  }
}
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
</style>
