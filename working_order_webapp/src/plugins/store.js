import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navigationStack: [
      { name: "用户工单管理", router: { path: "/workingorder" } }
    ]
  },
  mutations: {
    resetNavigationStack(state) {
      state.navigationStack = []
    },
    pushNavigationStack(state, item) {
      state.navigationStack.push(item)
    }
  },
  actions: {}
})
