import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import api from "./func/api/api"
import cookie from "./func/storage/cookie"
import config from "./func/config/config.js"
import util from "./func/util/util.js"
import timeUtil from "./func/util/timeUtil.js"
// import common from "./func/common/common.js"
import "./assets/css/global.css"
import "./assets/css/dialog.css"

Vue.config.productionTip = false
Vue.prototype.$api = api
Vue.prototype.$cookie = cookie
Vue.prototype.$config = config
Vue.prototype.$util = util
Vue.prototype.$timeUtil = timeUtil
// Vue.prototype.$common = common

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
