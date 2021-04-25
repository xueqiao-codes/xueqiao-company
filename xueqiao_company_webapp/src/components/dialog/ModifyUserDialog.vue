<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">编辑用户信息</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div>
            <div class="dialog-item g-form-item">
                <div class="dialog-item-key g-form-label">
                    <span>用户名</span>
                </div>
                <div class="dialog-item-value g-form-readonly-value">
                  <span>{{userInfo.userName}}</span>
                </div>
            </div>
            <div class="dialog-item dialog-item-with-errorinfo">
                <div class="dialog-item-key g-form-label">
                    <span>密码</span>
                </div>
                <!-- <div class="dialog-item-value g-form-readonly-value">
                  <span class="hidden-passwd">******</span>
                </div> -->
                <div v-if="modifyPasswd">
                  <!-- <input class="dialog-item-value g-form-value" type="text" v-model="userInfo.password"> -->
                  <passwdinput style="width: 280px;" v-model="password" @focus="onPasswordFocus" @blur="onPasswordBlur"></passwdinput>
                </div>
                <div v-else>
                  <input class="g-form-input-read-only-value" type="password" readonly="readonly" v-model="userInfo.password">
                  <a href="#" @click="showModifyPasswdFrame"><span>修改密码</span></a>
                </div>
                <div class="passwd-description" v-if="showPasswordDescription">
                  <span>6-20位字符。字母、数字、标点符号至少包含两种。</span>
                </div>
            </div>
            <div class="dialog-item g-form-item">
                <div class="dialog-item-key g-form-label">
                    <span>姓名</span>
                </div>
                <input class="dialog-item-value g-form-value" type="text" v-model="userInfo.cnName">
            </div>
            <div class="dialog-item g-form-item">
                <div class="dialog-item-key g-form-label">
                    <span>手机</span>
                </div>
                <input class="dialog-item-value g-form-value" type="text" v-model="userInfo.tel">
            </div>
            <div class="dialog-item g-form-item">
                <div class="dialog-item-key g-form-label">
                    <span>邮箱</span>
                </div>
                <input class="dialog-item-value g-form-value" type="text" v-model="userInfo.email">
            </div>
            <div class="dialog-item g-form-item dialog-item-hosting">
                <div class="dialog-item-key g-form-label dialog-item-key-hosting">
                    <span>{{$string.operatorRoleText}}</span>
                </div>
                <div class="dialog-item-value g-form-value dialog-item-value-hosting">
                    <div class="hosting-item" v-for="(groupUser,index) in userInfo.groupUserList" v-bind:key="index">
                        <input type="checkbox" v-model="groupUser.isGroupUser">
                        <div class="hosting-item-key g-form-label">{{groupUser.groupName}}</div>
                        <select class="hosting-item-value g-form-value" v-model="groupUser.role">
                            <option value="TRADER">交易员</option>
                            <option value="ADMIN">管理员</option>
                            <option v-if="$XQConfig.SHOW_OPERATOR_ROLE_OBSERVER" value="OBSERVER">观察员</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
      </div>
      <div class="dialog-button-container">
          <button class="g-button dialog-button" @click="confirm">确定</button>
        </div>
    </div>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DTips from '../dialog/TipsDialog'
export default {
  props: ['isShow', 'userInfo'],
  components: {
    'd-tips': DTips
  },
  data () {
    return {
      // dialog flag
      isShowTips: false,
      tipMsg: '',
      modifyValue: false,
      modifyUserInfo: null,
      modifyPasswd: false,
      password: null,
      // data
      showPasswordDescription: false
    }
  },
  watch: {
    isShow () {
      if (this.isShow) {
        // try token
        this.modifyPasswd = false
        this.password = null
        this.$commonFunc.tryToken()
      }
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
    doModify () {
      if (this.checkUserInfo()) {
        return
      }
      if (this.modifyPasswd) {
        this.password = this.$commonFunc.rsaEncode(this.password)
      }

      this.modifyUserInfo = {
        userId: this.userInfo.userId,
        companyId: this.userInfo.companyId,
        userName: this.userInfo.userName,
        password: this.password,
        tel: this.userInfo.tel,
        email: this.userInfo.email,
        cnName: this.userInfo.cnName,
        status: this.userInfo.status
      }

      var groupList = []
      for (var index = 0; index < this.userInfo.groupUserList.length; index++) {
        if (this.userInfo.groupUserList[index].isGroupUser) {
          var groupUser = {
            groupId: this.userInfo.groupUserList[index].groupId,
            groupName: this.userInfo.groupUserList[index].groupName,
            role: this.userInfo.groupUserList[index].role
          }
          groupList.push(groupUser)
        }
      }
      this.modifyUserInfo.groupUserList = groupList

      // modify company user
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyUser')
      // params.append('modifyCompanyUser', JSON.stringify(this.modifyUserInfo))
      var params = {
        'modifyCompanyUser' : JSON.stringify(this.modifyUserInfo)
      }
      this.$http.post('UpdateCompanyUser', params, function (response) {
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
          that.$eventBus.$emit('updateGroupUser')
        }
      })
    },
    showModifyPasswdFrame () {
      this.modifyPasswd = true
    },
    checkUserInfo () {
      if (this.checkPasswd()) {
        return 1
      }
      if (this.checkCnName()) {
        return 1
      }
      if (this.checkTel()) {
        return 1
      }
      if (this.checkEmail()) {
        return 1
      }
    },
    checkCnName () {
      if (this.userInfo.cnName == null || this.userInfo.cnName === '' || this.userInfo.cnName === undefined) {
        this.tipMsg = '请输入姓名'
        this.showTips()
        return 1
      }
      return 0
    },
    checkPasswd () {
      if (!this.modifyPasswd) {
        return 0
      }
      if (this.password == null || this.password.length < 1) {
        this.tipMsg = '请输入密码'
        this.showTips()
        return 1
      }
      let length = this.password.length
      // 不能包含空格
      let regContainWhiteSpace = /(^\s+)|(\s+$)|\s+/g
      if (regContainWhiteSpace.test(this.password)) {
        this.tipMsg = '密码不能包含空格'
        this.showTips()
        return 1
      }
      // 字母、数字和标点符号，至少包含两种
      let passwdCharacterTypeNum = 0
      let regContainNum = /\d+/
      if (regContainNum.test(this.password)) {
        // this.passwdErrorInfo = '密码包含数字'
        passwdCharacterTypeNum++
      }
      let regContainCharacter = /[a-zA-Z]+/
      if (regContainCharacter.test(this.password)) {
        // this.passwdErrorInfo = '密码包含字母'
        passwdCharacterTypeNum++
      }
      let regContainSpecialSymbol = /[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+/
      if (regContainSpecialSymbol.test(this.password)) {
        // this.passwdErrorInfo = '密码包含特殊字符'
        passwdCharacterTypeNum++
      }
      if (passwdCharacterTypeNum < 2) {
        this.tipMsg = '字母、数字和特殊符号，至少包含两种'
        this.showTips()
        return 1
      }
      // 密码长度在6-20位字符之间
      if (length < 6 || length > 20) {
        this.tipMsg = '密码长度不满足要求'
        this.showTips()
        return 1
      }
      return 0
    },
    checkTel () {
      if (this.userInfo.tel == null || this.userInfo.tel.length < 1) {
        this.tipMsg = '请输入手机号码'
        this.showTips()
        return 1
      }
      let regTelephone = /^0?(13[0-9]|(14[5,7])|15[012356789]|17[013678]|18[0-9]|166|198|199)[0-9]{8}$/
      var that = this
      if (!regTelephone.test(this.userInfo.tel)) {
        that.tipMsg = '手机号码不正确'
        that.showTips()
        return 1
      }
      return 0
    },
    checkEmail () {
      if (this.userInfo.email == null || this.userInfo.email.length < 1) {
        this.tipMsg = '请输入邮箱'
        this.showTips()
        return 1
      }
      let regEmail = /^[\w.\-]+@(?:[a-z0-9]+(?:-[a-z0-9]+)*\.)+[a-z]{2,3}$/
      var that = this
      if (!regEmail.test(this.userInfo.email)) {
        that.tipMsg = '邮箱不正确'
        that.showTips()
        return 1
      }
      return 0
    },
    onPasswordFocus () {
      this.showPasswordDescription = true
    },
    onPasswordBlur () {
      this.showPasswordDescription = false
    }
  }
}
</script>

<style scoped>
  .d-custom-dialog {
    width: 800px;
  }
  .custom-dialog-content {
    text-align: left;
  }
  .margin-top-10px {
    margin-top: 10px;
  }
  .dialog-item {
    text-align: left;
    display: flex;
    align-items: center;
    /* padding-top: 8px;
    padding-bottom: 8px; */
  }
  .dialog-item-with-errorinfo {
    margin-left: 0px;
    margin-top: 9px;
    text-align: left;
    /* background: red; */
    display: flex;
    align-items: center;
  }
  .passwd-description {
    text-align: right;
    /* align-items: flex-end; */
    padding-left: 50px;
    width: 150px;
    height: 32px;
    /* margin-left: 25px; */
    line-height: 16px;
    color: rgba(144, 144, 144, 1);
    font-size: 14px;
    text-align: left;
    font-family: STHeitiSC-Light;
  }
  .dialog-item-key {
    width: 120px;
    line-height: 16px;
    color: rgba(80, 80, 80, 1);
    font-size: 16px;
    text-align: left;
    font-family: STHeitiSC-Light;
  }
  .dialog-item-value {
    width: 300px;
  }
  .dialog-button {
    width: 320px;
  }
  .dialog-item-hosting {
    align-items: flex-start;
  }
  .dialog-item-key-hosting {
    margin-top: 10px;
  }
  .dialog-item-value-hosting {
    height: 200px;
    overflow: auto;
  }
  .hosting-item {
    margin-top: 10px;
    margin-bottom: 10px;
    text-align: left;
    display: flex;
    align-items: center;
  }
  .hosting-item-key {
    width: 170px;
    margin-left: 5px;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
  }
  .hosting-item-value {
    width: 90px;
    margin-left: 10px;
  }
  .g-form-input-read-only-value {
    width: 230px;
    height: 30px;
    border-radius: 4px;
    background-color: white;
    border: 0px solid rgba(229, 229, 229, 1);
    padding-left: 10px;
    padding-right: 10px;
}
  /* .hidden-passwd {
    text-align: center;
    margin-top: 5px;
    font-size: 20px;
  } */
</style>
