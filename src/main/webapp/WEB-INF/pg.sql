
-- 序列
create sequence seq_zl_log_info_main_id increment by 1 minvalue 1 no maxvalue start with 1;
select nextval('seq_zl_log_info_main_id');

-- 战斗日志表
CREATE TABLE "zl_log_info_main" (
"id" int8 DEFAULT nextval('seq_zl_log_info_main_id'::regclass) NOT NULL,
"server_id" int4,
"cont" json,
"time" timestamp(6),
"file" varchar(150) COLLATE "default",
"time_log" int8 DEFAULT 0 NOT NULL,
"uuid" int8 DEFAULT 0 NOT NULL,
PRIMARY KEY ("id")
)
WITH (OIDS=FALSE)
;
ALTER TABLE "zl_log_info_main" OWNER TO "game";
CREATE INDEX "zl_log_info_main_file_index" ON "zl_log_info_main" USING btree ("file" "pg_catalog"."text_ops");
CREATE INDEX "zl_log_info_main_server_index" ON "zl_log_info_main" USING btree ("server_id" "pg_catalog"."int4_ops");
CREATE INDEX "zl_log_info_main_time_index" ON "zl_log_info_main" USING btree ("time" "pg_catalog"."timestamp_ops");
CREATE INDEX "zl_log_info_main_time_log_index" ON "zl_log_info_main" USING btree ("time_log" "pg_catalog"."int8_ops");
CREATE INDEX "zl_log_info_main_uuid_index" ON "zl_log_info_main" USING btree ("uuid" "pg_catalog"."int8_ops");

-- 分表
create table zl_log_info_defualt() INHERITS (zl_log_info_main);

create table zl_log_info2017_07
(CHECK (time >= DATE '2017-07-01' AND time< DATE '2017-08-01'))
INHERITS (zl_log_info_main);

create table zl_log_info2017_08
(CHECK (time >= DATE '2017-08-01' AND time< DATE '2017-09-01'))
INHERITS (zl_log_info_main);

create table zl_log_info2017_09
(CHECK (time >= DATE '2017-09-01' AND time< DATE '2017-10-01'))
INHERITS (zl_log_info_main);

create table zl_log_info2017_10
(CHECK (time >= DATE '2017-10-01' AND time< DATE '2017-11-01'))
INHERITS (zl_log_info_main);

create table zl_log_info2017_11
(CHECK (time >= DATE '2017-11-01' AND time< DATE '2017-12-01'))
INHERITS (zl_log_info_main);

create table zl_log_info2017_12
(CHECK (time >= DATE '2017-12-01' AND time< DATE '2018-01-01'))
INHERITS (zl_log_info_main);

-- 主健
alter table zl_log_info2017_07 add primary key (id);
alter table zl_log_info2017_08 add primary key (id);
alter table zl_log_info2017_09 add primary key (id);
alter table zl_log_info2017_10 add primary key (id);
alter table zl_log_info2017_11 add primary key (id);
alter table zl_log_info2017_12 add primary key (id);


-- 分区键上建索引
CREATE INDEX "zl_log_info2017_07_file_index" ON zl_log_info2017_07 USING btree ("file" "pg_catalog"."text_ops");
CREATE INDEX "zl_log_info2017_07_server_index" ON zl_log_info2017_07 USING btree ("server_id" "pg_catalog"."int4_ops");
CREATE INDEX "zl_log_info2017_07_time_log_index" ON zl_log_info2017_07 USING btree ("time_log" "pg_catalog"."int8_ops");
CREATE INDEX "zl_log_info2017_07_uuid_index" ON zl_log_info2017_07 USING btree ("uuid" "pg_catalog"."int8_ops");
-- CREATE INDEX "zl_log_info2017_07_index_time" ON zl_log_info2017_07 USING btree ("time" "pg_catalog"."timestamp_ops");

CREATE INDEX "zl_log_info2017_08_file_index" ON zl_log_info2017_08 USING btree ("file" "pg_catalog"."text_ops");
CREATE INDEX "zl_log_info2017_08_server_index" ON zl_log_info2017_08 USING btree ("server_id" "pg_catalog"."int4_ops");
CREATE INDEX "zl_log_info2017_08_time_log_index" ON zl_log_info2017_08 USING btree ("time_log" "pg_catalog"."int8_ops");
CREATE INDEX "zl_log_info2017_08_uuid_index" ON zl_log_info2017_08 USING btree ("uuid" "pg_catalog"."int8_ops");

CREATE INDEX "zl_log_info2017_09_file_index" ON zl_log_info2017_09 USING btree ("file" "pg_catalog"."text_ops");
CREATE INDEX "zl_log_info2017_09_server_index" ON zl_log_info2017_09 USING btree ("server_id" "pg_catalog"."int4_ops");
CREATE INDEX "zl_log_info2017_09_time_log_index" ON zl_log_info2017_09 USING btree ("time_log" "pg_catalog"."int8_ops");
CREATE INDEX "zl_log_info2017_09_uuid_index" ON zl_log_info2017_09 USING btree ("uuid" "pg_catalog"."int8_ops");

CREATE INDEX "zl_log_info2017_10_file_index" ON zl_log_info2017_10 USING btree ("file" "pg_catalog"."text_ops");
CREATE INDEX "zl_log_info2017_10_server_index" ON zl_log_info2017_10 USING btree ("server_id" "pg_catalog"."int4_ops");
CREATE INDEX "zl_log_info2017_10_time_log_index" ON zl_log_info2017_10 USING btree ("time_log" "pg_catalog"."int8_ops");
CREATE INDEX "zl_log_info2017_10_uuid_index" ON zl_log_info2017_10 USING btree ("uuid" "pg_catalog"."int8_ops");

CREATE INDEX "zl_log_info2017_11_file_index" ON zl_log_info2017_11 USING btree ("file" "pg_catalog"."text_ops");
CREATE INDEX "zl_log_info2017_11_server_index" ON zl_log_info2017_11 USING btree ("server_id" "pg_catalog"."int4_ops");
CREATE INDEX "zl_log_info2017_11_time_log_index" ON zl_log_info2017_11 USING btree ("time_log" "pg_catalog"."int8_ops");
CREATE INDEX "zl_log_info2017_11_uuid_index" ON zl_log_info2017_11 USING btree ("uuid" "pg_catalog"."int8_ops");

CREATE INDEX "zl_log_info2017_12_file_index" ON zl_log_info2017_12 USING btree ("file" "pg_catalog"."text_ops");
CREATE INDEX "zl_log_info2017_12_server_index" ON zl_log_info2017_12 USING btree ("server_id" "pg_catalog"."int4_ops");
CREATE INDEX "zl_log_info2017_12_time_log_index" ON zl_log_info2017_12 USING btree ("time_log" "pg_catalog"."int8_ops");
CREATE INDEX "zl_log_info2017_12_uuid_index" ON zl_log_info2017_12 USING btree ("uuid" "pg_catalog"."int8_ops");


-- 触发器函数
CREATE
OR REPLACE FUNCTION fn_zl_log_info_main_insert_trigger () RETURNS TRIGGER AS $$
BEGIN
IF (
  NEW .time >= DATE '2017-07-01'  AND NEW .time < DATE '2017-08-01'
) THEN
  INSERT INTO zl_log_info2017_07 VALUES  (NEW .*) ;
ELSEIF (
  NEW .time >= DATE '2017-08-01'  AND NEW .time < DATE '2017-09-01'
) THEN
  INSERT INTO zl_log_info2017_08 VALUES (NEW .*) ;
  ELSEIF (
  NEW .time >= DATE '2017-09-01'  AND NEW .time < DATE '2017-10-01'
) THEN
  INSERT INTO zl_log_info2017_09 VALUES (NEW .*) ;
  ELSEIF (
  NEW .time >= DATE '2017-10-01'  AND NEW .time < DATE '2017-11-01'
) THEN
  INSERT INTO zl_log_info2017_10 VALUES (NEW .*) ;
  ELSEIF (
  NEW .time >= DATE '2017-11-01'  AND NEW .time < DATE '2017-12-01'
) THEN
  INSERT INTO zl_log_info2017_11 VALUES (NEW .*) ;
  ELSEIF (
  NEW .time >= DATE '2017-12-01'  AND NEW .time < DATE '2018-01-01'
) THEN
  INSERT INTO zl_log_info2017_12 VALUES (NEW .*) ;
ELSE
   INSERT INTO zl_log_info_defualt VALUES(NEW .*) ;
END
IF ; RETURN NULL ;
END ; $$ LANGUAGE plpgsql;

-- 创建触发器
CREATE TRIGGER trigger_insert_zl_log_info_main BEFORE INSERT ON zl_log_info_main
FOR EACH ROW
EXECUTE PROCEDURE fn_zl_log_info_main_insert_trigger();


-- 
drop TRIGGER trigger_insert_zl_log_info_main on zl_log_info_main
DROP FUNCTION fn_zl_log_info_main_insert_trigger();

-- 确保postgresql.conf 里的配置参数constraint_exclusion 是打开的。没有这个参数，查询不会按照需要进行优化。
-- 这里我们需要做的是确保该选项在配置文件中没有被注释掉。
--如果没有约束排除，查询会扫描tbl_partition 表中的每一个分区
constraint_exclusion = on;       

-- 测试效果 

EXPLAIN
SELECT * FROM "public"."zl_log_info_main" where time>='2017-09-10' and   time<='2017-11-10'

EXPLAIN
SELECT * FROM "public"."zl_log_info_main" where time_log>=1505001600
