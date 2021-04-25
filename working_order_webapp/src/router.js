import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import WorkingOrderPage from './views/WorkingOrderPage.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: "/workingorder",
      name: 'home',
      component: Home,
      children: [
        {
          path: '/workingorder',
          name: 'workingOrderPage',
          component: WorkingOrderPage
        }
      ]
    }
  ]
})
