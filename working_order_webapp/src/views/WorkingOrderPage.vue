<template>
  <div class="page">
    <div class="header">
      <div class="header-text">用户工单管理</div>
    </div>
    <div class="body">
      <div class="body-header">
        <div class="body-header-left">
          <div class="multi-line-search-item">
            <span>公司：</span>
            <el-input
              class="body-header-input body-header-component"
              placeholder="公司"
              v-model="searchKeyCompany"
              clearable
            ></el-input>
          </div>
          <div class="multi-line-search-item">
            <span>用户名：</span>
            <el-input
              class="body-header-input body-header-component"
              placeholder="用户名"
              v-model="searchKeyUserName"
              clearable
            ></el-input>
          </div>
          <div class="multi-line-search-item">
            <span>工单类型：</span>
            <el-select
              v-model="searchKeyType"
              placeholder="工单类型"
              clearable
              filterable
              allow-create
              class="body-header-input body-header-component"
            >
              <el-option
                v-for="item in WORKING_ORDER_TYPE"
                :key="item.VALUE"
                :label="item.LABEL"
                :value="item.KEY"
              >
              </el-option>
            </el-select>
          </div>
          <div class="multi-line-search-item">
            <span>处理状态：</span>
            <el-select
              v-model="searchKeyState"
              placeholder="处理状态"
              clearable
              filterable
              allow-create
              class="body-header-input body-header-component"
            >
              <el-option
                v-for="item in WORKING_ORDER_STATE"
                :key="item.VALUE"
                :label="item.LABEL"
                :value="item.KEY"
              >
              </el-option>
            </el-select>
          </div>
          <el-button
            class="body-header-component"
            type="primary"
            size="small"
            @click="query"
          >查询</el-button>
        </div>
        <div class="body-header-right">
        </div>
      </div>
      <div class="body-content">
        <table>
          <tr bgColor=#F8FCFF>
            <th width="400">工单ID</th>
            <th width="800">公司</th>
            <th width="600">用户名</th>
            <th width="500">工单类型</th>
            <th width="400">工单信息</th>
            <th width="400">处理状态</th>
            <th width="800">提交时间</th>
            <th width="800">处理时间</th>
            <th width="500">操作人</th>
            <th width="500">操作</th>
          </tr>
          <tr
            v-for="(workingOrder, index) in workingOrderList"
            v-bind:key="index"
          >
            <td>{{workingOrder.orderId}}</td>
            <td>{{workingOrder.companyCode}}</td>
            <td>{{workingOrder.userName}}</td>
            <td>{{WORKING_ORDER_TYPE[workingOrder.workingOrderInfo.baseWorkingOrder.workingOrderType].LABEL}}</td>
            <td><a
                href="#"
                @click="showWorkingOrderInfo(workingOrder)"
              >查看</a></td>
            <td>{{WORKING_ORDER_STATE[workingOrder.workingOrderInfo.baseWorkingOrder.state].LABEL}}</td>
            <td>{{formatTime(workingOrder.workingOrderInfo.baseWorkingOrder.createTimestamp)}}</td>
            <td>{{formatTime(workingOrder.workingOrderInfo.baseWorkingOrder.lastModifyTimestamp)}}</td>
            <td>{{workingOrder.workingOrderInfo.baseWorkingOrder.operateUser}}</td>
            <td>
              <a
                class="td-item"
                href="#"
                v-if="workingOrder.workingOrderInfo.baseWorkingOrder.state != 2"
                @click="showMarkWorkingOrderDoneDialog(workingOrder)"
              >标记处理</a>
              <a
                class="td-item"
                v-else
              >--</a>
            </td>
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
    <d-working-order-info
      v-bind:isShow="isShowWorkingOrderInfoDialog"
      :workingOrder="selectedWorkingOrder"
      @close="clsoeWorkingOrderInfoDialog"
    ></d-working-order-info>
  </div>
</template>

<script>
import WorkingOrderInfoDialog from "../components/dialog/WorkingOrderInfoDialog";
export default {
  components: {
    "d-working-order-info": WorkingOrderInfoDialog
  },
  data() {
    return {
      // const
      WORKING_ORDER_TYPE: this.$config.ENUM.WORKING_ORDER_TYPE.ITEMS,
      WORKING_ORDER_STATE: this.$config.ENUM.WORKING_ORDER_STATE.ITEMS,

      // page
      totalNum: 0,
      currentPage: 1,
      pageSize: 10,

      // dialog
      isShowWorkingOrderInfoDialog: false,

      // searchKey
      searchKeyCompany: null,
      searchKeyUserName: null,
      searchKeyType: null,
      searchKeyState: null,

      // selected
      selectedWorkingOrder: null,

      // data
      nodata: true,
      workingOrderList: [],

      // options
      typeOptions: [],
      statusOptions: []
    };
  },
  mounted() {
    this.$util.resetNavigationStack();
    this.$util.pushNavigationStack({
      name: "工单管理",
      router: { path: "/workingorder" }
    });

    this.reqWorkingOrderInfo();
  },
  methods: {
    onPageChange(currentPage) {
      this.currentPage = currentPage;
      this.reqWorkingOrderInfo();
    },
    handleSizeChange(size) {
      this.pageSize = size;
      this.reqWorkingOrderInfo();
    },
    query() {
      this.totalNum = 0;
      this.currentPage = 1;
      this.reqWorkingOrderInfo();
    },
    reqWorkingOrderInfo() {
      var that = this;

      var option = new ReqWorkingOrderWebInfoOption();
      if (this.searchKeyCompany) {
        option.companyNamePartical = this.searchKeyCompany;
      }
      if (this.searchKeyUserName) {
        option.companyUserNamePartical = this.searchKeyUserName;
      }
      if (this.searchKeyType) {
        option.orderType = WorkingOrderType[this.searchKeyType];
      }
      if (this.searchKeyState) {
        option.state = WorkingOrderState[this.searchKeyState];
      }

      var pageOption = new IndexedPageOption();
      pageOption.needTotalCount = true;
      pageOption.pageIndex = this.currentPage - 1;
      pageOption.pageSize = this.pageSize;

      // alert("option : " + JSON.stringify(option));

      this.$api.reqWorkingOrderInfo(option, pageOption, function(response) {
          // alert("response : " + JSON.stringify(response));
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          if (response && response.page && response.page.length > 0) {
            that.workingOrderList = response.page;
            that.totalNum = response.total;
            that.nodata = false;
            return;
          }
          that.workingOrderList = [];
          that.totalNum = 0;
          that.nodata = true;
        }
      });
    },
    showMarkWorkingOrderDoneDialog(workingOrder) {
      if (
        confirm(
          "确认已经完成资金账户（" +
            workingOrder.workingOrderInfo.account.accountName +
            " : " + workingOrder.workingOrderInfo.account.nickName + ") 信息相应更改？"
        )
      ) {
        this.markWorkingOrderDone(workingOrder);
      }
    },
    markWorkingOrderDone(workingOrder) {
      var that = this;
      var operateName = this.$cookie.getCookie("oa_user_name");
      if (!operateName) {
          alert("登录过期，请刷新页面重新登录后重试");
          return;
      }

      this.$api.markWorkingOrderState(
        workingOrder.orderId,
        WorkingOrderState.DONE,
        operateName,
        function(response) {
          if (response != null) {
            if (response.success) {
              alert("已标记完成");
              that.reqWorkingOrderInfo();
            } else if (response.message) {
              alert(response.message);
            } else if (response.errorMsg) {
              alert(response.errorMsg);
            } else {
              alert(that.$string.message.callMethodFail);
            }
          } else {
            alert(that.$string.message.serverBusy);
          }
        }
      );
    },
    showWorkingOrderInfo(workingOrder) {
      this.selectedWorkingOrder = workingOrder;
      this.isShowWorkingOrderInfoDialog = true;
    },
    clsoeWorkingOrderInfoDialog() {
      this.isShowWorkingOrderInfoDialog = false;
    },
    formatTime(time) {
      if (time) {
        return this.$timeUtil.getYYYY_MM_DD_HH_MM_SS(time * 1000);
      } else {
        return "--";
      }
    }
  }
};
</script>

<style scoped>
.page {
  width: 100%;
  height: 100%;
}
.main {
  height: 100%;
  background-color: white;
}
.td-item {
  margin-left: 5px;
  margin-right: 5px;
  margin-top: 2px;
  margin-bottom: 2px;
}
</style>
