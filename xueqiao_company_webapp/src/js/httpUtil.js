// ljq 20180926
// import axios from 'axios'
import router from "@/router"
import commonFunc from "@/js/commonFun"
import localStorage from "@/js/storage/localStore"
import env from "@/js/config/env"

var host = env.baseUrl

var tokenMethod = [
  "ChangePassword",
  "GetCompany",
  "SetEmail",
  "SetInfo",
  "SetTel",
  "SetCompanyCode",
  "Logout",
  "GetCompanyUsers",
  "AddCompanyUser",
  "UpdateCompanyUser",
  "GetHostedServices",
  "GetOrders",
  "AddNewHostedService",
  "GetCompanyGroupUsers",
  "UpdateCompanyGroupUsers",
  "UpdateCompanyGroupUser",
  "SubmitOrder", // deprecated
  "CreateOrder",
  "GetUpgradeSpecInfo",
  "GetRechargeTimeProducts",
  "GetCompanyGroups",
  "UpdateCompanyUserStatus",
  "PayOrderWIthXCoin",
  "CancelOrder",
  "removeOrder",
  "UpdateCompanyName",
  "UpdateCompanyContact",
  "UpdateCompanyTelephone",
  "UpdateCompanyEmail",
  "UpdateCompanyAddress",
  "UpdateCompanyPasswd",
  "RemoveHostedService",
  "getOrderDetail",
  "TryToken",
  "GetValidOrdersByGroupId",
  "GetSimpleHostedServices",
  "ResetCompanyUserPasswd",
  "GetServiceMaintenance",
  "SetServiceMaintenanceTimespan",
  // "VerifyCompanyUser",
  // "GetXQClientAppDownload"
  "GetPersonalUser",
  "GetPersonalUserHostedServiceProducts",
  "CreatePersonalUserOrder",
  "PersonalUserUpdatePassword",
]

import { Loading } from "element-ui"
export default {
  post(method, data, result) { 
    this.doPost(method, data, false, result);
  },
  postByPersonalUser(method, data, result) { 
    this.doPost(method, data, true, result);
  },
  doPost(method, data, isPersonal, result) {
    let loadingInstance = null
    if (method != "TryToken") {
      loadingInstance = Loading.service({
        fullscreen: true,
        background: "rgba(0, 0, 0, 0.2)"
      })
    }

    var requestUrl = host + "service?method=" + method
    if (this.isInArray(tokenMethod, method)) {
      var token = localStorage.getLocalStorage("token")
      var userId = localStorage.getLocalStorage("userId")
      if (!token || !userId) {
        // stop loading animation
        loadingInstance = Loading.service({
          fullscreen: true,
          background: "rgba(0, 0, 0, 0.2)"
        })
        loadingInstance.close()

        commonFunc.sendSessionTimeoutEvent()
        if (method != "Logout") {
          if (isPersonal) {
            router.push("/login/personal?timeout=true");
          } else {
            router.push("/login/company?timeout=true");
          }
        } else {
          router.push("/")
        }
        // commonFunc.sendBackendCallEvent()
        return
      }
      requestUrl += "&token=" + token + "&userId=" + userId
    }

    $.ajax({
      url: requestUrl,
      data: data,
      type: "POST",
      cache: false,
      async: false,
      // contentType: "application/json",
      dataType: "json",
      success: function(response) {
        // if (loadingInstance) {
        loadingInstance = Loading.service({
          fullscreen: true,
          background: "rgba(0, 0, 0, 0.2)"
        })
        loadingInstance.close()
        // }
        result(response)
        if (
          response.errorInfo &&
          response.errorInfo.errMsg &&
          response.errorInfo.errMsg == "Session timeout."
        ) {
          localStorage.clearLoginInfo()
          commonFunc.sendSessionTimeoutEvent()
          if (isPersonal) {
            router.push("/login/personal?timeout=true");
          } else { 
            router.push("/login?timeout=true");
          }
        }
      },
      error: function(response) {
        // if (loadingInstance) {
        loadingInstance = Loading.service({
          fullscreen: true,
          background: "rgba(0, 0, 0, 0.2)"
        })
        loadingInstance.close()
        // }
        // alert("error : " + method)
        result(response)
      }
    })

    // refresh local session
    localStorage.refreshLoginInfo()
  },
  isInArray(arr, value) {
    for (var i = 0; i < arr.length; i++) {
      if (value === arr[i]) {
        return true
      }
    }
    return false
  }
}
