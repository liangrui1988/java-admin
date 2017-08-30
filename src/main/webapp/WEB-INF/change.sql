/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/6/12 19:16:28                           */
/*==============================================================*/


drop index dep_name_unique on sys_department;

drop table if exists sys_department;

drop index dict_Index_type on sys_dict;

drop table if exists sys_dict;

drop index Index_create_user on sys_log;

drop index Index_create_time on sys_log;

drop table if exists sys_log;

drop index menu_Index_sort on sys_menu;

drop index menu_Index_create on sys_menu;

drop table if exists sys_menu;

drop index role_name_unique on sys_role;

drop table if exists sys_role;

drop table if exists sys_role_menu;

drop index user_name_unique on sys_user;

drop table if exists sys_user;

drop index userId on sys_user_department;

drop table if exists sys_user_department;

drop table if exists sys_user_role;

DROP TABLE IF EXISTS `sys_parameter`;

/*==============================================================*/
/* Table: sys_department                                        */
/*==============================================================*/
create table sys_department
(
   id                   int not null auto_increment,
   name                 varchar(20) comment '名称',
   sort                 int comment '排序',
   parent_id            int comment '父id',
   parent_ids           varchar(200) comment '所有父id',
   status               int default 0 comment '状态',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   create_by_id         int comment '创建者',
   update_by_id         int comment '更新者',
   remake               varchar(200) comment '备注',
   primary key (id)
);

alter table sys_department comment '部门';

/*==============================================================*/
/* Index: dep_name_unique                                       */
/*==============================================================*/
create unique index dep_name_unique on sys_department
(
   name
);

/*==============================================================*/
/* Table: sys_dict                                              */
/*==============================================================*/
create table sys_dict
(
   id                   int not null auto_increment,
   name                 varchar(100) comment '名称',
   value                varchar(100) comment '值',
   type                 varchar(100) comment '类型',
   sort                 int comment '排序',
   parent_id            int comment '父id',
   status               int default 0 comment '状态',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   create_by_id         int comment '创建者',
   update_by_id         int comment '更新者',
   remake               varchar(200) comment '备注',
   primary key (id)
);

alter table sys_dict comment '部门';

/*==============================================================*/
/* Index: dict_Index_type                                       */
/*==============================================================*/
create index dict_Index_type on sys_dict
(
   type
);

/*==============================================================*/
/* Table: sys_log                                               */
/*==============================================================*/
create table sys_log
(
   id                   int not null auto_increment,
   title                varchar(100) comment '菜单',
   type                 char(1) comment '类型',
   ip                   varchar(100) comment '操作ip',
   method               varchar(50) default '0' comment '请求方式',
   uri                  varchar(200) comment 'uri',
   parameters           varchar(200) comment '参数',
   agent                varchar(300) comment '代理',
   create_by_id         int comment '创建者',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   remake               varchar(300) comment '备注',
   end_time             datetime comment '结束时间',
   result               varchar(500),
   primary key (id)
);

alter table sys_log comment '系统日志';

/*==============================================================*/
/* Index: Index_create_time                                     */
/*==============================================================*/
create index Index_create_time on sys_log
(
   create_time
);

/*==============================================================*/
/* Index: Index_create_user                                     */
/*==============================================================*/
create index Index_create_user on sys_log
(
   create_by_id
);

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   varchar(200) not null,
   name                 varchar(200) comment '名称',
   types                varchar(200),
   sort_no              int,
   parent_id            varchar(200),
   parent_ids           varchar(200),
   permission           varchar(200) comment '权限',
   status               int default 0 comment '状态',
   icon                 varchar(200) comment '图标',
   href                 varchar(200) comment '链接',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   create_by_id         int comment '创建者',
   update_by_id         int comment '更新者',
   primary key (id)
);

alter table sys_menu comment '菜单';

/*==============================================================*/
/* Index: menu_Index_create                                     */
/*==============================================================*/
create index menu_Index_create on sys_menu
(
   create_time
);

/*==============================================================*/
/* Index: menu_Index_sort                                       */
/*==============================================================*/
create index menu_Index_sort on sys_menu
(
   sort_no
);


/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int not null auto_increment,
   name                 varchar(200) comment '角色名称',
   status               int default 0 comment '状态',
   types                varchar(100) comment '类型',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   create_by_id         int comment '创建者',
   update_by_id         int comment '更新者',
   group_id            int comment '角色组',
   remake               varchar(200) comment '备注',
   primary key (id)
);

alter table sys_role comment '角色';


/*==============================================================*/
/* Index: role_name_unique                                      */
/*==============================================================*/
create unique index role_name_unique on sys_role
(
   name
);

/*==============================================================*/
/* Table: sys_role_menu                                         */
/*==============================================================*/
create table sys_role_menu
(
   id                   int not null auto_increment,
   role_id              int,
   menu_id              varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int not null auto_increment,
   department_id        int comment '部门id',
   user_name            varchar(20) comment '登陆名',
   password             varchar(50) comment '密码',
   full_name            varchar(50) comment '姓名',
   type                 varchar(100) comment '类型',
   email                varchar(100) comment '邮箱',
   status               int default 0 comment '状态',
   create_by_id         int comment '创建者',
   update_by_id         int comment '更新者',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   remake               varchar(200) comment '备注',
   last_login_time      timestamp default CURRENT_TIMESTAMP comment '最后登陆时间',
   primary key (id)
);

alter table sys_user comment '用户';

/*==============================================================*/
/* Index: user_name_unique                                      */
/*==============================================================*/
create unique index user_name_unique on sys_user
(
   user_name
);

/*==============================================================*/
/* Table: sys_user_department                                   */
/*==============================================================*/
create table sys_user_department
(
   id                   int not null auto_increment,
   department_id        int,
   user_id              int,
   primary key (id)
);

/*==============================================================*/
/* Index: userId                                                */
/*==============================================================*/
create unique index userId on sys_user_department
(
   user_id
);

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   int not null auto_increment,
   user_id              int,
   role_id              int,
   primary key (id)
);


-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------

CREATE TABLE `sys_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyx` varchar(50) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by_id` int(11) DEFAULT NULL,
  `update_by_id` int(11) DEFAULT NULL,
  `remake` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- ----------------------------
-- Table structure for sys_role_group
-- ----------------------------
CREATE TABLE `sys_role_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '角色组名称',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `types` varchar(100) DEFAULT NULL COMMENT '类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) DEFAULT NULL COMMENT '更新者',
  `remake` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_unique` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色组';

