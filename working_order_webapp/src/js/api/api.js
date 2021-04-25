import cookie from "../storage/cookie.js"
import strings from "../config/strings.js"

var transport = new Thrift.Transport("")
var protocol = new Thrift.Protocol(transport)
var client = new WorkingOrderWebapiClient(protocol)

export default {
  reqWorkingOrderInfo(option, pageOption, response) {
    var clientSendData = client.send_reqWorkingOrderInfo(
      new PlatformArgs(),
      option,
      pageOption
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_reqWorkingOrderInfo()
          response(ret)
        } catch (ei) {
          let eiJson = JSON.stringify(ei)
          if (eiJson != "{}") {
            alert(eiJson)
          } else {
            alert(ei)
          }
        }
      },
      function() {
        var errorInfo = {
          errorCode: -1,
          errorMsg: strings.message.callMethodFail
        }
        response(errorInfo)
      }
    )
  },
  markWorkingOrderState(workingOrderId, state, operateName, response) {
    var clientSendData = client.send_markWorkingOrderState(
      new PlatformArgs(),
      workingOrderId,
      state,
      operateName
    )
    this.doHttpPost(
      clientSendData,
      function() {
        var ret = null
        try {
          ret = client.recv_markWorkingOrderState()
          response(ret)
        } catch (ei) {
          let eiJson = JSON.stringify(ei)
          if (eiJson != "{}") {
            alert(eiJson)
          } else {
            alert(ei)
          }
        }
      },
      function() {
        var errorInfo = {
          errorCode: -1,
          errorMsg: strings.message.callMethodFail
        }
        response(errorInfo)
      }
    )
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
    var proxyurl = proxyhost + "?servant=working_order_webapi"
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
      success: function (res) {
        transport.setRecvBuffer(res)
        responseSuccess()
      },
      error: function () {
        responseError()
      }
    })
  },
  getProxyHost() {
    var host = window.location.host
    if (host === "192.168.0.62:8080") {
      return "http://working-order.devoa.xueqiao.cn/webapi"
    } else if (host == "localhost:8080") {
      return "http://working-order.devoa.xueqiao.cn/webapi"
    }
    return "/webapi"
  }
}
