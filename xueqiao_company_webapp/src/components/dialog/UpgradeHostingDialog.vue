<template>
  <div
    v-if="isShow"
    class="dialog-bg"
  >
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">变更规格</div>
        <div class="dialog-close">
          <a
            href="#"
            @click="closeDialog"
          ><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">名称</div>
          <div class="dialog-item-value">{{hosting.groupName}} (ID:{{hosting.groupId}})</div>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">当前规格</div>
          <button
            class="item-active"
            disabled="disabled"
          >{{hosting.specNameCn}}</button>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">选择规格</div>
          <div
            v-for="(hostedServiceProduct, index) in hostingProductList"
            v-bind:key="index"
          >
            <button
              @click="specTabToggle(index)"
              :class="toggleCss(index==upgradeHostingInfo.specTabIndex)"
              style="text-decoration:none;"
            >{{hostedServiceProduct.productTypeCnName}}</button>
          </div>
        </div>
        <div class="dialog-item-value inner-table">
          <table>
            <tr>
              <td
                width="300"
                bgcolor="#f6fafe"
              ></td>
              <td
                width="100"
                align="center"
                bgcolor="#f6fafe"
              ><b>基本型</b></td>
              <td
                width="100"
                align="center"
                bgcolor="#ebf5fd"
              ><b>通用型</b></td>
              <td
                width="100"
                align="center"
                bgcolor="#e3f1fe"
              ><b>增强型</b></td>
            </tr>
            <tr
              v-for="(hosting_machine_spec, index) in $XQConfig.config.HOSTING_MACHINE_SPEC_LIST"
              v-bind:key="index"
            >
              <td bgcolor="#f6fafe">&nbsp;&nbsp;&nbsp;&nbsp;{{hosting_machine_spec.inteface}}</td>
              <td
                align="center"
                bgcolor="#f6fafe"
              >{{hosting_machine_spec.basic}}</td>
              <td
                align="center"
                bgcolor="#ebf5fd"
              >{{hosting_machine_spec.common}}</td>
              <td
                align="center"
                bgcolor="#e3f1fe"
              >{{hosting_machine_spec.enhanced}}</td>
            </tr>
          </table>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label"><b>配置费用</b></div>
          <div
            class="dialog-item-value"
            style="color: green;"
          ><b>￥{{selectedProductDetail.totalAmountStr}}</b></div>
        </div>
      </div>
      <div class="dialog-button-container">
        <button
          class="g-button dialog-button"
          @click="confirm"
        >提交订单</button>
      </div>
    </div>
    <d-confirm-order
      v-bind:isShow="isShowConfirmOrderDialog"
      v-bind:orderInfo="orderConfirmInfo"
      @close="closeConfirmOrderDialog"
      @confirm="confirmOrderDialog"
    ></d-confirm-order>
    <d-tips
      v-bind:isShow="isShowTips"
      v-bind:msg="tipMsg"
      @close="closeTips"
      @confirm="confirmTips"
    ></d-tips>
  </div>
</template>

<script>
// import config from '@/config/config'
import DConfirmOrderDialog from "./ConfirmOrderDialog";
import DTips from "../dialog/TipsDialog";
export default {
  props: ["isShow", "hosting", "hostingProductList"],
  components: {
    "d-tips": DTips,
    "d-confirm-order": DConfirmOrderDialog
  },
  data() {
    return {
      // dialog
      isShowTips: false,
      tipMsg: "",
      isShowConfirmOrderDialog: false,
      // data
      upgradeHostingProductList: [],
      selectedProductDetailList: null,
      selectedProductDetail: {
        productId: null,
        price: null,
        currencyType: null,
        timeIncrement: null
      },
      upgradeHostingInfo: {
        hostedServiceName: "",
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
        payType: "ALIPAY",
        productId: 0,
        groupId: 0,
        hostedServiceName: "null",
        productPurpose: null,
        orderTypeCN: null,
        serviceType: null,
        serviceTypeStr: "null",
        serviceSpecStr: "null",
        serviceTime: 0,
        totalAmount: 0,
        price: 0,
        quantity: 1
      }
    };
  },
  watch: {
    isShow() {
      if (this.isShow) {
        // try token
        this.$commonFunc.tryToken();

        this.upgradeHostingInfo.specTabIndex = 0;
        if (
          this.upgradeHostingInfo.specTabIndex < this.hostingProductList.length
        ) {
          this.selectedProductDetail = this.hostingProductList[
            this.upgradeHostingInfo.specTabIndex
          ];
        }
      }
    }
  },
  methods: {
    closeDialog() {
      this.$emit("close");
    },
    confirm() {
      this.doUpgrade();
      // this.$emit('confirm')
    },
    showTips() {
      this.isShowTips = true;
    },
    closeTips() {
      this.isShowTips = false;
    },
    confirmTips() {
      this.isShowTips = false;
    },
    specTabToggle(specTabIndex) {
      this.upgradeHostingInfo.specTabIndex = specTabIndex;
      if (specTabIndex < this.hostingProductList.length) {
        this.selectedProductDetail = this.hostingProductList[specTabIndex];
      }
    },
    toggleCss(isActive) {
      if (isActive) {
        return "item-active";
      } else {
        return "item-inactive";
      }
    },
    doUpgrade() {
      // set order info for create hosted service
      this.orderConfirmInfo.groupId = this.hosting.groupId;
      this.orderConfirmInfo.payType = "ALI_PAY";
      this.orderConfirmInfo.productId = this.selectedProductDetail.productId;
      this.orderConfirmInfo.hostedServiceName = this.hosting.groupName;
      this.orderConfirmInfo.orderTypeCN = "升级服务";
      this.orderConfirmInfo.productPurpose = this.$XQConfig.CONSTANTS.PRODUCT_PURPOSE_LIST[1];
      this.orderConfirmInfo.serviceType = this.hosting.serviceType;
      this.orderConfirmInfo.serviceTypeStr = this.hosting.serviceTypeCn;
      this.orderConfirmInfo.serviceSpecStr = this.selectedProductDetail.productTypeCnName;
      this.orderConfirmInfo.serviceTime = this.selectedProductDetail.day;
      this.orderConfirmInfo.totalAmount = this.selectedProductDetail.totalAmountStr;
      this.orderConfirmInfo.price = this.selectedProductDetail.price;
      this.orderConfirmInfo.quantity = this.selectedProductDetail.day;

      this.openNewPage(this.orderConfirmInfo);
      this.$emit("confirm");
      // this.showConfirmOrderDialog()
    },
    openNewPage(orderConfirmInfo) {
      this.$XQLocalStorage.setLocalStorage(
        "order",
        JSON.stringify(orderConfirmInfo)
      );
      const { href } = this.$router.resolve({
        name: "CreateOrderPage",
        path: "/createOrder"
      });
      window.open(href, "_blank");
    },
    showConfirmOrderDialog() {
      this.isShowConfirmOrderDialog = true;
    },
    closeConfirmOrderDialog() {
      this.isShowConfirmOrderDialog = false;
    },
    confirmOrderDialog() {
      this.$emit("confirm");
      this.isShowConfirmOrderDialog = false;
    }
  }
};
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
