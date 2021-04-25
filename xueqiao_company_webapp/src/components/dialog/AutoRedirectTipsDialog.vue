<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-service">
      <div class="dialog-header">
        <div class="dialog-title">{{title}}</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-service-content">
        <div>
          <span>{{msg1}}{{redirectCount}}{{msg2}}</span>
        </div>
      </div>
      <div class="dialog-button-container">
          <button class="g-button dialog-big-button" @click="confirm">立即跳转 ({{redirectCount}}")</button>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['isShow', 'title', 'msg1', 'msg2', 'currentTime'],
  data () {
    return {
      redirectCount: 3,
      timer: null
    }
  },
  watch: {
    currentTime () {
      // var time = 5
      // alert('current time : ' + this.currentTime)
      var that = this
      that.redirectCount = 3
      this.timer = setInterval(function () {
        if (that.redirectCount === 0) {
          that.redirectCount = 0
          clearInterval(that.timer)
          that.confirm()
        } else {
          that.redirectCount--
        }
      }, 1000)
    }
  },
  methods: {
    closeDialog () {
      // reset count
      this.redirectCount = 0
      clearInterval(this.timer)
      this.$emit('close')
    },
    confirm () {
      clearInterval(this.timer)
      this.$emit('confirm')
    }
  }
}
</script>

<style scoped>
  .d-custom-service {
    width: 520px;
  }
  .custom-service-content {
    text-align: center;
  }
  .margin-top-10px {
    margin-top: 10px;
  }
</style>
