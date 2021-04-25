<template>
  <div class="page">
    <div v-if="isShowLoadingAnimation">
      <div class="spinner">
        <div class="spinner-container container1">
          <div class="circle1"></div>
          <div class="circle2"></div>
          <div class="circle3"></div>
          <div class="circle4"></div>
        </div>
        <div class="spinner-container container2">
          <div class="circle1"></div>
          <div class="circle2"></div>
          <div class="circle3"></div>
          <div class="circle4"></div>
        </div>
        <div class="spinner-container container3">
          <div class="circle1"></div>
          <div class="circle2"></div>
          <div class="circle3"></div>
          <div class="circle4"></div>
        </div>
      </div>
      <div>
        <span>正在创建订单</span>
      </div>
    </div>
    <div v-else>
      <div class="error-icon-container">
        <img src="../assets/icon_error.png">
      </div>
      <div class="error-msg-container">
        <span>抱歉，{{errorMsg}}，订单创建失败</span>
      </div>
      <div class="operation-container">
        <router-link class="nav-menu-item-inner" to="/manager/hosting">返回交易托管服务页</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      isShowLoadingAnimation: true,
      orderInfo: null,
      errorMsg: ''
    }
  },
  mounted () {
    var orderJsonStr = this.$XQLocalStorage.getLocalStorage('personalUserOrder')
    this.orderInfo = JSON.parse(orderJsonStr)
    // 获取后清除数据
    this.$XQLocalStorage.setLocalStorage('personalUserOrder', null)
    this.submitOrder()
  },
  methods: {
    submitOrder () {
      var that = this
      var params = {
        // 'userId' : this.orderInfo.userId,
        'productId' : this.orderInfo.productId,
        'isRechargeTime': this.orderInfo.isRechargeTime,
      }
      this.$http.postByPersonalUser('CreatePersonalUserOrder', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.errorMsg = response.errorMsg
          that.isShowLoadingAnimation = false
        } else if (response.errorInfo) {
          that.errorMsg = response.errorInfo.errMsg
          that.isShowLoadingAnimation = false
        } else {
          var orderId = response.value
          that.$XQLocalStorage.setTempOrderId(orderId)
          that.gotoConfirmOrder(orderId)
        }
      })
    },
    gotoConfirmOrder (orderId) {
      this.$router.push('/confirmPersonalUserOrder?orderId=' + orderId)
    }
  }
}
</script>

<style scoped>
.page {
  left: 0;
  top: 0;
  height: 1020px;
  width: 100vw;
  min-width: 1920px;
  min-height: 900px;
  background-color: rgba(255, 255, 255, 1);
  display: flex;
  align-items: center;
  flex-direction: column;
}
.spinner {
  margin: 100px auto;
  width: 40px;
  height: 40px;
  position: relative;
}

.container1 > div,
.container2 > div,
.container3 > div {
  width: 6px;
  height: 6px;
  background-color: rgba(0, 117, 255, 1);

  border-radius: 100%;
  position: absolute;
  -webkit-animation: bouncedelay 1.2s infinite ease-in-out;
  animation: bouncedelay 1.2s infinite ease-in-out;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}

.spinner .spinner-container {
  position: absolute;
  width: 100%;
  height: 100%;
}

.container2 {
  -webkit-transform: rotateZ(45deg);
  transform: rotateZ(45deg);
}

.container3 {
  -webkit-transform: rotateZ(90deg);
  transform: rotateZ(90deg);
}

.circle1 {
  top: 0;
  left: 0;
}
.circle2 {
  top: 0;
  right: 0;
}
.circle3 {
  right: 0;
  bottom: 0;
}
.circle4 {
  left: 0;
  bottom: 0;
}

.container2 .circle1 {
  -webkit-animation-delay: -1.1s;
  animation-delay: -1.1s;
}

.container3 .circle1 {
  -webkit-animation-delay: -1s;
  animation-delay: -1s;
}

.container1 .circle2 {
  -webkit-animation-delay: -0.9s;
  animation-delay: -0.9s;
}

.container2 .circle2 {
  -webkit-animation-delay: -0.8s;
  animation-delay: -0.8s;
}

.container3 .circle2 {
  -webkit-animation-delay: -0.7s;
  animation-delay: -0.7s;
}

.container1 .circle3 {
  -webkit-animation-delay: -0.6s;
  animation-delay: -0.6s;
}

.container2 .circle3 {
  -webkit-animation-delay: -0.5s;
  animation-delay: -0.5s;
}

.container3 .circle3 {
  -webkit-animation-delay: -0.4s;
  animation-delay: -0.4s;
}

.container1 .circle4 {
  -webkit-animation-delay: -0.3s;
  animation-delay: -0.3s;
}

.container2 .circle4 {
  -webkit-animation-delay: -0.2s;
  animation-delay: -0.2s;
}

.container3 .circle4 {
  -webkit-animation-delay: -0.1s;
  animation-delay: -0.1s;
}

@-webkit-keyframes bouncedelay {
  0%,
  80%,
  100% {
    -webkit-transform: scale(0);
  }
  40% {
    -webkit-transform: scale(1);
  }
}

@keyframes bouncedelay {
  0%,
  80%,
  100% {
    transform: scale(0);
    -webkit-transform: scale(0);
  }
  40% {
    transform: scale(1);
    -webkit-transform: scale(1);
  }
}
.error-icon-container {
  margin-top: 124px;
  /* width: 96px;
  height: 96px; */
}
.error-msg-container {
  margin-top: 30px;
  line-height: 37px;
  color: rgba(144, 144, 144, 1);
  font-size: 36px;
  text-align: left;
  font-family: STHeitiSC-Medium;
}
.operation-container {
  margin-top: 30px;
}
</style>
