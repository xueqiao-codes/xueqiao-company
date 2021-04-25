import Vue from "vue"
import Router from "vue-router"

import Page403 from "./views/403"
import Page404 from "./views/404"
import MainPage from "./views/MainPage"
import TouyanMainPage from "./views/TouyanMainPage"
import HomePage from "./views/HomePage"
import DocumentPage from "./views/DocumentPage"
import LoginPage from "./views/LoginPage"
import CompanyLoginPage from "./views/CompanyLoginPage"
import PersonalUserLoginPage from "./views/PersonalUserLoginPage"
import RegisterPage from "./views/RegisterPage"
import CompanyRegisterPage from "./views/CompanyRegisterPage"
import PersonalUserRegisterPage from "./views/PersonalUserRegisterPage"
import ModifyPasswdPage from "./views/ModifyPasswdPage"
import ManagePage from "./views/ManagePage"
import HostingManagerPage from "./views/HostingManagerPage"
import ChargeManagerPage from "./views/ChargeManagerPage"
import OrderManagerPage from "./views/OrderManagerPage"
import CompanyInfoManagerPage from "./views/CompanyInfoManagerPage"
import CreateOrderPage from "./views/CreateOrderPage"
import ConfirmOrderPage from "./views/ConfirmOrderPage"
import AlipayResultPage from "./views/AlipayResultPage"
import UserVerifyForDownloadPage from "./views/UserVerifyForDownloadPage"
import CompanyUserVerifyForDownloadPage from "./views/CompanyUserVerifyForDownloadPage"
import PersonalUserVerifyForDownloadPage from "./views/PersonalUserVerifyForDownloadPage"
import ClientAppDownloadPage from "./views/ClientAppDownloadPage"
import PersonalUserManagerPage from "./views/PersonalUserManagerPage"
import PersonalUserHostingServicePage from "./views/PersonalUserHostingServicePage"
import CreatePersonalUserOrderPage from "./views/CreatePersonalUserOrderPage"
import ConfirmPersonalUserOrderPage from "./views/ConfirmPersonalUserOrderPage"
import PersonalUserChangePasswordPage from "./views/PersonalUserChangePasswordPage"
import PersonalUserForgetPasswordPage from "./views/PersonalUserForgetPasswordPage"
import ClientAppDownloadForPersonalUserPage from "./views/ClientAppDownloadForPersonalUserPage"
import XueqiaoServiceAgreementPage from "./views/XueqiaoServiceAgreementPage"
import XueqiaoPrivacyAgreementPage from "./views/XueqiaoPrivacyAgreementPage"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: "/",
      redirect: "/home",
      name: "MainPage",
      component: MainPage,
      children: [
        {
          path: "/home",
          name: "HomePage",
          component: HomePage
        },
        {
          path: "/doc",
          name: "DocumentPage",
          component: DocumentPage
        },
        {
          path: "/login",
          redirect: "/login/company",
          name: "LoginPage",
          component: LoginPage,
          children: [
            {
              path: "/login/company",
              name: "CompanyLoginPage",
              component: CompanyLoginPage
            },
            {
              path: "/login/personal",
              name: "PersonalUserLoginPage",
              component: PersonalUserLoginPage
            },
          ]
        },
        {
          path: "/userverify",
          // redirect: "/userverify/company",
          name: "UserVerifyForDownloadPage",
          component: UserVerifyForDownloadPage,
          children: [
            {
              path: "/userverify/company",
              name: "CompanyUserVerifyForDownloadPage",
              component: CompanyUserVerifyForDownloadPage
            },
            {
              path: "/userverify/personal",
              name: "PersonalUserVerifyForDownloadPage",
              component: PersonalUserVerifyForDownloadPage
            },
          ]
        },
        {
          path: "/download",
          name: "clientAppDownloadPage",
          component: ClientAppDownloadPage
        },
        {
          path: "/personalUserDownload",
          name: "clientAppDownloadForPersonalUserPage",
          component: ClientAppDownloadForPersonalUserPage
        },
        {
          path: "/serviceAgreement",
          name: "xueqiaoServiceAgreementPage",
          component: XueqiaoServiceAgreementPage
        },
        {
          path: "/privacyAgreement",
          name: "xueqiaoPrivacyAgreementPage",
          component: XueqiaoPrivacyAgreementPage
        },
        {
          path: "/register",
          redirect: "/register/company",
          name: "RegisterPage",
          component: RegisterPage,
          children: [
            {
              path: "/register/company",
              name: "CompanyRegisterPage",
              component: CompanyRegisterPage
            },
            {
              path: "/register/personal",
              name: "PersonalUserRegisterPage",
              component: PersonalUserRegisterPage
            },
          ]
        },
        {
          path: "/modifypasswd",
          name: "ModifyPasswdPage",
          component: ModifyPasswdPage
        },
        {
          path: "/personalUserChangePassword",
          name: "PersonalUserChangePasswordPage",
          component: PersonalUserChangePasswordPage
        },
        {
          path: "/personalUserForgetPassword",
          name: "PersonalUserForgetPasswordPage",
          component: PersonalUserForgetPasswordPage
        },
        {
          path: "/createOrder",
          name: "CreateOrderPage",
          component: CreateOrderPage
        },
        {
          path: "/confirmOrder",
          name: "ConfirmOrderPage",
          component: ConfirmOrderPage
        },
        {
          path: "/createPersonalUserOrder",
          name: "CreatePersonalUserOrderPage",
          component: CreatePersonalUserOrderPage
        },
        {
          path: "/confirmPersonalUserOrder",
          name: "ConfirmPersonalUserOrderPage",
          component: ConfirmPersonalUserOrderPage
        },
        {
          path: "/alipayresult",
          name: "AlipayResultPage",
          component: AlipayResultPage
        },
        {
          path: "/personal",
          redirect: "/personal/hosting",
          name: "personal",
          component: PersonalUserManagerPage,
          children: [
            {
              path: "/personal/hosting",
              name: "PersonalUserHostingServicePage",
              component: PersonalUserHostingServicePage
            },
          ]
        },
        {
          path: "/manager",
          redirect: "/manager/hosting",
          name: "ManagePage",
          component: ManagePage,
          children: [
            {
              path: "/manager/hosting",
              name: "HostingManagerPage",
              component: HostingManagerPage
            },
            {
              path: "/manager/charge",
              name: "ChargeManagerPage",
              component: ChargeManagerPage
            },
            {
              path: "/manager/order",
              name: "OrderManagerPage",
              component: OrderManagerPage
            },
            {
              path: "/manager/companyinfo",
              name: "CompanyInfoManagerPage",
              component: CompanyInfoManagerPage
            }
          ]
        }
      ]
    },
    {
      path: "/touyan",
      name: "TouyanMainPage",
      component: TouyanMainPage
    },
    {
      path: "/403",
      name: "Page403",
      component: Page403
    },
    {
      path: "*",
      name: "Page404",
      component: Page404
    }
  ]
})
