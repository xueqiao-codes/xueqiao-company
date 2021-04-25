<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">修改手机号</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div class="dialog-item">
          <div class="dialog-item-key g-form-label">
            <span>新手机号</span>
          </div>
          <input class="dialog-item-value g-form-value" type="text" v-model="modifyTelephone">
        </div>
        <div class="info-error-item">
          {{telephoneErrorInfo}}
        </div>
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
      modifyTelephone: null,

      telephoneErrorInfo: '',
      verifyCode: null,
      verifyCodeErrorInfo: '',
      verifyCodeBtnText: '获取验证码',
      verifyCodeBtnDisabled: false,
      modifyValue: false,
      // dialog
      isShowTips: false,
      tipMsg: '',
      isShowCustomServiceDialog: false
    }
  },
  watch: {
    isShow () {
      if (this.isShow) {
        this.modifyTelephone = this.telephone
        // try token
        this.$commonFunc.tryToken()
      }
    },
    telephone () {
      this.checkTelephone()
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
      if (this.checkTelephone()) return

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
        'tel' : this.modifyTelephone
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
    checkTelephone () {
      this.telephoneErrorInfo = ''
      if (this.modifyTelephone == null || this.modifyTelephone.length < 1) {
        this.telephoneErrorInfo = '请输入手机号码'
        return 1
      }
      let regTelephone = /^0?(13[0-9]|(14[5,7])|15[012356789]|17[013678]|18[0-9]|166|198|199)[0-9]{8}$/
      if (!regTelephone.test(this.modifyTelephone)) {
        this.telephoneErrorInfo = '手机号码不正确'
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
      checkResult += this.checkTelephone()
      checkResult += this.checkVerifyCode()
      if (checkResult > 0) {
        return
      }

      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyTelephone')
      // params.append('telephone', this.telephone)
      // params.append('verifyCode', this.verifyCode)
      var params = {
        'telephone' : this.modifyTelephone,
        'verifyCode' : this.verifyCode
      }
      this.$http.post('UpdateCompanyTelephone', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          var companyInfo = that.$store.state.companyInfo
          companyInfo.telephone = that.modifyTelephone
          that.$store.commit('setCompanyInfo', companyInfo)
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
