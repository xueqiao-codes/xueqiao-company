<template>
  <div
    v-if="isShow"
    class="dialog-bg"
  >
    <div class="dialog-container">
      <div class="dialog-header">
        <div class="dialog-title">{{companyName}} 维护记录</div>
        <div class="dialog-close">
          <a
            href="#"
            @click="close"
          ><img src="../../assets/img/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content">
        <table>
          <tr bgColor=#F8FCFF>
            <th width="200">维护时间</th>
            <th width="100">维护类型</th>
            <th width="100">维护前版本</th>
            <th width="100">目标版本</th>
            <th width="200">维护时段</th>
            <th width="200">计划提交时间</th>
          </tr>
          <tr
            v-for="(maintenance, index) in maintenanceHistoryList"
            v-bind:key="index"
          >
            <td>{{convertTimestampToString(maintenance.scheduledTimestamp)}}</td>
            <td>{{OPERATE_TYPE[maintenance.operateType].label}}</td>
            <td>{{maintenance.oldVersionTag}}</td>
            <td>{{maintenance.targetVersionTag}}</td>
            <td>
              <span
                class="tabItem"
                v-for="(item, index) in maintenance.maintenanceTimeSpans"
                :key="index"
              >
                {{MAINTENANCE_TIMESPAN[item].label}}
              </span>
            </td>
            <td>{{convertTimestampToString(maintenance.createScheduleTimestamp)}}</td>
          </tr>
        </table>
        <div
          class="body-content-nodata"
          v-if="nodata"
        >无数据</div>
      </div>
      <div class="dialog-button-container">
        <el-button
          type="primary"
          size="medium"
          @click="close"
        >确定</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["isShow", "companyId", "companyName"],
  data() {
    return {
      // const
      MAINTENANCE_TIMESPAN: this.$config.MAINTENANCE_TIMESPAN.ITEMS,
      OPERATE_TYPE: this.$config.OPERATE_TYPE.ITEMS,

      // selected data
      selectedTargetVersionId: null,

      // data
      maintenanceHistoryList: [],
      nodata: true
    };
  },
  mounted() {},
  watch: {
    isShow() {
      if (this.isShow) {
        this.projectName = null;
        this.projectDescription = null;

        this.queryMaintenanceHistory();
      }
    }
  },
  methods: {
    close() {
      this.$emit("close");
    },
    queryMaintenanceHistory() {
      var that = this;
      //   alert("this.companyId : " + JSON.stringify(this.companyId));
      var companyId = this.companyId;
      this.$api.reqMaintenanceHistory(companyId, function(response) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          if (response == null || response.length < 1) {
            that.maintenanceHistoryList = [];
            that.nodata = true;
            return;
          }
          that.maintenanceHistoryList = response;
          that.nodata = false;
        }
      });
    },
    convertTimestampToString(timestamp) {
      return this.$timeUtil.getYYYY_MM_DD_HH_MM_SS(timestamp * 1000);
    }
  }
};
</script>

<style scoped>
.my-input-value {
  width: 300px;
}
.tabItem {
  padding-left: 15px;
  padding-right: 15px;
}
</style>

