/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/4/5 15:01:50                            */
/*==============================================================*/


drop table if exists sys_department;

drop table if exists sys_menu;

drop table if exists sys_role;

drop table if exists sys_role_menu;

drop table if exists sys_user;

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
   primary key (id)
);

alter table sys_department comment '部门';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   int not null auto_increment,
   name                 varchar(200),
   types                varchar(200),
   sort                 int,
   parentId             int,
   parentIds            varchar(200),
   permission           varchar(200) comment '权限',
   status               int default 0,
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
   menuId               int,
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
   status               int default 0 comment '状态',
   createTime           timestamp default CURRENT_TIMESTAMP comment '创建时间',
   updateTime           timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
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
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   int not null auto_increment,
   userId               int,
   roleId               int,
   primary key (id)
);

