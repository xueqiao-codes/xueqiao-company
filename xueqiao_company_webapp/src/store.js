import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    currentPath: '',
    companyInfo: {
      companyId: null,
      companyCode: null,
      companyName: null,
      fund: null,
      address: null,
      email: null,
      telephone: null,
      contact: null
    },
    orderId: null,
    tabIndex: {
      register: 0,
      login: 0,
      clientAppDownload: 0
    }
  },
  mutations: {
    setCurrentPath(state, path) {
      state.currentPath = path
    },
    setCompanyInfo(state, companyData) {
      if (companyData != null) {
        state.companyInfo.companyId = companyData.companyId
        state.companyInfo.companyCode = companyData.companyCode
        if (companyData.companyName == null || companyData.companyName === '') {
          state.companyInfo.companyName = ''
        } else {
          state.companyInfo.companyName = companyData.companyName
        }
        if (companyData.fund == null || companyData.fund === '') {
          state.companyInfo.fund = '-'
        } else {
          state.companyInfo.fund = companyData.fund
        }
        if (companyData.address == null || companyData.address === '') {
          state.companyInfo.address = '-'
        } else {
          state.companyInfo.address = companyData.address
        }
        if (companyData.email == null || companyData.email === '') {
          state.companyInfo.email = '-'
        } else {
          state.companyInfo.email = companyData.email
        }
        state.companyInfo.telephone = companyData.telephone
        if (companyData.contact == null || companyData.contact === '') {
          state.companyInfo.contact = '-'
        } else {
          state.companyInfo.contact = companyData.contact
        }
      } else {
        state.companyInfo.companyId = null
        state.companyInfo.companyCode = null
        state.companyInfo.companyName = null
        state.companyInfo.fund = null
        state.companyInfo.address = null
        state.companyInfo.email = null
        state.companyInfo.telephone = null
        state.companyInfo.contact = null
      }
    },
    setOrderId(state, orderId) {
      state.orderId = orderId
    },
    registerTabIndex(state, tabIndex) {
      state.tabIndex.register = tabIndex
    },
    loginTabIndex(state, tabIndex) {
      state.tabIndex.login = tabIndex
    },
    clientAppDownloadTabIndex(state, tabIndex) {
      state.tabIndex.clientAppDownload = tabIndex
    }
  },
  actions: {

  }
})
