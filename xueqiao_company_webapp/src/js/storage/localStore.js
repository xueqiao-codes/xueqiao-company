// ljq 20181022
import cookie from "./cookie"
export default {
  setLocalStorage(key, value) {
    cookie.setCookie(key, value, null)
    // localStorage.setItem(key, value)
  },
  getLocalStorage(key) {
    return cookie.getCookie(key)
    // return localStorage.getItem(key)
  },
  setLoginInfo(loginData) {
    let expireSeconds = 540 // 30 * 60  -- 9 minutes
    cookie.setCookie("userName", loginData.userName, expireSeconds)
    cookie.setCookie("userId", loginData.userId, expireSeconds)
    cookie.setCookie("token", loginData.token, expireSeconds)
    cookie.setCookie("isLogin", true, expireSeconds)
    cookie.setCookie("userType", "company", expireSeconds)
  },
  setPersonalUserLoginInfo(loginData) {
    let expireSeconds = 540 // 30 * 60  -- 9 minutes
    cookie.setCookie("userName", loginData.userName, expireSeconds)
    cookie.setCookie("userId", loginData.userId, expireSeconds)
    cookie.setCookie("token", loginData.token, expireSeconds)
    cookie.setCookie("isLogin", true, expireSeconds)
    cookie.setCookie("userType", "personal", expireSeconds)
  },
  getLoginInfo() {
    var loginInfo = {
      userName: this.getLocalStorage("userName"),
      userId: this.getLocalStorage("userId"),
      token: this.getLocalStorage("token"),
      isLogin: this.getLocalStorage("isLogin"),
      userType: this.getLocalStorage("userType")
    }
    return loginInfo
  },
  refreshLoginInfo() { 
    var loginInfo = this.getLoginInfo()
    if (loginInfo.isLogin && loginInfo.userName && loginInfo.userId && loginInfo.token && loginInfo.userType) { 
      let expireSeconds = 540 // 30 * 60  -- 9 minutes
      cookie.setCookie("userName", loginInfo.userName, expireSeconds)
      cookie.setCookie("userId", loginInfo.userId, expireSeconds)
      cookie.setCookie("token", loginInfo.token, expireSeconds)
      cookie.setCookie("isLogin", true, expireSeconds)
      cookie.setCookie("userType", loginInfo.userType, expireSeconds)
    }
  },
  clearLoginInfo() {
    let expireSeconds = 1
    cookie.setCookie("userName", "", expireSeconds)
    cookie.setCookie("userId", "", expireSeconds)
    cookie.setCookie("token", "", expireSeconds)
    cookie.setCookie("isLogin", false, null)
    cookie.setCookie("userType", "", expireSeconds)
  },
  isUserTypeCompany(userType) { 
    return userType == "company";
  },
  isUserTypePersonal(userType) { 
    return userType == "personal";
  },
  setTempOrderId(orderId) { 
    cookie.setCookie("orderId", orderId, null)
  },
  getTempOrderId() { 
    return this.getLocalStorage("orderId");
  },
  clearTempOrderId() { 
    cookie.setCookie("orderId", "", 1)
  }
}
