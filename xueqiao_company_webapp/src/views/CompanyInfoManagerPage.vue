<template>
  <div class="fragment-manager">
    <div class="manager-title">
      <span class="manager-title-space"></span>
      <span class="manager-title-text">公司资料</span>
    </div>
    <div class="companyinfo-container">
      <div class="companyinfo-item">
        <div class="companyinfo-item-key">
          <span>公司登录名</span>
        </div>
        <div class="companyinfo-item-value">
          <span>{{$store.state.companyInfo.companyCode}}</span>
        </div>
        <div class="companyinfo-item-operation">
          <a href="#" @click="showModifyPasswdDialog">
            <span>修改密码</span>
          </a>
        </div>
      </div>
      <hr class="hr-divider">
      <div class="companyinfo-item">
        <div class="companyinfo-item-key">
          <span>余额</span>
        </div>
        <div class="companyinfo-item-value">
          <span>-</span>
        </div>
        <div class="companyinfo-item-operation">
          <a href="#">
            <span></span>
          </a>
        </div>
      </div>
      <hr class="hr-divider">
      <div class="companyinfo-item">
        <div class="companyinfo-item-key">
          <span>公司ID</span>
        </div>
        <div class="companyinfo-item-value">
          <span>{{$store.state.companyInfo.companyId}}</span>
        </div>
        <div class="companyinfo-item-operation">
          <a href="#">
            <span></span>
          </a>
        </div>
      </div>
      <hr class="hr-divider">
      <div class="companyinfo-item">
        <div class="companyinfo-item-key">
          <span>公司名称</span>
        </div>
        <div class="companyinfo-item-value">
          <span>{{$store.state.companyInfo.companyName}}</span>
        </div>
        <div class="companyinfo-item-operation">
          <a href="#" @click="showModifyCompanyNameDialog">
            <span>修改</span>
          </a>
        </div>
      </div>
      <hr class="hr-divider">
      <div class="companyinfo-item">
        <div class="companyinfo-item-key">
          <span>联系人</span>
        </div>
        <div class="companyinfo-item-value">
          <span>{{$store.state.companyInfo.contact}}</span>
        </div>
        <div class="companyinfo-item-operation">
          <a href="#" @click="showModifyContactDialog">
            <span>修改</span>
          </a>
        </div>
      </div>
      <hr class="hr-divider">
      <div class="companyinfo-item">
        <div class="companyinfo-item-key">
          <span>手机号</span>
        </div>
        <div class="companyinfo-item-value">
          <span>{{$store.state.companyInfo.telephone}}</span>
        </div>
        <div class="companyinfo-item-operation">
          <a href="#" @click="showModifyTelDialog">
            <span>修改</span>
          </a>
        </div>
      </div>
      <hr class="hr-divider">
      <div class="companyinfo-item">
        <div class="companyinfo-item-key">
          <span>邮箱</span>
        </div>
        <div class="companyinfo-item-value">
          <span>{{$store.state.companyInfo.email}}</span>
        </div>
        <div class="companyinfo-item-operation">
          <a href="#" @click="showModifyEmailDialog">
            <span>修改</span>
          </a>
        </div>
      </div>
      <hr class="hr-divider">
      <div class="companyinfo-item">
        <div class="companyinfo-item-key">
          <span>联系地址</span>
        </div>
        <div class="companyinfo-item-value">
          <span>{{$store.state.companyInfo.address}}</span>
        </div>
        <div class="companyinfo-item-operation">
          <a href="#" @click="showModifyAddressDialog">
            <span>修改</span>
          </a>
        </div>
      </div>
    </div>
    <d-modify-one-item v-bind:isShow="isShowModifyOneItemDialog" v-bind:param="modifyOneItemDialogKey" v-bind:value="modifyOneItemDialogValue" @close="closeModifyOneItemDialog"  @confirm="confirmModifyOneItem"></d-modify-one-item>
    <d-modify-address v-bind:isShow="isShowModifyAddressDialog" param="联系地址" v-bind:value="modifyAddressValue" @close="closeModifyAddressDialog"  @confirm="confirmModifyAddress"></d-modify-address>
    <d-modify-tel v-bind:isShow="isShowModifyTelDialog" v-bind:telephone="modifyTelValue" @close="closeModifyTelDialog"  @confirm="confirmModifyTel"></d-modify-tel>
    <d-modify-passwd v-bind:isShow="isShowModifyPasswdDialog" v-bind:telephone="$store.state.companyInfo.telephone" @close="closeModifyPasswdDialog"  @confirm="confirmModifyPasswd"></d-modify-passwd>
    <d-tips v-bind:isShow="isShowTips" v-bind:msg="tipMsg" @close="closeTips"  @confirm="confirmTips"></d-tips>
  </div>
</template>

<script>
import DModifyOneItemDialog from '../components/dialog/ModifyOneItemDialog'
import DModifyLongTextDialog from '../components/dialog/ModifyLongTextDialog'
import DModifyTelDialog from '../components/dialog/ModifyTelDialog'
import DModifyPasswdDialog from '../components/dialog/ModifyPasswdDialog'
import DTips from '../components/dialog/TipsDialog'

export default {
  components: {
    'd-tips': DTips,
    'd-modify-one-item': DModifyOneItemDialog,
    'd-modify-address': DModifyLongTextDialog,
    'd-modify-tel': DModifyTelDialog,
    'd-modify-passwd': DModifyPasswdDialog
  },
  data () {
    return {
      // dialog
      isShowTips: false,
      tipMsg: '',
      isShowModifyOneItemDialog: false,
      modifyOneItemDialogKey: '',
      modifyOneItemDialogValue: null,
      isShowModifyAddressDialog: false,
      modifyAddressValue: null,
      isShowModifyTelDialog: false,
      modifyTelValue: null,
      isShowModifyPasswdDialog: false
    }
  },
  mounted () {
    if (this.$util.checkLoginState("/manager/companyinfo") != "company") {
      return;
    }
    // try token
    // this.$commonFunc.tryToken()
    // alert("this.$store.state.companyInfo.companyCode : " + this.$store.state.companyInfo.companyCode)
    // if (!this.$store.state.companyInfo.companyCode) {
      this.getCompanyInfo()
    // }
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
    getCompanyInfo() {
      var that = this;
      // var params = new URLSearchParams()
      // params.append('method', 'GetCompany')
      var params = {};
      this.$http.post("GetCompany", params, function(response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg;
          that.showTips();
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg;
          that.showTips();
        } else {
          if (response.value) {
            that.$store.commit("setCompanyInfo", response.value);
          } else {
            alert("GetCompany fail : " + JSON.stringify(response));
          }
        }
      });
    },
    showModifyCompanyNameDialog () {
      this.modifyOneItemDialogKey = '公司名'
      this.modifyOneItemDialogValue = this.$store.state.companyInfo.companyName
      if (this.modifyOneItemDialogValue === '-') {
        this.modifyOneItemDialogValue = ''
      }
      this.isShowModifyOneItemDialog = true
    },
    showModifyEmailDialog () {
      this.modifyOneItemDialogKey = '邮箱'
      this.modifyOneItemDialogValue = this.$store.state.companyInfo.email
      if (this.modifyOneItemDialogValue === '-') {
        this.modifyOneItemDialogValue = ''
      }
      this.isShowModifyOneItemDialog = true
    },
    showModifyContactDialog () {
      this.modifyOneItemDialogKey = '联系人'
      this.modifyOneItemDialogValue = this.$store.state.companyInfo.contact
      if (this.modifyOneItemDialogValue === '-') {
        this.modifyOneItemDialogValue = ''
      }
      this.isShowModifyOneItemDialog = true
    },
    closeModifyOneItemDialog () {
      this.isShowModifyOneItemDialog = false
    },
    confirmModifyOneItem (value) {
      if (this.modifyOneItemDialogKey === '公司名') {
        this.modifyCompanyName(value)
      } else if (this.modifyOneItemDialogKey === '邮箱') {
        this.modifyCompanyEmail(value)
      } else if (this.modifyOneItemDialogKey === '联系人') {
        this.modifyCompanyContact(value)
      }
      this.isShowModifyOneItemDialog = false
    },
    modifyCompanyName (value) {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyName')
      // params.append('companyName', value)
      var params = {
        'companyName' : value
      }
      this.$http.post('UpdateCompanyName', params, function (response) {
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          var companyInfo = that.$store.state.companyInfo
          companyInfo.companyName = value
          that.$store.commit('setCompanyInfo', companyInfo)
          that.tipMsg = '修改成功'
          that.showTips()
        }
      })
    },
    modifyCompanyEmail (value) {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyEmail')
      // params.append('email', value)
      var params = {
        'email': value
      }
      this.$http.post('UpdateCompanyEmail', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          var companyInfo = that.$store.state.companyInfo
          companyInfo.email = value
          that.$store.commit('setCompanyInfo', companyInfo)
          that.tipMsg = '修改成功'
          that.showTips()
        }
      })
    },
    modifyCompanyContact (value) {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyContact')
      // params.append('contact', value)
      var params = {
        'contact' : value
      }
      this.$http.post('UpdateCompanyContact', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          var companyInfo = that.$store.state.companyInfo
          companyInfo.contact = value
          that.$store.commit('setCompanyInfo', companyInfo)
          that.tipMsg = '修改成功'
          that.showTips()
        }
      })
    },
    showModifyAddressDialog () {
      this.modifyAddressValue = this.$store.state.companyInfo.address
      if (this.modifyAddressValue === '-') {
        this.modifyAddressValue = ''
      }
      this.isShowModifyAddressDialog = true
    },
    closeModifyAddressDialog () {
      this.isShowModifyAddressDialog = false
    },
    confirmModifyAddress (value) {
      var that = this
      // var params = new URLSearchParams()
      // params.append('method', 'UpdateCompanyAddress')
      // params.append('address', value)
      var params = {
        'address' : value
      }
      this.$http.post('UpdateCompanyAddress', params, function (response) {
        // alert('login : ' + JSON.stringify(response))
        if (response.errorMsg) {
          that.tipMsg = response.errorMsg
          that.showTips()
        } else if (response.errorInfo) {
          that.tipMsg = response.errorInfo.errMsg
          that.showTips()
        } else {
          var companyInfo = that.$store.state.companyInfo
          companyInfo.address = value
          that.$store.commit('setCompanyInfo', companyInfo)
          that.tipMsg = '修改成功'
          that.showTips()
        }
        that.isShowModifyAddressDialog = false
      })
    },
    showModifyTelDialog () {
      this.modifyTelValue = this.$store.state.companyInfo.telephone
      this.isShowModifyTelDialog = true
    },
    closeModifyTelDialog () {
      this.isShowModifyTelDialog = false
    },
    confirmModifyTel (value) {
      this.isShowModifyTelDialog = false
    },
    showModifyPasswdDialog () {
      this.isShowModifyPasswdDialog = true
    },
    closeModifyPasswdDialog () {
      this.isShowModifyPasswdDialog = false
    },
    confirmModifyPasswd () {
      this.isShowModifyPasswdDialog = false
    }
  }
}
</script>

<style scoped>
.companyinfo-container {
  width: 1330px;
  height: 615px;
  margin-left: 30px;
  margin-right: 30px;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 1);
  border: 1px solid rgba(229, 229, 229, 1);
}
.companyinfo-item {
  height: 60px;
  display: flex;
  flex-direction: row;
  text-align: left;
  /* align-content: center; */
  /* background: green; */
}
.companyinfo-item-key {
  width: 150px;
  line-height: 16px;
  color: rgba(80, 80, 80, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Medium;
  /* background: red; */
  padding-left: 30px;
  padding-top: 25px;
  /* align-content: center; */
}
.companyinfo-item-value {
  color: rgba(80, 80, 80, 0.7);
  font-size: 16px;
  font-family: STHeitiSC-Light;
  width: 1000px;
  line-height: 16px;
  text-align: left;
  /* background: yellow; */
  padding-left: 30px;
  padding-top: 25px;
  align-content: center;
}
.companyinfo-item-operation {
  width: 100px;
  line-height: 16px;
  color: rgba(80, 80, 80, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Medium;
  /* background: blue; */
  padding-right: 30px;
  padding-top: 25px;
  align-content: center;
}
.operate-enable {
  line-height: 16px;
  color: rgba(54, 158, 239, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
</style>
