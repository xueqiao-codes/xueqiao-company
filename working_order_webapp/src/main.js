import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './plugins/store'
import "./plugins/element"

import api from "./js/api/api"
import config from "./js/config/config"
import cookie from "./js/storage/cookie"
import cache from "./js/storage/cache"
import timeUtil from "./js/util/timeUtil"
import util from "./js/util/util"
import apiUtil from "./js/util/apiUtil"
import strings from "./js/config/strings"

import "./assets/css/global.css"
import "./assets/css/dialog.css"

Vue.config.productionTip = false
Vue.prototype.$api = api
Vue.prototype.$config = config
Vue.prototype.$cookie = cookie
Vue.prototype.$cache = cache
Vue.prototype.$timeUtil = timeUtil
Vue.prototype.$util = util
Vue.prototype.$apiUtil = apiUtil
Vue.prototype.$string = strings

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
