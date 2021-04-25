<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container">
      <div class="dialog-header">
        <div class="dialog-title">管理用户组</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <table>
          <thead>
            <tr bgColor=#F8FCFF>
              <th width="47"></th>
              <th width="190">用户名</th>
              <th width="210">姓名</th>
              <th width="425">手机</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(companyUser,index) in userList" v-bind:key="index">
              <td width="47" align="center"><input type="checkbox" v-bind:value="companyUser.userId" v-model="groupUserIdList"></td>
              <td width="190" align="center">{{companyUser.userName}}</td>
              <td width="210" align="center">{{companyUser.cnName}}</td>
              <td width="425" align="center">{{companyUser.tel}}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="dialog-button-container">
        <button class="g-button dialog-big-button" @click="confirm">确定</button>
      </div>
    </div>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DTips from './TipsDialog'
export default {
  props: ['isShow', 'hosting', 'userList', 'groupUserIdList'],
  components: {
    'd-tips': DTips
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      modifySuccess: false
    }
  },
  watch: {
    isShow () {
      if (this.isShow) {
        // try token
        this.$commonFunc.tryToken()
      }
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
        this.$emit('confirm')
      }
    },
    confirmTips () {
      this.isShowTips = false
      if (this.modifySuccess) {
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
      var that = this
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
          that.tipMsg = '设置成功'
          that.showTips()
          // that.$store.commit('increaseCompanyUserVersion')
          that.modifySuccess = true
          that.$eventBus.$emit('updateGroupUser')
        }
      })
    }
  }
}
</script>

<style scoped>
.custom-dialog-content {
  text-align: left;
}
.hosting-item-value {
  width: 90px;
  margin-left: 10px;
}
table tbody {
  display:block;
  width: 884px;
  max-height:500px;
  overflow-y:auto;
  overflow-x:hidden;
}
table thead, tbody tr {
  display:table;
  /* width:100%;   */
  width: 847px;
  table-layout:fixed;
}
table thead {
  width: 847px;
  /* width: calc( 100% - 1em )   */
}
table,td,th {
  border: 1px solid #e5e5e5;
}
</style>
