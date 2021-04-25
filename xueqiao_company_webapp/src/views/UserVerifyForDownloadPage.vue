<template>
  <div class="fragment-bg">
    <div class="fragment-sub-bg">
      <div class="fragment-tab">
        <div :class=getTabClass(0)><a
            class="fragment-tab-item-text"
            href="#"
            @click="tabClick(0)"
          >机构用户验证</a></div>
        <div :class=getTabClass(1)><a
            class="fragment-tab-item-text"
            href="#"
            @click="tabClick(1)"
          >个人用户验证</a></div>
      </div>
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
    };
  },
  mounted() {
    var loginInfo = this.$XQLocalStorage.getLoginInfo();
    this.isLogin = loginInfo.isLogin === "true";
    // 如果已经处于登录状态，则跳转到主页
    if (this.isLogin) {
      this.$commonFunc.sendBackendCallEvent();
      // this.$router.push("/download");
      var userId = this.$XQLocalStorage.getLocalStorage("userId");
      if (userId && userId > 0) {
        var userType = this.$XQLocalStorage.getLocalStorage("userType");
        if (userType == "personal") {
          this.$router.push({name: "clientAppDownloadForPersonalUserPage",});
        } else {
          this.$router.push("/userverify/company");
        }
      }
    } else {
      this.$router.push("/userverify/company");
    }
  },
  methods: {
    tabClick(tabIndex) {
      if (tabIndex == 0) {
        this.$router.push("/userverify/company");
      } else {
        this.$router.push("/userverify/personal");
      }
    },
    getTabClass(tabIndex) {
      if (tabIndex == this.$store.state.tabIndex.clientAppDownload) {
        return "fragment-tab-item-active";
      } else {
        return "fragment-tab-item";
      }
    }
  }
};
</script>

<style scoped>
.fragment-tab {
  display: flex;
}
.fragment-tab-item {
  width: 100%;
  height: 48px;
  background-color: rgba(221, 221, 221, 1);
}
.fragment-tab-item-active {
  width: 100%;
  height: 48px;
  background-color: rgba(255, 255, 255, 1);
}
.fragment-tab-item-text {
  display: block;
  width: 100%;
  height: 100%;
  margin-top: 13px;
  font-size: 16px;
  color: rgba(80, 80, 80, 1);
}
</style>
