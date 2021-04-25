<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">新建交易托管服务</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">名称</div>
          <input class="dialog-item-value g-form-value" type="text" v-model="createHostingInfo.hostedServiceName">
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">交易类型</div>
          <button @click="typeTabToggle(0)" :class="toggleCss(0==createHostingInfo.typeTabIndex)" style="text-decoration:none;">实盘</button>
          <button @click="typeTabToggle(1)" :class="toggleCss(1==createHostingInfo.typeTabIndex)" style="text-decoration:none;">模拟盘</button>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">服务规格</div>
          <div v-for="(hostedServiceProduct, index) in createHostingProductList" v-bind:key="index">
            <button @click="specTabToggle(index)" :class="toggleCss(index==createHostingInfo.specTabIndex)" style="text-decoration:none;">{{hostedServiceProduct.name}}</button>
          </div>
        </div>
        <div class="dialog-item-value inner-table">
          <table>
            <!-- <tr>
                  <th width="300"></th>
                  <th width="100" align="center">基本型</th>
                  <th width="100" align="center">通用型</th>
                  <th width="100" align="center" bgcolor="#369EEF">增强型</th>
              </tr> -->
            <tr>
              <td width="300" bgcolor="#f6fafe"></td>
              <td width="100" align="center" bgcolor="#f6fafe"><b>基本型</b></td>
              <td width="100" align="center" bgcolor="#ebf5fd"><b>通用型</b></td>
              <td width="100" align="center" bgcolor="#e3f1fe"><b>增强型</b></td>
            </tr>
            <tr v-for="(hosting_machine_spec, index) in $XQConfig.config.HOSTING_MACHINE_SPEC_LIST" v-bind:key="index">
              <td bgcolor="#f6fafe">&nbsp;&nbsp;&nbsp;&nbsp;{{hosting_machine_spec.inteface}}</td>
              <td align="center" bgcolor="#f6fafe">{{hosting_machine_spec.basic}}</td>
              <td align="center" bgcolor="#ebf5fd">{{hosting_machine_spec.common}}</td>
              <td align="center" bgcolor="#e3f1fe">{{hosting_machine_spec.enhanced}}</td>
            </tr>
          </table>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">服务时长：</div>
          <select class="dialog-item-value g-form-value" v-model="selectedProductDetail">
            <option v-for="(item, index) in selectedProductDetailList" v-bind:key="index" v-bind:value="item">
              {{item.timeIncrement}}天
            </option>
          </select>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label"><b>配置费用</b></div>
          <div class="dialog-item-value" style="color: green;"><b>￥{{selectedProductDetail.price}}</b></div>
        </div>
      </div>
      <div class="dialog-button-container">
        <button class="g-button dialog-button" @click="confirm">提交订单</button>
      </div>
    </div>
    <!-- <d-confirm-order v-bind:isShow="isShowConfirmOrderDialog" v-bind:orderInfo="orderConfirmInfo" @close="closeConfirmOrderDialog" @confirm="confirmOrderDialog"></d-confirm-order> -->
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips" @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DConfirmOrderDialog from './ConfirmOrderDialog'
import DTips from '../dialog/TipsDialog'
export default {
  props: ['isShow', 'createHostingProductList'],
  components: {
    'd-tips': DTips,
    'd-confirm-order': DConfirmOrderDialog
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      // isShowConfirmOrderDialog: false,
      // data
      selectedProductDetailList: null,
      selectedProductDetail: {
        productId: null,
        price: null,
        currencyType: null,
        timeIncrement: null
      },
      createHostingInfo: {
        hostedServiceName: null,
        orderType: null,
        productId: 0,
        typeTabIndex: 0,
        specTabIndex: 0,
        serviceTypeStr: null,
        specStr: null,
        timeIncrement: 0,
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

        this.createHostingInfo.typeTabIndex = 0
        this.createHostingInfo.specTabIndex = 0
        this.createHostingInfo.hostedServiceName = ''

        if (
          this.createHostingInfo.specTabIndex <
          this.createHostingProductList.length
        ) {
          this.selectedProductDetailList = this.createHostingProductList[this.createHostingInfo.specTabIndex].productNewDetailList
          this.selectedProductDetail = this.selectedProductDetailList[0]
        }
      }
    }
  },
  mounted () {
    // test
    // var that = this
    // window.addEventListener('storage', function(e) {
    //   if (e.key === 'ms_username') {
    //     // alert(e.key + ', old : ' + e.oldValue + ', new : ' + e.newValue)
    //     that.test = !that.test
    //   }
    // }, false)
  },
  methods: {
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      this.doCreate()
      // this.$emit('confirm')
    },
    openNewPage (orderConfirmInfo) {
      this.$XQLocalStorage.setLocalStorage('order', JSON.stringify(orderConfirmInfo))
      const {href} = this.$router.resolve({
        name: 'CreateOrderPage',
        path: '/createOrder'
      })
      window.open(href, '_blank')
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
    typeTabToggle (typeTabIndex) {
      this.createHostingInfo.typeTabIndex = typeTabIndex
    },
    specTabToggle (specTabIndex) {
      this.resetSpec()

      this.createHostingInfo.specTabIndex = specTabIndex
      if (specTabIndex < this.createHostingProductList.length) {
        this.selectedProductDetailList = this.createHostingProductList[specTabIndex].productNewDetailList
        this.selectedProductDetail = this.selectedProductDetailList[0]
      }
    },
    resetSpec() {
      this.selectedProductDetailList = []
      this.selectedProductDetail = {
        productId: null,
        price: null,
        currencyType: null,
        timeIncrement: null
      }
    },
    toggleCss (isActive) {
      if (isActive) {
        return 'item-active'
      } else {
        return 'item-inactive'
      }
    },
    doCreate () {
      if (!this.createHostingInfo.hostedServiceName) {
        this.tipMsg = '请设置服务名称'
        this.showTips()
        return
      }
      // set order info for create hosted service
      this.orderConfirmInfo.payType = 'ALI_PAY'
      this.orderConfirmInfo.productId = this.selectedProductDetail.productId
      this.orderConfirmInfo.hostedServiceName = this.createHostingInfo.hostedServiceName
      this.orderConfirmInfo.orderTypeCN = '开通服务'
      this.orderConfirmInfo.productPurpose = this.$XQConfig.CONSTANTS.PRODUCT_PURPOSE_LIST[0]
      this.orderConfirmInfo.serviceType = this.$XQConfig.CONSTANTS.SERVICE_TYPE_LIST[this.createHostingInfo.typeTabIndex]
      this.orderConfirmInfo.serviceTypeStr = this.$XQConfig.CONSTANTS.SERVICE_TYPE_CN_LIST[this.createHostingInfo.typeTabIndex]
      this.orderConfirmInfo.serviceSpecStr = this.createHostingProductList[this.createHostingInfo.specTabIndex].name
      this.orderConfirmInfo.serviceTime = this.selectedProductDetail.timeIncrement
      this.orderConfirmInfo.totalAmount = this.selectedProductDetail.price
      this.orderConfirmInfo.price = this.selectedProductDetail.price
      this.orderConfirmInfo.quantity = 1 // 开通服务的商品数量为1

      // this.showConfirmOrderDialog()
      this.openNewPage(this.orderConfirmInfo)

      this.$XQLocalStorage.clearTempOrderId()
      this.$emit('confirm')
    },
    // showConfirmOrderDialog () {
    //   this.isShowConfirmOrderDialog = true
    // },
    // closeConfirmOrderDialog () {
    //   this.isShowConfirmOrderDialog = false
    // },
    // confirmOrderDialog () {
    //   this.$emit('confirm')
    //   this.isShowConfirmOrderDialog = false
    // }
  }
}
</script>

<style scoped>
.d-custom-dialog {
  width: 1023px;
}
.custom-dialog-content {
  text-align: left;
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
  width: 300px;
}
.dialog-button {
  width: 320px;
}
.dialog-item-hosting {
  align-items: flex-start;
}
.dialog-item-key-hosting {
  margin-top: 10px;
}
.dialog-item-value-hosting {
  height: 200px;
  /* max-height: 200px;
    min-height: 150px; */
  overflow: auto;
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
.item-active {
  display: block;
  /* margin: 2px; */
  width: 80px;
  height: 30px;
  border-style: solid;
  border-width: 1px;
  border-radius: 3px;
  border-color: #369eef;
  text-align: center;
  color: #369eef;
  background: white;
  font-size: 15px;
  margin-right: 20px;
}
.item-inactive {
  display: block;
  /* margin: 2px; */
  width: 80px;
  height: 30px;
  border-style: solid;
  border-width: 1px;
  border-radius: 3px;
  border-color: #bbbbbb;
  text-align: center;
  color: #bbbbbb;
  background: white;
  font-size: 15px;
  margin-right: 20px;
}
.inner-table {
  width: 603px;
  margin-left: 120px;
}
</style>
