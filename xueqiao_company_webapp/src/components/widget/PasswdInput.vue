<template>
  <div class="widget">
    <input class="input-frame" type="text" placeholder="密码" v-bind:value="value" v-on:input="$emit('input', $event.target.value)" @focus="$emit('focus')" @blur="$emit('blur')" v-if="isShowPasswd">
    <input class="input-frame" type="password" placeholder="密码" v-bind:value="value" v-on:input="$emit('input', $event.target.value)" @focus="$emit('focus')" @blur="$emit('blur')" v-else>
    <div class="ico" @mousedown="showPasswd" @mouseup="hidePasswd"></div>
  </div>
</template>

<script>
export default {
  props: ['value'],
  data () {
    return {
      isShowPasswd: false
    }
  },
  methods: {
    showPasswd () {
      this.isShowPasswd = true
    },
    hidePasswd () {
      this.isShowPasswd = false
    }
  }
}
</script>

<style scoped>
.widget {
  position: relative;
}
.widget .ico {
  width: 16px;
  height: 16px;
  position: absolute;
  top: 12px;
  right: -35px;
  z-index: 5;
  background: url(../../assets/icon_show_passwd.png) no-repeat transparent;
}
.input-frame {
  padding-left: 10px;
  padding-right: 30px;
  width: 100%;
  height: 30px;
  border-radius: 4px;
  background-color: rgba(251, 251, 251, 1);
  border: 1px solid rgba(229, 229, 229, 1);
}
</style>
