import Vue from "vue"
import Router from "vue-router"
import Home from "./views/Home.vue"
import Maintenance from "./views/Maintenance.vue"
import Plan from "./views/Plan.vue"
import ServiceDetail from "./views/ServiceDetail.vue"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: "/",
      name: "home",
      redirect: "/maintenance",
      component: Home
    },
    {
      path: "/maintenance",
      name: "maintenance",
      component: Maintenance
    },
    {
      path: "/plan",
      name: "plan",
      component: Plan
    },
    {
      path: "/serviceDetail",
      name: "serviceDetail",
      component: ServiceDetail
    }
  ]
})
