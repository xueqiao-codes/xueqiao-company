<template>
  <div class="manager">
    <div class="left-side-menu">
      <div class="side-menu-item" v-bind:class="{'side-menu-item-selected':($store.state.currentPath === '/manager/hosting')}">
        <router-link to="/manager/hosting" class="side-menu-item-inner">
          <img src='@/assets/icon_hosting.png'>
          <span class="side-menu-item-text">交易云服务</span>
        </router-link>
      </div>
      <!-- <div class="side-menu-item"  v-bind:class="{'side-menu-item-selected':($store.state.currentPath === '/manager/charge')}">
        <router-link to="/manager/charge" class="side-menu-item-inner">
          <img src='@/assets/icon_charge.png'>
          <span class="side-menu-item-text">充值中心</span>
        </router-link>
      </div> -->
      <div class="side-menu-item"  v-bind:class="{'side-menu-item-selected':($store.state.currentPath === '/manager/order')}">
        <router-link to="/manager/order" class="side-menu-item-inner">
          <img src='@/assets/icon_order.png'>
          <span class="side-menu-item-text">订单列表</span>
        </router-link>
      </div>
      <div class="side-menu-item"  v-bind:class="{'side-menu-item-selected':($store.state.currentPath === '/manager/companyinfo')}">
        <router-link to="/manager/companyinfo" class="side-menu-item-inner">
          <img src='@/assets/icon_info.png'>
          <span class="side-menu-item-text">公司资料</span>
        </router-link>
      </div>
    </div>
    <router-view></router-view>
    <f-usermanager class="right-side-menu"></f-usermanager>
  </div>
</template>

<script>
import FUserManager from './common/UserManager'
export default {
  components: {
    'f-usermanager': FUserManager
  },
  mounted() {
    if (this.$util.checkLoginState("/manager") != "company") {
      return;
    }
  }
}
</script>

<style scoped>
.manager {
  background-color: rgba(205, 205, 205, 1);
  display: flex;
  justify-content: space-between;
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
