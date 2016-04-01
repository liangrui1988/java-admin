/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/4/1 11:06:09                            */
/*==============================================================*/


drop table if exists sys_department;

drop table if exists sys_menu;

drop table if exists sys_role;

drop table if exists sys_user;

/*==============================================================*/
/* Table: sys_department                                        */
/*==============================================================*/
create table sys_department
(
   id                   int not null,
   name                 varbinary(200) comment '名称',
   sort                 int comment '排序',
   parentId             int comment '父id',
   parentIds            varbinary(200) comment '所有父id',
   status               int comment '状态',
   remake               varchar(200) comment '备注',
   primary key (id)
);

alter table sys_department comment '部门';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   int not null,
   name                 varchar(200),
   types                varchar(200),
   sort                 int,
   parentId             int,
   parentIds            varchar(200),
   permission           varchar(200) comment '权限',
   status               int,
   primary key (id)
);

alter table sys_menu comment '菜单';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int not null,
   name                 varchar(200) comment '角色名称',
   menuIds              int comment '菜单id',
   remake               varchar(200) comment '备注',
   status               int comment '状态',
   primary key (id)
);

alter table sys_role comment '角色';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int not null,
   userName             varchar(20) comment '登陆名',
   password             varbinary(30) comment '密码',
   status               int comment '状态',
   createTime           timestamp comment '创建时间',
   updateTime           timestamp comment '修改时间',
   roleIds              int comment '角色id',
   primary key (id)
);

alter table sys_user comment '用户';

