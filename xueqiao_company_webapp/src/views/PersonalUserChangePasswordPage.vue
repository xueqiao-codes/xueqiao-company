<template>
  <div class="fragment-bg">
    <div class="fragment-sub-bg">
      <div class="fragment-title">修改密码</div>
      <div style="height: 60px;"></div>
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
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DTips from '../components/dialog/TipsDialog'
export default {
  components: {
    'd-tips': DTips,
  },
  data () {
    return {
      showPasswordDescription: false,
      // input field
      passwd: null,
      passwdDoubleCheck: null,

      // error info
      passwdErrorInfo: '',
      passwdDoubleCheckErrorInfo: '',

      changePasswdSuccess: false,

      // dialog
      isShowTips: false,
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
  },
  methods: {
    onPasswordFocus () {
      this.showPasswordDescription = true
    },
    onPasswordBlur () {
      this.showPasswordDescription = false
    },
    showTips () {
      this.isShowTips = true
    },
    closeTips () {
      this.isShowTips = false
    },
    confirmTips () {
      this.isShowTips = false
      if (this.changePasswdSuccess) {
        this.$router.push('/personal/hosting')
      }
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
    doModifyPasswd () {
      this.changePasswdSuccess = false;
      var checkResult = 0
      checkResult += this.checkPasswd()
      checkResult += this.checkPasswdDoubleCheck()
      if (checkResult > 0) {
        return
      }

      // modify passwd
      var that = this
      var params = {
        'password' : this.passwd,
      }
      this.$http.postByPersonalUser('PersonalUserUpdatePassword', params, function (response) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.tipMsg = '修改成功'
          that.changePasswdSuccess = true;
          that.showTips()
        //   that.showAutoRedirectTips()
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
