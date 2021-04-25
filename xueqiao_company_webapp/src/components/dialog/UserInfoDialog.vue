<template>
  <div v-if="isShow" class="dialog-bg">
    <div class="dialog-container d-custom-dialog">
      <div class="dialog-header">
        <div class="dialog-title">用户信息</div>
        <div class="dialog-close">
          <a href="#" @click="closeDialog"><img src="../../assets/icon_close.png" /></a>
        </div>
      </div>
      <div class="dialog-content custom-dialog-content">
        <div>
          <div class="dialog-item g-form-item">
            <div class="dialog-item-key g-form-label">
              <span>用户名</span>
            </div>
            <div class="dialog-item-value">
              <span>{{userInfo.userName}}</span>
            </div>
          </div>
          <div class="dialog-item g-form-item">
            <div class="dialog-item-key g-form-label">
              <span>姓名</span>
            </div>
            <div class="dialog-item-value">
              <span>{{userInfo.cnName}}</span>
            </div>
          </div>
          <div class="dialog-item g-form-item">
            <div class="dialog-item-key g-form-label">
              <span>手机</span>
            </div>
            <div class="dialog-item-value">
              <span>{{userInfo.tel}}</span>
            </div>
          </div>
          <div class="dialog-item g-form-item">
            <div class="dialog-item-key g-form-label">
              <span>邮箱</span>
            </div>
            <div class="dialog-item-value">
              <span>{{userInfo.email}}</span>
            </div>
          </div>
          <div class="dialog-item g-form-item dialog-item-hosting">
            <div class="dialog-item-key g-form-label dialog-item-key-hosting">
              <span>{{$string.operatorRoleText}}</span>
            </div>
            <div class="dialog-item-value dialog-item-value-hosting" v-if="userInfo.groupUserList.length > 0">
              <div class="hosting-item" v-for="(groupUser,index) in userInfo.groupUserList" v-bind:key="index">
                <div class="hosting-item-key g-form-label">{{groupUser.groupName}}</div>
                <div class="hosting-item-key g-form-label">({{groupUser.cnRole}})</div>
              </div>
            </div>
            <div class="dialog-item-value dialog-item-value-no-hosting" v-else><span>-</span></div>
          </div>
        </div>
      </div>
      <div class="dialog-button-container">
        <button class="g-button dialog-button" @click="confirm">确定</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['isShow', 'userInfo'],
  data () {
    return {
      newCompanyUser: {
        userName: 'dapaul',
        password: '123456',
        tel: '12345678912',
        cnName: 'dapaul lee',
        groupUserList: [
          {
            groupName: 'hosting1sldksdflskfsdlfksgfdljsdlskdfj'
          },
          {
            groupName: 'hosting2'
          }
        ]
      }
    }
  },
  watch: {
    isShow () {
      if (this.isShow) {
        // try token
        this.$commonFunc.tryToken()
      }
    }
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
.d-custom-dialog {
  width: 520px;
}
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
  width: 160px;
  line-height: 16px;
  color: rgba(80, 80, 80, 1);
  font-size: 16px;
  text-align: left;
  font-family: STHeitiSC-Light;
}
.dialog-item-value {
  width: 250px;
}
.dialog-button {
  width: 220px;
}
.dialog-item-hosting {
  align-items: flex-start;
}
.dialog-item-key-hosting {
  margin-top: 10px;
}
.dialog-item-value-no-hosting {
  margin-top: 10px;
}
.dialog-item-value-hosting {
  height: 200px;
  overflow: auto;
}
.hosting-item {
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: left;
  display: flex;
  align-items: center;
}
.hosting-item-key {
  width: 170px;
  margin-left: 5px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.hosting-item-value {
  width: 90px;
  margin-left: 10px;
}
</style>
