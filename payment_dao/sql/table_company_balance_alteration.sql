DROP TABLE IF EXISTS tcompany_balance_alteration;
CREATE TABLE tcompany_balance_alteration(
	Falteration_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '变更ID',
	Fcompany_id INT UNSIGNED NOT NULL COMMENT '公司id',
	Foperation_type VARCHAR(32) NOT NULL DEFAULT "" COMMENT '变更类型',
	Fchannel VARCHAR(32) NOT NULL DEFAULT "" COMMENT '交易渠道',
	Fthirdparty_trade_no VARCHAR(128) NOT NULL DEFAULT "" COMMENT '第三方订单号',
	Fdescription VARCHAR(512) NOT NULL DEFAULT "" COMMENT '备注',
	Foperator VARCHAR(64) NOT NULL DEFAULT "" COMMENT '操作人',
	Faleration_xcoin DOUBLE UNSIGNED NOT NULL DEFAULT 0.0  COMMENT '变更雪橇币数量',
	Fxcoin_balance DOUBLE UNSIGNED NOT NULL DEFAULT 0.0  COMMENT '变更后雪橇币余额',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
	Flastmodify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间',
	PRIMARY KEY(Falteration_id),
	INDEX(Fcompany_id)
) CHARSET=utf8mb4, ENGINE=InnoDb;
