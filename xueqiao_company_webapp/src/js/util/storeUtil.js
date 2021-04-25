import store from '../../store'
export default {
    setRegisterTabIndex(tabIndex) { 
        store.commit("registerTabIndex", tabIndex);
    },
    setLoginTabIndex(tabIndex) {
        store.commit("loginTabIndex", tabIndex);
    },
    setClientAppDownloadTabIndex(tabIndex) {
        store.commit("clientAppDownloadTabIndex", tabIndex);
    },
    setCurrentPath(path) { 
        store.commit("setCurrentPath", path)
    }
}