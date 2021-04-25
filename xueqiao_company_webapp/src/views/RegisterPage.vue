<template>
  <div class="fragment-bg">
    <div class="fragment-sub-bg">
      <div class="fragment-tab">
        <div :class=getTabClass(0)><a
            class="fragment-tab-item-text"
            href="#"
            @click="tabClick(0)"
          >机构用户注册</a></div>
        <div :class=getTabClass(1)><a
            class="fragment-tab-item-text"
            href="#"
            @click="tabClick(1)"
          >个人用户注册</a></div>
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
  methods: {
    tabClick(tabIndex) {
      if (tabIndex == 0) {
        this.$router.push("/register/company");
      } else {
        this.$router.push("/register/personal");
      }
    },
    getTabClass(tabIndex) {
      if (tabIndex == this.$store.state.tabIndex.register) {
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
