<template>
  <div class="page">
    <div class="title">
      <span>雪橇产品说明文档</span>
    </div>
    <div class="doc-content">
      <div class="doc-line">
        <div class="block-frame">
          <div class="block-title">
            <span>1、{{doc.DOC.QUICT_START.LABEL}}</span>
          </div>
          <div
            class="block-item"
            v-for="(item, index) in doc.DOC.QUICT_START.ITEMS"
            v-bind:key="index"
          >
            <a
              :href="item.URL"
              target="_blank"
            >{{item.LABEL}}</a>
          </div>
        </div>
        <div class="block-frame">
          <div class="block-title">
            <span>2、{{doc.DOC.TRADE_CONFIG.LABEL}}</span>
          </div>
          <div
            class="block-item"
            v-for="(item, index) in doc.DOC.TRADE_CONFIG.ITEMS"
            v-bind:key="index"
          >
            <a
              :href="item.URL"
              target="_blank"
            >{{item.LABEL}}</a>
          </div>
        </div>
        <div class="block-frame">
          <div class="block-title">
            <span>3、{{doc.DOC.TRADER_DOC.LABEL}}</span>
          </div>
          <div
            class="block-item"
            v-for="(item, index) in doc.DOC.TRADER_DOC.ITEMS"
            v-bind:key="index"
          >
            <a
              :href="item.URL"
              target="_blank"
            >{{item.LABEL}}</a>
          </div>
        </div>
      </div>
      <div class="doc-line">
        <div class="block-frame">
          <div class="block-title">
            <span>4、{{doc.DOC.ADMIN_DOC.LABEL}}</span>
          </div>
          <div
            class="block-item"
            v-for="(item, index) in doc.DOC.ADMIN_DOC.ITEMS"
            v-bind:key="index"
          >
            <a
              :href="item.URL"
              target="_blank"
            >{{item.LABEL}}</a>
          </div>
        </div>
        <div class="block-frame">
          <div class="block-title">
            <span>5、{{doc.DOC.RISK_MANAGE.LABEL}}</span>
          </div>
          <div
            class="block-item"
            v-for="(item, index) in doc.DOC.RISK_MANAGE.ITEMS"
            v-bind:key="index"
          >
            <a
              :href="item.URL"
              target="_blank"
            >{{item.LABEL}}</a>
          </div>
        </div>
        <div class="block-frame">
          <div class="block-title">
            <span>6、{{doc.DOC.USER_INTERFACE.LABEL}}</span>
          </div>
          <div
            class="block-item"
            v-for="(item, index) in doc.DOC.USER_INTERFACE.ITEMS"
            v-bind:key="index"
          >
            <a
              :href="item.URL"
              target="_blank"
            >{{item.LABEL}}</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DOC_CONFIG from "../js/config/docConfig.js";
export default {
  data() {
    return {
      doc: DOC_CONFIG
    };
  }
};
</script>

<style scoped>
.page {
  /* margin-top: 100px; */
  left: 0px;
  top: 0px;
  min-width: 1920px;
  height: 900px;
  background-image: url("../assets/docbg.png");
}
.title {
  padding-top: 60px;
  padding-bottom: 20px;
  line-height: 45px;
  color: rgba(255, 255, 255, 1);
  font-size: 32px;
  font-family: PingFangSC-Medium;
}
.doc-content{
  /* width: 100%; */
  display: flex;
  flex-direction: column;
  /* align-content: center; */
  align-items: center;
}
.doc-line {
  display: flex;
}
.block-frame {
  width: 320px;
  height: 300px;
  border-radius: 10px;
  border: 1px solid rgba(255, 255, 255, 1);
  margin: 15px;
}
.block-title {
  margin: 30px;
  line-height: 33px;
  color: rgba(255, 255, 255, 1);
  font-size: 24px;
  text-align: left;
  font-family: PingFangSC-Regular;
}
.block-item {
  margin-left: 30px;
  margin-top: 10px;
  margin-bottom: 10px;
  height: 25px;
  line-height: 25px;
  color: rgba(255, 255, 255, 1);
  font-size: 18px;
  text-align: left;
  font-family: PingFangSC-Light;
}
</style>
