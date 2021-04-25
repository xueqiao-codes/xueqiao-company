import store from "../../plugins/store"
// import config from "../config/config"
// import api from "../api/api"
// import apiUtil from "../util/apiUtil"
export default {
  resetNavigationStack() {
    store.commit("resetNavigationStack")
  },
  pushNavigationStack(item) {
    store.commit("pushNavigationStack", item)
  }
}
