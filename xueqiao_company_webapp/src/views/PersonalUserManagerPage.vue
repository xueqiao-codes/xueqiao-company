<template>
  <div class="manager">
    <div class="left-side-menu">
      <div
        class="side-menu-item"
        v-bind:class="{'side-menu-item-selected':($store.state.currentPath === '/personal/hosting')}"
      >
        <router-link
          to="/personal/hosting"
          class="side-menu-item-inner"
        >
          <img src='@/assets/icon_hosting.png'>
          <span class="side-menu-item-text">我的云服务</span>
        </router-link>
      </div>
    </div>
    <router-view></router-view>
    <!-- <f-usermanager class="right-side-menu"></f-usermanager> -->
  </div>
</template>

<style scoped>
.manager {
  background-color: rgba(205, 205, 205, 1);
  display: flex;
  /* justify-content: space-between; */
  min-width: 1920px;
}
.left-side-menu {
  /* position: absolute; */
  width: 260px;
  height: 1020px;
  background-color: rgba(50, 58, 85, 1);
}
.right-side-menu {
  float: right;
  /* position: absolute; */
  width: 260px;
}
.side-menu-item {
  height: 53px;
  width: 260px;
  line-height: 21px;
  /* padding-left: 30px; */
  /* background: red; */
  color: rgba(255, 255, 255, 1);
  font-size: 20px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.side-menu-item-inner {
  display: inline-flex;
  height: 53px;
  width: 230px;
  /* padding-top: 15px; */
  padding-left: 30px;
  color: rgba(255, 255, 255, 1);
  font-size: 20px;
  text-align: left;
  font-family: STHeitiSC-Light;
  align-content: center;
  align-items: center;
  /* background: red; */
}
.side-menu-item-text {
  line-height: 21px;
  margin-left: 20px;
  color: rgba(255, 255, 255, 1);
  font-size: 20px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.side-menu-item:hover {
  background-color: #00acff;
}
.side-menu-item-selected {
  background-color: #0075ff;
}
</style>
