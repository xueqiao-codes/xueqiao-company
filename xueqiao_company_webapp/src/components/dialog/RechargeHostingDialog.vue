<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">续费</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">名称</div>
          <div class="dialog-item-value">{{hosting.groupName}} (ID:{{hosting.groupId}})</div>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">到期时间</div>
          <div class="dialog-item-value">{{hosting.expiredTimestamp}}</div>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">延续时长</div>
          <select class="dialog-item-value g-form-value" v-model="selectedProductRechargeTime">
            <option v-for="(item, index) in hostingProductList" v-bind:key="index" v-bind:value="item">
              {{item.dayIncrement}}天
            </option>
          </select>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label"><b>续期费用</b></div>
          <div class="dialog-item-value" style="color: green;"><b>￥{{selectedProductRechargeTime.price}}</b></div>
        </div>
      </div>
      <div class="dialog-button-container">
        <button class="g-button dialog-button" @click="confirm">提交订单</button>
      </div>
    </div>
    <d-confirm-order v-bind:isShow="isShowConfirmOrderDialog" v-bind:orderInfo="orderConfirmInfo" @close="closeConfirmOrderDialog" @confirm="confirmOrderDialog"></d-confirm-order>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
// import config from '@/config/config'
import DConfirmOrderDialog from './ConfirmOrderDialog'
import DTips from '../dialog/TipsDialog'
export default {
  props: ['isShow', 'hosting', 'hostingProductList'],
  components: {
    'd-tips': DTips,
    'd-confirm-order': DConfirmOrderDialog
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      isShowConfirmOrderDialog: false,
      // data
      upgradeHostingProductList: [],
      selectedProductDetailList: null,
      selectedProductRechargeTime: {
        price: 0
      },
      orderConfirmInfo: {
        payType: 'ALIPAY',
        productId: 0,
        groupId: 0,
        hostedServiceName: 'null',
        productPurpose: null,
        orderTypeCN: null,
        serviceType: null,
        serviceTypeStr: 'null',
        serviceSpecStr: 'null',
        serviceTime: 0,
        totalAmount: 0,
        price: 0,
        quantity: 1
      }
    }
  },
  watch: {
    isShow () {
      if (this.isShow) {
        // try token
        this.$commonFunc.tryToken()

        if (this.hostingProductList && this.hostingProductList.length > 0) {
          this.selectedProductRechargeTime = this.hostingProductList[0]
        }
      }
    }
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
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      // this.$emit('confirm')
      this.doRecharge()
    },
    doRecharge () {
      this.orderConfirmInfo.payType = 'ALI_PAY'
      this.orderConfirmInfo.productId = this.selectedProductRechargeTime.productId
      this.orderConfirmInfo.groupId = this.hosting.groupId
      this.orderConfirmInfo.hostedServiceName = this.hosting.groupName
      this.orderConfirmInfo.orderTypeCN = '续费服务'
      this.orderConfirmInfo.productPurpose = this.$XQConfig.CONSTANTS.PRODUCT_PURPOSE_LIST[2]
      this.orderConfirmInfo.serviceType = this.hosting.serviceType
      this.orderConfirmInfo.serviceTypeStr = this.hosting.serviceTypeCn
      this.orderConfirmInfo.serviceSpecStr = this.hosting.specNameCn
      this.orderConfirmInfo.serviceTime = this.selectedProductRechargeTime.dayIncrement
      this.orderConfirmInfo.totalAmount = this.selectedProductRechargeTime.price
      this.orderConfirmInfo.price = this.selectedProductRechargeTime.price
      this.orderConfirmInfo.quantity = 1

      this.openNewPage(this.orderConfirmInfo)
      this.$emit('confirm');
      // this.showConfirmOrderDialog()
    },
    openNewPage (orderConfirmInfo) {
      this.$XQLocalStorage.setLocalStorage('order', JSON.stringify(orderConfirmInfo))
      const {href} = this.$router.resolve({
        name: 'CreateOrderPage',
        path: '/createOrder'
      })
      window.open(href, '_blank')
    },
    showConfirmOrderDialog () {
      this.isShowConfirmOrderDialog = true
    },
    closeConfirmOrderDialog () {
      this.isShowConfirmOrderDialog = false
    },
    confirmOrderDialog () {
      this.$emit('confirm')
      this.isShowConfirmOrderDialog = false
    }
  }
}
</script>

<style scoped>
.d-custom-dialog {
  /* width: 1023px; */
}
.custom-dialog-content {
  text-align: left;
  widows: 220px;
}
.margin-top-10px {
  margin-top: 10px;
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
  width: 200px;
}
.dialog-button {
  width: 220px;
}
.dialog-item-hosting {
  align-items: flex-start;
}
.dialog-item-key-hosting {
  margin-top: 10px;
}
/* .dialog-item-value-hosting {
  height: 200px;
  overflow: auto;
} */
/* .hosting-item {
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: left;
  display: flex;
  align-items: center;
} */
/* .hosting-item-key {
  width: 170px;
  margin-left: 5px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
} */
/* .hosting-item-value {
  width: 90px;
  margin-left: 10px;
} */
/* .inner-table {
  width: 603px;
  margin-left: 120px;
} */
</style>
