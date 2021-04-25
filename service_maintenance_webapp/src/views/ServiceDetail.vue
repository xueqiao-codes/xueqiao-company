<template>
  <div class="page">
    <div class="breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/maintenance' }">公司管理 服务维护</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/serviceDetail' }">云服务详情</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="header">
      <div class="header-text">{{companyName}} 云服务详情 (公司版本：{{versionTag}})</div>
    </div>
    <div class="body">
      <div class="body-header">
        <div class="body-header-left"></div>
        <div class="body-header-right"></div>
      </div>
      <div class="body-content">
        <table>
          <tr bgColor=#F8FCFF>
            <th width="600">云服务ID</th>
            <th width="1600">云服务名称</th>
            <th width="600">交易环境</th>
            <th width="600">服务状态</th>
            <th width="600">系统版本</th>
          </tr>
          <tr
            v-for="(detail, index) in detailList"
            v-bind:key="index"
          >
            <td>{{detail.groupId}}</td>
            <td>{{detail.groupName}}</td>
            <td v-if="detail.tradeType == null || detail.tradeType == undefined">-</td>
            <td v-else>{{TRADE_TYPE[detail.tradeType].label}}</td>
            <td>{{HOSTING_SERVICE_STATE[detail.hostingServiceState].label}}</td>
            <td>{{detail.versionTag}}</td>
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
      // const
      HOSTING_SERVICE_STATE: this.$config.HOSTING_SERVICE_STATE.ITEMS,
      TRADE_TYPE: this.$config.TRADE_TYPE.ITEMS,

      // dialog
      isShowAdditionDialog: false,
      isShowEditionDialog: false,

      // params
      companyId: null,
      companyName: null,
      versionTag: null,

      // data
      nodata: true,
      detailList: []
    };
  },
  mounted() {
    this.companyId = this.$route.params.companyId;
    this.companyName = this.$route.params.companyName;
    this.versionTag = this.$route.params.versionTag;
    this.queryCompanyServiceDetail();
  },
  methods: {
    queryCompanyServiceDetail() {
      var that = this;
      this.$api.reqCompanyServiceDetail(this.companyId, function(response) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          if (response == null || response.length < 1) {
            that.detailList = [];
            that.nodata = true;
            return;
          }
          that.nodata = false;
          that.detailList = response;
        }
      });
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
</style>
