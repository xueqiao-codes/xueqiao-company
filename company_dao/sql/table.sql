DROP TABLE IF EXISTS tcompany_entry;
CREATE TABLE tcompany_entry(
	Fcompany_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fcompany_code VARCHAR(32) NOT NULL DEFAULT 0,
	Fcompany_name VARCHAR(64) NOT NULL DEFAULT "",
	Fchannel VARCHAR(32) NOT NULL DEFAULT "",
	Faddress VARCHAR(256) NOT NULL DEFAULT "",
	Ffunds VARCHAR(128) NOT NULL DEFAULT "",
	Femail VARCHAR(64) NOT NULL DEFAULT "",
	Ftelephone VARCHAR(32) NOT NULL DEFAULT "",
	Fpassword VARCHAR(256) NOT NULL DEFAULT "",
	Fcontact VARCHAR(64) NOT NULL DEFAULT "",
	Ftype SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Flastmodify_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fcompany_id),
	UNIQUE KEY(Fcompany_code),
	UNIQUE KEY(Femail),
	UNIQUE KEY(Ftelephone)
) CHARSET=utf8mb4, ENGINE=InnoDb;

ALTER TABLE tcompany_entry ADD(Fpassword VARCHAR(128) NOT NULL DEFAULT "");  #密码
ALTER TABLE tcompany_entry ADD(Fcontact VARCHAR(64) NOT NULL DEFAULT "");    #联系人
ALTER TABLE tcompany_entry MODIFY COLUMN Fpassword VARCHAR(256);   # 修改密码字段长度
ALTER TABLE tcompany_entry ADD(Ftype SMALLINT UNSIGNED NOT NULL DEFAULT 0);  #公司类型

CREATE TABLE tcompany_group(
	Fgroup_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fcompany_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fgroup_code VARCHAR(32) NOT NULL DEFAULT "",
	Fgroup_name VARCHAR(64) NOT NULL DEFAULT "",
	Faes_key VARCHAR(256) NOT NULL DEFAULT "",
	
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Flastmodify_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fcompany_id, Fgroup_id),
	UNIQUE KEY(Fgroup_code)
) CHARSET=utf8mb4, ENGINE=InnoDb;


ALTER TABLE tcompany_group ADD(Faes_key VARCHAR(256) NOT NULL DEFAULT "");  #AES16KEY


DROP TABLE IF EXISTS tcompany_spec;
CREATE TABLE tcompany_spec(
	Fcompany_id INT UNSIGNED NOT NULL,
	Fsubscribe_commodity_ids VARCHAR(1024) NOT NULL DEFAULT "",

	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fcompany_id)
) CHARSET=utf8mb4, ENGINE=InnoDb;

DROP TABLE IF EXISTS tcompany_group_spec;
CREATE TABLE tcompany_group_spec(
	Fgroup_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fcompany_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fspec_name VARCHAR(32) NOT NULL DEFAULT "",
	Fexpired_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Fhosting_service_status SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	Fservice_type SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	Foperate_description VARCHAR(512) NOT NULL DEFAULT "",
	Frelease_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fcompany_id, Fgroup_id)
) CHARSET=utf8mb4, ENGINE=InnoDb;

ALTER TABLE tcompany_group_spec ADD(Foperate_description VARCHAR(512) NOT NULL DEFAULT "");  #托管服务操作描述
ALTER TABLE tcompany_group_spec ADD(Frelease_timestamp INT UNSIGNED NOT NULL DEFAULT 0);  #托管服务释放时间


DROP TABLE IF EXISTS tcompany_user;
CREATE TABLE tcompany_user(
	Fuser_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fcompany_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fuser_name VARCHAR(64) NOT NULL DEFAULT "",
	Fpassword VARCHAR(256) NOT NULL DEFAULT "",
	Ftelephone VARCHAR(64) NOT NULL DEFAULT "",
	Fcn_name VARCHAR(64) NOT NULL DEFAULT "",
    Femail VARCHAR(64) NOT NULL DEFAULT "",
	Fstatus SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	Ftype SMALLINT UNSIGNED NOT NULL DEFAULT 0,
    Fexpired_time_millis BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fuser_id),
	UNIQUE KEY(Fcompany_id,Fuser_name)
) CHARSET=utf8mb4, ENGINE=InnoDb;
ALTER TABLE tcompany_user ADD(Fstatus SMALLINT UNSIGNED NOT NULL DEFAULT 0);    #状态
ALTER TABLE tcompany_user ADD(Femail VARCHAR(64) NOT NULL DEFAULT "");          #邮箱(因为业务不定，为了灵活性，不做唯一限定，若需要，在代码层面限定)
ALTER TABLE tcompany_user MODIFY COLUMN Fpassword VARCHAR(256);   # 修改密码字段长度
ALTER TABLE tcompany_user ADD(Ftype SMALLINT UNSIGNED NOT NULL DEFAULT 0);    #公司用户类型
ALTER TABLE tcompany_user ADD(Fexpired_time_millis BIGINT UNSIGNED NOT NULL DEFAULT 0);    #公司用户过期时间


DROP TABLE IF EXISTS tgroup_user;
CREATE TABLE tgroup_user(
	Fuser_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fcompany_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fgroup_id INT UNSIGNED NOT NULL DEFAULT 0,
	Foperator_role SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Flast_modify_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fuser_id,Fcompany_id,Fgroup_id)
) CHARSET=utf8mb4, ENGINE=InnoDb;

CREATE TABLE tdeleted_group(
	Fgroup_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fcompany_id INT UNSIGNED NOT NULL DEFAULT 0,
	Fgroup_code VARCHAR(32) NOT NULL DEFAULT "",
	Fgroup_name VARCHAR(64) NOT NULL DEFAULT "",
	Faes_key VARCHAR(256) NOT NULL DEFAULT "",
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Flastmodify_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fcompany_id, Fgroup_id)
) CHARSET=utf8mb4, ENGINE=InnoDb;
