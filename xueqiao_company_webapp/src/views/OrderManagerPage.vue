<template>
  <div class="fragment-manager">
    <div class="manager-title">
      <span class="manager-title-space"></span>
      <span class="manager-title-text">订单列表</span>
    </div>
    <div class="manager-content">
      <table class="manager-table">
        <tr bgColor=#F8FCFF>
          <th width="150">创建时间</th>
          <th width="100">订单号</th>
          <th width="100">类型</th>
          <th width="200">业务组</th>
          <th width="100">支付金额</th>
          <th width="100">订单状态</th>
          <th width="200">操作</th>
        </tr>
        <tr v-for="(order, index) in orderPage" v-bind:key="index">
          <td>{{order.createDay}}&nbsp;&nbsp;{{order.createTime}}</td>
          <td>{{order.orderId}}</td>
          <td>{{order.productProposeCn}}</td>
          <td>{{order.companyGroupName}}</td>
          <td>{{order.totalAmount}}</td>
          <td><span :class="getStatusClass(order.status)">{{order.statusCn}}</span></td>
          <td>
            <a href="#" @click="showOrderDetailDialog(order)" class="operate-show-detail operation-item">详情</a>&nbsp;
            <a href="#" v-if="isShowPayOrderButton(order.status)" class="operate-pay operation-item" @click="payOrder(order)">立即支付</a>
            <a href="#" v-if="isShowPayOrderButton(order.status)" class="operate-cancel operation-item" @click="cancelOrder(order)">取消订单</a>
            <a href="#" v-if="isShowRemoveOrderButton(order.status)" class="operate-remove operation-item" @click="removeOrder(order)">删除订单</a>
          </td>
        </tr>
      </table>
    </div>
    <el-pagination background layout="prev, pager, next" :total="totalNum" :page-size="10" :current-page="currentPage" @current-change="onPageChange">
    </el-pagination>
    <d-confirm v-bind:isShow="isShowConfirmDialog" v-bind:title="confirmDialogTitle" v-bind:msg="confirmDialogMsg" @close="closeConfirmDialog" @confirm="confirmConfirmDialog"></d-confirm>
    <d-order-detail v-bind:isShow="isShowOrderDetailDialog" v-bind:order="orderDetail" @close="closeOrderDetailDialog" @confirm="confirmOrderDetail"></d-order-detail>
    <d-submit-order-success v-bind:isShow="isShowSubmitOrderSuccessDialog" v-bind:orderId="confirmOrderId" @close="closeSubmitOrderSuccessDialog"  @confirm="confirmSubmitOrderSuccessDialog"></d-submit-order-success>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DConfirmDialog from '../components/dialog/ConfirmDialog'
import DOrderDetailDialog from '../components/dialog/OrderDetailDialog'
import DSubmitOrderSuccessDialog from '../components/dialog/SubmitOrderSuccessDialog'
import DTips from '../components/dialog/TipsDialog'
export default {
  components: {
    'd-tips': DTips,
    'd-confirm': DConfirmDialog,
    'd-order-detail': DOrderDetailDialog,
    'd-submit-order-success': DSubmitOrderSuccessDialog
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      isShowConfirmDialog: false,
      confirmDialogTitle: '',
      confirmDialogMsg: '',
      confirmDialogType: '',
      isShowOrderDetailDialog: false,
      isShowSubmitOrderSuccessDialog: false,
      // data
      totalNum: 0,
      totalPage: 1,
      currentPage: 1,
      orderPage: [],
      payOrderInfo: null,
      cancelOrderInfo: null,
      removeOrderInfo: null,
      orderDetail: null,
      // confirm order
      confirmOrderId: 0
    }
  },
  mounted () {
    if (this.$util.checkLoginState("/manager/order") != "company") {
      return;
    }
    this.queryOrderList()
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
    onPageChange (currentPage) {
      this.currentPage = currentPage
      this.queryOrderList()
      // alert(currentPage)
    },
    queryOrderList () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'GetOrders')
      // params.append('pageIndex', this.currentPage - 1)
      var params = {
        'pageIndex' : this.currentPage - 1
      }
      this.$http.post('GetOrders', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.totalNum = response.value.total
          that.totalPage = Math.floor((that.totalNum + 9) / 10)
          if (that.totalPage < 1) {
            that.totalPage = 1
          }
          that.orderPage = response.value.page
        }
      })
    },
    isShowPayOrderButton (status) {
      return status === 'CREATED'
    },
    isShowRemoveOrderButton (status) {
      return status === 'EXPIRED' || status === 'CANCELLED'
    },
    getStatusClass (status) {
      if (status === 'CREATED') {
        return 'status-unpay'
      } else if (
        status === 'CANCELLED' ||
        status === 'REMOVED' ||
        status === 'EXPIRED'
      ) {
        return 'status-invalid'
      } else {
        return 'status-pay'
      }
    },
    payOrder (order) {
      // this.payOrderInfo = order
      // this.confirmDialogTitle = '确认支付'
      // this.confirmDialogMsg = '是否确认支付？'
      // this.showConfirmDialog('pay')
      this.openNewPage(order)
      this.showSubmitOrderSuccessDialog(order)
    },
    openNewPage (order) {
      // this.$XQLocalStorage.setLocalStorage('order', JSON.stringify(orderConfirmInfo))
      const {href} = this.$router.resolve({
        name: 'ConfirmOrderPage',
        path: '/confirmOrder'
      })
      window.open(href + '?orderId=' + order.orderId, '_blank')
    },
    cancelOrder (order) {
      this.cancelOrderInfo = order
      this.confirmDialogTitle = '确认取消'
      this.confirmDialogMsg = '是否确认取消订单？'
      this.showConfirmDialog('cancelOrder')
    },
    doCancelOrder () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'CancelOrder')
      // params.append('orderId', this.cancelOrderInfo.orderId)
      var params = {
        'orderId' : this.cancelOrderInfo.orderId
      }
      this.$http.post('CancelOrder', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.tipMsg = '订单已取消'
          that.showTips()
          that.queryOrderList()
        }
        that.isShowConfirmDialog = false
      })
    },
    removeOrder (order) {
      this.removeOrderInfo = order
      this.confirmDialogTitle = '确认删除'
      this.confirmDialogMsg = '是否确认删除订单？'
      this.showConfirmDialog('removeOrder')
    },
    doRemoveOrder () {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'removeOrder')
      // params.append('orderId', this.removeOrderInfo.orderId)
      var params = {
        'orderId' : this.removeOrderInfo.orderId
      }
      this.$http.post('removeOrder', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.tipMsg = '订单已删除'
          that.showTips()
          that.queryOrderList()
        }
        that.isShowConfirmDialog = false
      })
    },
    showOrderDetailDialog (order) {
      this.orderDetail = order
      this.isShowOrderDetailDialog = true
    },
    closeOrderDetailDialog () {
      this.isShowOrderDetailDialog = false
    },
    confirmOrderDetail () {
      this.isShowOrderDetailDialog = false
    },
    showConfirmDialog (type) {
      this.confirmDialogType = type
      this.isShowConfirmDialog = true
    },
    closeConfirmDialog () {
      this.isShowConfirmDialog = false
    },
    confirmConfirmDialog () {
      if (this.confirmDialogType === 'pay') {
        this.confirmConfirmPay()
      } else if (this.confirmDialogType === 'cancelOrder') {
        this.doCancelOrder()
      } else if (this.confirmDialogType === 'removeOrder') {
        this.doRemoveOrder()
      }
    },
    showSubmitOrderSuccessDialog (order) {
      this.confirmOrderId = order.orderId
      this.isShowSubmitOrderSuccessDialog = true
    },
    closeSubmitOrderSuccessDialog () {
      this.isShowSubmitOrderSuccessDialog = false
    },
    confirmSubmitOrderSuccessDialog () {
      this.queryOrderList()
      this.isShowSubmitOrderSuccessDialog = false
    }
    // confirmConfirmPay () {
    //   if (this.payOrderInfo.payType === 'INNER') {
    //     // 雪橇币
    //     this.payWithXCoin(this.payOrderInfo.orderId)
    //   } else {
    //     // 支付宝
    //     this.payWithAlipay(this.payOrderInfo.orderId)
    //     this.isShowConfirmDialog = false
    //   }
    // },
    // payWithXCoin (orderId) {
    //   var that = this
    //   var params = new URLSearchParams()
    //   params.append('method', 'PayOrderWIthXCoin')
    //   params.append('orderId', orderId)
    //   this.$http.post('PayOrderWIthXCoin', params, function (response) {
    //     // alert('login : ' + JSON.stringify(response))
    //     if (response.errorMsg) {
    //       that.tipMsg = response.errorMsg
    //       that.showTips()
    //     } else if (response.errorInfo) {
    //       that.tipMsg = response.errorInfo.errMsg
    //       that.showTips()
    //     } else {
    //       that.tipMsg = '支付成功'
    //       that.showTips()
    //       this.isShowConfirmDialog = false
    //     }
    //   })
    // },
    // payWithAlipay (orderId) {
    //   // var host = 'http://devtcompany.sledtrader.cn/'
    //   var url = this.$XQEnv.baseUrl + 'pay.jsp?orderId=' + orderId
    //   // window.open(url, '_blank')
    //   var newWindow = window.open('_blank')
    //   newWindow.location = url
    // }
  }
}
</script>

<style scoped>
.manager {
  background-color: rgba(255, 255, 255, 1);
  min-width: 1390px;
}
.status-unpay {
  line-height: 16px;
  opacity: 0.5;
  color: rgba(255, 204, 27, 1);
  font-size: 16px;
  text-align: center;
  font-family: STHeitiSC-Light;
}
.status-pay {
  line-height: 16px;
  opacity: 0.5;
  color: rgba(0, 242, 29, 1);
  font-size: 16px;
  text-align: center;
  font-family: STHeitiSC-Light;
}
.status-invalid {
  line-height: 16px;
  opacity: 0.5;
  color: rgba(144, 144, 144, 1);
  font-size: 16px;
  text-align: center;
  font-family: STHeitiSC-Light;
}
.operate-show-detail {
  line-height: 16px;
  color: rgba(54, 158, 239, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.operate-pay {
  line-height: 16px;
  color: rgba(54, 158, 239, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.operate-cancel {
  line-height: 16px;
  color: rgba(255, 124, 27, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.operate-remove {
  line-height: 16px;
  color: rgba(255, 124, 27, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.operation-item {
  margin-left: 5px;
  margin-right: 5px;
}
</style>
