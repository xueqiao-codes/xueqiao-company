<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-service">
      <div class="dialog-header">
        <div class="dialog-title">重置密码</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-service-content">
        <div>
          <span>是否要重置用户 {{userInfo.cnName}} 的密码？</span>
        </div>
        <div class="local-attention-text">
          <span class="attention-text">重置成功后将会以邮件的形式通知用户。</span>
        </div>
      </div>
      <div class="dialog-button-container">
        <button class="g-button dialog-small-button dialog-local-button" @click="confirm">确定</button>
        <button class="g-button-white dialog-small-button dialog-local-button" @click="cancel">取消</button>
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
      // dialog
      isShowTips: false,
      tipMsg: '',
      executeSuccess: false
    }
  },
  methods: {
    showTips () {
      this.executeSuccess = false
      this.isShowTips = true
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
      // this.tipMsg = '接口未接上'
      // this.showTips()
      // this.executeSuccess = true
      this.doReset()
    },
    cancel () {
      this.$emit('close')
    },
    doReset () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'ResetCompanyUserPasswd')
      // params.append('companyUser', JSON.stringify(this.userInfo))
      var params = {
        'companyUser' : JSON.stringify(this.userInfo)
      }
      this.$http.post('ResetCompanyUserPasswd', params, function (response) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.tipMsg = '重置成功'
          that.showTips()
          that.executeSuccess = true
          that.$eventBus.$emit('updateGroupUser')
        }
      })
    }
  }
}
</script>

<style scoped>
.d-custom-service {
  width: 520px;
}
.custom-service-content {
  text-align: center;
}
.margin-top-10px {
  margin-top: 10px;
}
.dialog-local-button {
  margin-left: 10px;
  margin-right: 10px;
}
.local-attention-text {
  margin-top: 10px;
  line-height: 16px;
  opacity: 0.75;
  font-size: 16px;
  text-align: center;
  font-family: STHeitiSC-Light;
}
</style>
