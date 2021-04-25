<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-service">
      <div class="dialog-header">
        <div class="dialog-title">联系客服</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-service-content">
        <div>
          <span>您可以通过以下方式联系到我们，我们将竭诚为您服务。</span>
        </div>
        <div class="margin-top-10px">
          <span>客服电话：{{$XQConfig.config.CUSTOM_SERVICE_TEL}}</span>
        </div>
      </div>
      <div class="dialog-button-container">
          <button class="g-button dialog-big-button" @click="closeDialog">确定</button>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['isShow'],
  data () {
    return {}
  },
  // watch: {
  //   isShow () {
  //     if (this.isShow) {
  //       // try token
  //       this.$commonFunc.tryToken()
  //     }
  //   }
  // },
  methods: {
    closeDialog () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>
  .d-custom-service {
    width: 520px;
  }
  .custom-service-content {
    text-align: left;
  }
  .margin-top-10px {
    margin-top: 10px;
  }
</style>
