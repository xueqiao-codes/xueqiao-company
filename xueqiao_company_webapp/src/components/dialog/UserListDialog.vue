<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-service">
      <div class="dialog-header">
        <div class="dialog-title">用户列表</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-service-content">
        <div>
          <table>
            <thead>
              <tr bgColor=#F8FCFF>
                <th width="150">用户名称</th>
                <th width="150">姓名</th>
                <th width="150">手机</th>
                <th width="200">邮箱</th>
                <th width="300">{{$string.operatorRoleText}}</th>
                <th width="100">状态</th>
                <th width="200">操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(userInfo, index) in userInfoList" v-bind:key="index">
                <td width="150">{{userInfo.userName}}</td>
                <td width="150">{{userInfo.cnName}}</td>
                <td width="150">{{userInfo.tel}}</td>
                <td width="200">{{userInfo.email}}</td>
                <td width="300">
                  <div v-for="(groupUser, index) in userInfo.groupUserList" v-bind:key="index">
                    {{groupUser.groupName}}&nbsp;&nbsp;&nbsp;({{groupUser.cnRole}})
                  </div>
                </td>
                <td width="100">{{getUserCNStatus(userInfo.status)}}</td>
                <td width="200">
                  <a href="#" v-if="userInfo.status == 'NORMAL'" class="operate-disable" @click="showModifyUserDialog(userInfo)">编辑&nbsp;&nbsp;</a>
                  <a href="#" v-if="userInfo.status == 'NORMAL'" class="operate-disable" @click="showResetPasswdDialog(userInfo)">重置密码&nbsp;&nbsp;</a>
                  <a href="#" v-if="userInfo.status == 'NORMAL'" class="operate-disable" @click="showDisableUserConfirmDialog(userInfo)">停用</a>
                  <a href="#" v-else class="operate-enable" @click="showEnableUserConfirmDialog(userInfo)">启用</a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <d-confirm v-bind:isShow="isShowConfirmDialog" title="确认操作" v-bind:msg="confirmDialogMsg" @close="closeConfirmDialog" @cancel="cancelConfirmDialog" @confirm="confirmConfirmDialog"></d-confirm>
    <d-modify-user v-bind:isShow="isShowModifyUserDialog" v-bind:userInfo="modifyUserInfo" @close="closeModifyUserDialog" @confirm="confirmModifyUserDialog"></d-modify-user>
    <d-reset-passwd v-bind:isShow="isShowResetUserPasswdDialog" v-bind:userInfo="userInfo" @close="closeResetPasswdDialog" @confirm="confirmResetPasswdDialog"></d-reset-passwd>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DConfirm from '../dialog/ConfirmWithCancelDialog'
import DModifyUser from '../dialog/ModifyUserDialog'
import DTips from '../dialog/TipsDialog'
import DResetUserPasswd from '../dialog/ResetUserPasswdDialog'
export default {
  props: ['isShow', 'groupList'],
  components: {
    'd-tips': DTips,
    'd-modify-user': DModifyUser,
    'd-confirm': DConfirm,
    'd-reset-passwd': DResetUserPasswd
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      isShowConfirmDialog: false,
      confirmDialogMsg: '',
      confirmDialogType: null,
      isShowModifyUserDialog: false,
      isShowResetUserPasswdDialog: false,
      // data
      userInfoList: [],
      userInfo: null,
      // modifyUser
      modifyUserInfo: null
    }
  },
  watch: {
    isShow () {
      if (this.isShow) {
        this.getUserList()
      }
    }
  },
  methods: {
    showTips () {
      this.isShowTips = true
    },
    closeTips () {
      this.isShowTips = false
    },
    confirmTips () {
      this.isShowTips = false
    },
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      this.$emit('confirm')
    },
    getUserList () {
      // alert('get user list')
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'GetCompanyUsers')
      // params.append('withGroupUser', 1)
      // params.append('allUser', 1)
      var params = {
        'withGroupUser' : 1,
        'allUser' : 1
      }
      this.$http.post('GetCompanyUsers', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.userInfoList = response.value.page
        }
      })
    },
    getUserCNStatus (status) {
      if (status === 'NORMAL') {
        return '正常'
      } else {
        return '已停用'
      }
    },
    showEnableUserConfirmDialog (userInfo) {
      if (userInfo) {
        this.userInfo = {
          userId: userInfo.userId,
          companyId: userInfo.companyId,
          userName: userInfo.userName,
          password: userInfo.password,
          tel: userInfo.tel,
          email: userInfo.email,
          cnName: userInfo.cnName,
          status: 'NORMAL'
        }
      }

      this.isShowConfirmDialog = true
      this.confirmDialogMsg = '确定要启用用户 ' + userInfo.cnName
      this.confirmDialogType = 'enable'
    },
    showDisableUserConfirmDialog (userInfo) {
      if (userInfo) {
        this.userInfo = {
          userId: userInfo.userId,
          companyId: userInfo.companyId,
          userName: userInfo.userName,
          password: userInfo.password,
          tel: userInfo.tel,
          email: userInfo.email,
          cnName: userInfo.cnName,
          status: 'DISABLED'
        }
      }

      this.isShowConfirmDialog = true
      this.confirmDialogMsg = '确定要停用用户 ' + userInfo.cnName
      this.confirmDialogType = 'disable'
    },
    closeConfirmDialog () {
      this.isShowConfirmDialog = false
    },
    cancelConfirmDialog () {
      this.isShowConfirmDialog = false
    },
    confirmConfirmDialog () {
      if (this.confirmDialogType === 'enable') {
        this.doEnableGroupUser()
      } else if (this.confirmDialogType === 'disable') {
        this.doDisableGroupUser()
      }
      this.isShowConfirmDialog = false
    },
    doEnableGroupUser () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyUserStatus')
      // params.append('modifyCompanyUser', JSON.stringify(this.userInfo))
      var params = {
        'modifyCompanyUser' : JSON.stringify(this.userInfo)
      }
      this.$http.post('UpdateCompanyUserStatus', params, function (response) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.tipMsg = '已启用'
          that.showTips()
          that.getUserList()
          that.$eventBus.$emit('updateGroupUser')
        }
      })
      this.isShowConfirmDisableUserDialog = false
    },
    doDisableGroupUser () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyUserStatus')
      // params.append('modifyCompanyUser', JSON.stringify(this.userInfo))
      var params = {
        'modifyCompanyUser' : JSON.stringify(this.userInfo)
      }
      this.$http.post('UpdateCompanyUserStatus', params, function (response) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.tipMsg = '已停用'
          that.showTips()
          that.getUserList()
          that.$eventBus.$emit('updateGroupUser')
        }
      })
      this.isShowConfirmDisableUserDialog = false
    },
    showModifyUserDialog (userInfo) {
      if (userInfo) {
        this.modifyUserInfo = {
          userId: userInfo.userId,
          companyId: userInfo.companyId,
          userName: userInfo.userName,
          password: userInfo.password,
          tel: userInfo.tel,
          email: userInfo.email,
          cnName: userInfo.cnName,
          status: userInfo.status
        }
        var groupUserIdList = []
        var groupUserRoleList = []

        // alert(JSON.stringify(this.groupList))

        for (var index = 0; index < userInfo.groupUserList.length; index++) {
          groupUserIdList.push(userInfo.groupUserList[index].groupId)
          groupUserRoleList.push(userInfo.groupUserList[index].role)
        }
        // alert(JSON.stringify(groupUserIdList))
        // alert(JSON.stringify(groupUserRoleList))
        var tempGroupUserList = []
        if (this.groupList) {
          for (index = 0; index < this.groupList.length; index++) {
            var isGroupUser = false
            var role = 'TRADER'
            var indexOfGroupId = groupUserIdList.indexOf(
              this.groupList[index].groupId
            )
            if (indexOfGroupId >= 0) {
              isGroupUser = true
              role = groupUserRoleList[indexOfGroupId]
            }
            var groupUser = {
              groupId: this.groupList[index].groupId,
              groupName: this.groupList[index].groupName,
              role: role,
              isGroupUser: isGroupUser
            }
            tempGroupUserList.push(groupUser)
          }
        }
        this.modifyUserInfo.groupUserList = tempGroupUserList
        this.isShowModifyUserDialog = true
      }
    },
    closeModifyUserDialog () {
      this.isShowModifyUserDialog = false
    },
    confirmModifyUserDialog () {
      this.isShowModifyUserDialog = false
      // this.queryCompanyUserList()
      this.getUserList()
    },
    showResetPasswdDialog (userInfo) {
      if (userInfo) {
        this.userInfo = {
          userId: userInfo.userId,
          companyId: userInfo.companyId,
          userName: userInfo.userName,
          password: userInfo.password,
          tel: userInfo.tel,
          email: userInfo.email,
          cnName: userInfo.cnName,
          status: userInfo.status
        }
      }
      this.isShowResetUserPasswdDialog = true
    },
    closeResetPasswdDialog () {
      this.isShowResetUserPasswdDialog = false
    },
    confirmResetPasswdDialog () {
      this.isShowResetUserPasswdDialog = false
      this.getUserList()
    }
  }
}
</script>

<style scoped>
.d-custom-service {
  width: 1460px;
}
.custom-service-content {
  text-align: center;
}
.margin-top-10px {
  margin-top: 10px;
}
.user-list-table {
  width: 1234px;
}
.operate-enable {
  line-height: 16px;
  color: rgba(54, 158, 239, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.operate-disable {
  line-height: 16px;
  color: rgba(255, 124, 27, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
table tbody {
  display: block;
  width: 1268px;
  max-height: 600px;
  overflow-y: auto;
  overflow-x: hidden;
}
table thead,
tbody tr {
  display: table;
  width: 1234px;
  table-layout: fixed;
}
/* table thead {
  width: 1234px;
} */
</style>
