<template>
  <div class="error-page">
    <div class="error-symbol-wrapper">
      <div class="error-symbol">404</div>
      <div># 页面不存在 #</div>
      <div>
        <button class="g-button button-back" @click="gotoMainPage">返回首页</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {}
  },
  methods: {
    gotoMainPage () {
      this.$router.push('/home')
    }
  }
}
</script>

<style scoped>
.error-page {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin-top: 0px;
  height: 100vh;
  min-height: 900px;
  background: white;
}
.error-symbol-wrapper {
  text-align: center;
}
.error-symbol {
  color: rgba(238, 238, 238, 1);
  font-size: 300px;
  margin-top: 200px;
  font-family: STHeitiSC-Medium;
}
.button-back {
  width: 220px;
  height: 42px;
  margin-top: 30px;
  border-radius: 4px;
  color: rgba(255, 255, 255, 1);
  font-size: 18px;
  font-family: STHeitiSC-Medium;
}
</style>
