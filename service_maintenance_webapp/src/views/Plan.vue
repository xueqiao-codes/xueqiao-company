<template>
  <div class="page">
    <div class="breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/project' }">公司管理 维护计划</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="header">
      <div class="header-text">公司管理</div>
      <ul class="header-tab">
        <li @click="gotoTabCompanyList">公司列表</li>
        <li @click="gotoTabMaintenance">服务维护</li>
        <li class="active">维护计划</li>
      </ul>
    </div>
    <div class="body">
      <div class="body-header">
        <div class="body-header-left">
          <span>公司ID：</span>
          <el-input
            class="body-header-input body-header-component"
            placeholder="公司ID"
            v-model="searchKeyCompanyId"
            clearable
          >
          </el-input>
          <span>公司名称：</span>
          <el-input
            class="body-header-input body-header-component"
            placeholder="公司名称"
            v-model="searchKeyCompanyName"
            clearable
          ></el-input>
          <span>目标版本：</span>
          <el-input
            class="body-header-input body-header-component"
            placeholder="目标版本"
            v-model="searchKeyVersionTag"
            clearable
          ></el-input>
          <el-button
            class="body-header-component"
            type="primary"
            size="small"
            @click="query"
          >查询</el-button>
        </div>
        <div class="body-header-right">
          <el-button
            class="body-header-component"
            type="primary"
            size="small"
            @click="showCancelMaintenanceScheduleDialog"
          >终止</el-button>
        </div>
      </div>
      <div class="body-content">
        <table>
          <tr bgColor=#F8FCFF>
            <th width="100"></th>
            <th width="600">创建时间</th>
            <th width="300">公司ID</th>
            <th width="600">公司名称</th>
            <th width="600">公司登名名</th>
            <th width="600">维护类型</th>
            <th width="600">当前版本</th>
            <th width="600">目标版本</th>
            <th width="600">维护时段</th>
            <th width="600">计划维护时间</th>
            <th width="600">创建人</th>
          </tr>
          <tr
            v-for="(detail, index) in maintenanceScheduleDetailList"
            v-bind:key="index"
          >
            <td><input
                type="checkbox"
                :id="detail.companyId"
                :value="detail.companyId"
                v-model="selectedCompanyIds"
              ></td>
            <td>{{convertTimestampToString(detail.createScheduleTime)}}</td>
            <td>{{detail.companyId}}</td>
            <td>{{detail.companyName}}</td>
            <td>{{detail.companyCode}}</td>
            <td>{{OPERATE_TYPE[detail.operateType].label}}</td>
            <td>{{detail.oldVersionTag}}</td>
            <td>{{detail.targetVersionTag}}</td>
            <td>
              <span
                class="tabItem"
                v-for="(item, index) in detail.maintenanceTimeSpans"
                :key="index"
              >
                {{MAINTENANCE_TIMESPAN[item].label}}
              </span>
            </td>
            <td>{{convertTimestampToString(detail.scheduleMaintenanceTime)}}</td>
            <td>{{detail.creater}}</td>
          </tr>
        </table>
        <div
          class="body-content-nodata"
          v-if="nodata"
        >无数据</div>
      </div>
      <div class="no-pagination"></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // dialog
      isShowAdditionDialog: false,
      isShowEditionDialog: false,

      // const
      MAINTENANCE_TIMESPAN: this.$config.MAINTENANCE_TIMESPAN.ITEMS,
      OPERATE_TYPE: this.$config.OPERATE_TYPE.ITEMS,

      // searchKey
      searchKeyCompanyId: null,
      searchKeyCompanyName: null,
      searchKeyVersionTag: null,

      // data
      nodata: true,
      maintenanceScheduleDetailList: [],

      // selected data
      selectedCompanyIds: []
    };
  },
  mounted() {
    this.queryMaintenanceScheduleDetailList();
  },
  methods: {
    gotoTabCompanyList() {
      var companyListUrl = this.$util.getCompanyListUrl();
      if (!companyListUrl) {
        alert("获取不到公司列表url，请联系开发人员");
      }
      window.location.href = companyListUrl;
    },
    gotoTabMaintenance() {
      this.$router.push({
        name: "maintenance"
      });
    },
    gotoTabPlan() {
      this.$router.push({
        name: "plan"
      });
    },
    query() {
      this.queryMaintenanceScheduleDetailList();
    },
    queryMaintenanceScheduleDetailList() {
      var that = this;
      var filter = new ReqMaintenanceScheduleDetailFilter();
      if (this.searchKeyCompanyId) {
        filter.companyId = this.searchKeyCompanyId;
      }
      if (this.searchKeyCompanyName) {
        filter.companyNamePartical = this.searchKeyCompanyName;
      }
      if (this.searchKeyVersionTag) {
        filter.versionTag = this.searchKeyVersionTag;
      }

      this.$api.reqMaintenanceScheduleDetail(filter, function(response) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          if (response == null || response.length < 1) {
            that.maintenanceScheduleDetailList = [];
            that.nodata = true;
            return;
          }
          that.nodata = false;
          that.maintenanceScheduleDetailList = response;
        }
      });
    },
    showCancelMaintenanceScheduleDialog() {
      if (!this.selectedCompanyIds || this.selectedCompanyIds.length < 1) {
        alert("请选择终止的计划");
        return;
      }
      if (
        confirm("确认终止公司（" + this.selectedCompanyIds + "）的维护计划?")
      ) {
        var that = this;
        this.$api.cancelMaintenanceSchedule(this.selectedCompanyIds, function(
          response
        ) {
          if (response != null && response.errorMsg) {
            alert(response.errorMsg);
          } else {
            alert("终止成功");
            that.queryMaintenanceScheduleDetailList();
          }
        });
      }
    },
    convertTimestampToString(timestamp) {
      return this.$timeUtil.getYYYY_MM_DD_HH_MM_SS(timestamp * 1000);
    }
  }
};
</script>

<style scoped>
.body-header-input {
  width: 150px;
}
.body-header-left-item {
  margin-top: 5px;
  margin-bottom: 5px;
  margin-right: 20px;
}
.table-item-project-description {
  text-align: left;
  padding-left: 30px;
  padding-right: 30px;
}
.no-pagination {
  height: 50px;
}
.tabItem {
  padding-left: 15px;
  padding-right: 15px;
}
</style>
