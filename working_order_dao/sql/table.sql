DROP TABLE IF EXISTS tworking_order;
CREATE TABLE tworking_order(
	Fworking_order_id BIGINT UNSIGNED NOT NULL,
	Fcompany_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Fcompany_user_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
	Forder_class_type VARCHAR(256) NOT NULL DEFAULT "",
	Fcontent TEXT NOT NULL,
	Foperator VARCHAR(64) NOT NULL DEFAULT "",
	Ftype SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	Fstate SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	Fcreate_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	Flastmodify_timestamp INT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(Fworking_order_id)
) CHARSET=utf8mb4, ENGINE=InnoDb;