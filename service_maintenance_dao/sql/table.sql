## 服务维护信息 ##
set names utf8;
DROP TABLE IF EXISTS t_service_maintenance;
CREATE TABLE t_service_maintenance(
	Fcompany_id BIGINT UNSIGNED NOT NULL COMMENT '公司id',
	Fmaintenance_timespan VARCHAR(64) NOT NULL DEFAULT '' COMMENT '维护时间段',
	Fmaintenance_state SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '当前维护状态',
	Fversion_id BIGINT UNSIGNED NOT NULL DEFAULT 0  COMMENT '版本id',
	Fversion_tag VARCHAR(32) NOT NULL DEFAULT '' COMMENT '版本tag',
	Fkeep_latest SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否保持最新',
	Fcreate_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fcompany_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;


set names utf8;
alter table t_service_maintenance add column Fkeep_latest SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否保持最新';


## 服务维护历史 ##
set names utf8;
DROP TABLE IF EXISTS t_maintenance_history;
CREATE TABLE t_maintenance_history(
	Fhistory_id BIGINT UNSIGNED NOT NULL COMMENT '历史id',
	Fcompany_id BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '公司id',
	Fcreate_schedule_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '计划提交时间',
	Fold_version_id BIGINT UNSIGNED NOT NULL DEFAULT 0  COMMENT '原版本id',
	Fold_version_tag VARCHAR(32) NOT NULL DEFAULT '' COMMENT '原版本tag',
	Ftarget_version_id BIGINT UNSIGNED NOT NULL DEFAULT 0  COMMENT '目标版本id',
	Ftarget_version_tag VARCHAR(32) NOT NULL DEFAULT '' COMMENT '目标版本tag',
	Foperate_type SMALLINT UNSIGNED NOT NULL DEFAULT 0  COMMENT '操作类型',
	Fscheduled_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '原定操作时间',
	Fmaintenance_timespan VARCHAR(64) NOT NULL DEFAULT '' COMMENT '维护时间段',
	Fcreater VARCHAR(32) NOT NULL DEFAULT '' COMMENT '创建者',
	Fcreate_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fhistory_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;

## 服务维护计划执行详情 ##
set names utf8;
DROP TABLE IF EXISTS t_schedule_operate_detail;
CREATE TABLE t_schedule_operate_detail(
	Fcompany_id BIGINT UNSIGNED NOT NULL DEFAULT 0  COMMENT '公司id',
	Fold_version_id BIGINT UNSIGNED NOT NULL DEFAULT 0  COMMENT '原版本id',
	Fold_version_tag VARCHAR(32) NOT NULL DEFAULT '' COMMENT '原版本tag',
	Ftarget_version_id BIGINT UNSIGNED NOT NULL DEFAULT 0  COMMENT '目标版本id',
	Ftarget_version_tag VARCHAR(32) NOT NULL DEFAULT '' COMMENT '目标版本tag',
	Foperate_type SMALLINT UNSIGNED NOT NULL DEFAULT 0  COMMENT '操作类型',
	Fscheduled_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '原定操作时间',
	Fmaintenance_timespan VARCHAR(64) NOT NULL DEFAULT '' COMMENT '维护时间段',
	Fcreater VARCHAR(32) NOT NULL DEFAULT '' COMMENT '创建者',
	Fcreate_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fcompany_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;