
var DEBUG = false;

var host = DEBUG ? 'http://my.galacard.me/' : '/';


// post数据通用函数
 var postMsg = function(method_name, data, success, error) {
   var requestUrl = host + 'service?method='+ method_name;
   var tokenMethod = [
     'Apply',
     'BuyNow',
     'ChangePassword',
     'GetCompany',
     'SetEmail',
     'SetInfo',
     'SetTel',
     'SetCompanyCode',
     'Logout'
   ];
   if($.inArray(method_name, tokenMethod) != -1) {
     var token = Cookies.get('token');
     var userId = Cookies.get('userId');
     requestUrl += ('&token=' + token + '&userId='+ userId);
   }
   $.post(requestUrl, data, function(json) {
       if(json.errorInfo) {
        //  alert(json.errorInfo.errMsg);
         error && error(json);
       }else {
         success && success(json)
       }
     },
     "json"
   )
 }


// get数据通用函数
 var getMsg = function(method_name, data, success, error) {
   var requestUrl = host + 'service?method='+ method_name;
   var tokenMethod = [
     'Apply',
     'BuyNow',
     'ChangePassword',
     'GetCompany',
     'SetEmail',
     'SetInfo',
     'SetTel',
     'SetCompanyCode',
     'Logout'
   ];

   if($.inArray(method_name, tokenMethod) != -1) {
     var token = Cookies.get('token');
     var userId = Cookies.get('userId');
     requestUrl += ('&token=' + token + '&userId='+ userId);
   }

   $.getJSON(requestUrl,data,function(json) {
       if(json.errorInfo){
         alert(json.errorInfo.errMsg);
         error && error(json);
       }else {
         success && success(json)
       }
     }
   )
 }

  // 注册页
  var vm_SignUp = new Vue({
    el: '#reg_page',
    delimiters: ["{=", "}"],
    data: {
      // 邮箱输入验证参数
      validImgEmail: '',
      emailValid: false,
      emailValidMsg: '',
      email_reg: false,
      // 密码输入验证参数
      validImgPwd: '',
      pwdValid: false,
      pwdValidMsg: '',
      pwd_reg: false,
      // 确认密码输入验证参数
      validImgVpwd: '',
      vpwdValid: false,
      vpwdValidMsg: '',
      vpwd_reg: false,
      // 联系人输入验证参数
      validImgContact: '',
      contactValid: false,
      contactValidMsg: '',
      contact_reg: false,
      // 手机号码输入验证参数
      validImgTel: '',
      telValid: false,
      telValidMsg: '',
      tel_reg: false,
      // 注册表单提交数据
      email: '',
      pwd: '',
      vpwd: '',
      contact: '',
      tel: '',
      verify_code: '',
      company_name: '',

      btnDisabled: false,

      vbtnDisabled: false,
      time: '',
      getVerifyMsg: false,
      // 注册提示信息
      signUpTips: false,
      signUpTipsMsg: ''
    },
    mounted:function() {
      if($('#reg_page').length) {
        var token = $.trim(Cookies.get('token'));
        if(token) {
          window.location.href = '/';
        }
      }
    },
    methods: {
      // 注册
      signUp: function() {
        var data = {
          email: this.email,
          pwd: this.pwd,
          contact: this.contact,
          tel: this.tel,
          verify_code: this.verify_code,
          company_name: this.company_name
        };

        if(this.btnDisabled) return ;

        var that = this;

        if(!data.email || !data.pwd || !data.contact || !data.tel || !data.verify_code) {
          that.signUpTips = true;
          that.signUpTipsMsg = '请完善资料信息';
          return ;
        }else {
          that.signUpTips = false;
          that.signUpTipsMsg = '';
        }

        that.btnDisabled = true;

        var requestUrl = host + 'service?method=SignUp';
        $.post(requestUrl, data, function(json) {
            if(json.errorInfo) {
              that.btnDisabled = false;
              that.signUpTipsMsg = json.errorInfo.errMsg;
              that.signUpTips = true;
            }else {
              that.btnDisabled = false;
              location.href = '/login.html';
            }
          },
          "json"
        )


      },

      // 获取手机验证码
      getTelVerifyCode: function() {
        var data = {
          tel: this.tel
        };

        if(this.vbtnDisabled) return ;

        var that = this,
            time = 60;
        that.vbtnDisabled = true;
        postMsg (
          'GetTelVerifyCode', data, function(json) {
            if(json.errorInfo) {
              alert(json.errorInfo.errMsg);
            }else {
              that.getVerifyMsg = true;
              that.time = '('+time+')';

              var timer = setInterval(function() {
                if(time == '0'){
                    that.time = '';
                    clearInterval(timer);
                    that.vbtnDisabled = false;
                    that.getVerifyMsg = false;
                }else {
                    time--;
                    that.time = '('+time+')';
                }
              },1000)
            }
          }
        )
      },


      // 邮箱验证
      checkEmail: function() {
        this.emailValid = true;
        this.email_reg = (/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(this.email));

        if(this.email_reg) {
          this.validImgEmail = 'right';
          this.emailValidMsg = '该邮箱可以使用';
        }else {
          this.validImgEmail = 'wrong';
          this.emailValidMsg = '该邮箱格式不正确';
        }
      },

      // 密码验证
      cheakPwd: function() {
        this.pwdValid = true;
        this.pwd_reg = (/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test(this.pwd));
        if(this.pwd_reg) {
          this.validImgPwd = 'right';
          this.pwdValidMsg = '密码可以使用';
        }else {
          this.validImgPwd = 'wrong';
          this.pwdValidMsg = '密码格式不正确';
        }
      },

      // 确认密码验证
      verifyPwd: function() {
        this.vpwdValid = true;
        this.vpwd_reg = (this.pwd===this.vpwd);
        if(this.vpwd_reg&&this.pwd_reg) {
          this.validImgVpwd = 'right';
          this.vpwdValidMsg = '验证通过';
        }else{
          this.validImgVpwd = 'wrong';
          this.vpwdValidMsg = '密码不一致';
        }
      },

      // 联系人输入验证
      checkContact: function() {
        this.contactValid = true;
        this.contact_reg = (/^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/.test(this.contact));
        if(this.contact_reg) {
          this.validImgContact = 'right';
          this.contactValidMsg = '';
        }else {
          this.validImgContact = 'wrong';
          this.contactValidMsg = '请输入正确的人名';
        }
      },

      // 手机号输入验证
      checkTel: function() {
        this.telValid = true;
        this.tel_reg = (/^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/.test(this.tel));
        if(this.tel_reg) {
          this.validImgTel = 'right';
          this.telValidMsg = '';
        }else {
          this.validImgTel = 'wrong';
          this.telValidMsg = '请输入正确的手机号码';
        }
      }
    }
  });


  // 登录页
  var vm_Login = new Vue({
    el: '.login_page',
    delimiters: ["{=", "}"],
    data: {
      email: '',
      pwd: '',
      btnDisabled: false,
      loginError: false,
      loginErrorMsg: ''
    },
    mounted:function() {
      if($('.login_page').length) {
        var token = $.trim(Cookies.get('token'));
        if(token) {
          window.location.href = '/';
        }
      }
    },
    methods: {
      login: function() {
        if(this.btnDisabled) return ;

        var that = this;

        var data = {
          email: this.email,
          password: this.pwd
        };

        if(!data.email || !data.password) {
          that.loginError = true;
          that.loginErrorMsg = '请输入完整信息';
          return ;
        }

        that.btnDisabled = true;
        $.post(host + 'service?method=Login', data, function(json){
            that.btnDisabled = false;

            if(json.errorInfo) {
              console.log(json);
              that.loginError = true;
              that.loginErrorMsg = json.errorInfo.errMsg;
            }else {
              Cookies.set('token',json.value.token,{ expires: 7, path: '/' });
              Cookies.set('userId',json.value.userId,{ expires: 7, path: '/' });
              location.href = '/';
            }
          }, 'json')
      }
    }
  })

  // 首页

  $('.index_container').length && new Vue({
    el: '.index_container',
    delimiters: ["{=", "}"],
    data: {
      canRender: false,

      tabIndex: 0,
      tryBtnShow: false,
      openBtnShow: false,
      emailEditShow: false,
      phoneEditShow: false,
      companyCodeEditShow: false,

      downloadUrl: '',

      msgData: {},

      formSaveCompanyCode: '',


      // 购买参数
      formApplyCompany: '',
      formApplyFund: '',
      formApplyContact: '',
      formApplyTel: '',

      formSaveContact: '',
      formSaveCompanyName: '',

      // 改密参数
      formOldPwd: '',
      formNewPwd: '',
      formNewVpwd: '',

      // 改团队名参数
      setCompanyCodeTips: false,
      setCompanyCodeMsg: '',

      changePwdTips: false,
      changePwdMsg: '',
      changePwdJudge: '',
      changePwd_reg: false,

      applyTips: false,
      setInfoTips: false
    },

    mounted:function() {
      this.init();
    },

    methods: {
      // 在文档加载完成后执行获取团队信息
      init: function() {
        var that = this;
        var token = Cookies.get('token');

        if(!token) {
          window.location.href = '/login.html';
          return ;
        }

        this.canRender = true;

        getMsg('GetCompany', {}, function(json) {
          var msg = json.value;
          that.msgData = msg;
          that.formSaveContact = msg.contact;
          that.formSaveCompanyName = msg.companyName;
        }, function() {
          Cookies.set('token', '',{ expires: 7, path: '/' });
          Cookies.set('userId', '',{ expires: 7, path: '/' });
          window.location.href = '/login.html';
        });

        getMsg('GetDownload', {}, function(json) {
          that.downloadUrl = json.value;
        });
      },

      // 购买、试用申请
      apply: function() {
        var data = {
          company: this.formApplyCompany,
          fund: this.formApplyFund,
          contact: this.formApplyContact,
          tel: this.formApplyTel
        }

        var that = this;
        if(this.tryBtnShow){
          postMsg ('Apply', data, function(json){
            // alert ('Apply ok');
            that.applyTips = true;
            setTimeout(function() {
              window.location.reload();
            },1000);
          });
        }else if (this.openBtnShow) {
          postMsg ('BuyNow', data, function(json){
            that.applyTips = true;
            setTimeout(function() {
              window.location.reload();
            },1000);
          });
        }else {
          alert ('Error!')
        }
      },

      // 更改密码
      changePwd: function() {
        var data = {
          old_pwd: this.formOldPwd,
          new_pwd: this.formNewPwd
        }
        var that = this;
        if(!this.formOldPwd || !this.formNewPwd || !this.formNewVpwd){
          that.changePwdJudge = 'wrong';
          that.changePwdMsg = '请填写完整信息！';
          that.changePwdTips = true;
          return
        }
        if(this.formNewPwd !== this.formNewVpwd){
          that.changePwdJudge = 'wrong';
          that.changePwdMsg = '设置密码不一致！';
          that.changePwdTips = true;
          return
        }
        postMsg ('ChangePassword',data,
          function(json){
          that.changePwd_reg = true;
          that.changePwdJudge = 'right';
          that.changePwdMsg = '密码修改成功';
          that.changePwdTips = true;
          setTimeout(function(){
            window.location.reload();
          },1000);
        })
      },

      // 获取邮箱验证码
      getEmailVerifyCode: function() {
        var data = {
          email: this.email
        };

        getMsg('GetEmailVerifyCode', data, function(json) {
            alert ('验证码发送成功！')
        })
      },


      //资料修改保存
      setInfo: function() {
        var data = {
          contact: this.formSaveContact,
          com_name: this.formSaveCompanyName
        }
        var that = this;
        postMsg('SetInfo', data, function(json) {
            that.setInfoTips = true;
            setTimeout(function(){
              window.location.reload();
            },1000);
        });
      },

      // 修改团队名
      setCompanyCode: function() {
        var data = {
          company_code: this.formSaveCompanyCode
        }

        var that = this;

        postMsg('SetCompanyCode', data, function(json) {
          // alert ('团队名修改成功!');
          that.setCompanyCodeMsg = '修改成功!';
          that.setCompanyCodeTips = true;
          setTimeout(function() {
            window.location.reload();
          },1000);
        })
      },

      //登出
      logout: function() {
        getMsg(
          'Logout',{},function(json){
            Cookies.set('token', '',{ expires: 7, path: '/' });
            Cookies.set('userId', '',{ expires: 7, path: '/' });
            location.href = '/login.html';
          }
        )
      }
    }
  })

  // 忘记密码页
  var vm_Forgot = new Vue({
    el: '#forgot_page',
    delimiters: ["{=", "}"],
    data: {
      // 忘记密码页数据项
      forgot_tel: '',
      forgot_verifyCode: '',
      forgot_pwd: '',
      forgot_vpwd: '',

      forgot_validImgPwd: '',
      forgot_pwdValid: false,
      forgot_pwdValidMsg: '',
      forgot_pwd_reg: false,

      forgot_validImgVpwd: '',
      forgot_vpwdValid: false,
      forgot_vpwdValidMsg: '',
      forgot_vpwd_reg: false,
      // 验证码参数
      forgotVbtnDisabled: false,
      time: '',
      getForgotVerifyMsg: false,

      // 提示信息参数
      resetPwdTips: false,
      resetPwd_reg: false,
      forgot_validImgResetPwd: '',
      resetPwdMsg: ''
    },
    methods:{
      // 忘记密码页功能
      resetPassword: function() {
        var data = {
          tel: this.forgot_tel,
          verify_code: this.forgot_verifyCode,
          pwd: this.forgot_pwd,
        }
        var that = this;
        if(this.forgot_tel!='' && this.forgot_verifyCode!='' && this.forgot_pwd!='' && this.forgot_vpwd!=''){
          postMsg('ResetPassword', data, function(json){
            that.resetPwd_reg =true;
            that.forgot_validImgResetPwd = 'right';
            that.resetPwdMsg = '密码重置成功';
            that.resetPwdTips = true;
            setTimeout(function() {
              window.location.href = '/login.html';
            },1000);
          },function(json) {
            that.resetPwd_reg =false;
            that.forgot_validImgResetPwd = 'wrong';
            that.resetPwdMsg = json.errorInfo.errMsg;
            that.resetPwdTips = true;
          })
        }else {
          that.resetPwd_reg = false;
          that.forgot_validImgResetPwd = 'wrong';
          that.resetPwdMsg = '请完善信息！';
          that.resetPwdTips = true;
        }

      },

      //忘记密码验证码获取
      getForgotTelVerifyCode: function() {
        var data = {
          tel: this.forgot_tel
        };
        if(this.forgotVbtnDisabled) return ;

        var that = this,
            time = 60;
        that.forgotVbtnDisabled = true;
        postMsg('GetTelVerifyCode', data, function(json) {
          if(json.errorInfo) {
            alert(json.errorInfo.errMsg);
          }else {
            that.getForgotVerifyMsg = true;
            that.time = '('+time+')';

            var timer = setInterval(function() {
              if(time == '0'){
                  that.time = '';
                  clearInterval(timer);
                  that.forgotVbtnDisabled = false;
                  that.getForgotVerifyMsg = false;
              }else {
                  time--;
                  that.time = '('+time+')';
              }
            },1000)
          }
        }
      )
    },

      // 忘记密码验证
      cheakForgotPwd: function() {
        this.forgot_pwdValid = true;
        this.forgot_pwd_reg = (/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test(this.forgot_pwd));
        if(this.forgot_pwd_reg) {
          this.forgot_validImgPwd = 'right';
          this.forgot_pwdValidMsg = '密码可以使用';
        }else {
          this.forgot_validImgPwd = 'wrong';
          this.forgot_pwdValidMsg = '密码格式不正确';
        }
      },

      // 忘记密码重复验证
      verifyForgotPwd: function() {
        this.forgot_vpwdValid = true;
        this.forgot_vpwd_reg = (this.forgot_pwd===this.forgot_vpwd);
        if (this.forgot_vpwd_reg && this.forgot_pwd_reg) {
          this.forgot_validImgVpwd = 'right';
          this.forgot_vpwdValidMsg = '验证通过';
        }else {
          this.forgot_validImgVpwd = 'wrong';
          this.forgot_vpwdValidMsg = '密码不一致';
        }
      }
    }
  })
