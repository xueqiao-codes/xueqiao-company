DROP TABLE IF EXISTS t_sled_product;
CREATE TABLE t_sled_product(
	Fsled_product_id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '内部唯一数字id',
	Fproduct_type SMALLINT NOT NULL DEFAULT 0 COMMENT '商品类型',
	Fproduct_name VARCHAR(64) NOT NULL DEFAULT "" COMMENT '商品名称',
	Fdescription VARCHAR(512) NOT NULL DEFAULT "" COMMENT '商品描述',
	Finventory_quantity INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '库存数量',
	Fcurrency_type SMALLINT NOT NULL DEFAULT 0 COMMENT '使用对应的货币类型',
	Fprice DOUBLE UNSIGNED NOT NULL DEFAULT 0.0 COMMENT '商品单价',
	Fimage_codes VARCHAR(128) NOT NULL DEFAULT "" COMMENT '图片地址代码',
	Fproduct_status SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品状态',
	Fextend_properties VARCHAR(1024) NOT NULL DEFAULT "" COMMENT '扩展信息',
	Fneed_operator SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否需要操作人员',
	Foperation SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品所需操作类型',
	Fxcoin_increment DOUBLE UNSIGNED NOT NULL DEFAULT 0.0  COMMENT '雪橇币增量',
	Ftime_increment INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '过期时间增量',
	Fpurpose SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品目的',
	Fpurpose_type SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '目标类型',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fsled_product_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb, AUTO_INCREMENT=1;


DROP TABLE IF EXISTS t_sled_order;
CREATE TABLE t_sled_order(
	Fsled_order_id INT UNSIGNED NOT NULL COMMENT '内部唯一数字id',
	Fcompany_id INT UNSIGNED NOT NULL COMMENT '公司id',
	Fcompany_group_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '公司业务组id',
	Fcompany_group_name VARCHAR(64) NOT NULL DEFAULT "" COMMENT '公司业务组名',
	Forder_source SMALLINT NOT NULL DEFAULT 0 COMMENT '订单来源',
	Fsled_product_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
	Fproduct_name VARCHAR(64) NOT NULL DEFAULT "" COMMENT '商品名称',
	Fproduct_description VARCHAR(512) NOT NULL DEFAULT "" COMMENT '商品描述',
	Fproduct_quantity INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品数量',
	Fcurrency_type SMALLINT NOT NULL DEFAULT 0 COMMENT '使用对应的货币类型',
	Fprice DOUBLE UNSIGNED NOT NULL DEFAULT 0.0 COMMENT '商品单价',
	Ftotal_amount DOUBLE UNSIGNED NOT NULL DEFAULT 0.0  COMMENT '商品总价',
	Fpay_type SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付类型',
	Fthird_party_order_No VARCHAR(512) NOT NULL DEFAULT "" COMMENT '第三方订单号',
	Forder_status SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单状态',
	Ftrade_info VARCHAR(1024) NOT NULL DEFAULT "" COMMENT '第三方回调的具体交易信息',
	FextraInfo VARCHAR(512) NOT NULL DEFAULT "" COMMENT '订单附属信息',
	Fneed_operator SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否需要操作人员',
	Foperator VARCHAR(64) NOT NULL DEFAULT "" COMMENT '操作人员名称',
	Foperation SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品所需操作类型',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fsled_order_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;

ALTER TABLE t_sled_order ADD(Fcompany_group_name VARCHAR(64) NOT NULL DEFAULT "" COMMENT '公司业务组名');
ALTER TABLE t_sled_order ADD(FextraInfo VARCHAR(512) NOT NULL DEFAULT "" COMMENT '订单附属信息');

	
DROP TABLE IF EXISTS t_order_flow;
CREATE TABLE t_order_flow(
	Forder_flow_id INT UNSIGNED NOT NULL COMMENT '内部唯一数字id',
	Fsled_order_id INT UNSIGNED NOT NULL COMMENT '订单id',
	Forder_status SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单状态',
	Fdescription VARCHAR(512) NOT NULL DEFAULT "" COMMENT '流程描述',
	Forder TEXT COMMENT '订单切面信息',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Forder_flow_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;

ALTER TABLE t_order_flow ADD(Forder TEXT COMMENT '订单切面信息');

DROP TABLE IF EXISTS t_company_balance;
CREATE TABLE t_company_balance(
	Fcompany_id INT UNSIGNED NOT NULL COMMENT '内部唯一数字id',
	Fxcoin_balance DOUBLE UNSIGNED NOT NULL DEFAULT 0.0  COMMENT '雪橇币余额',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fcompany_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;

###### t_group_spec 在做废计划中，将不再使用。因为payment和company等，都划分到company下，所以，用到groupspec的地方，都只用company中的tcompany_group_spec  #####
DROP TABLE IF EXISTS t_group_spec;
CREATE TABLE t_group_spec(
	Fcompany_id INT UNSIGNED NOT NULL COMMENT '内部唯一数字id',
	Fcompany_group_id INT UNSIGNED NOT NULL COMMENT '公司业务组id',
	Fexpired_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '过期时间点',
	Fproduct_type SMALLINT NOT NULL DEFAULT 0 COMMENT '服务配置类型',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fcompany_id,Fcompany_group_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;

DROP TABLE IF EXISTS t_purchase_history;
CREATE TABLE t_purchase_history(
	Fpurchase_history_id INT UNSIGNED NOT NULL COMMENT '内部唯一数字id',
	Fcompany_id INT UNSIGNED NOT NULL COMMENT '公司id',
	Fcompany_group_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '公司业务组id',
	Fsled_product_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
	Fproduct_name VARCHAR(64) NOT NULL DEFAULT "" COMMENT '商品名称',
	Fsled_order_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单id',
	Ftotal_amount DOUBLE UNSIGNED NOT NULL DEFAULT 0.0 COMMENT '总价',
	Fcurrency_type SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易货币类型',
	Fnew_xcoin_balance DOUBLE UNSIGNED NOT NULL DEFAULT 0.0 COMMENT '新雪橇币余额',
	Fnew_expired_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '新的过期时间点',
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间点',
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间点',
	PRIMARY KEY(Fpurchase_history_id)
	) CHARSET=utf8mb4, ENGINE=InnoDb;