<template>
  <div class="footer">
    <div class="company-info-container">
      <div class="company-info-content">
        <div class="company-info-content-item">
          <div class="company-info-content-key">
            <span>联系我们:</span>
          </div>
          <div class="company-info-content-value">
            <div>
              <span class="company-info-content-sub-item">联系电话：{{$XQConfig.config.CUSTOM_SERVICE_TEL}}</span>
              <span class="company-info-content-sub-item">地址：{{$XQConfig.config.COMPANY_ADDRESS}}</span>
            </div>
            <div>
              <span class="company-info-content-sub-item">邮箱：{{$XQConfig.config.COMPANY_EAMIL}}</span>
            </div>
          </div>
        </div>
        <div class="company-info-content-item">
          <div class="company-info-content-key">
            <span>友情链接:</span>
          </div>
          <div class="company-info-content-value">
            <a
              href="http://xiaohatu.com"
              target="_blank"
            >小哈图</a>
          </div>
        </div>
      </div>
    </div>
    <div class="authority-info-container">
      <div class="authority-info-content">
        <span>深圳市雪橇科技股份有限公司版权所有 粤ICP备18100097号-1 Copyright ©2017-2019 XueQiao</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.footer {
    min-width: 1920px;
}
.company-info-container {
  height: 175px;
  background-color: rgba(39, 49, 84, 1);
}
.company-info-content {
  /* padding-top: 30px; */
  margin-left: 465px;
  margin-right: 465px;
}
.authority-info-container {
  height: 50px;
  background-color: rgba(27, 37, 70, 1);
}
.company-info-content-item {
  text-align: left;
  display: flex;
  padding-top: 30px; /* align-items: center; */
}
.company-info-content-sub-item {
  margin-right: 60px;
}
.company-info-content-key {
  width: 90px;
  height: 25px;
  line-height: 25px;
  color: rgba(255, 255, 255, 0.5);
  font-size: 18px;
  text-align: left;
  font-family: PingFangSC-Light;
}
.company-info-content-value {
  margin-left: 30px;
  line-height: 25px;
  color: rgba(255, 255, 255, 0.5);
  font-size: 16px;
  text-align: left;
  font-family: PingFangSC-Light;
}
.authority-info-content {
  padding-top: 15px;
  line-height: 25px;
  color: rgba(255, 255, 255, 0.5);
  font-size: 16px;
  text-align: center;
  font-family: PingFangSC-Light;
}
</style>
