export default {
  PROXY_ROUTER: {
    SERVANT: "service_maintenance_webapi",
    LOCALHOST: {
      HOST: "localhost:8080",
      PROXY_HOST: "https://devproxy.xueqiao.cn"
    },
    DEV: {
      HOST: "company-service-maintenance.devoa.xueqiao.cn",
      PROXY_HOST: "https://devproxy.xueqiao.cn"
    },
    GAMMA: {
      HOST: "company-service-maintenance.gaoa.xueqiao.cn",
      PROXY_HOST: "https://gaproxy.xueqiao.cn"
    },
    IDC: {
      HOST: "company-service-maintenance.oa.xueqiao.cn",
      PROXY_HOST: "https://proxy.xueqiao.cn"
    }
  },
  TOP_HOST: {
    LOCALHOST: {
      HOST: "localhost:8080",
      TOP_HOST: "devoa.xueqiao.cn"
    },
    DEV: {
      HOST: "company-service-maintenance.devoa.xueqiao.cn",
      TOP_HOST: "devoa.xueqiao.cn"
    },
    GAMMA: {
      HOST: "company-service-maintenance.gaoa.xueqiao.cn",
      TOP_HOST: "gaoa.xueqiao.cn"
    },
    IDC: {
      HOST: "company-service-maintenance.oa.xueqiao.cn",
      TOP_HOST: "oa.xueqiao.cn"
    }
  },
  MAINTENANCE_TIMESPAN: {
    DESCRIPTION: "服务维护时间段",
    ITEMS: [
      {
        value: "WEEKEND",
        label: "周末"
      },
      {
        value: "WORKING_DAY",
        label: "工作日"
      }
    ]
  },
  MAINTENANCE_STATE: {
    DESCRIPTION: "服务维护状态",
    ITEMS: [
      {
        value: "EMPTY",
        label: "空闲"
      },
      {
        value: "SCHEDULED",
        label: "计划中"
      }
    ]
  },
  OPERATE_TYPE: {
    DESCRIPTION: "服务操作类型",
    ITEMS: [
      {
        value: "UPGRADE",
        label: "升级"
      },
      {
        value: "ROLLBACK",
        label: "回退"
      }
    ]
  },
  HOSTING_SERVICE_STATE: {
    DESCRIPTION: "托管服务状态",
    ITEMS: [
      {
        value: "WAITING",
        label: "等待中"
      },
      {
        value: "OPENING",
        label: "开通中"
      },
      {
        value: "UPGRADING",
        label: "升级中"
      },
      {
        value: "WORKING",
        label: "工作中"
      },
      {
        value: "EXPIRED",
        label: "已过期"
      },
      {
        value: "RELEASED",
        label: "已释放"
      },
      {
        value: "UNKNOWN",
        label: "未知"
      }
    ]
  },
  TRADE_TYPE: {
    DESCRIPTION: "交易类型",
    ITEMS: [
      {
        value: "NONE",
        label: "未设定"
      },
      {
        value: "REAL",
        label: "实盘"
      },
      {
        value: "SIM",
        label: "模拟盘"
      }
    ]
  }
}
