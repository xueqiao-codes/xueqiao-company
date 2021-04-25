import Cookies from "js-cookie"
export default {
  // get cookie
  getCookie(name) {
    return Cookies.get(name)
    // var arr,
    //   reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)")
    // if ((arr = document.cookie.match(reg))) return arr[2]
    // else return null
  },
  // set cookie
  setCookie(key, value, expireSeconds) {
    if (expireSeconds) {
      let expires = new Date(new Date() * 1 + expireSeconds * 1000);
      Cookies.set(key, value, { expires: expires });
    } else {
      Cookies.set(key, value);
    }

    // let exdate = null
    // if (expireSeconds) {
    //   exdate = new Date(new Date() * 1 + expireSeconds * 1000)
    // }
    // document.cookie =
    //   key +
    //   "=" +
    //   escape(value) +
    //   (expireSeconds == null ? "" : ";expires=" + exdate.toGMTString()) +
    //   (domain == null ? "" : ";domain=" + domain)
  },
  // set
  setLoginInfo(loginData) {
    this.setCookie("userName", loginData.userName, 1)
    this.setCookie("userId", loginData.userId, 1)
    this.setCookie("token", loginData.token, 1)
    // this.setCookie('companyCode', loginData.companyCode, 1)
    this.setCookie("isLogin", true, 1)
  },
  clearLoginInfo() {
    this.setCookie("userName", "", 1)
    this.setCookie("userId", null, 1)
    this.setCookie("token", null, 1)
    // this.setCookie('companyCode', null, 1)
    this.setCookie("isLogin", false, 1)
  }
}
