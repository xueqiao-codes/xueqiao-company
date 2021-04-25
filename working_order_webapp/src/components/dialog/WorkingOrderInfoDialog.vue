<template>
  <div
    v-if="isShow"
    class="dialog-bg"
  >
    <div class="dialog-container my-dialog-container">
      <div class="dialog-header">
        <div class="dialog-title">工单信息</div>
        <div class="dialog-close">
          <a
            href="#"
            @click="close"
          ><img src="../../assets/img/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content">
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>工单ID：</span>
          </div>
          <div>
            <span>{{workingOrder.orderId}}</span>
          </div>
        </div>
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>公司：</span>
          </div>
          <div>
            <span>{{workingOrder.companyCode}}</span>
          </div>
        </div>
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>用户名：</span>
          </div>
          <div>
            <span>{{workingOrder.userName}}</span>
          </div>
        </div>
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>内盘/外盘：</span>
          </div>
          <div>
            <span v-if="workingOrder.brokerAccessEntry">{{TECH_PLATFORM_ENV[workingOrder.brokerAccessEntry.techPlatformEnv].LABEL}}</span>
            <span v-else>--</span>
          </div>
        </div>
        <div style="height: 15px;"></div>
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>券商(经纪商)：</span>
          </div>
          <div>
            <span>{{workingOrder.brokerEntry.cnName}}</span>
          </div>
        </div>
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>交易接口：</span>
          </div>
          <div>
            <span v-if="workingOrder.brokerAccessEntry">{{BROKER_PLATFORM[workingOrder.brokerAccessEntry.platform].LABEL}}</span>
            <span v-else>--</span>
          </div>
        </div>
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>席位：</span>
          </div>
          <div>
            <span v-if="workingOrder.brokerAccessEntry">{{workingOrder.brokerAccessEntry.accessName}}</span>
            <span v-else>--</span>
          </div>
        </div>
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>账号：</span>
          </div>
          <div>
            <span>{{workingOrder.workingOrderInfo.account.accountName}}</span>
          </div>
        </div>
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>密码：</span>
          </div>
          <div>
            <span>{{workingOrder.workingOrderInfo.account.password}}</span>
          </div>
        </div>
        <div class="input-item">
          <div class="input-key my-input-key">
            <span>账户简称：</span>
          </div>
          <div>
            <span>{{workingOrder.workingOrderInfo.account.nickName}}</span>
          </div>
        </div>
        <div class="multi-input-item">
          <div class="multi-input-key my-input-key">
            <span>交易授权码：</span>
          </div>
          <div class="multi-input-key">
            <el-input
              class="my-input-value"
              type="textarea"
              autosize
              readonly
              placeholder="无"
              v-model="authorizationCode"
            >
            </el-input>
          </div>
        </div>
        <div class="input-item" v-for="(index, key) in extraInfoMap" :key="index">
          <div class="input-key my-input-key">
            <span>{{key}}：</span>
          </div>
          <div>
            <span>{{extraInfoMap[key]}}</span>
          </div>
        </div>
      </div>
      <div class="dialog-button-container">
        <el-button
          size="medium"
          @click="close"
        >关闭</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["isShow", "workingOrder"],
  data() {
    return {
      // const
      TECH_PLATFORM_ENV: this.$config.ENUM.TECH_PLATFORM_ENV.ITEMS,
      BROKER_PLATFORM: this.$config.ENUM.BROKER_PLATFORM.ITEMS,

      authorizationCode: null,

      extraInfoMap: [],
    };
  },
  mounted() {},
  watch: {
    isShow() {
      if (this.isShow) {
        this.authorizationCode = this.workingOrder.workingOrderInfo.account.authorizationCode;
        if (this.workingOrder.workingOrderInfo.account.extraInfo) {
          this.extraInfoMap = this.workingOrder.workingOrderInfo.account.extraInfo;
        } else {
          this.extraInfoMap =  [];
        }
      }
    }
  },
  methods: {
    close() {
      this.$emit("close");
    },
    confirm() {
      this.doUpdate();
    },
    checkParams() {
      if (!this.chart) {
        alert("页面过期，请刷新后再尝试");
        return 1;
      }
      return 0;
    },
    doUpdate() {
      if (this.checkParams()) {
        return;
      }
      var that = this;
      this.$api.updateChartTags(this.chart.chartId, this.selectedTags, function(
        response
      ) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          alert("修改成功");
          that.$emit("confirm");
        }
      });
    },
    reqTags() {
      var that = this;
      this.$api.reqTags(function(response) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          if (response && response.length > 0) {
            that.tagOptions = response;
            return;
          }
          that.tagOptions = [];
        }
      });
    }
  }
};
</script>

<style scoped>
.my-dialog-container{
    width: 700px;
}
.my-input-key {
  width: 230px;
}
.my-input-value {
  width: 300px;
}
</style>

