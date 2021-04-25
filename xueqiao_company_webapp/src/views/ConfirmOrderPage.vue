<template>
  <div class="page">
    <div class="container" v-if="isShowOrderInfo">
      <div class="dialog-header">
        <div class="title">确认支付</div>
      </div>
      <div class="content">
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
              {{orderDetail.companyGroupName}}
            </div>
          </div>
          <div class="dialog-sub-item">
            <div class="dialog-sub-item-key g-form-label">
              <span>类型</span>
            </div>
            <div class="dialog-sub-item-value">
              {{orderDetail.productProposeCn}}
            </div>
          </div>
          <div class="dialog-sub-item">
            <div class="dialog-sub-item-key g-form-label">
              <span>服务规格</span>
            </div>
            <div class="dialog-sub-item-value">
              {{orderDetail.productTypeCn}}
            </div>
          </div>
          <div class="dialog-sub-item">
            <div class="dialog-sub-item-key g-form-label">
              <span>服务时长</span>
            </div>
            <div class="dialog-sub-item-value">
              {{orderDetail.serviceDays}}天
            </div>
          </div>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">
            <span>支付金额</span>
          </div>
          <div class="dialog-item-value"><span>￥{{orderDetail.totalAmount}}</span></div>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">
            <span>支付方式</span>
          </div>
          <select class="g-form-value dialog-item-value" v-model="payType">
            <option value="ALI_PAY">支付宝</option>
            <option value="INNER">雪橇币</option>
          </select>
        </div>
      </div>
      <div class="dialog-button-container">
        <button class="g-button dialog-button" @click="confirmPayment">确定</button>
      </div>
    </div>
    <div v-else>
      <div class="error-icon-container">
        <img src="../assets/icon_error.png">
      </div>
      <div class="error-msg-container">
        <span>{{errorMsg}}</span>
      </div>
      <div class="operation-container">
        <router-link class="nav-menu-item-inner" to="/manager/order">返回我的订单页面</router-link>
      </div>
    </div>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DTips from '../components/dialog/TipsDialog'
export default {
  components: {
    'd-tips': DTips
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      // order
      isShowOrderInfo: true,
      orderId: 0,
      orderDetail: {},
      payType: 'ALI_PAY',
      payResult: null,
      errorMsg: ''
    }
  },
  mounted () {
    this.orderId = this.$route.query.orderId
    this.getOrderDetail()
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
    getOrderDetail () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'getOrderDetail')
      // params.append('orderId', this.orderId)
      var params = {
        'orderId' : this.orderId
      }
      this.$http.post('getOrderDetail', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          // that.tipMsg = response.errorMsg
          // that.showTips()
          that.errorMsg = '抱歉，' + response.errorMsg
          that.isShowOrderInfo = false
        } else if (response.errorInfo) {
          // that.tipMsg = response.errorInfo.errMsg
          // that.showTips()
          that.errorMsg = '抱歉，' + response.errorInfo.errMsg
          that.isShowOrderInfo = false
        } else {
          that.orderDetail = response.value
          if (that.orderDetail) {
            if (that.orderDetail.status === 'CREATED') {
              that.isShowOrderInfo = true
            } else if (that.orderDetail.status === 'PAY_SUCCESS' ||
                      that.orderDetail.status === 'SYSTEM_PROCESSED' ||
                      that.orderDetail.status === 'MANUAL_PROCESSING' ||
                      that.orderDetail.status === 'SUCCESS') {
              that.errorMsg = '订单已支付，不要重复支付'
              that.isShowOrderInfo = false
            } else if (that.orderDetail.status === 'CANCELLED') {
              that.errorMsg = '抱歉，订单已取消'
              that.isShowOrderInfo = false
            } else if (that.orderDetail.status === 'REMOVED') {
              that.errorMsg = '抱歉，订单已删除'
              that.isShowOrderInfo = false
            } else if (that.orderDetail.status === 'EXPIRED') {
              that.errorMsg = '抱歉，订单已过期'
              that.isShowOrderInfo = false
            }
          } else {
            that.errorMsg = '抱歉，订单无效'
            that.isShowOrderInfo = false
          }
        }
      })
    },
    confirmPayment () {
      if (this.payType === 'INNER') {
        this.payByXCoin()
      } else if (this.payType === 'ALI_PAY') {
        this.payByAlipay()
      }
    },
    payByXCoin () {
      if (!this.orderDetail) {
        return
      }
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'PayOrderWIthXCoin')
      // params.append('orderId', this.orderDetail.orderId)
      var params = {
        'orderId' : this.orderDetail.orderId
      }
      this.$http.post('PayOrderWIthXCoin', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.payResult = response.value
          that.gotoAlipayResultPage(that.payResult.totalAmount, that.payResult.orderId)
        }
      })
    },
    payByAlipay () {
      if (!this.orderDetail) {
        return
      }
      // var url = this.$XQEnv.baseUrl + 'pay.jsp?orderId=' + this.orderDetail.orderId
      var url = this.$XQEnv.baseUrl + 'pay?orderId=' + this.orderDetail.orderId
      this.$commonFunc.openNewPage(url)
    },
    gotoAlipayResultPage (totalAmount, tradeNo) {
      this.$router.push('/alipayresult?total_amount=' + totalAmount + '&out_trade_no=' + tradeNo)
    }
  }
}
</script>

<style scoped>
.page {
  left: 0;
  top: 0;
  height: 1020px;
  width: 100vw;
  min-width: 1920px;
  min-height: 900px;
  background-color: rgba(255, 255, 255, 1);
  display: flex;
  align-items: center;
  flex-direction: column;
}
.container {
  margin-top: 30px;
  width: 700px;
  height: 290px;
  /* background-color: rgba(229, 255, 229, 1); */
}
.title {
  margin-left: 100px;
  margin-top: 30px;
  line-height: 25px;
  color: rgba(80, 80, 80, 1);
  font-size: 24px;
  text-align: left;
  font-family: STHeitiSC-Medium;
}
.content {
  margin-left: 100px;
  margin-right: 100px;
  margin-top: 30px;
  margin-bottom: 30px;
  /* background: green; */
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
  /* .errorinfo-container { */
    /* margin-top: 124px; */
  /* } */
  .error-icon-container {
  margin-top: 124px;
  /* width: 96px;
  height: 96px; */
}
.error-msg-container {
  margin-top: 30px;
  line-height: 37px;
  color: rgba(144, 144, 144, 1);
  font-size: 36px;
  text-align: left;
  font-family: STHeitiSC-Medium;
}
.operation-container {
  margin-top: 30px;
}
</style>
