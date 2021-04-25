// ljq 20181017 common function

import http from './httpUtil'
import eventBus from './eventbus/eventBus'
// import './jsencrypt.min.js'

export default {
  // try if token is invalid
  tryToken () {
    // var params = new URLSearchParams()
    // params.append('method', 'TryToken')
    var params = {}
    http.post('TryToken', params, function () {})
  },
  openNewWin (url) {
    var form = document.createElement('form')
    form.action = url
    form.target = '_blank'
    form.method = 'POST'
    document.body.appendChild(form)
    form.submit()
  },
  openNewPage (url) {
    window.location.href = url
  },
  // 发送后端请求事件，也就是用户操作事件
  sendBackendCallEvent () {
    eventBus.$emit('backendCall')
  },
  // 接收用户操作事件
  onBackendCall (onCall) {
    eventBus.$on('backendCall', () => {
      onCall()
    })
  },
  sendLoginSuccessEvent() { 
    eventBus.$emit('loginSuccess')
  },
  onLoginSuccess(onCall) { 
    eventBus.$on('loginSuccess', () => {
      onCall()
    })
  },
  sendSessionTimeoutEvent() { 
    eventBus.$emit('sessionTimeout')
  },
  onSessionTimeout(onCall) { 
    eventBus.$on('sessionTimeout', () => {
      onCall()
    })
  },
  rsaEncode (password) {
    let publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnLYa/mAar2hVKJPHK/KGJNaq2\nNBX9LLPRD6+KAWh5vZIwu3tA6uLfb6//FC2vajNYweiw5gpjfZgPbO8Wb2qOyphg\nJrqWke2L9wBWl5Qo+F2Uh+Q7AaoXDshzcI6T9xDrG4F6jCnQVyjjVvCB2rXA65tv\nrjUuy5IRFoVWP5xhFQIDAQAB'
    var encrypt = new JSEncrypt()
    encrypt.setPublicKey(publicKey)
    return encrypt.encrypt(password)
    // return password
  }
}
