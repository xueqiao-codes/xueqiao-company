import cookie from "../storage/cookie.js"
import config from "../config/config.js"
import strings from "../config/strings.js"

var transport = new Thrift.Transport("")
var protocol = new Thrift.Protocol(transport)
var client = new CompanyServiceMaintenanceWebapiClient(protocol)

export default {
  reqCompanyServiceMaintenance(filter, pageOption, response) {
    var clientSendData = client.send_reqCompanyServiceMaintenance(
      new PlatformArgs(),
      filter,
      pageOption
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_reqCompanyServiceMaintenance()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
        // alert("error callback")
      }
    )
  },
  reqMaintenanceScheduleDetail(filter, response) {
    var clientSendData = client.send_reqMaintenanceScheduleDetail(
      new PlatformArgs(),
      filter
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_reqMaintenanceScheduleDetail()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  reqCompanyExistVersionTags(response) {
    var clientSendData = client.send_reqCompanyExistVersionTags(
      new PlatformArgs()
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_reqCompanyExistVersionTags()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  reqCompanyUpgradeVersion(companyIds, response) {
    var clientSendData = client.send_reqCompanyUpgradeVersion(
      new PlatformArgs(),
      companyIds
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_reqCompanyUpgradeVersion()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  reqCompanyRollbackVersion(companyId, response) {
    var clientSendData = client.send_reqCompanyRollbackVersion(
      new PlatformArgs(),
      companyId
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_reqCompanyRollbackVersion()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  addUpgradeSchedule(upgradeSchedule, response) {
    var clientSendData = client.send_addUpgradeSchedule(
      new PlatformArgs(),
      upgradeSchedule
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_addUpgradeSchedule()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  addRollbackSchedule(rollbackSchedule, response) {
    var clientSendData = client.send_addRollbackSchedule(
      new PlatformArgs(),
      rollbackSchedule
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_addRollbackSchedule()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  reqMaintenanceHistory(companyId, response) {
    var clientSendData = client.send_reqMaintenanceHistory(
      new PlatformArgs(),
      companyId
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_reqMaintenanceHistory()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  reqCompanyServiceDetail(companyId, response) {
    var clientSendData = client.send_reqCompanyServiceDetail(
      new PlatformArgs(),
      companyId
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_reqCompanyServiceDetail()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  cancelMaintenanceSchedule(companyIds, response) {
    var clientSendData = client.send_cancelMaintenanceSchedule(
      new PlatformArgs(),
      companyIds
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_cancelMaintenanceSchedule()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  updateKeepLatestTag(companyId, keepLatest, response) {
    var clientSendData = client.send_updateKeepLatestTag(
      new PlatformArgs(),
      companyId,
      keepLatest
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_updateKeepLatestTag()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  reqServerVersions(response) {
    var clientSendData = client.send_reqServerVersions(new PlatformArgs())
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_reqServerVersions()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  initCompanyVersion(companyId, versionId, response) {
    var clientSendData = client.send_initCompanyVersion(
      new PlatformArgs(),
      companyId,
      versionId
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_initCompanyVersion()
          response(ret)
        } catch (ei) {
          alert(JSON.stringify(ei))
        }
      },
      function() {
        var errorInfo = { errorCode: -1, errorMsg: strings.message.errorMsg }
        response(errorInfo)
      }
    )
  },
  test() {
    alert("empty test")
  },
  /**
   * common do post method
   * @param {thrift send data} clientSendData
   * @param {http success callback} responseSuccess
   * @param {http error callback} responseError
   */
  doHttpPost(clientSendData, responseSuccess, responseError) {
    let proxyhost = this.getProxyHost()
    // alert("proxyhost : " + proxyhost)
    if (!proxyhost) {
      alert("proxyHost is empty, please refresh and try again")
      return
    }
    var proxyurl = proxyhost + "?servant=" + config.PROXY_ROUTER.SERVANT
    $.ajax({
      url: proxyurl,
      data: clientSendData,
      type: "POST",
      cache: false,
      contentType: "application/json",
      dataType: "json",
      beforeSend: function(XMLHttpRequest) {
        XMLHttpRequest.setRequestHeader(
          "X-Oa-Falcon-Sig",
          cookie.getCookie("oa_falcon_sig")
        )
        XMLHttpRequest.setRequestHeader(
          "X-Oa-User-Id",
          cookie.getCookie("oa_user_id")
        )
        XMLHttpRequest.setRequestHeader(
          "X-Oa-User-Name",
          cookie.getCookie("oa_user_name")
        )
        XMLHttpRequest.setRequestHeader(
          "X-Oa-User-Secret",
          cookie.getCookie("oa_user_secret")
        )
      },
      success: function(res) {
        // alert("res : " + JSON.stringify(res))
        transport.setRecvBuffer(res)
        responseSuccess()
      },
      error: function() {
        responseError()
      }
    })
  },
  getProxyHost() {
    var host = window.location.host
    if (host === "192.168.0.62:8080") {
      return "https://company-service-maintenance.devoa.xueqiao.cn/webapi"
    } else if (host === "localhost:8080") {
      return "https://company-service-maintenance.devoa.xueqiao.cn/webapi"
    }
    // if (host === "192.168.0.62:8080") {
    //   return config.PROXY_ROUTER.LOCALHOST.PROXY_HOST
    // } else if (host === config.PROXY_ROUTER.LOCALHOST.HOST) {
    //   return config.PROXY_ROUTER.LOCALHOST.PROXY_HOST
    // } else if (host === config.PROXY_ROUTER.DEV.HOST) {
    //   return config.PROXY_ROUTER.DEV.PROXY_HOST
    // } else if (host === config.PROXY_ROUTER.GAMMA.HOST) {
    //   return config.PROXY_ROUTER.GAMMA.PROXY_HOST
    // } else if (host === config.PROXY_ROUTER.IDC.HOST) {
    //   return config.PROXY_ROUTER.IDC.PROXY_HOST
    // }
    return "/webapi"
  }
}
