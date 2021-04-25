<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container">
      <div class="dialog-header">
        <div class="dialog-title">修改{{param}}</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png"/></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div>
            <div class="dialog-long-text-item g-form-item">
                <div class="dialog-long-text-key g-form-label">
                    <span>{{param}}</span>
                </div>
                <textarea class="dialog-long-text-value g-form-value" type="text" v-model="modifyValue" rows="3" cols="20"></textarea>
            </div>
        </div>
      </div>
      <div class="dialog-button-container">
          <button class="g-button dialog-button" @click="confirm">确定</button>
        </div>
    </div>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips"  @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DTips from '../dialog/TipsDialog'
export default {
  props: ['isShow', 'param', 'value'],
  components: {
    'd-tips': DTips
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      modifyValue: null
    }
  },
  watch: {
    isShow () {
      if (this.isShow) {
        // try token
        this.$commonFunc.tryToken()
        this.modifyValue = this.value
      }
    }
  },
  methods: {
    showTips () {
      this.isShowTips = true
    },
    closeTips () {
      this.isShowTips = false
    },
    confirmTips () {
      this.isShowTips = false
    },
    closeDialog () {
      this.$emit('close')
    },
    confirm () {
      // this.$emit('confirm', this.value)
      if (this.modifyValue) {
        // this.value = this.modifyValue
        this.$emit('confirm', this.modifyValue)
      } else {
        this.tipMsg = '请输入' + this.param
        this.showTips()
      }
    }
  }
}
</script>

<style scoped>
  .custom-dialog-content {
    text-align: left;
  }
  .margin-top-10px {
    margin-top: 10px;
  }
  .dialog-item {
    text-align: left;
    display: flex;
    align-items: center;
  }
  .dialog-item-key {
    width: 100px;
    line-height: 16px;
    color: rgba(80, 80, 80, 1);
    font-size: 16px;
    text-align: left;
    font-family: STHeitiSC-Light;
  }
  .dialog-item-value {
    width: 200px;
  }
  .dialog-long-text-item {
    text-align: left;
    display: flex;
    /* align-items: center; */
  }
  .dialog-long-text-key {
    width: 100px;
    line-height: 16px;
    color: rgba(80, 80, 80, 1);
    font-size: 16px;
    text-align: left;
    margin-top: 5px;
    font-family: STHeitiSC-Light;
  }
  .dialog-long-text-value {
    width: 200px;
    height: 60px;
  }
  .dialog-button {
    width: 220px;
  }
</style>
