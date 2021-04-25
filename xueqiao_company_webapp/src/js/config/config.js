export default {
  config: {
    HOSTING_MACHINE_SPEC_LIST: [
      {
        inteface: "支持的交易接口",
        basic: "CTP",
        common: "CTP&易盛",
        enhanced: "CTP&易盛"
      },
      {
        inteface: "支持同时启用资金账户数量",
        basic: "3",
        common: "5",
        enhanced: "8"
      },
      {
        inteface: "支持同时启用子账户数量",
        basic: "5",
        common: "10",
        enhanced: "15"
      },
      {
        inteface: "每个子账户可分配用户数量",
        basic: "5",
        common: "10",
        enhanced: "15"
      },
      {
        inteface: "支持用户订阅合约行情数量",
        basic: "50",
        common: "80",
        enhanced: "100"
      },
      {
        inteface: "支持用户订阅套利行情数量",
        basic: "20",
        common: "50",
        enhanced: "80"
      }
    ],
    CUSTOM_SERVICE_TEL: "0755-2230 4680",
    COMPANY_ADDRESS: "深圳市南山区科技园高新中一道9号软件大厦810室",
    COMPANY_EAMIL: "info@xueqiao.cn"
  },
  CONSTANTS: {
    PRODUCT_PURPOSE_LIST: ["NEW", "UPGRADE", "RECHARGE_TIME"],
    SERVICE_TYPE_LIST: ["REAL", "SIM"],
    SERVICE_TYPE_CN_LIST: ["实盘", "模拟盘"]
  },
  MAINTENANCE_TIMESPAN: {
    DESCRIPTION: "服务维护时间段",
    LABELS: {
      WEEKEND: "周六 5:00 至 周日 23:00（冬令时 周六 6:00 至 周日 24:00）",
      WORKING_DAY: "周一至周五 5:00-6:00（冬令时 6:00-7:00）"
    },
    VALUES: {
      WEEKEND: 0,
      WORKING_DAY: 1
    },
    ITEMS: [
      {
        value: "WEEKEND",
        label: "周六 5:00 至 周日 23:00（冬令时 周六 6:00 至 周日 24:00）"
      },
      {
        value: "WORKING_DAY",
        label: "周一至周五 5:00-6:00（冬令时 6:00-7:00）"
      }
    ]
  },
  SHOW_OPERATOR_ROLE_OBSERVER: false,
}
