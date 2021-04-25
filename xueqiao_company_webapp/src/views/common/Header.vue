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
      <div class="header-tab-item-active"><span>雪橇投资管理系统</span></div>
      <div><a
          class="header-tab-item"
          href="#"
          @click="gotoTouyanMainPage"
        ><span>雪橇投研</span></a></div>
    </div>
    <div class="nav-menu">
      <div class="nav-menu-item">
        <router-link
          class="nav-menu-item-inner"
          to="/home"
        >首页</router-link>
      </div>
      <div
        class="nav-menu-item"
        v-if="false"
      >
        <router-link
          class="nav-menu-item-inner"
          to="/doc"
        >产品说明</router-link>
      </div>
      <div
        class="nav-menu-item"
        v-if="!isLogin"
      >
        <router-link
          class="nav-menu-item-inner"
          to="/login"
        >登录</router-link>
      </div>
      <div
        class="nav-menu-item"
        v-if="!isLogin"
      >
        <router-link
          class="nav-menu-item-inner"
          to="/register"
        >立即注册</router-link>
      </div>

      <div
        class="nav-menu-item dropdown-menu nav-menu-item-inner"
        v-if="isLogin"
      >
        <div class="nav-menu-text-wrapper">
          <div class="nav-menu-item-text">
            <!-- {{$store.state.companyInfo.companyCode}} -->
            {{loginInfo.userName}}
          </div>
          <div class="icon-down-wrapper">
            <span class="icon-down"></span>
          </div>
        </div>
        <div class="dropdown-content">
          <div
            v-if="isPersonalUser"
            class="dropdown-content-item"
            v-bind:class="{'dropdown-content-item-selected':($store.state.currentPath === '/personal/hosting')}"
          >
            <router-link
              class="dropdown-content-item-inner"
              to="/personal/hosting"
            >我的云服务</router-link>
          </div>
          <div
            v-else
            class="dropdown-content-item"
            v-bind:class="{'dropdown-content-item-selected':($store.state.currentPath === '/manager/hosting')}"
          >
            <router-link
              class="dropdown-content-item-inner"
              to="/manager/hosting"
            >交易云服务</router-link>
          </div>
          <div
            v-if="isPersonalUser"
            class="dropdown-content-item"
            v-bind:class="{'dropdown-content-item-selected':($store.state.currentPath === '/personalUserChangePassword')}"
          >
            <router-link
              class="dropdown-content-item-inner"
              to="/personalUserChangePassword"
            >修改密码</router-link>
          </div>
          <!-- <div class="dropdown-content-item" v-bind:class="{'dropdown-content-item-selected':($store.state.currentPath === '/manager/charge')}">
            <router-link to="/manager/charge">充值中心</router-link>
          </div> -->
          <div
            v-if="isCompanyUser"
            class="dropdown-content-item"
            v-bind:class="{'dropdown-content-item-selected':($store.state.currentPath === '/manager/order')}"
          >
            <router-link
              class="dropdown-content-item-inner"
              to="/manager/order"
            >订单列表</router-link>
          </div>
          <div
            v-if="isCompanyUser"
            class="dropdown-content-item"
            v-bind:class="{'dropdown-content-item-selected':($store.state.currentPath === '/manager/companyinfo')}"
          >
            <router-link
              class="dropdown-content-item-inner"
              to="/manager/companyinfo"
            >公司资料</router-link>
          </div>
        </div>
      </div>
      <div
        class="nav-menu-item"
        v-if="isLogin"
        @click="showConfirmLogoutDialog"
      >
        <a
          class="nav-menu-item-inner"
          href="#"
        >退出登录</a>
      </div>
    </div>
    <d-confirm
      v-bind:isShow="isShowConfirmDialog"
      title="退出登录"
      msg="是否确认退出登录？"
      @close="closeConfirmDialog"
      @confirm="confirmLogout"
    ></d-confirm>
    <d-tips
      v-bind:isShow="isShowTips"
      v-bind:msg="tipMsg"
      @close="closeTips"
      @confirm="confirmTips"
    ></d-tips>
  </div>
</template>

<script>
import DConfirm from "../../components/dialog/ConfirmDialog";
import DTips from "../../components/dialog/TipsDialog";
export default {
  components: {
    "d-confirm": DConfirm,
    "d-tips": DTips
  },
  data() {
    return {
      isShowConfirmDialog: false,
      isShowTips: false,
      tipMsg: "",
      // login info
      loginInfo: {
        userName: "",
        userId: "",
        token: "",
        isLogin: false
      },
      isLogin: false,
      isPersonalUser: false,
      isCompanyUser: true
    };
  },
  mounted() {
    this.getLoginInfo();
    var that = this;
    // this.$commonFunc.onBackendCall(() => {
    // alert('recieve backend call event')
    // that.getLoginInfo()
    // })
    this.$commonFunc.onLoginSuccess(function() {
      that.getLoginInfo();
    });
    this.$commonFunc.onSessionTimeout(function() {
      that.getLoginInfo();
    });
  },
  methods: {
    gotoTouyanMainPage() {
      this.$router.push("/touyan");
    },
    showConfirmLogoutDialog() {
      this.isShowConfirmDialog = true;
    },
    closeConfirmDialog() {
      this.isShowConfirmDialog = false;
    },
    showTips() {
      this.isShowTips = true;
    },
    closeTips() {
      this.isShowTips = false;
      // go to home page after logout
      this.$router.push("/home");
    },
    confirmTips() {
      this.isShowTips = false;
      // go to home page after logout
      this.$router.push("/home");
    },
    confirmLogout() {
      this.isShowConfirmDialog = false;
      // logout
      var that = this;
      // var params = new URLSearchParams()
      // params.append('method', 'Logout')
      var params = {};
      this.$http.post("Logout", params, function(response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          that.$router.push("/home");
          // that.$store.commit('setLoginInfo', response.value)
          //   that.tipMsg = '已退出登录'
          //   that.showTips()
        }
        that.clearLoginInfo();
      });
    },
    clearLoginInfo() {
      // this.$store.commit('setLoginInfo', null)
      this.$XQLocalStorage.clearLoginInfo();
      this.getLoginInfo();
    },
    getLoginInfo() {
      this.loginInfo = this.$XQLocalStorage.getLoginInfo();
      // alert("getLoginInfo : " + JSON.stringify(this.loginInfo))
      this.isLogin = this.loginInfo.isLogin === "true";
      this.isPersonalUser = this.loginInfo.userType === "personal";
      this.isCompanyUser = this.loginInfo.userType !== "personal";
      // alert("getLoginInfo userType: " + this.loginInfo.userType)
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
