import Vue from 'vue'
import 'babel-polyfill'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'

import http from './js/httpUtil'
import util from './js/util/util.js'
import storeUtil from './js/util/storeUtil.js'
import timeUtil from "./js/util/timeUtil.js"
import commonfun from './js/commonFun'
import config from './js/config/config'
import string from './js/config/string'
import env from './js/config/env'
import cookie from './js/storage/cookie'
import localStorage from './js/storage/localStore'
import eventBus from './js/eventbus/eventBus'
import Pagination from 'element-ui'
import Loading from 'element-ui'

import './assets/css/dialog.css' // common dialog css
import './assets/css/fragment.css'
import './assets/css/global.css'

import wPasswdInput from '@/components/widget/PasswdInput'

// npm install url-search-params-polyfill --save
// solve compatibility of URLSearchParams
import 'url-search-params-polyfill';

Vue.config.productionTip = false

Vue.prototype.$http = http
Vue.prototype.$commonFunc = commonfun
Vue.prototype.$XQConfig = config
Vue.prototype.$XQEnv = env
Vue.prototype.$XQCookie = cookie
Vue.prototype.$XQLocalStorage = localStorage
Vue.prototype.$util = util
Vue.prototype.$storeUtil = storeUtil
Vue.prototype.$timeUtil = timeUtil
Vue.prototype.$loading = Loading
Vue.prototype.$string = string
Vue.use(Pagination)
// Vue.use(Loading)

// event bus
Vue.prototype.$eventBus = eventBus

// register components
Vue.component('passwdinput', wPasswdInput)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

const loginPage = [
  'ManagePage'

]
const logoutPage = [
  '/login',
  '/login?timeout=true',
  '/register',
  '/modifypasswd'
]

router.beforeEach((to, from, next) => {
  var loginInfo = localStorage.getLoginInfo()
  var isLogin = loginInfo.isLogin === 'true'
  store.commit('setCurrentPath', to.path)
  if (isLogin) {
    if (logoutPage.indexOf(to.path) >= 0) {
      next('/home')
    } else {
      next()
    }
  } else {
    if (loginPage.indexOf(to.name) >= 0) {
      next('/403')
    } else {
      next()
    }
  }
})
