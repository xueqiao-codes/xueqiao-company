<template>
  <div
    v-if="isShow"
    class="dialog-bg"
  >
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">设置系统维护时间</div>
        <div class="dialog-close">
          <a
            href="#"
            @click="closeDialog"
          ><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div>
          <div class="dialog-item g-form-item">
            <div class="dialog-item-key">
              <span>时间：</span>
            </div>
            <div>
              <!-- <div class="dialog-item-value" v-for="(item, index) in MAINTENANCE_TIMESPAN" :key="index">
                  <input type="checkbox" v-model="selectedTimeSpans" :value="index">
                <span>{{item.label}}</span>
              </div> -->
              <div class="dialog-item-value">
                <input
                  type="checkbox"
                  v-model="selectedTimeSpans"
                  value=1
                >
                <span>{{MAINTENANCE_TIMESPAN_LABELS.WORKING_DAY}}</span>
              </div>
              <div class="dialog-item-value">
                <input
                  type="checkbox"
                  disabled
                  v-model="selectedTimeSpans"
                  value=0
                >
                <span>{{MAINTENANCE_TIMESPAN_LABELS.WEEKEND}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="dialog-button-container">
        <button
          class="g-button dialog-button"
          @click="confirm"
        >确定</button>
      </div>
    </div>
    <d-tips
      v-bind:isShow="isShowTips"
      v-bind:msg="tipMsg"
      @close="closeTips"
      @confirm="confirmTips"
    ></d-tips>
  </div>
</template>

<script>
import DTips from "../dialog/TipsDialog";
export default {
  props: ["isShow", "serviceMaintenanceTimeSpans"],
  components: {
    "d-tips": DTips
  },
  data() {
    return {
      // const
      MAINTENANCE_TIMESPAN: this.$XQConfig.MAINTENANCE_TIMESPAN.ITEMS,
      MAINTENANCE_TIMESPAN_LABELS: this.$XQConfig.MAINTENANCE_TIMESPAN.LABELS,
      MAINTENANCE_TIMESPAN_VALUES: this.$XQConfig.MAINTENANCE_TIMESPAN.VALUES,

      // dialog flag
      isShowTips: false,
      tipMsg: "",
      modifyValue: false,

      // data
      selectedTimeSpans: null
    };
  },
  watch: {
    isShow() {
      if (this.isShow) {
        // try token
        this.modifyPasswd = false;
        this.password = null;
        this.$commonFunc.tryToken();

        this.selectedTimeSpans = [];
        if (!this.serviceMaintenanceTimeSpans) {
          return;
        }
        // alert("this.serviceMaintenanceTimeSpans : " + JSON.stringify(this.serviceMaintenanceTimeSpans))
        for (
          var index = 0;
          index < this.serviceMaintenanceTimeSpans.length;
          index++
        ) {
          this.selectedTimeSpans.push(
            this.MAINTENANCE_TIMESPAN_VALUES[
              this.serviceMaintenanceTimeSpans[index]
            ]
          );
        }
        // alert("this.selectedTimeSpans : " + JSON.stringify(this.selectedTimeSpans))
      }
    }
  },
  methods: {
    showTips() {
      this.isShowTips = true;
      this.modifyValue = false;
    },
    closeTips() {
      this.isShowTips = false;
    },
    confirmTips() {
      this.isShowTips = false;
      if (this.modifyValue) {
        this.$emit("confirm");
      }
    },
    closeDialog() {
      this.$emit("close");
    },
    confirm() {
      // this.$emit('confirm')
      this.doModify();
    },
    doModify() {
      if (!this.selectedTimeSpans || this.selectedTimeSpans.length < 1) {
        alert("请选维护时间");
        return;
      }

      var timeSpans = [];
      for (var index = 0; index < this.selectedTimeSpans.length; index++) {
          timeSpans.push(parseInt(this.selectedTimeSpans[index]))
      }

      this.modifyValue = false;
      var that = this;
      // var params = new URLSearchParams();
      // params.append("method", "SetServiceMaintenanceTimespan");
      // params.append("timeSpanJson", JSON.stringify(timeSpans));
      var params = {
        "timeSpanJson" : JSON.stringify(timeSpans)
      }
      this.$http.post("SetServiceMaintenanceTimespan", params, function(
        response
      ) {
        // alert('queryCompanyGroupUsers : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          that.tipMsg = "设置成功";
          that.showTips();
          that.modifyValue = true;
        }
      });
    }
  }
};
</script>

<style scoped>
.custom-dialog-content {
  text-align: left;
}
.margin-top-10px {
  margin-top: 10px;
}
.dialog-item {
  text-align: left;
  display: flex;
  /* align-items: center; */
  /* padding-top: 8px;
    padding-bottom: 8px; */
}
.dialog-item-key {
  margin-top: 12px;
  width: 120px;
  line-height: 16px;
  color: rgba(80, 80, 80, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.dialog-item-value {
  height: 16px;
  color: rgba(80, 80, 80, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
  margin-top: 10px;
  margin-bottom: 10px;
}
.dialog-button {
  width: 320px;
}
</style>
