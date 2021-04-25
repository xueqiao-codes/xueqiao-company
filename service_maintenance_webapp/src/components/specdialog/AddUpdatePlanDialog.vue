<template>
  <div
    v-if="isShow"
    class="dialog-bg"
  >
    <div class="dialog-container">
      <div class="dialog-header">
        <div class="dialog-title">添加升级计划</div>
        <div class="dialog-close">
          <a
            href="#"
            @click="close"
          ><img src="../../assets/img/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content">
        <div class="input-item">
          <div class="input-key">
            <span>选择版本：</span>
          </div>
          <div class="input-value my-input-value">
            <el-select
              v-model="selectedTargetVersionId"
              placeholder="请选择"
              class="my-input-value"
            >
              <el-option
                v-for="item in upgradeVersionInfoList"
                :key="item.versionId"
                :label="item.versionTag"
                :value="item.versionId"
              >
              </el-option>
            </el-select>
          </div>
        </div>
      </div>
      <div class="dialog-button-container">
        <el-button
          type="primary"
          size="medium"
          @click="confirm"
        >确定</el-button>
        <el-button
          size="medium"
          @click="close"
        >取消</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["isShow", "companyIds"],
  data() {
    return {
      // selected data
      selectedTargetVersionId: null,

      // data
      upgradeVersionInfoList: []
    };
  },
  mounted() {},
  watch: {
    isShow() {
      if (this.isShow) {
        this.projectName = null;
        this.projectDescription = null;

        this.queryCompanyUpgradeVersion();
      }
    }
  },
  methods: {
    close() {
      this.$emit("close");
    },
    confirm() {
      // this.$emit("confirm");
      this.doAddition();
    },
    checkParams() {
      if (!this.companyIds) {
        alert("页面过期，请刷新后再尝试");
        return 1;
      }
      if (!this.selectedTargetVersionId) {
        alert("请选择版本");
        return 1;
      }
      return 0;
    },
    doAddition() {
      if (this.checkParams()) {
        return;
      }

      var that = this;
      var upgradeSchedule = new NewUpgradeSchedule();
      upgradeSchedule.companyIds = this.companyIds;
      upgradeSchedule.targetVersionId = this.selectedTargetVersionId;
      upgradeSchedule.operator = this.$util.getOaUserName()

      this.$api.addUpgradeSchedule(upgradeSchedule, function(response) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          alert("添加成功");
          that.$emit("confirm");
        }
      });
    },
    queryCompanyUpgradeVersion() {
      var that = this;
      // alert("this.companyIds : " + JSON.stringify(this.companyIds))
      var companyIds = this.companyIds;
      this.$api.reqCompanyUpgradeVersion(companyIds, function(response) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          if (response == null || response.length < 1) {
            that.upgradeVersionInfoList = [];
            return;
          }
          that.upgradeVersionInfoList = response;
        }
      });
    }
  }
};
</script>

<style scoped>
.my-input-value {
  width: 300px;
}
</style>

