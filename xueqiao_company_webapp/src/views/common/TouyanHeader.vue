<template>
  <div class="header">
    <div class="nav-title">
      <img
        class="nav-title-logo"
        src='../../assets/logo.png'
      />
      <div class="nav-title-text">
        雪橇科技
      </div>
    </div>
    <div class="header-tab-container">
      <div><a
          class="header-tab-item"
          href="#"
          @click="gotoMainPage"
        ><span>雪橇投资管理系统</span></a></div>
      <div class="header-tab-item-active"><span>雪橇投研</span></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  mounted() {},
  methods: {
    gotoMainPage() {
      this.$router.push("/home");
    }
  }
};
</script>

<style scoped>
a {
  text-decoration: none;
}
a:hover,
a:visited,
a:link,
a:active {
  text-decoration: none;
  color: rgba(205, 205, 205, 0.7);
  /* color: rgba(54, 158, 239, 1); */
}
.header {
  height: 60px;
  min-width: 1920px;
  background-color: rgba(27, 37, 70, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.1);
}
.header-tab-container {
  float: left;
  display: flex;
  height: 100%;
  margin-left: 100px;
}
.header-tab-item {
  display: flex;
  height: 100%;
  align-items: center;
  color: rgba(255, 255, 255, 0.3);
  font-size: 18px;
  font-family: STHeitiSC-Light;
  margin-left: 15px;
  margin-right: 15px;
}
.header-tab-item-active {
  display: flex;
  height: 100%;
  align-items: center;
  color: rgba(255, 255, 255, 0.3);
  font-size: 18px;
  font-family: STHeitiSC-Light;
  margin-left: 15px;
  margin-right: 15px;
  color: rgba(255, 255, 255, 1);
}
.main-bg {
  position: relative;
  margin: 0 auto;
}
.nav-title {
  float: left;
  height: 60px;
  display: flex;
  align-items: center;
  margin-left: 40px;
}
.nav-title-logo {
  width: 40px;
  height: 40px;
}
.nav-title-text {
  height: 24px;
  line-height: 25px;
  color: rgba(255, 255, 255, 1);
  font-size: 24px;
  text-align: left;
  margin-left: 10px;
  font-family: STHeitiSC-Medium;
}
.nav-menu {
  float: right;
  height: 60px;
  margin-right: 40px;
  display: flex;
  align-items: center;
  /* orientation: landscape; */
}
.nav-menu-item {
  line-height: 60px;
  height: 60px;
  max-width: 200px;
  color: rgba(205, 205, 205, 0.7);
  font-size: 18px;
  text-align: left;
  /* padding-left: 20px;
  padding-right: 20px; */
  font-family: STHeitiSC-Medium;
}
.nav-menu-item-inner {
  display: inline-flex;
  line-height: 60px;
  height: 60px;
  padding-left: 20px;
  padding-right: 20px;
  /* align-content: center;
  align-items: center; */
}
.nav-menu-item:hover {
  background-color: rgba(0, 0, 0, 1);
}
.nav-menu-iem-selected {
  color: rgba(255, 255, 255, 1);
}
.nav-menu-text-wrapper {
  display: flex;
}
.nav-menu-item-text {
  height: 60px;
  max-width: 150px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.dropdown-menu {
  position: relative;
  display: inline-block;
  z-index: 1000;
}
.dropdown-content {
  display: none;
  position: absolute;
  min-width: 160px;
  background-color: rgba(50, 58, 85, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.5);
  left: 0;
}
.dropdown-content-item {
  height: 40px;
  text-align: left;
  color: rgba(205, 205, 205, 0.7);
  font-size: 16px;
  padding-bottom: 20px;
  /* padding-left: 20px; */
  text-decoration: none;
}
.dropdown-content-item-inner {
  display: inline-flex;
  height: 40px;
  min-width: 140px;
  padding-bottom: 20px;
  padding-left: 20px;
  /* padding-left: 20px;
  padding-right: 20px; */
}
.dropdown-content-item-selected {
  background-color: #0075ff;
}
.dropdown-content-item:hover {
  background-color: rgba(0, 0, 0, 1);
}
.dropdown-menu:hover .dropdown-content {
  display: block;
}
.icon-down-wrapper {
  display: inline-flex;
  align-items: center;
  margin-left: 5px;
}
.icon-down {
  float: right;
  width: 0;
  height: 0;
  border: 4px solid transparent;
  border-top-color: #b0b0b0;
  text-align: center;
}
</style>
