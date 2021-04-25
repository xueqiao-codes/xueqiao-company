DROP TABLE IF EXISTS tsync_task_queue;
CREATE TABLE IF NOT EXISTS tsync_task_queue (
Ftask_id int(11) unsigned NOT NULL AUTO_INCREMENT,
Ftask_type TINYINT unsigned NOT NULL DEFAULT 0,
Fqueue_message text NOT NULL DEFAULT "",
Fretry_times TINYINT unsigned NOT NULL DEFAULT 0,
Fstatus TINYINT unsigned NOT NULL DEFAULT 0,
Fcreate_timestamp int(11) unsigned NOT NULL DEFAULT 0,
Flast_modify_timestamp int(11) unsigned NOT NULL DEFAULT 0,
PRIMARY KEY (Ftask_id)
) DEFAULT CHARSET=utf8;