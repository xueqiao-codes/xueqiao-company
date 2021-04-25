export default {
  PROXY_ROUTER: {
    LOCALHOST: {
      HOST: "localhost:8080",
      PROXY_HOST: "https://devproxy.xueqiao.cn"
    },
    DEV: {
      HOST: "errorcode-manager.devoa.xueqiao.cn",
      PROXY_HOST: "https://devproxy.xueqiao.cn"
    },
    GAMMA: {
      HOST: "errorcode-manager.gaoa.xueqiao.cn",
      PROXY_HOST: "https://gaproxy.xueqiao.cn"
    },
    IDC: {
      HOST: "errorcode-manager.oa.xueqiao.cn",
      PROXY_HOST: "https://proxy.xueqiao.cn"
    }
  },
  ENUM: {
    WORKING_ORDER_TYPE: {
      DESCRIPTION: "工单类型",
      ITEMS: [
        {
          KEY: "ASSET_ACCOUNT",
          VALUE: 0,
          LABEL: "资金账号"
        },
      ]
    },
    WORKING_ORDER_STATE: {
      DESCRIPTION: "工单状态",
      ITEMS: [
        {
          KEY: "CREATE",
          VALUE: 0,
          LABEL: "新建"
        },
        {
          KEY: "IN_PROGRESS",
          VALUE: 1,
          LABEL: "进行中"
        },
        {
          KEY: "DONE",
          VALUE: 2,
          LABEL: "完成"
        },
      ]
    },
    TECH_PLATFORM_ENV: {
      DESCRIPTION: "技术平台环境",
      ITEMS: [
        {
          KEY: "NONE",
          VALUE: 0,
          LABEL: "未设定"
        },
        {
          KEY: "REAL",
          VALUE: 1,
          LABEL: "实盘"
        },
        {
          KEY: "SIM",
          VALUE: 2,
          LABEL: "模拟盘"
        },
      ]
    },
    BROKER_PLATFORM: {
      DESCRIPTION: "交易接口",
      ITEMS: [
        {
          KEY: "NONE",
          VALUE: 0,
          LABEL: "未设定"
        },
        {
          KEY: "CTP",
          VALUE: 1,
          LABEL: "CTP"
        },
        {
          KEY: "ESUNNY",
          VALUE: 2,
          LABEL: "ESUNNY"
        },
        {
          KEY: "SP",
          VALUE: 3,
          LABEL: "SP"
        },
        {
          KEY: "ESUNNY_3",
          VALUE: 4,
          LABEL: "ESUNNY_3"
        },
      ]
    }
  }
}
