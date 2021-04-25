<template>
  <div class="fragment-manager">
    <div class="manager-title">
      <span class="manager-title-space"></span>
      <span class="manager-title-text">交易云服务</span>
    </div>
    <div class="manager-menu">
      <button
        class="g-button button-add-hosting"
        @click="showCreateHostingDialog"
      >新建交易云服务</button>
    </div>
    <div class="manager-menu-maintenance">
      <span class="maintenance-key">系统维护时段：</span>
      <span
        class="maintenance-value"
        v-for="(item, index) in serviceMaintenanceTimeSpans"
        :key="index"
      >
        {{MAINTENANCE_TIMESPAN[item]}}
      </span>
      <!-- <span class="maintenance-value">周一至周五 5:00-6:00（冬令时 6:00-7:00）</span>
      <span>&nbsp;;&nbsp;&nbsp;</span>
      <span class="maintenance-value">周六 7:00 至 周日 17:00</span> -->
      <a
        href="#"
        class="maintenance-setting"
        @click="showSetMaintenanceTimeDialog"
      >设置</a>
    </div>
    <div class="manager-content">
      <div class="manager-content-sub-title"><span>服务列表：</span></div>
      <table class="manager-table">
        <tr bgColor=#F8FCFF>
          <th width="100">服务ID</th>
          <th width="500">名称</th>
          <th width="100">交易类型</th>
          <th width="100">规格</th>
          <th width="150">服务状态</th>
          <th width="200">到期日</th>
          <th width="100">操作</th>
        </tr>
        <tr
          v-for="(hostedService, index) in hostedServicePage"
          v-bind:key="index"
        >
          <td>{{hostedService.groupId}}</td>
          <td>{{hostedService.groupName}}</td>
          <td>{{hostedService.serviceTypeCn}}</td>
          <td>{{hostedService.specNameCn}}</td>
          <td>{{hostedService.hostingServiceStatusCn}}</td>
          <td>
            <div class="hosting-expired-time">{{hostedService.expiredTimestamp}}</div>
            <div
              v-if="hostedService.hostingServiceStatus === 'EXPIRED'"
              class="hosting-attention"
            >即将释放</div>
            <div
              v-else-if="hostedService.isAboutToExpired === 1"
              class="hosting-attention"
            >即将到期</div>
          </td>
          <td>
            <a
              v-if="getRechargeStatus(hostedService) == 1"
              class="operator-text status-manage"
              href="#"
              @click="showRechargeDailog(hostedService)"
            >续费</a>
            <a
              v-if="getHostingStatus(hostedService) == 1"
              class="operator-text status-manage"
              href="#"
              @click="showManagHostingDailog(hostedService)"
            >管理</a>
            <a
              v-else-if="getHostingStatus(hostedService) == 2"
              class="operator-text status-remove"
              href="#"
              @click="showRemoveHostingDialog(hostedService)"
            >删除</a>
          </td>
        </tr>
      </table>
    </div>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="totalNum"
      :page-size="10"
      :current-page="currentPage"
      @current-change="onPageChange"
    >
    </el-pagination>
    <d-create-hosting
      v-bind:isShow="isShowCreateHostingDialog"
      v-bind:createHostingProductList="createHostingProductList"
      @close="closeCreateHostingDialog"
      @confirm="confirmCreateHosting"
    ></d-create-hosting>
    <d-manage-hosting
      v-bind:isShow="isShowManageHostingDialog"
      v-bind:hosting="selectedhostingService"
      v-bind:groupUserList="selectedHostedServiceGroupUserPage"
      v-bind:hostingProductList="createHostingProductList"
      @close="closeManageHostingDialog"
      @confirm="confirmManageHosting"
      @afterPay="onAfterPay"
    ></d-manage-hosting>
    <d-remove-hosting
      v-bind:isShow="isShowRemoveHostingDialog"
      title="删除托管机"
      v-bind:msg="removeHostingDialogMsg"
      @close="closeRemoveHostingDialog"
      @cancel="cancelRemoveHosting"
      @confirm="confirmRemoveHosting"
    ></d-remove-hosting>
    <d-submit-order-success
      v-bind:isShow="isShowSubmitOrderSuccessDialog"
      v-bind:orderId="confirmOrderId"
      @close="closeSubmitOrderSuccessDialog"
      @confirm="confirmSubmitOrderSuccessDialog"
    ></d-submit-order-success>
    <d-set-maintenance-time
      v-bind:isShow="isShowSetMaintenanceTimeDialog"
      v-bind:serviceMaintenanceTimeSpans="serviceMaintenanceTimeSpans"
      @close="cancelSetMaintenanceTime"
      @confirm="confirmSetMaintenanceTime"
    ></d-set-maintenance-time>
    <d-recharge-hosting
      v-bind:isShow="isShowRechargeHostingDialog"
      v-bind:hosting="selectedhostingService"
      v-bind:hostingProductList="rechargeHostingProductList"
      @close="closeRechargeHostingDialog"
      @confirm="confirmRechargeHosting"
    ></d-recharge-hosting>
    <d-tips
      v-bind:isShow="isShowTips"
      v-bind:msg="tipMsg"
      @close="closeTips"
      @confirm="confirmTips"
    ></d-tips>
  </div>
</template>

<script>
import DCreateHostingDialog from "../components/dialog/CreateHostingDialog";
import DManageHostingDialog from "../components/dialog/ManageHostingDialog";
import DConfirmWithCancelDialog from "../components/dialog/ConfirmWithCancelDialog";
import DSubmitOrderSuccessDialog from "../components/dialog/SubmitOrderSuccessDialog";
import DTips from "../components/dialog/TipsDialog";
import DSetMaintenanceTime from "../components/dialog/SetMaintenanceTimeDialog";
import DRechargeHostingDialog from "../components/dialog/RechargeHostingDialog";
export default {
  components: {
    "d-tips": DTips,
    "d-create-hosting": DCreateHostingDialog,
    "d-manage-hosting": DManageHostingDialog,
    "d-remove-hosting": DConfirmWithCancelDialog,
    "d-submit-order-success": DSubmitOrderSuccessDialog,
    "d-set-maintenance-time": DSetMaintenanceTime,
    "d-recharge-hosting": DRechargeHostingDialog
  },
  data() {
    return {
      // page
      totalNum: 35,
      totalPage: 1,
      currentPage: 1,

      // const
      MAINTENANCE_TIMESPAN: this.$XQConfig.MAINTENANCE_TIMESPAN.LABELS,

      // data
      hostedServicePage: [],
      createHostingProductList: [],
      selectedhostingService: null,
      selectedHostedServiceGroupUserPage: null,
      removeHostingServiceInfo: null,
      serviceMaintenanceTimeSpans: [],
      rechargeHostingProductList: [],

      // dialog flag
      isShowTips: false,
      tipMsg: "",
      isShowCreateHostingDialog: false,
      isShowManageHostingDialog: false,
      isShowModifyUserRoleDialog: false,
      isShowRemoveHostingDialog: false,
      removeHostingDialogMsg: "",
      isShowSubmitOrderSuccessDialog: false,
      isShowSetMaintenanceTimeDialog: false,
      isShowRechargeHostingDialog: false,

      // confirm order
      confirmOrderId: 0
    };
  },
  mounted() {
    if (this.$util.checkLoginState("/manager/hosting") != "company") {
      return;
    }

    this.queryHostedServiceList();
    this.queryMaintenanceTimeSpan();
    // this.queryNewHostedServiceProductList()

    // var that = this;
    // this.$commonFunc.onCreatedOrder(function() {
    //   var orderId = that.$XQLocalStorage.getTempOrderId();
    //   alert("onCreatedOrder ---- orderId : " + orderId)
    //   if (orderId) {
    //     that.showSubmitOrderSuccessDialog(orderId);
    //   }
    // });
    // 监听新窗口的订单提交完成事件
    // var that = this;
    // window.addEventListener(
    //   "storage",
    //   function(e) {
    //     if (e.key === "orderstatus" && e.oldValue === "submit") {
    //       that.isShowCreateHostingDialog = false;
    //       that.isShowManageHostingDialog = false;
    //       var orderId = e.newValue;
    //       that.showSubmitOrderSuccessDialog(orderId);
    //       that.$XQLocalStorage.setLocalStorage("orderstatus", null);
    //     }
    //   },
    //   false
    // );
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
    onPageChange(currentPage) {
      this.currentPage = currentPage;
      this.queryHostedServiceList();
      // alert(currentPage)
    },
    queryHostedServiceList() {
      var that = this;
      // var params = new URLSearchParams();
      // params.append("method", "GetSimpleHostedServices");
      // params.append("pageIndex", this.currentPage - 1);
      var params = {
        pageIndex: this.currentPage - 1
      };
      this.$http.post("GetSimpleHostedServices", params, function(response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          if (response.value) {
            that.totalNum = response.value.total;
            that.totalPage = Math.floor((that.totalNum + 9) / 10);
            if (that.totalPage < 1) {
              that.totalPage = 1;
            }
            that.hostedServicePage = response.value.page;
          } else {
            alert(
              "GetSimpleHostedServices get result fail : " +
                JSON.stringify(response)
            );
          }
        }
      });
    },
    queryNewHostedServiceProductList(reponse) {
      var that = this;
      // var params = new URLSearchParams();
      // params.append("method", "GetNewHostedServiceProducts");
      var params = {};
      this.$http.post("GetNewHostedServiceProducts", params, function(
        response
      ) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          that.createHostingProductList = response.results;
          reponse();
        }
      });
    },
    queryCompanyGroupUsers(groupId) {
      var that = this;
      // var params = new URLSearchParams();
      // params.append("method", "GetCompanyGroupUsers");
      // params.append("groupId", groupId);
      var params = {
        groupId: groupId
      };
      this.$http.post("GetCompanyGroupUsers", params, function(response) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          if (response.value) {
            that.selectedHostedServiceGroupUserPage = response.value.page;
          } else {
            alert("GetCompanyGroupUsers fail : " + JSON.stringify(reponse));
          }
        }
      });
    },
    getHostingStatus(hostedService) {
      if (
        hostedService.hostingServiceStatus === "OPENING" ||
        hostedService.hostingServiceStatus === "UPGRADING" ||
        hostedService.hostingServiceStatus === "WORKING" ||
        hostedService.hostingServiceStatus === "EXPIRED"
      ) {
        return 1;
      } else if (
        hostedService.hostingServiceStatus === "WAITING" ||
        hostedService.hostingServiceStatus === "RELEASED"
      ) {
        return 2;
      } else {
        return 0;
      }
    },
    getRechargeStatus(hostedService) {
      if (
        hostedService.hostingServiceStatus === "UPGRADING" ||
        hostedService.hostingServiceStatus === "WORKING"
      ) {
        return 1;
      } else {
        return 0;
      }
    },
    showCreateHostingDialog() {
      var that = this;
      this.queryNewHostedServiceProductList(function() {
        that.isShowCreateHostingDialog = true;
      });
    },
    closeCreateHostingDialog() {
      this.isShowCreateHostingDialog = false;
    },
    confirmCreateHosting() {
      this.isShowCreateHostingDialog = false;
      this.queryOrderStatus();
    },
    queryOrderStatus() {
      var time = 10;
      var that = this;
      var timer = setInterval(function() {
        if (time === 0) {
          clearInterval(timer);
        } else {
          time--;
          var orderId = that.$XQLocalStorage.getTempOrderId();
          if (orderId) {
            clearInterval(timer);
            that.queryHostedServiceList();
            that.showSubmitOrderSuccessDialog(orderId);
          }
        }
      }, 1000);
    },

    showManagHostingDailog(hostedService) {
      this.queryCompanyGroupUsers(hostedService.groupId);
      this.selectedhostingService = hostedService;
      this.isShowManageHostingDialog = true;
    },
    closeManageHostingDialog() {
      this.isShowManageHostingDialog = false;
    },
    confirmManageHosting() {
      this.isShowManageHostingDialog = false;
      this.queryHostedServiceList();
    },
    showRemoveHostingDialog(hostedService) {
      if (hostedService.hostingServiceStatus === "WAITING") {
        // check order status
        var that = this;
        // var params = new URLSearchParams();
        // params.append("method", "GetValidOrdersByGroupId");
        // params.append("groupId", hostedService.groupId);
        var params = {
          groupId: hostedService.groupId
        };
        this.$http.post("GetValidOrdersByGroupId", params, function(response) {
          // alert('queryCompanyGroupUsers : ' + JSON.stringify(response))
          if (response.errorMsg) {
            that.tipMsg = response.errorMsg;
            that.showTips();
          } else if (response.errorInfo) {
            that.tipMsg = response.errorInfo.errMsg;
            that.showTips();
          } else {
            if (response.results.length > 0) {
              that.tipMsg = "托管机有关联订单，请先取消或删除订单";
              that.showTips();
            } else {
              that.removeHostingDialogMsg =
                "确定删除托管机 " + hostedService.groupName + " ?";
              that.removeHostingServiceInfo = hostedService;
              that.isShowRemoveHostingDialog = true;
            }
          }
        });
      } else {
        this.removeHostingDialogMsg =
          "确定删除托管机 " + hostedService.groupName + " ?";
        this.removeHostingServiceInfo = hostedService;
        this.isShowRemoveHostingDialog = true;
      }
    },
    closeRemoveHostingDialog() {
      this.isShowRemoveHostingDialog = false;
    },
    cancelRemoveHosting() {
      this.isShowRemoveHostingDialog = false;
    },
    confirmRemoveHosting() {
      var that = this;
      // var params = new URLSearchParams();
      // params.append("method", "RemoveHostedService");
      // params.append("groupId", this.removeHostingServiceInfo.groupId);
      var params = {
        groupId: this.removeHostingServiceInfo.groupId
      };
      this.$http.post("RemoveHostedService", params, function(response) {
        // alert('queryCompanyGroupUsers : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          that.tipMsg = "删除成功";
          that.showTips();
          that.queryHostedServiceList();
          that.$eventBus.$emit('updateGroupUser')
        }
        that.isShowRemoveHostingDialog = false;
      });
    },
    showSubmitOrderSuccessDialog(orderId) {
      this.confirmOrderId = orderId;
      this.isShowSubmitOrderSuccessDialog = true;
    },
    closeSubmitOrderSuccessDialog() {
      this.isShowSubmitOrderSuccessDialog = false;
    },
    confirmSubmitOrderSuccessDialog() {
      this.queryHostedServiceList();
      this.isShowSubmitOrderSuccessDialog = false;
    },
    onAfterPay() {
      this.isShowManageHostingDialog = false;
      this.queryOrderStatus();
    },
    showSetMaintenanceTimeDialog() {
      this.isShowSetMaintenanceTimeDialog = true;
    },
    confirmSetMaintenanceTime() {
      this.isShowSetMaintenanceTimeDialog = false;
      this.queryMaintenanceTimeSpan();
    },
    cancelSetMaintenanceTime() {
      this.isShowSetMaintenanceTimeDialog = false;
    },
    queryMaintenanceTimeSpan() {
      var that = this;
      // var params = new URLSearchParams();
      // params.append("method", "GetServiceMaintenance");
      var params = {};
      this.$http.post("GetServiceMaintenance", params, function(response) {
        // alert('queryMaintenanceTimeSpan : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          if (response.value) {
            that.serviceMaintenanceTimeSpans =
              response.value.maintenanceTimeSpans;
            if (!that.serviceMaintenanceTimeSpans) {
              that.serviceMaintenanceTimeSpans = [];
            }
          } else {
            alert(
              "GetServiceMaintenance get result fail : " +
                JSON.stringify(response)
            );
          }
        }
      });
    },
    queryRechargeProductList (groupId) {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'GetRechargeTimeProducts')
      // params.append('groupId', this.hosting.groupId)
      var params = {
        'groupId' : groupId
      }
      this.$http.post('GetRechargeTimeProducts', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          that.rechargeHostingProductList = response.results
          if (that.rechargeHostingProductList && that.rechargeHostingProductList.length > 0) {
            that.isShowRechargeHostingDialog = true
          } else {
            that.tipMsg = '没有续费服务，请联系客服'
            that.showTips()
          }
        }
      })
    },
    showRechargeDailog(hostedService) {
      this.queryRechargeProductList(hostedService.groupId);
      this.selectedhostingService = hostedService;
      this.isShowRechargeHostingDialog = true;
    },
    closeRechargeHostingDialog () {
      this.isShowRechargeHostingDialog = false
    },
    confirmRechargeHosting () {
      this.isShowRechargeHostingDialog = false
      this.queryOrderStatus();
    }
  }
};
</script>

<style scoped>
.manager {
  background-color: rgba(255, 255, 255, 1);
  min-width: 1390px;
}
.manager-menu {
  display: flex;
  align-content: center;
}
.manager-menu-maintenance {
  display: flex;
  align-content: center;
  margin-top: 30px;
}
.button-add-hosting {
  width: 133px;
  height: 32px;
  margin-left: 30px;
  line-height: 14px;
  color: rgba(255, 255, 255, 1);
  font-size: 14px;
  font-family: STHeitiSC-Medium;
}
.hosting-expired-time {
  height: 16px;
  line-height: 16px;
  color: rgba(144, 144, 144, 1);
  font-size: 16px;
  text-align: center;
  font-family: STHeitiSC-Light;
}
.hosting-attention {
  height: 16px;
  line-height: 16px;
  opacity: 0.75;
  color: rgba(255, 124, 27, 1);
  font-size: 16px;
  text-align: center;
  font-family: STHeitiSC-Light;
}
.operator-text {
  padding-left: 3px;
  padding-right: 3px;
}
.status-manage {
  line-height: 16px;
  color: rgba(54, 158, 239, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.status-remove {
  line-height: 16px;
  color: rgba(255, 124, 27, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.manager-content-sub-title {
  display: flex;
  align-content: center;
  color: rgba(80, 80, 80, 1);
  font-size: 18px;
  text-align: left;
  font-family: STHeitiSC-Light;
  margin-bottom: 10px;
}
.maintenance-key {
  margin-left: 30px;
  width: 126px;
  height: 18px;
  color: rgba(80, 80, 80, 1);
  font-size: 18px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.maintenance-value {
  height: 18px;
  color: rgba(80, 80, 80, 1);
  font-size: 18px;
  text-align: left;
  font-family: STHeitiSC-Light;
  margin-left: 15px;
  margin-right: 15px;
}
.maintenance-setting {
  margin-left: 30px;
  width: 36px;
  height: 18px;
  color: rgba(0, 117, 255, 1);
  font-size: 18px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
</style>
