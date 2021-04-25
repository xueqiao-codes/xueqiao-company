<template>
  <div class="page">
    <div class="breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/maintenance' }">公司管理 服务维护</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="header">
      <div class="header-text">公司管理</div>
      <ul class="header-tab">
        <li @click="gotoTabCompanyList">公司列表</li>
        <li class="active">服务维护</li>
        <li @click="gotoTabPlan">维护计划</li>
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
          <span>版本：</span>
          <el-select
            v-model="searchKeyVersionTag"
            placeholder="版本"
            clearable
            class="body-header-input body-header-component"
          >
            <el-option
              v-for="item in existVersionTags"
              :key="item.versionId"
              :label="item.versionTag"
              :value="item.versionTag"
            >
            </el-option>
          </el-select>
          <span>维护时间段：</span>
          <el-select
            v-model="searchKeyMaintenanceTimeSpan"
            placeholder="维护时间段"
            clearable
            class="body-header-input body-header-component"
          >
            <el-option
              v-for="item in MAINTENANCE_TIMESPAN"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
          <span>维护状态：</span>
          <el-select
            v-model="searchKeyMaintenanceState"
            placeholder="维护状态"
            clearable
            class="body-header-input body-header-component"
          >
            <el-option
              v-for="item in MAINTENANCE_STATE"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
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
            @click="showAddUpdatePlanDialog"
          >升级</el-button>
        </div>
      </div>
      <div class="body-content">
        <table>
          <tr bgColor=#F8FCFF>
            <th width="50"></th>
            <th width="150">公司ID</th>
            <th width="400">公司名称</th>
            <th width="300">公司登录名</th>
            <th width="150">公司版本</th>
            <th width="300">维护时段</th>
            <th width="150">维护状态</th>
            <th width="150">云服务详情</th>
            <th width="150">维护记录</th>
            <th width="150">操作</th>
          </tr>
          <tr
            v-for="(maintenance, index) in maintenanceList"
            v-bind:key="index"
          >
            <td :class="getItemClass(maintenance)"><input
                type="checkbox"
                :id="maintenance.companyId"
                :value="maintenance.companyId"
                v-model="selectedCompanyIds"
              ></td>
            <td :class="getItemClass(maintenance)">{{maintenance.companyId}}</td>
            <td :class="getItemClass(maintenance)">{{maintenance.companyName}}</td>
            <td :class="getItemClass(maintenance)">{{maintenance.companyCode}}</td>
            <td
              :class="getItemClass(maintenance)"
              v-if="maintenance.keepLatest"
            >Latest</td>
            <td
              :class="getItemClass(maintenance)"
              v-else
            >{{maintenance.versionTag}}</td>
            <td :class="getItemClass(maintenance)">
              <span
                class="tabItem"
                v-for="(item, index) in maintenance.maintenanceTimeSpans"
                :key="index"
              >
                {{MAINTENANCE_TIMESPAN[item].label}}
              </span>
            </td>
            <td :class="getItemClass(maintenance)">{{MAINTENANCE_STATE[maintenance.maintenanceState].label}}</td>
            <td :class="getItemClass(maintenance)"><a
                href="#"
                @click="gotoServiceDetailPage(maintenance)"
              >查看</a></td>
            <td :class="getItemClass(maintenance)"><a
                href="#"
                @click="showMaintenanceHistoryDialog(maintenance)"
              >查看</a></td>
            <td :class="getItemClass(maintenance)">
              <a
                class="tabItem"
                v-if="showOperation(maintenance) == 1"
                href="#"
                @click="showInitVersionDialog(maintenance)"
              >初始化</a>
              <a
                class="tabItem"
                v-if="showOperation(maintenance) == 2"
                href="#"
                @click="showSettingDialog(maintenance)"
              >设置</a><a
                class="tabItem"
                href="#"
                @click="showRollbackDialog(maintenance)"
              >回退</a></td>
          </tr>
        </table>
        <div
          class="body-content-nodata"
          v-if="nodata"
        >无数据</div>
      </div>
      <div class="pagination">
        <el-pagination
          background
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalNum"
          :page-size="pageSize"
          :current-page="currentPage"
          @size-change="handleSizeChange"
          @current-change="onPageChange"
        >
        </el-pagination>
      </div>
    </div>
    <dialog-update
      v-bind:isShow="isShowUpdateDialog"
      :companyIds="selectedCompanyIds"
      @confirm="confirmUpdate"
      @close="cancelUpdate"
    ></dialog-update>
    <dialog-rollback
      v-bind:isShow="isShowRollbackDialog"
      :companyId="selectedCompanyId"
      @confirm="confirmRollback"
      @close="cancelRollback"
    ></dialog-rollback>
    <dialog-maintenance
      v-bind:isShow="isShowMaintenanceHistoryDialog"
      :companyId="selectedCompanyId"
      :companyName="selectedCompanyName"
      @close="closeMaintenanceHistoryDialog"
    ></dialog-maintenance>
    <dialog-version-tag
      v-bind:isShow="isSHowSetVersionTagDialog"
      :maintenance="selectedMaintenance"
      @confirm="confirmSetVersionTag"
      @close="closeSetVersionTagDialog"
    ></dialog-version-tag>
    <dialog-init-version
      v-bind:isShow="isShowInitVersionDialog"
      :maintenance="selectedMaintenance"
      @confirm="confirmInitVersionDialog"
      @close="closeInitVersionDialog"
    ></dialog-init-version>
  </div>
</template>

<script>
import DialogAddUpdate from "../components/specdialog/AddUpdatePlanDialog.vue";
import DialogAddRollback from "../components/specdialog/AddRollbackPlanDialog.vue";
import DialogMaintenanceHistory from "../components/specdialog/MaintenanceHistoryDialog.vue";
import DialogSetVersionTag from "../components/specdialog/SetVersionTagDialog.vue";
import DialogInitVersion from "../components/specdialog/InitVersionDialog.vue";

export default {
  components: {
    "dialog-update": DialogAddUpdate,
    "dialog-rollback": DialogAddRollback,
    "dialog-maintenance": DialogMaintenanceHistory,
    "dialog-version-tag": DialogSetVersionTag,
    "dialog-init-version": DialogInitVersion
  },
  data() {
    return {
      // page
      totalNum: 0,
      currentPage: 1,
      pageSize: 10,

      // dialog
      isShowUpdateDialog: false,
      isShowRollbackDialog: false,
      isShowMaintenanceHistoryDialog: false,
      isSHowSetVersionTagDialog: false,
      isShowInitVersionDialog: false,

      // const
      MAINTENANCE_TIMESPAN: this.$config.MAINTENANCE_TIMESPAN.ITEMS,
      MAINTENANCE_STATE: this.$config.MAINTENANCE_STATE.ITEMS,

      // search key
      searchKeyCompanyId: null,
      searchKeyCompanyName: null,
      searchKeyVersionTag: null,
      searchKeyMaintenanceTimeSpan: null,
      searchKeyMaintenanceState: null,

      // data
      nodata: true,
      maintenanceList: [],
      existVersionTags: [],

      // selected data
      selectedCompanyIds: [],
      selectedCompanyId: null,
      selectedCompanyName: null,
      selectedProject: null,
      selectedMaintenance: null
    };
  },
  mounted() {
    this.queryCompanyServiceMaintenanceList();
    this.queryCompanyExistVersionTags();
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
    gotoServiceDetailPage(maintenance) {
      this.$router.push({
        name: "serviceDetail",
        params: {
          companyId: maintenance.companyId,
          companyName: maintenance.companyName,
          versionTag: maintenance.versionTag
        }
      });
    },
    onPageChange(currentPage) {
      this.currentPage = currentPage;
      this.queryCompanyServiceMaintenanceList();
    },
    handleSizeChange(size) {
      this.pageSize = size;
      this.queryCompanyServiceMaintenanceList();
    },
    query() {
      this.queryCompanyServiceMaintenanceList();
    },
    queryCompanyServiceMaintenanceList() {
      var that = this;
      var filter = new CompanyServiceMaintenanceFilter();

      if (this.searchKeyCompanyId) {
        filter.companyId = this.searchKeyCompanyId;
      }
      if (this.searchKeyCompanyName) {
        filter.companyNamePartical = this.searchKeyCompanyName;
      }
      if (this.searchKeyVersionTag) {
        filter.versionTag = this.searchKeyVersionTag;
      }

      if (this.searchKeyMaintenanceTimeSpan) {
        filter.maintenanceTimeSpan =
          MaintenanceTimeSpan[this.searchKeyMaintenanceTimeSpan];
      }

      if (this.searchKeyMaintenanceState) {
        filter.maintenanceState =
          MaintenanceState[this.searchKeyMaintenanceState];
      }

      var pageOption = new IndexedPageOption();
      pageOption.needTotalCount = true;
      pageOption.pageIndex = this.currentPage - 1;
      pageOption.pageSize = this.pageSize;

      this.$api.reqCompanyServiceMaintenance(filter, pageOption, function(
        response
      ) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          if (
            response == null ||
            response.page == null ||
            response.page.length < 1
          ) {
            that.maintenanceList = [];
            that.totalNum = 0;
            that.nodata = true;
            return;
          }
          that.nodata = false;
          that.maintenanceList = response.page;
          that.totalNum = response.total;
        }
      });
    },
    queryCompanyExistVersionTags() {
      var that = this;
      this.$api.reqCompanyExistVersionTags(function(response) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          if (response == null || response.length < 1) {
            that.existVersionTags = [];
            return;
          }
          that.existVersionTags = response;
        }
      });
    },
    showAddUpdatePlanDialog() {
      if (this.selectedCompanyIds && this.selectedCompanyIds.length > 0) {
        this.isShowUpdateDialog = true;
      } else {
        alert("请选择公司");
      }
    },
    confirmUpdate() {
      this.isShowUpdateDialog = false;
      this.queryProjectList();
    },
    cancelUpdate() {
      this.isShowUpdateDialog = false;
    },
    showRollbackDialog(maintenance) {
      this.selectedCompanyId = maintenance.companyId;
      this.isShowRollbackDialog = true;
    },
    confirmRollback() {
      this.isShowRollbackDialog = false;
      this.queryCompanyServiceMaintenanceList();
    },
    cancelRollback() {
      this.isShowRollbackDialog = false;
    },
    showMaintenanceHistoryDialog(maintenance) {
      this.selectedCompanyId = maintenance.companyId;
      this.selectedCompanyName = maintenance.companyName;
      this.isShowMaintenanceHistoryDialog = true;
    },
    closeMaintenanceHistoryDialog() {
      this.isShowMaintenanceHistoryDialog = false;
    },
    getItemClass(maintenance) {
      if (maintenance.versionDifferent) {
        return "error-item";
      }
      return "";
    },
    showSettingDialog(maintenance) {
      this.selectedMaintenance = maintenance;
      this.isSHowSetVersionTagDialog = true;
    },
    closeSetVersionTagDialog() {
      this.isSHowSetVersionTagDialog = false;
    },
    confirmSetVersionTag() {
      this.isSHowSetVersionTagDialog = false;
      this.queryCompanyServiceMaintenanceList();
    },
    showInitVersionDialog(maintenance) {
      this.selectedMaintenance = maintenance;
      this.isShowInitVersionDialog = true;
    },
    closeInitVersionDialog() {
      this.isShowInitVersionDialog = false;
    },
    confirmInitVersionDialog() {
      this.isShowInitVersionDialog = false;
      this.queryCompanyServiceMaintenanceList();
    },
    showOperation(maintenance) {
      if (!maintenance.versionTag) {
        return 1;
      } else if (this.getEnv() != "idc") {
        return 2;
      }
      return 0;
    },
    getEnv() {
      return this.$util.getEnv();
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
.error-item {
  background-color: rgba(255, 0, 0, 0.15);
}
</style>
