CREATE TABLE thosting_machine (
  Fmachine_id           BIGINT UNSIGNED NOT NULL DEFAULT 0,
  Fmachine_hostname     VARCHAR(64)     NOT NULL DEFAULT "",
  Fmachine_inner_ip     VARCHAR(64)     NOT NULL DEFAULT "",
  Fmachine_outer_ip     VARCHAR(64)     NOT NULL DEFAULT "",
  Frunning_status       INT UNSIGNED    NOT NULL DEFAULT 0,
  Fassign_status        INT UNSIGNED    NOT NULL DEFAULT 0,

  Finstance_id          VARCHAR(32)     NOT NULL DEFAULT "",
  Fregion_id            VARCHAR(32)     NOT NULL DEFAULT "" COMMENT 'cn-qingdao,cn-beijing,cn-zhangjiakou,cn-huhehaote,cn-hangzhou,
                                                           cn-shanghai,cn-shenzhen,cn-hongkong,
                                                           ap-northeast-1,ap-southeast-1,ap-southeast-2,ap-southeast-3,ap-south-1,
                                                           us-east-1,us-west-1,me-east-1,eu-central-1',

  Fspec_memory_gb       INT UNSIGNED    NOT NULL DEFAULT 0,
  Fspec_cpu_count       INT UNSIGNED    NOT NULL DEFAULT 0,
  Fspec_outif_bandmb    INT UNSIGNED    NOT NULL DEFAULT 0,

  Fcreate_timestamp     INT UNSIGNED    NOT NULL DEFAULT 0,
  Flastmodify_timestamp INT UNSIGNED    NOT NULL DEFAULT 0,

  PRIMARY KEY (Fmachine_id),
  UNIQUE KEY (Fmachine_hostname),
  UNIQUE KEY (Fmachine_outer_ip),
  UNIQUE KEY (Fmachine_inner_ip)
)
  CHARSET = utf8mb4, ENGINE = Inno_db;

alter table thosting_machine add column Fregion_id  VARCHAR(32)  NOT NULL DEFAULT '' COMMENT 'cn-qingdao,cn-beijing,cn-zhangjiakou,cn-huhehaote,cn-hangzhou,cn-shanghai,cn-shenzhen,cn-hongkong,ap-northeast-1,ap-southeast-1,ap-southeast-2,ap-southeast-3,ap-south-1,us-east-1,us-west-1,me-east-1,eu-central-1';

alter table thosting_machine add column Frunning_status       INT UNSIGNED    NOT NULL DEFAULT 0;
alter table thosting_machine add column Fassign_status        INT UNSIGNED    NOT NULL DEFAULT 0;


CREATE TABLE thosting_related_info (
  Frelated_id             BIGINT UNSIGNED NOT NULL DEFAULT 0,
  Fcompany_id             INT UNSIGNED    NOT NULL DEFAULT 0,
  Fcompany_group_id       INT UNSIGNED    NOT NULL DEFAULT 0,
  Fmachine_id             BIGINT UNSIGNED NOT NULL DEFAULT 0,

  Factive_start_timestamp INT UNSIGNED    NOT NULL DEFAULT 0,
  Factive_end_timestamp   INT UNSIGNED    NOT NULL DEFAULT 0,

  Fmachine_inner_ip       VARCHAR(64)     NOT NULL DEFAULT "",
  Fmachine_outer_ip       VARCHAR(64)     NOT NULL DEFAULT "",

  Fcreate_timestamp       INT UNSIGNED    NOT NULL DEFAULT 0,
  Flastmodify_timestamp   INT UNSIGNED    NOT NULL DEFAULT 0,

  PRIMARY KEY (Frelated_id),
  INDEX machine_id_index(Fmachine_id),
  UNIQUE KEY company_group_unique_machine(Fcompany_id, Fcompany_group_id, Fmachine_id)
)
  CHARSET = utf8mb4, ENGINE = Inno_db;


-- 这个表是否已做废？ --
CREATE TABLE taliyun_instance (
  Finstance_id                        VARCHAR(32) PRIMARY KEY,
  Finstance_name                      VARCHAR(128),
  Fdescription                        VARCHAR(256),
  Fimage_id                           VARCHAR(128),
  Fos_name                            VARCHAR(128),
  Fos_type                            VARCHAR(32),
  Fregion_id                          VARCHAR(32) COMMENT 'cn-qingdao,cn-beijing,cn-zhangjiakou,cn-huhehaote,cn-hangzhou,
                                                           cn-shanghai,cn-shenzhen,cn-hongkong,
                                                           ap-northeast-1,ap-southeast-1,ap-southeast-2,ap-southeast-3,ap-south-1
                                                           us-east-1,us-west-1,me-east-1,eu-central-1',
  Fzone_id                            VARCHAR(32),
  Fcluster_id                         VARCHAR(32),
  Finstance_type                      VARCHAR(32),
  Finstance_type_family               VARCHAR(32),
  Fcpu                                INT,
  Fmemory                             INT,
  Fhost_name                          VARCHAR(128),
  Fstatus                             VARCHAR(16) COMMENT 'Running, Starting, Stopping, Stopped(from aliyun), Unknown, Deleted(from xueqiao)',
  Fserial_number                      VARCHAR(36),
  Finternet_charge_type               VARCHAR(32) COMMENT 'PayByTraffic：按流量计费; PayByBandwidth：按带宽计费',
  Finternet_max_bandwidth_in          INT,
  Finternet_max_bandwidth_out         INT,
  Fvlan_id                            VARCHAR(32),
  Fcreation_time                      VARCHAR(32) COMMENT 'yyyy-MM-dd"T"HH:mm"Z"',
  Fstart_time                         VARCHAR(32) COMMENT 'yyyy-MM-dd"T"HH:mm"Z"',
  Finstance_network_type              VARCHAR(16) COMMENT 'Classic,Vpc',
  Finstance_charge_type               VARCHAR(16) COMMENT 'PrePaid：预付费，即包年包月; PostPaid：后付费，即按量付费',
  Fsale_cycle                         VARCHAR(32),
  Fexpired_time                       VARCHAR(32) COMMENT 'yyyy-MM-dd"T"HH:mm"Z"',
  Fauto_release_time                  VARCHAR(32),
  Fio_optimized                       BOOLEAN,
  Fdevice_available                   BOOLEAN,
  Flocal_storage_capacity             BIGINT,
  Flocal_storage_amount               INT,
  Fgpu_amount                         INT,
  Fgpu_spec                           VARCHAR(32),
  Fspot_strategy                      VARCHAR(32) COMMENT '当InstanceChargeType为PostPay时为生效:NoSpot(default),SpotWithPriceLimit,SpotAsPriceGo',
  Fspot_price_limit                   DOUBLE,
  Fresource_group_id                  VARCHAR(32),
  Fkey_pair_name                      VARCHAR(64),
  Frecyclable                         BOOLEAN,
  Fhpc_cluster_id                     VARCHAR(32),
  Fstopped_mode                       VARCHAR(32),
  Fnetwork_interfaces                 VARCHAR(1024) COMMENT 'json for list of NetworkInterface',
  Foperation_locks                    VARCHAR(1024) COMMENT 'json for list of LockReason',
  Ftags                               VARCHAR(1024) COMMENT 'json for list of Tag',
  Fsecurity_group_ids                 VARCHAR(1024) COMMENT 'join by "^_^"',
  Fpublic_ip_address                  VARCHAR(1024) COMMENT 'join by ","',
  Finner_ip_address                   VARCHAR(1024) COMMENT 'join by ","',
  Frdma_ip_address                    VARCHAR(1024) COMMENT 'join by ","',
  Fvpc_attributes_vpc_id              VARCHAR(32) COMMENT 'VpcAttributes.vpcId',
  Fvpc_attributes_v_switch_id         VARCHAR(32) COMMENT 'VpcAttributes.vSwitchId',
  Fvpc_attributes_nat_ip_address      VARCHAR(16) COMMENT 'VpcAttributes.natIpAddress',
  Fvpc_attributes_private_ip_address  VARCHAR(1024) COMMENT 'VpcAttributes.privateIpAddress, join by ","',
  Feip_address_allocation_id          VARCHAR(32) COMMENT 'EipAddress.allocationId',
  Feip_address_ip_address             VARCHAR(16) COMMENT 'EipAddress.ipAddress',
  Feip_address_bandwidth              INT COMMENT 'EipAddress.bandwidth',
  Feip_address_internet_charge_type   VARCHAR(32) COMMENT 'EipAddress.internetChargeType',
  Feip_address_is_support_unassociate BOOLEAN COMMENT 'EipAddress.isSupportUnassociate',
  Fupdate_timestamp                   LONG
)
  CHARSET = utf8mb4, ENGINE = Inno_db;