/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/5/20 17:27:35                           */
/*==============================================================*/


drop table if exists sys_department;

drop table if exists sys_dict;

drop table if exists sys_menu;

drop table if exists sys_role;

drop table if exists sys_role_menu;

drop table if exists sys_user;

drop index userId on sys_user_department;

drop table if exists sys_user_department;

drop table if exists sys_user_role;

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
   office_id            int comment '归属机构',
   remake               varchar(200) comment '备注',
   primary key (id)
);

alter table sys_role comment '角色';

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
   email                char(10) comment '邮箱',
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

