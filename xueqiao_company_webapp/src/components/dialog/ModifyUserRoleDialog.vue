<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">设置权限</div>
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
              <div class="dialog-item-value">{{groupUser.userName}}</div>
          </div>
          <div class="dialog-item g-form-item">
              <div class="dialog-item-key g-form-label">
                  <span>权限</span>
              </div>
              <select class="dialog-item-value g-form-value" v-model="groupUser.role">
                  <option value="TRADER">交易员</option>
                  <option value="ADMIN">管理员</option>
                  <option v-if="$XQConfig.SHOW_OPERATOR_ROLE_OBSERVER" value="OBSERVER">观察员</option>
              </select>
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
import DTips from './TipsDialog'
export default {
  props: ['isShow', 'hosting', 'groupUser'],
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
      // params.append('method', 'UpdateCompanyGroupUser')
      // params.append('groupId', this.hosting.groupId)
      // params.append('groupUserId', this.groupUser.userId)
      // params.append('role', this.groupUser.role)
      var params = {
        'groupId' : this.hosting.groupId,
        'groupUserId' : this.groupUser.userId,
        'role' : this.groupUser.role
      }
      this.$http.post('UpdateCompanyGroupUser', params, function (response) {
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
          that.modifySuccess = true
          that.$eventBus.$emit('updateGroupUser')
        }
      })
    }
  }
}
</script>

<style scoped>
  .d-custom-dialog {
    width: 510px;
  }
  .custom-dialog-content {
    text-align: left;
  }
  /* .margin-top-10px {
    margin-top: 10px;
  } */
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
    width: 150px;
  }
  .dialog-button {
    width: 220px;
  }
  /* .dialog-item-hosting {
    align-items: flex-start;
  }
  .dialog-item-key-hosting {
    margin-top: 10px;
  }
  .dialog-item-value-hosting {
    height: 200px;
    overflow: auto;
  } */
  /* .hosting-item {
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
  } */
</style>
