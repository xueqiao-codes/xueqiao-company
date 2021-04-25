<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-service">
      <div class="dialog-header">
        <div class="dialog-title">订单提交成功</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-service-content">

          <div class="dialog-item">
            <span>您的支付订单已经创建，请在新打开的页面完成支付操作。</span>
          </div>
          <div class="dialog-item">
            <span class="hosted_service_key attention-text">如果浏览器没打开新窗口，请检查浏览器设置，或</span>
            <a href="#" @click="payOrderAgain"><span>点击此处继续完成支付</span></a>
          </div>
        <div>
          <button class="g-button button-item" @click="gotoHostingPage">支付完成，返回交易云服务页</button>
        </div>
        <div class="dialog-item">
          <div class="gerneral_margin_10px">
            <span class="hosted_service_key">如果遇到支付问题，您可以</span>
            <!-- <a href="#" @click="gotoOrderPage"><span>订单列表</span></a>
            <span>尝试重新支付，或</span> -->
            <a href="#" @click="showCustomServiceDialog"><span>联系客服</span></a>
          </div>
        </div>
      </div>
    </div>
    <d-custom-service v-bind:isShow="isShowCustomServiceDialog" @close="closeCustomServiceDialog" @confirm="confirmCustomService"></d-custom-service>
  </div>
</template>

<script>
import DCustomServiceDialog from './CustomServiceDialog'
export default {
  props: ['isShow', 'msg', 'orderId'],
  components: {
    'd-custom-service': DCustomServiceDialog
  },
  data () {
    return {
      // dialog
      isShowCustomServiceDialog: false
    }
  },
  methods: {
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      this.$emit('confirm')
    },
    showCustomServiceDialog () {
      this.isShowCustomServiceDialog = true
    },
    closeCustomServiceDialog () {
      this.isShowCustomServiceDialog = false
    },
    confirmCustomService () {
      this.isShowCustomServiceDialog = false
    },
    gotoHostingPage () {
      this.$emit('confirm')
      this.$router.push('/personal/hosting')
    },
    // gotoOrderPage () {
    //   this.$emit('confirm')
    //   this.$router.push('/manager/order')
    // },
    payOrderAgain () {
      const {href} = this.$router.resolve({
        name: 'ConfirmPersonalUserOrderPage',
        path: '/confirmPersonalUserOrder'
      })
      window.open(href + '?orderId=' + this.orderId, '_blank')
    }
  }
}
</script>

<style scoped>
  .d-custom-service {
    width: 720px;
  }
  .custom-service-content {
    text-align: center;
  }
  .margin-top-10px {
    margin-top: 10px;
  }
  .dialog-item {
    text-align: left;
    display: flex;
    align-items: center;
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .button-item {
    width: 340px;
  }
</style>
