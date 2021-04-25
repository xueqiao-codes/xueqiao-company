<template>
  <div class="user-manager">
    <div class="manager-title">
      <span class="manager-title-space"></span>
      <span class="manager-title-text">公司用户</span>
    </div>
    <div class="menu-wrapper">
      <a href="#" class="menu-item-wrapper1" @click="showAddUserDialog">
        <img src="@/assets/icon_add_user.png">
        <span class="menu-text">添加用户</span>
      </a>
      <a href="#" class="menu-item-wrapper2" @click="showUserListDialog">
        <span class="menu-text">查看全部用户</span>
      </a>
    </div>
    <hr class="hr-style">
    <!-- user list  -->
    <div class="right-side-bar-list-container">
      <div v-for="(companyUser, index) in companyUserPage" v-bind:key="index">
        <div class="right_side_bar_item">
          <div>
            <a href="#" @click="showUserInfoDialog(companyUser)" title="点击查看用户信息"><span class="right_side_bar_item_text">{{companyUser.cnName}}</span></a>
          </div>
          <div>
            <a class="btn-edit" href="#" @click="showModifyUserDialog(companyUser)"><img src="@/assets/icon_edit.png" title="编辑用户"></a>
            <a class="btn-resetpasswd" href="#" @click="showResetPasswdDialog(companyUser)"><img src="@/assets/icon_reset_passwd.png" title="重置密码"></a>
            <a class="btn-disable" href="#" @click="showDisableUserDialog(companyUser)"><img src="@/assets/icon_disable.png" title="停用用户"></a>
          </div>
        </div>

        <hr class="hr-divider">
      </div>
    </div>
    <d-add-user v-bind:isShow="isShowAddUserDialog" v-bind:groupUserList="userInfo.groupUserList" @close="closeAddUserDialog" @confirm="confirmAddUserDialog"></d-add-user>
    <d-user-info v-bind:isShow="isShowUserInfoDialog" v-bind:userInfo="userInfo" @close="closeUserInfoDialog" @confirm="confirmUserInfoDialog"></d-user-info>
    <d-modify-user v-bind:isShow="isShowModifyUserDialog" v-bind:userInfo="userInfo" @close="closeModifyUserDialog" @confirm="confirmModifyUserDialog"></d-modify-user>
    <d-reset-passwd v-bind:isShow="isShowResetUserPasswdDialog" v-bind:userInfo="userInfo" @close="closeResetPasswdDialog" @confirm="confirmResetPasswdDialog"></d-reset-passwd>
    <d-confirm v-bind:isShow="isShowConfirmDisableUserDialog" title="确认操作" v-bind:msg="disableUserMsg" @close="closeDisableUserDialog" @cancel="cancelDisableUserDialog" @confirm="confirmDisableUserDialog"></d-confirm>
    <d-user-list v-bind:isShow="isShowUserListDialog" v-bind:groupList="groupList" @close="closeUserListDialog"></d-user-list>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DTips from '../../components/dialog/TipsDialog'
import DAddUser from '../../components/dialog/AddUserDialog'
import DUserInfo from '../../components/dialog/UserInfoDialog'
import DModifyUser from '../../components/dialog/ModifyUserDialog'
import DConfirm from '../../components/dialog/ConfirmWithCancelDialog'
import DUserList from '../../components/dialog/UserListDialog'
import DResetUserPasswd from '../../components/dialog/ResetUserPasswdDialog'
export default {
  components: {
    'd-tips': DTips,
    'd-add-user': DAddUser,
    'd-user-info': DUserInfo,
    'd-modify-user': DModifyUser,
    'd-confirm': DConfirm,
    'd-user-list': DUserList,
    'd-reset-passwd': DResetUserPasswd
  },
  data () {
    return {
      // dialog flag
      isShowTips: false,
      tipMsg: '',
      isShowAddUserDialog: false,
      isShowUserInfoDialog: false,
      isShowModifyUserDialog: false,
      isShowConfirmDisableUserDialog: false,
      isShowUserListDialog: false,
      isShowResetUserPasswdDialog: false,
      // dialog data
      disableUserMsg: '',
      // data
      companyUserPage: null,
      userInfo: {
        userName: null,
        password: null,
        cnName: null,
        tel: null,
        email: null,
        groupUserList: []
      },
      groupList: []
    }
  },
  mounted () {
    this.queryCompanyUserList()
    this.queryGroupList()
    this.$eventBus.$on('updateGroupUser', () => {
      // alert('UserManager : accept event-> updateGroupUser')
      this.queryCompanyUserList()
    })
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
      // this.$router.push('/home')
    },
    queryCompanyUserList () {
      var that = this
      var params = new URLSearchParams()
      // params.append('method', 'GetCompanyUsers')
      // params.append('withGroupUser', 1)
      var params = {
        'withGroupUser' : 1
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
          that.companyUserPage = response.value.page
        }
      })
    },
    queryGroupList () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'GetCompanyGroups')
      var params = {}
      this.$http.post('GetCompanyGroups', params, function (response) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.groupList = response.results
        }
      })
    },
    showAddUserDialog () {
      // update group list
      this.queryGroupList()

      var groupList = []
      if (this.groupList) {
        for (var index = 0; index < this.groupList.length; index++) {
          var groupUser = {
            groupId: this.groupList[index].groupId,
            groupName: this.groupList[index].groupName,
            role: 'TRADER',
            isGroupUser: false
          }
          groupList.push(groupUser)
        }
      }
      this.userInfo = {
        userName: null,
        password: null,
        cnName: null,
        tel: null,
        email: null,
        groupUserList: groupList
      }
      this.isShowAddUserDialog = true
    },
    closeAddUserDialog () {
      this.isShowAddUserDialog = false
    },
    confirmAddUserDialog () {
      this.isShowAddUserDialog = false
      this.queryCompanyUserList()
    },
    showUserInfoDialog (userInfo) {
      if (userInfo) {
        this.userInfo = userInfo
        this.isShowUserInfoDialog = true
      }
    },
    closeUserInfoDialog () {
      this.isShowUserInfoDialog = false
    },
    confirmUserInfoDialog () {
      this.isShowUserInfoDialog = false
    },
    showModifyUserDialog (userInfo) {
      // update group list
      this.queryGroupList()

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
            var indexOfGroupId = groupUserIdList.indexOf(this.groupList[index].groupId)
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
        this.userInfo.groupUserList = tempGroupUserList
        this.isShowModifyUserDialog = true
      }
    },
    closeModifyUserDialog () {
      this.isShowModifyUserDialog = false
    },
    confirmModifyUserDialog () {
      this.isShowModifyUserDialog = false
      this.queryCompanyUserList()
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
      // this.queryCompanyUserList()
    },
    showDisableUserDialog (userInfo) {
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

        this.disableUserMsg = '确定要停用用户 ' + userInfo.cnName
        this.isShowConfirmDisableUserDialog = true
      }
    },
    closeDisableUserDialog () {
      this.isShowConfirmDisableUserDialog = false
    },
    cancelDisableUserDialog () {
      this.isShowConfirmDisableUserDialog = false
    },
    confirmDisableUserDialog () {
      // disable company user
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
          that.queryCompanyUserList()
        }
      })
      this.isShowConfirmDisableUserDialog = false
    },
    showUserListDialog () {
      // update group list
      this.queryGroupList()

      this.isShowUserListDialog = true
      // this.tipMsg = 'test'
      // this.showTips()
    },
    closeUserListDialog () {
      this.isShowUserListDialog = false
    }
  }
}
</script>

<style scoped>
a:hover,
a:visited,
a:link,
a:active {
  text-decoration: none;
  color: rgba(0, 117, 255, 1);
}
.hr-style {
  background-color: #e5e5e5;
  border: 0;
  height: 1px;
}
.hr-divider {
  background-color: #e5e5e5;
  border: 0;
  height: 1px;
  margin-left: 30px;
  margin-right: 10px;
}
.user-manager {
  width: 260px;
  height: 1020px;
  background-color: rgba(255, 255, 255, 1);
}
/* .title {
  display: flex;
  align-content: center;
  height: 30px;
  padding: 30px;
  line-height: 31px;
  color: rgba(80, 80, 80, 1);
  font-size: 30px;
  text-align: left;
  font-family: STHeitiSC-Medium;
} */
/* .title-text {
  margin-left: 10px;
} */
.menu-wrapper {
  text-align: left;
}
.menu-item-wrapper1 {
  text-align: left;
  margin-left: 30px;
}
.menu-item-wrapper2 {
  text-align: left;
  margin-left: 15px;
}
.menu-text {
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.right-side-bar-list-container {
  height: 880px;
  overflow: auto;
}
.right_side_bar_item {
  height: 30px;
  display: flex;
  flex-direction: row;
  text-align: left;
  padding-left: 30px;
  margin-top: 20px;
}
.right_side_bar_title {
  font-size: 20px;
  color: #505050;
  font-weight: bold;
}
.right_side_bar_btn_text {
  font-size: 16px;
  color: #369eef;
}
.right_side_bar_item_text {
  display: block;
  overflow: hidden;
  width: 110px;
  white-space: nowrap;
  text-overflow: ellipsis;
  color: rgba(80, 80, 80, 1);
  font-size: 16px;
  font-family: PingFangSC-Light;
}
.btn-edit {
  margin-left: 10px;
}
.btn-resetpasswd {
  margin-left: 15px;
}
.btn-disable {
  margin-left: 15px;
  margin-right: 10px;
}
</style>
