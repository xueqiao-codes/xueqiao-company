import config from "../config/config.js"
import cookie from "../storage/cookie.js"
export default {
  getCompanyListUrl() {
    var prefix = "https://adminweb."
    var suffix = "/admin/SledCompanyAdmin/show"
    var host = window.location.host
    if (host === "192.168.0.62:8080") {
      return prefix + config.TOP_HOST.LOCALHOST.TOP_HOST + suffix
    } else if (host === config.TOP_HOST.LOCALHOST.HOST) {
      return prefix + config.TOP_HOST.LOCALHOST.TOP_HOST + suffix
    } else if (host === config.TOP_HOST.DEV.HOST) {
      return prefix + config.TOP_HOST.DEV.TOP_HOST + suffix
    } else if (host === config.TOP_HOST.GAMMA.HOST) {
      return prefix + config.TOP_HOST.GAMMA.TOP_HOST + suffix
    } else if (host === config.TOP_HOST.IDC.HOST) {
      return prefix + config.TOP_HOST.IDC.TOP_HOST + suffix
    }
    return null
  },
  getOaUserName() { 
    return cookie.getCookie("oa_user_name")
  },
  getEnv() { 
    var host = window.location.host
    if (host === "192.168.0.62:8080") {
      return "dev"
    } else if (host === config.TOP_HOST.LOCALHOST.HOST) {
      return "dev"
    } else if (host === config.TOP_HOST.DEV.HOST) {
      return "dev"
    } else if (host === config.TOP_HOST.GAMMA.HOST) {
      return "gamma"
    } else if (host === config.TOP_HOST.IDC.HOST) {
      return "idc"
    }
  }
}
