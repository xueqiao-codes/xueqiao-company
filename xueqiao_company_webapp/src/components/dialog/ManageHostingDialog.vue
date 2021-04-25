<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">{{hosting.groupName}} (ID:{{hosting.groupId}})</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div class="hosting-title-item">
          <div>
            <span class="hint_font">交易环境:</span>
            <span class="hint_font">{{hosting.serviceTypeCn}}</span>
          </div>
          <div>
            <span class="hint_font">规格:</span>
            <span class="hint_font">{{hosting.specNameCn}}</span>

            <a href="#" @click="showUpgradeHostingDialog"><span class="link_hint_font">变更规格</span></a>
          </div>
          <div>
            <span class="hint_font">到期时间:</span>
            <span class="hint_font">{{hosting.expiredTimestamp}}</span>

            <a href="#" @click="showRechargeHostingDialog"><span class="link_hint_font">续费</span></a>
          </div>

        </div>

        <div class="hosting-item">
          <div class="g-form-label">用户组</div>
          <a href="#" @click="showManageGroupUserDialog()"><span class="operate-item">管理用户组</span></a>
        </div>
        <table class="inner-table">
            <tr bgColor=#F8FCFF>
              <th width="150">用户名称</th>
              <th width="150">姓名</th>
              <th width="200">手机</th>
              <th width="173">{{$string.operatorRoleText}}</th>
              <th width="150">操作</th>
            </tr>
            <tr v-for="(groupUser,index) in groupUserList" v-bind:key="index">
              <td align="center">{{groupUser.userName}}</td>
              <td align="center">{{groupUser.cnName}}</td>
              <td align="center">{{groupUser.tel}}</td>
              <td align="center">{{groupUser.cnRole}}</td>
              <td align="center">
                <a href="#" @click="showModifyGroupUserRoleDialog(groupUser)">设置权限</a>
                &nbsp;&nbsp;
                <a href="#" @click="showRemoveGroupUserDialog(groupUser)">移除</a>
              </td>
            </tr>
        </table>
      </div>
      <div class="dialog-button-container">
        <button class="g-button dialog-button" @click="confirm">确定</button>
      </div>
    </div>
    <d-modify-user-role v-bind:isShow="isShowModifyUserRoleDialog" v-bind:hosting="hosting" v-bind:groupUser="modifyGroupUser" @close="closeModifyUserRoleDialog" @confirm="confirmModifyUserRole"></d-modify-user-role>
    <d-remove-user v-bind:isShow="isShowRemoveGroupUserDialog" title="移除组用户" v-bind:msg="removeGroupUserDialogMsg" @close="closeRemoveGroupUserDialog" @cancel="cancelRemoveGroupUser" @confirm="confirmRemoveGroupUser"></d-remove-user>
    <d-manage-group-user v-bind:isShow="isShowManageGroupUserDialog" v-bind:hosting="hosting" v-bind:userList="companyUserPage" v-bind:groupUserIdList="groupUserIdList" @close="closeManageGroupUserDialog" @confirm="confirmManageGroupUser"></d-manage-group-user>
    <d-upgrade-hosting v-bind:isShow="isShowUpgradeHostingDialog" v-bind:hosting="hosting" v-bind:hostingProductList="upgradeHostingProductList" @close="closeUpgradeHostingDialog" @confirm="confirmUpgradeHosting"></d-upgrade-hosting>
    <d-recharge-hosting v-bind:isShow="isShowRechargeHostingDialog" v-bind:hosting="hosting" v-bind:hostingProductList="rechargeHostingProductList" @close="closeRechargeHostingDialog" @confirm="confirmRechargeHosting"></d-recharge-hosting>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DModifyUserRoleDialog from './ModifyUserRoleDialog'
import DConfirmWithCancelDialog from './ConfirmWithCancelDialog'
import DManageGroupUserDialog from './ManageGroupUserDialog'
import DUpgradeHostingDialog from './UpgradeHostingDialog'
import DRechargeHostingDialog from './RechargeHostingDialog'
import DTips from './TipsDialog'
export default {
  props: ['isShow', 'hosting', 'hostingProductList'],
  components: {
    'd-tips': DTips,
    'd-modify-user-role': DModifyUserRoleDialog,
    'd-remove-user': DConfirmWithCancelDialog,
    'd-manage-group-user': DManageGroupUserDialog,
    'd-upgrade-hosting': DUpgradeHostingDialog,
    'd-recharge-hosting': DRechargeHostingDialog
  },
  watch: {
    isShow () {
      if (this.isShow) {
        this.queryCompanyGroupUsers()
      }
    }
  },
  data () {
    return {
      selectedProductDetailList: null,
      selectedProductDetail: {
        productId: null,
        price: null,
        currencyType: null,
        timeIncrement: null
      },
      // dialog
      isShowTips: false,
      tipMsg: '',
      modifySuccess: false,
      isShowModifyUserRoleDialog: false,
      isShowRemoveGroupUserDialog: false,
      removeGroupUserDialogMsg: '',
      isShowManageGroupUserDialog: false,
      isShowUpgradeHostingDialog: false,
      isShowRechargeHostingDialog: false,
      // data
      groupUserList: null,
      // modify group user role
      modifyGroupUser: null,
      // remove group user
      removeGroupUser: null,
      // manage group user
      companyUserPage: null,
      groupUserIdList: [],
      // upgrade hosting
      // upgradeHosting: null,
      upgradeHostingProductList: [],
      // recharge hosting
      // rechargeHosting: null,
      rechargeHostingProductList: []
    }
  },
  methods: {
    showTips () {
      this.isShowTips = true
      this.modifySuccess = false
    },
    closeTips () {
      this.isShowTips = false
      if (this.modifySuccess) {
        this.queryCompanyGroupUsers()
        this.isShowRemoveGroupUserDialog = false
      }
    },
    confirmTips () {
      this.isShowTips = false
      if (this.modifySuccess) {
        this.queryCompanyGroupUsers()
        this.isShowRemoveGroupUserDialog = false
      }
    },
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      this.$emit('confirm')
    },
    queryCompanyUserList () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'GetCompanyUsers')
      var params = {}
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
    queryUpgradeProductList (result) {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'GetUpgradeSpecInfo')
      // params.append('groupId', this.hosting.groupId)
      var params = {
        'groupId' : this.hosting.groupId
      }
      this.$http.post('GetUpgradeSpecInfo', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.upgradeHostingProductList = response.results
          result()
        }
      })
    },
    queryRechargeProductList (result) {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'GetRechargeTimeProducts')
      // params.append('groupId', this.hosting.groupId)
      var params = {
        'groupId' : this.hosting.groupId
      }
      this.$http.post('GetRechargeTimeProducts', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.rechargeHostingProductList = response.results
          result()
        }
      })
    },
    queryCompanyGroupUsers () {
      var that = this
      var groupId = this.hosting.groupId
      // var params = new URLSearchParams()
      // params.append('method', 'GetCompanyGroupUsers')
      // params.append('groupId', groupId)
      var params = {
        'groupId' : groupId
      }
      this.$http.post('GetCompanyGroupUsers', params, function (response) {
        // alert('queryCompanyGroupUsers : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.groupUserList = response.value.page
        }
      })
    },
    typeTabToggle (typeTabIndex) {
      this.hosting.typeTabIndex = typeTabIndex
      //   alert('type toggle ' + typeTabIndex)
    },
    specTabToggle (specTabIndex) {
      // alert('specTabIndex : ' + specTabIndex);
      this.hosting.specTabIndex = specTabIndex
      if (specTabIndex < this.createHostingProductList.length) {
        this.selectedProductDetailList = this.createHostingProductList[specTabIndex].productNewDetailList
        this.selectedProductDetail = this.selectedProductDetailList[0]
      } else {
        // alert('specTabToggle : ' + specTabIndex)
      }
    },
    toggleCss (isActive) {
      if (isActive) {
        return 'item-active'
      } else {
        return 'item-inactive'
      }
    },
    showModifyGroupUserRoleDialog (groupUser) {
      this.modifyGroupUser = {
        userName: groupUser.userName,
        groupId: groupUser.groupId,
        userId: groupUser.userId,
        role: groupUser.role
      }
      this.isShowModifyUserRoleDialog = true
    },
    closeModifyUserRoleDialog () {
      this.isShowModifyUserRoleDialog = false
    },
    confirmModifyUserRole () {
      this.queryCompanyGroupUsers()
      this.isShowModifyUserRoleDialog = false
    },
    showRemoveGroupUserDialog (groupUser) {
      this.removeGroupUserDialogMsg = '确定要移除组用户 ' + groupUser.userName + ' ?'
      this.removeGroupUser = groupUser
      this.isShowRemoveGroupUserDialog = true
    },
    closeRemoveGroupUserDialog () {
      this.isShowRemoveGroupUserDialog = false
    },
    cancelRemoveGroupUser () {
      this.isShowRemoveGroupUserDialog = false
    },
    confirmRemoveGroupUser () {
      var that = this
      // get remaining group user list
      this.groupUserIdList = []
      if (this.groupUserList.length > 0) {
        this.groupUserList.forEach(function (item, index) {
          if (that.removeGroupUser.userId !== item.userId) {
            that.groupUserIdList.push(item.userId)
          }
        })
      }
      // do modify
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyGroupUsers')
      // params.append('groupId', this.hosting.groupId)
      // params.append('groupUserIdList', JSON.stringify(this.groupUserIdList))
      var params = {
        'groupId' : this.hosting.groupId,
        'groupUserIdList' : JSON.stringify(this.groupUserIdList)
      }
      this.$http.post('UpdateCompanyGroupUsers', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.tipMsg = '移除成功'
          that.showTips()
          that.modifySuccess = true
        }
      })
    },
    showManageGroupUserDialog () {
      if (this.hosting.hostingServiceStatus === 'EXPIRED') {
        this.tipMsg = '托管机已过期，不能进行用户操作'
        this.showTips()
        return
      } else if (this.hosting.hostingServiceStatus === 'RELEASED') {
        this.tipMsg = '托管机已释放，不能进行用户操作'
        this.showTips()
        return
      }

      this.queryCompanyUserList()
      this.groupUserIdList = []
      var that = this
      if (this.groupUserList.length > 0) {
        this.groupUserList.forEach(function (item, index) {
          that.groupUserIdList.push(item.userId)
        })
      } else {
        this.groupUserIdList = []
      }
      this.isShowManageGroupUserDialog = true
    },
    closeManageGroupUserDialog () {
      this.isShowManageGroupUserDialog = false
    },
    confirmManageGroupUser () {
      this.queryCompanyGroupUsers()
      this.isShowManageGroupUserDialog = false
    },
    showUpgradeHostingDialog () {
      if (this.hosting.hostingServiceStatus === 'WAITING' ||
          this.hosting.hostingServiceStatus === 'OPENING') {
        this.tipMsg = '托管机开通中，不能进行变更规格操作'
        this.showTips()
        return
      } else if (this.hosting.hostingServiceStatus === 'UPGRADING') {
        this.tipMsg = '托管机升级中，不能进行变更规格操作'
        this.showTips()
        return
      } else if (this.hosting.hostingServiceStatus === 'EXPIRED') {
        this.tipMsg = '托管机已过期，不能进行变更规格操作'
        this.showTips()
        return
      } else if (this.hosting.hostingServiceStatus === 'RELEASED') {
        this.tipMsg = '托管机已释放，不能进行变更规格操作'
        this.showTips()
        return
      }

      this.upgradeHosting = {
        hostedServiceName: '',
        orderType: null,
        productId: 0,
        typeTabIndex: 0,
        specTabIndex: 0,
        serviceTypeStr: null,
        specStr: null,
        timeIncrement: 0,
        price: 0
      }
      var that = this
      this.queryUpgradeProductList(function () {
        if (that.upgradeHostingProductList && that.upgradeHostingProductList.length > 0) {
          that.isShowUpgradeHostingDialog = true
        } else {
          that.tipMsg = '没有更高规格的产品，不能升级'
          that.showTips()
        }
      })
    },
    closeUpgradeHostingDialog () {
      this.isShowUpgradeHostingDialog = false
    },
    confirmUpgradeHosting () {
      // alert('confirmUpgradeHosting , emit afterpay')
      this.$emit('afterPay')
      this.isShowUpgradeHostingDialog = false
    },
    showRechargeHostingDialog () {
      if (this.hosting.hostingServiceStatus === 'WAITING' ||
          this.hosting.hostingServiceStatus === 'OPENING') {
        this.tipMsg = '托管机开通中，不能进行续费操作'
        this.showTips()
        return
      } else if (this.hosting.hostingServiceStatus === 'UPGRADING') {
        this.tipMsg = '托管机升级中，不能进行续费操作'
        this.showTips()
        return
      } else if (this.hosting.hostingServiceStatus === 'RELEASED') {
        this.tipMsg = '托管机已释放，不能进行续费操作'
        this.showTips()
        return
      }

      this.rechargeHostingProductList = []
      var that = this
      this.queryRechargeProductList(function () {
        if (that.rechargeHostingProductList && that.rechargeHostingProductList.length > 0) {
          that.isShowRechargeHostingDialog = true
        } else {
          that.tipMsg = '没有续费服务，请联系客服'
          that.showTips()
        }
      })
    },
    closeRechargeHostingDialog () {
      this.isShowRechargeHostingDialog = false
    },
    confirmRechargeHosting () {
      this.$emit('afterPay')
      this.isShowRechargeHostingDialog = false
    }
  }
}
</script>

<style scoped>
.d-custom-dialog {
  width: 1027px;
}
.custom-dialog-content {
  text-align: left;
}
.margin-top-10px {
  margin-top: 10px;
}
.dialog-title-item {
  text-align: left;
  display: flex;
  justify-content: space-between;
}
.dialog-item {
  text-align: left;
  display: flex;
  align-items: center;
}
.dialog-button {
  width: 320px;
}
.hosting-title-item {
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: left;
  display: flex;
  align-items: center;
  justify-content: space-between;
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
.inner-table {
  width: 828px;
}
.operate-item {
  margin-left: 10px;
}
</style>
