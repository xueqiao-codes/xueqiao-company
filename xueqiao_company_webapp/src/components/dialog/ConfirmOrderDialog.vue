<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">确认支付</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div class="dialog-item g-form-item">
            <div class="dialog-item-key g-form-label">
                <span>订单信息</span>
            </div>
        </div>
        <div>
          <div class="dialog-sub-item">
            <div class="dialog-sub-item-key g-form-label">
                <span>名称</span>
            </div>
            <div class="dialog-sub-item-value">
              {{orderInfo.hostedServiceName}}
            </div>
          </div>
          <div class="dialog-sub-item">
            <div class="dialog-sub-item-key g-form-label">
                <span>类型</span>
            </div>
            <div class="dialog-sub-item-value">
              {{orderInfo.orderTypeCN}}
            </div>
          </div>
          <div class="dialog-sub-item">
            <div class="dialog-sub-item-key g-form-label">
                <span>服务规格</span>
            </div>
            <div class="dialog-sub-item-value">
              {{orderInfo.serviceSpecStr}}
            </div>
          </div>
          <div class="dialog-sub-item">
            <div class="dialog-sub-item-key g-form-label">
                <span>服务时长</span>
            </div>
            <div class="dialog-sub-item-value">
              {{orderInfo.serviceTime}}
            </div>
          </div>
        </div>
        <div class="dialog-item g-form-item">
            <div class="dialog-item-key g-form-label">
                <span>支付金额</span>
            </div>
            <div class="dialog-item-value"><span>￥{{orderInfo.totalAmount}}</span></div>
        </div>
        <div class="dialog-item g-form-item">
            <div class="dialog-item-key g-form-label">
                <span>支付方式</span>
            </div>
            <select class="g-form-value dialog-item-value" v-model="orderInfo.payType">
                <option value="ALI_PAY">支付宝</option>
                <option value="INNER">雪橇币</option>
            </select>
        </div>
      </div>
      <div class="dialog-button-container">
        <button class="g-button dialog-button" @click="confirm">确定</button>
      </div>
    </div>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips"  @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
// import DConfirmDialog from './ConfirmDialog'
import DTips from '../dialog/TipsDialog'
export default {
  props: ['isShow', 'orderInfo'],
  components: {
    'd-tips': DTips
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      closeThisDialog: false
    }
  },
  methods: {
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      // this.$emit('confirm')
      this.submitOrder()
      // this.showSubmitOrderSuccessDialog()
    },
    showTips () {
      this.isShowTips = true
      this.closeThisDialog = true
    },
    closeTips () {
      if (this.closeThisDialog) {
        this.$emit('confirm')
      }
      this.isShowTips = false
    },
    confirmTips () {
      if (this.closeThisDialog) {
        this.$emit('confirm')
      }
      this.isShowTips = false
    },
    submitOrder () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'SubmitOrder')
      // params.append('payType', this.orderInfo.payType)
      // params.append('productPurpose', this.orderInfo.productPurpose)
      // params.append('groupName', this.orderInfo.hostedServiceName)
      // params.append('tradeType', this.orderInfo.serviceType)
      // params.append('productId', this.orderInfo.productId)
      // params.append('groupId', this.orderInfo.groupId)
      // params.append('totalAmount', this.orderInfo.totalAmount)
      // params.append('quantity', this.orderInfo.quantity)
      // params.append('serviceDays', this.orderInfo.serviceTime)
      var params = {
        'payType' : this.orderInfo.payType,
        'productPurpose' : this.orderInfo.productPurpose,
        'groupName' : this.orderInfo.hostedServiceName,
        'tradeType' : this.orderInfo.serviceType,
        'productId' : this.orderInfo.productId,
        'groupId' : this.orderInfo.groupId,
        'totalAmount' : this.orderInfo.totalAmount,
        'quantity' : this.orderInfo.quantity,
        'serviceDays' : this.orderInfo.serviceTime
      }
      this.$http.post('SubmitOrder', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          if (that.orderInfo.payType === 'INNER') {
            if (response.value < 1) {
              that.tipMsg = '支付失败，请联系客服'
              that.showTips()
              that.closeThisDialog = true
            } else {
              that.tipMsg = '支付成功'
              that.showTips()
              that.closeThisDialog = true
            }
          } else {
            var orderId = response.value
            that.$store.commit('setOrderId', orderId)
            that.payOrder(orderId)
            that.$emit('confirm')
          }
        }
      })
    },
    payOrder (orderId) {
      // 另起一页
      // var url = this.$XQEnv.baseUrl + 'pay.jsp?orderId=' + orderId
      var url = this.$XQEnv.baseUrl + 'pay?orderId=' + orderId
      this.$commonFunc.openNewWin(url)
    }
  }
}
</script>

<style scoped>
  .d-custom-dialog {
    width: 520px;
  }
  .custom-dialog-content {
    text-align: left;
  }
  .dialog-item {
    text-align: left;
    display: flex;
    align-items: center;
    /* justify-content: flex-start; */
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
    width: 250px;
  }
  .dialog-sub-item {
    text-align: left;
    display: flex;
    align-items: center;
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .dialog-sub-item-key {
    width: 120px;
    height: 16px;
    line-height: 16px;
    margin-left: 10px;
    color: rgba(144, 144, 144, 1);
    font-size: 14px;
    text-align: left;
    font-family: STHeitiSC-Light;
  }
  .dialog-sub-item-value {
    width: 300px;
    height: 16px;
    line-height: 16px;
    color: rgba(144, 144, 144, 1);
    font-size: 16px;
    text-align: left;
    font-family: STHeitiSC-Light;
  }
  .dialog-button {
    width: 220px;
  }
</style>
