<template>
  <div
    v-if="isShow"
    class="dialog-bg"
  >
    <div class="dialog-container">
      <div class="dialog-header">
        <div class="dialog-title">设置公司版本(公司ID：{{maintenance.companyId}})</div>
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
            <span>是否跟随新最版本：</span>
          </div>
          <div class="input-value my-input-value">
            <el-checkbox label="跟随最新版本" name="type" v-model="keepLatest"></el-checkbox>
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
      keepLatest: false,
      upgradeVersionInfoList: []
    };
  },
  mounted() {},
  watch: {
    isShow() {
      if (this.isShow && this.maintenance) {
        this.keepLatest = this.maintenance.keepLatest
      }
    }
  },
  methods: {
    close() {
      this.$emit("close");
    },
    confirm() {
      this.doSetVersionTag();
    },
    checkParams() {
      if (!this.maintenance) {
        alert("页面过期，请刷新后再尝试");
        return 1;
      }
      return 0;
    },
    doSetVersionTag() {
      if (this.checkParams()) {
        return;
      }

      var that = this;
      this.$api.updateKeepLatestTag(this.maintenance.companyId, this.keepLatest, function(response) {
        if (response != null && response.errorMsg) {
          alert(response.errorMsg);
        } else {
          alert("设置成功");
          that.$emit("confirm");
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

