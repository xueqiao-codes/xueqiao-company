<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">订单详情</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div class="dialog-sub-item">
          <div class="dialog-sub-item-key g-form-label">
              <span>创建时间</span>
          </div>
          <div class="dialog-sub-item-value">
            {{orderDetail.createTimestamp}}
          </div>
        </div>
        <div class="dialog-sub-item">
          <div class="dialog-sub-item-key g-form-label">
              <span>雪橇订单号</span>
          </div>
          <div class="dialog-sub-item-value">
            {{orderDetail.orderId}}
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
              <span>支付金额</span>
          </div>
          <div class="dialog-sub-item-value">
            ￥{{orderDetail.totalAmount}}
          </div>
        </div>
        <div class="dialog-sub-item">
          <div class="dialog-sub-item-key g-form-label">
              <span>订单状态</span>
          </div>
          <div class="dialog-sub-item-value">
            {{orderDetail.statusCn}}
          </div>
        </div>
        <div class="dialog-sub-item">
          <div class="dialog-sub-item-key g-form-label">
              <span>支付时间</span>
          </div>
          <div class="dialog-sub-item-value">
            <span v-if="orderDetail.payTimestamp == null || orderDetail.payTimestamp == ''">-</span>
            <span v-else>{{orderDetail.payTimestamp}}</span>
          </div>
        </div>
        <div class="dialog-sub-item">
          <div class="dialog-sub-item-key g-form-label">
              <span>支付方式</span>
          </div>
          <div class="dialog-sub-item-value">
            {{orderDetail.payTypeCn}}
          </div>
        </div>
        <div class="dialog-sub-item">
          <div class="dialog-sub-item-key g-form-label">
              <span>第三方支付单号</span>
          </div>
          <div class="dialog-sub-item-value">
            <span v-if="orderDetail.thirdPartyOrderNo == null || orderDetail.thirdPartyOrderNo == ''">-</span>
            <span v-else>{{orderDetail.thirdPartyOrderNo}}</span>
          </div>
        </div>
        <div class="hosting-info-content">
          <div class="dialog-sub-item">
            <div class="dialog-sub-item-key g-form-label">
                <span>交易托管服务</span>
            </div>
            <div class="dialog-sub-item-value">
              {{orderDetail.companyGroupName}}
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
              <span v-if="orderDetail.serviceDays == null">-</span>
              <span v-else>{{orderDetail.serviceDays}}天</span>
            </div>
          </div>
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
import DTips from '../dialog/TipsDialog'
export default {
  props: ['isShow', 'order'],
  components: {
    'd-tips': DTips
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      // closeThisDialog: false
      // data
      orderDetail: []
    }
  },
  watch: {
    isShow () {
      if (this.isShow) {
        this.queryOrderDetail()
      }
    }
  },
  methods: {
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      this.$emit('confirm')
    },
    showTips () {
      this.isShowTips = true
    },
    closeTips () {
      this.isShowTips = false
    },
    confirmTips () {
      this.isShowTips = false
    },
    queryOrderDetail () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'getOrderDetail')
      // params.append('orderId', this.order.orderId)
      var params = {
        'orderId' : this.order.orderId
      }
      this.$http.post('getOrderDetail', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.orderDetail = response.value
        }
      })
    }
  }
}
</script>

<style scoped>
  .d-custom-dialog {
    /* width: 520px; */
  }
  .custom-dialog-content {
    text-align: left;
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
  .dialog-sub-item {
    text-align: left;
    display: flex;
    align-items: center;
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .dialog-sub-item-key {
    width: 140px;
    height: 16px;
    line-height: 16px;
    /* margin-left: 10px; */
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
  .hosting-info-content {
    margin-top: 30px;
  }
</style>
