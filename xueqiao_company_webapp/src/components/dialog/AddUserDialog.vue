<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">添加新用户</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div>
            <div class="dialog-item g-form-item">
                <div class="dialog-item-key g-form-label">
                    <span>用户名</span>
                    <span style="color:red">*</span>
                </div>
                <input class="dialog-item-value g-form-value" type="text" v-model="userInfo.userName">
            </div>
            <!-- <div class="dialog-item g-form-item">
                <div class="dialog-item-key g-form-label">
                    <span>密码</span>
                    <span style="color:red">*</span>
                </div>
                <input class="dialog-item-value g-form-value" type="text" v-model="userInfo.password">
            </div> -->
            <div class="dialog-item g-form-item">
                <div class="dialog-item-key g-form-label">
                    <span>姓名</span>
                    <span style="color:red">*</span>
                </div>
                <input class="dialog-item-value g-form-value" type="text" v-model="userInfo.cnName">
            </div>
            <div class="dialog-item g-form-item">
                <div class="dialog-item-key g-form-label">
                    <span>手机</span>
                    <span style="color:red">*</span>
                </div>
                <input class="dialog-item-value g-form-value" type="text" v-model="userInfo.tel">
            </div>
            <div class="dialog-item g-form-item">
                <div class="dialog-item-key g-form-label">
                    <span>邮箱</span>
                    <span style="color:red">*</span>
                </div>
                <input class="dialog-item-value g-form-value" type="text" v-model="userInfo.email">
            </div>
            <div class="dialog-item g-form-item dialog-item-hosting">
                <div class="dialog-item-key g-form-label dialog-item-key-hosting">
                    <span>{{$string.operatorRoleText}}</span>
                </div>
                <div class="dialog-item-value g-form-value dialog-item-value-hosting">
                    <div class="hosting-item" v-for="(groupUser,index) in groupUserList" v-bind:key="index">
                        <input type="checkbox" v-model="groupUser.isGroupUser">
                        <div class="hosting-item-key g-form-label">{{groupUser.groupName}}</div>
                        <select class="hosting-item-value g-form-value" v-model="groupUser.role">
                            <option value="TRADER">交易员</option>
                            <option value="ADMIN">管理员</option>
                            <option  v-if="$XQConfig.SHOW_OPERATOR_ROLE_OBSERVER" value="OBSERVER">观察员</option>
                        </select>
                    </div>
                </div>
            </div>
            <div>
              <span class="local-attention-text attention-text">用户初始密码由系统生成，管理员和用户可以通过相应方式修改。</span>
            </div>
        </div>
      </div>
      <div class="dialog-button-container">
          <button class="g-button dialog-button" @click="confirm">添加</button>
        </div>
    </div>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
    <d-confirm-add-user v-bind:isShow="isShowConfirmAddUserDialog" v-bind:userInfo="userInfo" @close="closeConfirmAddUserDialog" @confirm="confirmAddUser" @cancel="cancelAddUser"></d-confirm-add-user>
  </div>
</template>

<script>
import DTips from '../dialog/TipsDialog'
import DConfirmAddUser from '../dialog/ConfirmAddUserDialog'
export default {
  props: ['isShow', 'groupUserList'],
  components: {
    'd-tips': DTips,
    'd-confirm-add-user': DConfirmAddUser
  },
  data () {
    return {
      // dialog flag
      isShowTips: false,
      tipMsg: '',
      executeSuccess: false,
      isShowConfirmAddUserDialog: false,
      // data
      userInfo: {
        userName: null,
        password: null,
        cnName: null,
        tel: null,
        email: null,
        groupUserList: null
      }
    }
  },
  watch: {
    isShow () {
      if (this.isShow) {
        this.userInfo.userName = null
        this.userInfo.password = null
        this.userInfo.cnName = null
        this.userInfo.tel = null
        this.userInfo.email = null
        this.userInfo.groupUserList = null
        // try token
        this.$commonFunc.tryToken()
      }
    }
  },
  methods: {
    showTips () {
      this.isShowTips = true
      this.executeSuccess = false
    },
    closeTips () {
      this.isShowTips = false
    },
    confirmTips () {
      this.isShowTips = false
      if (this.executeSuccess) {
        this.$emit('confirm')
      }
    },
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      // this.$emit('confirm')
      // this.doAddition()
      if (this.checkUserInfo()) {
        return
      }

      var groupList = []
      for (var index = 0; index < this.groupUserList.length; index++) {
        if (this.groupUserList[index].isGroupUser) {
          var groupUser = {
            groupId: this.groupUserList[index].groupId,
            groupName: this.groupUserList[index].groupName,
            role: this.groupUserList[index].role
          }
          groupList.push(groupUser)
        }
      }
      this.userInfo.groupUserList = groupList
      this.isShowConfirmAddUserDialog = true
    },
    closeConfirmAddUserDialog () {
      this.isShowConfirmAddUserDialog = false
    },
    confirmAddUser () {
      this.isShowConfirmAddUserDialog = false
      this.doAddition()
    },
    cancelAddUser () {
      this.isShowConfirmAddUserDialog = false
    },
    checkUserInfo () {
      if (this.checkUserName()) {
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
    doAddition () {
      // if (this.checkUserInfo()) {
      //   return
      // }

      // var groupList = []
      // for (var index = 0; index < this.userInfo.groupUserList.length; index++) {
      //   if (this.userInfo.groupUserList[index].isGroupUser) {
      //     var groupUser = {
      //       groupId: this.userInfo.groupUserList[index].groupId,
      //       groupName: this.userInfo.groupUserList[index].groupName,
      //       role: this.userInfo.groupUserList[index].role
      //     }
      //     groupList.push(groupUser)
      //   }
      // }
      // this.userInfo.groupUserList = groupList

      // add company user
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'AddCompanyUser')
      // params.append('newCompanyUser', JSON.stringify(this.userInfo))
      var params = {
        'newCompanyUser' : JSON.stringify(this.userInfo)
      }
      this.$http.post('AddCompanyUser', params, function (response) {
        // alert(JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          if (response.value && response.value === 'addSuccess') {
            that.tipMsg = '用户添加成功，邮件发送失败'
          } else if (response.value && response.value === 'success') {
            that.tipMsg = '用户添加成功，邮件发送成功'
          }
          that.isShowAddUserDialog = false
          that.showTips()
          that.executeSuccess = true
          // that.queryCompanyUserList()
        }
      })
    },
    checkUserName () {
      if (this.userInfo.userName == null || this.userInfo.userName === '' || this.userInfo.userName === undefined) {
        this.tipMsg = '请输入用户名'
        this.showTips()
        return 1
      }
      return 0
    },
    checkCnName () {
      if (this.userInfo.cnName == null || this.userInfo.cnName === '' || this.userInfo.cnName === undefined) {
        this.tipMsg = '请输入姓名'
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
    }
  }
}
</script>

<style scoped>
  .d-custom-dialog {
    width: 760px;
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
    /* max-height: 200px;
    min-height: 150px; */
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
  .local-attention-text {
  line-height: 16px;
  opacity: 0.75;
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
</style>
