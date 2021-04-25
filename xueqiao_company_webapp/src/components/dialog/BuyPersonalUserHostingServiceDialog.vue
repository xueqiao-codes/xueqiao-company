<template>
  <div
    v-if="isShow"
    class="dialog-bg"
  >
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">{{dialogTitle}}</div>
        <div class="dialog-close">
          <a
            href="#"
            @click="closeDialog"
          ><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label">购买时长</div>
          <select
            class="dialog-item-value g-form-value"
            v-model="selectedProduct"
          >
            <option
              v-for="(item, index) in hostingProductList"
              v-bind:key="index"
              v-bind:value="item"
            >
              {{item.dayIncrement}}天
            </option>
          </select>
        </div>
        <div class="dialog-item g-form-item">
          <div class="dialog-item-key g-form-label"><b>费用</b></div>
          <div
            class="dialog-item-value"
            style="color: green;"
          ><b>￥{{selectedProduct.price}}</b></div>
        </div>
      </div>
      <div class="dialog-button-container personal-user-button-container">
        <button
          class="g-button dialog-button"
          @click="confirm"
        >提交订单</button>
      </div>
    </div>
    <!-- <d-confirm-order
      v-bind:isShow="isShowConfirmOrderDialog"
      v-bind:orderInfo="orderConfirmInfo"
      @close="closeConfirmOrderDialog"
      @confirm="confirmOrderDialog"
    ></d-confirm-order> -->
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
// import ConfirmPersonalUserOrderDialog from "./ConfirmPersonalUserOrderDialog";
import DTips from "../dialog/TipsDialog";
export default {
  props: ["isShow", "userId", "type"],
  components: {
    "d-tips": DTips,
    // "d-confirm-order": ConfirmPersonalUserOrderDialog
  },
  data() {
    return {
      // dialog
      isShowTips: false,
      tipMsg: "",
      isShowConfirmOrderDialog: false,

      // title
      dialogTitle: "",

      // data
      hostingProductList: [],
      selectedProduct: {},
      isRechargeTime: 0,

      orderConfirmInfo: {
        productId: 0,
        isRechargeTime: 0,
      }
    };
  },
  watch: {
    isShow() {
      if (this.isShow) {
        // try token
        this.$commonFunc.tryToken();
        if (this.type == "recharge") {
          this.dialogTitle = "续费";
          this.isRechargeTime = 1;
        } else {
          this.dialogTitle = "购买";
          this.isRechargeTime = 0;
        }
        this.queryPersonalUserHostedServiceProducts();
      }
    }
  },
  methods: {
    showTips() {
      this.isShowTips = true;
    },
    closeTips() {
      this.isShowTips = false;
    },
    confirmTips() {
      this.isShowTips = false;
    },
    queryPersonalUserHostedServiceProducts() {
      this.hostingProductList = [];
      this.selectedProduct = {};
      var that = this;
      var params = {
        'isRechargeTime': this.isRechargeTime,
      };
      this.$http.postByPersonalUser(
        "GetPersonalUserHostedServiceProducts",
        params,
        function(response) {
          // alert(
          //   "GetPersonalUserHostedServiceProducts : " + JSON.stringify(response)
          // );
          if (response.errorMsg) {
            that.tipMsg = response.errorMsg;
            that.showTips();
          } else if (response.errorInfo) {
            that.tipMsg = response.errorInfo.errMsg;
            that.showTips();
          } else {
            if (response.results) {
              // that.$store.commit("setCompanyInfo", response.value);
              that.hostingProductList = response.results;
              if (that.hostingProductList && that.hostingProductList.length > 0) {
                that.selectedProduct = that.hostingProductList[0];
              }
            } else {
              alert("获取商品信息失败 : " + JSON.stringify(response));
            }
          }
        }
      );
    },
    closeDialog() {
      this.$emit("close");
    },
    confirm() {
      // this.$emit('confirm')
      this.doRecharge();
    },
    doRecharge() {
      this.orderConfirmInfo.productId = this.selectedProduct.productId;
      this.orderConfirmInfo.isRechargeTime = this.isRechargeTime;
      // this.orderConfirmInfo.serviceDays = this.$util.getPersonalUserHostingServiceDays(this.selectedProduct.timeIncrement);
      this.openNewPage(this.orderConfirmInfo);
      this.$emit("confirm");
    },
    openNewPage(orderConfirmInfo) {
      this.$XQLocalStorage.setLocalStorage(
        "personalUserOrder",
        JSON.stringify(orderConfirmInfo)
      );
      const { href } = this.$router.resolve({
        name: "CreatePersonalUserOrderPage",
        path: "/createPersonalUserOrder"
      });
      window.open(href, "_blank");
    },
  }
};
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
.personal-user-button-container {
  text-align: center;
}
</style>
