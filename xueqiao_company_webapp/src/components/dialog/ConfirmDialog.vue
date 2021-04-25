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
          <span>{{msg}}</span>
        </div>
      </div>
      <div class="dialog-button-container">
          <button class="g-button dialog-big-button" @click="confirm">确定</button>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['isShow', 'title', 'msg'],
  data () {
    return {}
  },
  methods: {
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
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
