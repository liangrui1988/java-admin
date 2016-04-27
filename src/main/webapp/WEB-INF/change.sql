/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/4/27 17:17:39                           */
/*==============================================================*/


drop table if exists sys_department;

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
   parentId             int comment '父id',
   parentIds            varchar(200) comment '所有父id',
   status               int default 0 comment '状态',
   remake               varchar(200) comment '备注',
   createTime           timestamp default CURRENT_TIMESTAMP,
   updateTime           timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   primary key (id)
);

alter table sys_department comment '部门';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   varchar(200) not null,
   name                 varchar(200) comment '名称',
   types                varchar(200),
   sortNo               int,
   parentId             varchar(200),
   parentIds            varchar(200),
   permission           varchar(200) comment '权限',
   status               int default 0 comment '状态',
   icon                 varchar(200) comment '图标',
   href                 varchar(200) comment '链接',
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
   remake               varchar(200) comment '备注',
   status               int default 0 comment '状态',
   types                varchar(100) comment '类型',
   createTime           timestamp default CURRENT_TIMESTAMP comment '创建时间',
   updateTime           timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   primary key (id)
);

alter table sys_role comment '角色';

/*==============================================================*/
/* Table: sys_role_menu                                         */
/*==============================================================*/
create table sys_role_menu
(
   id                   int not null auto_increment,
   roleId               int,
   menuId               varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int not null auto_increment,
   departmentId         int comment '部门id',
   userName             varchar(20) comment '登陆名',
   password             varchar(50) comment '密码',
   fullName             varchar(50) comment '姓名',
   type                 varchar(100) comment '类型',
   status               int default 0 comment '状态',
   createTime           timestamp default CURRENT_TIMESTAMP comment '创建时间',
   updateTime           timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   remake               varchar(200) comment '备注',
   lastLoginTime        timestamp comment '最后登陆时间',
   primary key (id)
);

alter table sys_user comment '用户';

/*==============================================================*/
/* Table: sys_user_department                                   */
/*==============================================================*/
create table sys_user_department
(
   id                   int not null auto_increment,
   departmentId         int,
   userId               int,
   primary key (id)
);

/*==============================================================*/
/* Index: userId                                                */
/*==============================================================*/
create unique index userId on sys_user_department
(
   userId
);

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   int not null auto_increment,
   userId               int,
   roleId               int,
   primary key (id)
);

