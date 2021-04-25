<template>
  <div
    v-if="isShow"
    class="dialog-bg"
  >
    <div class="dialog-container">
      <div class="dialog-header">
        <div class="dialog-title">初始化公司版本(公司ID：{{maintenance.companyId}})</div>
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
            <span>初始版本：</span>
          </div>
          <div class="input-value my-input-value">
            <el-select
              v-model="selectedTargetVersionId"
              placeholder="请选择"
              class="my-input-value"
            >
              <el-option
                v-for="item in initVersionInfoList"
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
  props: ["isShow", "maintenance"],
  data() {
    return {
      // data
      selectedTargetVersionId: null,
      initVersionInfoList: []
    };
  },
  mounted() {},
  watch: {
    isShow() {
      if (this.isShow && this.maintenance) {
        this.queryServerVersions();
      }
    }
  },
  methods: {
    close() {
      this.$emit("close");
    },
    confirm() {
      this.doInit();
    },
    checkParams() {
      if (!this.maintenance) {
        alert("页面过期，请刷新后再尝试");
        return 1;
      }
      if (!this.selectedTargetVersionId) {
        alert("请选择初始版本");
        return 1;
      }
      return 0;
    },
    doInit() {
      if (this.checkParams()) {
        return;
      }

      var that = this;
      this.$api.initCompanyVersion(
        this.maintenance.companyId,
        this.selectedTargetVersionId,
        function(response) {
          if (response != null && response.errorMsg) {
            alert(response.errorMsg);
          } else {
            alert("设置成功");
            that.$emit("confirm");
          }
        }
      );
    },
    queryServerVersions() {
      var that = this;
      var companyId = this.companyId;
      this.$api.reqServerVersions(function(response) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          if (response == null || response.length < 1) {
            that.initVersionInfoList = [];
            return;
          }
          that.initVersionInfoList = response;
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

