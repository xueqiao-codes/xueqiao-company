<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">修改密码</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div class="dialog-item">
          <div class="dialog-item-key g-form-label">
            <span>密码</span>
          </div>
          <!-- <input class="dialog-item-value g-form-value" type="password" v-model="passwd"> -->
          <passwdinput style="width: 280px;" v-model="passwd"></passwdinput>
        </div>
        <div class="info-error-item">
          {{passwdErrorInfo}}
        </div>
        <div class="dialog-item">
          <div class="dialog-item-key g-form-label">
            <span>确认密码</span>
          </div>
          <!-- <input class="dialog-item-value g-form-value"  type="password" v-model="passwdDoubleCheck"> -->
          <passwdinput style="width: 280px;" v-model="passwdDoubleCheck"></passwdinput>
        </div>
        <div class="info-error-item">
          {{passwdDoubleCheckErrorInfo}}
        </div>
        <!-- <div class="dialog-item">
          <div class="dialog-item-key g-form-label">
            <span>手机号码</span>
          </div>
          <div class="dialog-item-value">{{telephone}}</div>
        </div> -->
        <div class="dialog-item">
          <div class="dialog-item-key g-form-label">
            <span>验证码</span>
          </div>
          <input class="g-form-value verifycode-value" type="text" v-model="verifyCode">
          <button class="g-button verifycode-button" v-bind:disabled="verifyCodeBtnDisabled" @click="getVerifyCode">{{verifyCodeBtnText}}</button>
        </div>
        <div class="verifycode-hint">
          <span class="signup_hint">收不到验证短信？</span>
          <a href="#" @click="showCustomServiceDialog">联系客服</a>
        </div>
        <div class="info-error-item">
          {{verifyCodeErrorInfo}}
        </div>
      </div>
      <div class="dialog-button-container">
          <button class="g-button dialog-button" @click="confirm">确定</button>
        </div>
    </div>
    <d-custom-service v-bind:isShow="isShowCustomServiceDialog" @close="closeCustomServiceDialog"></d-custom-service>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips"  @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DCustomService from '../dialog/CustomServiceDialog'
import DTips from '../dialog/TipsDialog'
export default {
  props: ['isShow', 'telephone'],
  components: {
    'd-custom-service': DCustomService,
    'd-tips': DTips
  },
  data () {
    return {
      verifyCode: null,
      verifyCodeErrorInfo: '',
      verifyCodeBtnText: '获取验证码',
      verifyCodeBtnDisabled: false,
      passwd: null,
      passwdErrorInfo: '',
      passwdDoubleCheck: null,
      passwdDoubleCheckErrorInfo: '',
      // dialog
      isShowTips: false,
      tipMsg: '',
      isShowCustomServiceDialog: false,
      modifyValue: false
    }
  },
  watch: {
    isShow () {
      // try token
      this.$commonFunc.tryToken()

      this.verifyCode = null
      this.passwd = null
      this.passwdDoubleCheck = null
      this.verifyCodeErrorInfo = ''
      this.passwdErrorInfo = ''
      this.passwdDoubleCheckErrorInfo = ''
    },
    passwd () {
      this.checkPasswd()
      this.checkPasswdDoubleCheck()
    },
    passwdDoubleCheck () {
      this.checkPasswd()
      this.checkPasswdDoubleCheck()
    },
    verifyCode () {
      this.checkVerifyCode()
    }
  },
  methods: {
    showTips () {
      this.isShowTips = true
      this.modifyValue = false
    },
    closeTips () {
      this.isShowTips = false
    },
    confirmTips () {
      this.isShowTips = false
      if (this.modifyValue) {
        this.$emit('confirm')
      }
    },
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      // this.$emit('confirm')
      this.doModify()
    },
    getVerifyCode () {
      if (this.verifyCodeBtnDisabled) return
      // if (this.checkTelephone()) return
      if (this.telephone == null || this.telephone === '') {
        this.tipMsg = '页面过期，请刷新页面后重试'
        this.showTips()
        return
      }

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

      // var params = new URLSearchParams()
      // params.append('method', 'GetTelVerifyCode')
      // params.append('tel', this.telephone)
      var params = {
        'tel' : this.telephone
      }
      this.$http.post('GetTelVerifyCode', params, function (response) {
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
    checkVerifyCode () {
      this.verifyCodeErrorInfo = ''
      if (this.verifyCode == null || this.verifyCode.length < 1) {
        this.verifyCodeErrorInfo = '请输入验证码'
        return 1
      }
      return 0
    },
    showCustomServiceDialog () {
      this.isShowCustomServiceDialog = true
    },
    closeCustomServiceDialog () {
      this.isShowCustomServiceDialog = false
    },
    doModify () {
      var checkResult = 0
      checkResult += this.checkPasswd()
      checkResult += this.checkPasswdDoubleCheck()
      checkResult += this.checkVerifyCode()
      if (checkResult > 0) {
        return
      }

      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyPasswd')
      // params.append('telephone', this.telephone)
      // params.append('passwd', this.passwd)
      // params.append('verifyCode', this.verifyCode)
      var params = {
        'telephone' : this.telephone,
        'passwd' : this.passwd,
        'verifyCode' : this.verifyCode
      }
      this.$http.post('UpdateCompanyPasswd', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.tipMsg = '修改成功'
          that.showTips()
          that.modifyValue = true
        }
      })
    }
  }
}
</script>

<style scoped>
  .d-custom-dialog {
    width: 720px;
  }
  .custom-dialog-content {
    text-align: left;
  }
  .dialog-item {
    text-align: left;
    display: flex;
    align-items: center;
    margin-top: 15px;
    margin-bottom: 2px;
  }
  .dialog-item-key {
    width: 100px;
    line-height: 16px;
    color: rgba(80, 80, 80, 1);
    font-size: 16px;
    text-align: left;
    font-family: STHeitiSC-Light;
  }
  .dialog-item-value {
    width: 300px;
  }
  .verifycode-value {
    width: 140px;
  }
  .verifycode-button {
    width: 140px;
    margin-left: 20px;
  }
  .verifycode-hint {
    margin-left: 100px;
  }
  .dialog-button {
    width: 320px;
  }
  .info-error-item {
    margin-left: 100px;
    height: 16px;
    line-height: 16px;
    color: rgba(255, 64, 64, 1);
    font-size: 14px;
    text-align: left;
    font-family: STHeitiSC-Light;
  }
</style>
