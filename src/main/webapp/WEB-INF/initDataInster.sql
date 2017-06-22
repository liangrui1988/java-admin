-- 系统初始化时 需要的数据


/*==============================================================*/
/* Table: sys_dict      数据字典                                       */
/*==============================================================*/
INSERT INTO `sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('3', '系统管理员', '1', 'role_type', NULL, NULL, '0', '2016-06-14 23:47:07', '2016-06-14 23:57:50', '1', '1', '系统管理员');
INSERT INTO `sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('4', '商家管理', '2', 'role_type', NULL, NULL, '0', '2016-06-14 23:47:50', '2016-06-15 00:03:16', '1', '1', '商家管理');
INSERT INTO `sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('5', '政府管理', '3', 'role_type', NULL, NULL, '0', '2016-06-14 23:47:59', '2016-06-14 23:47:59', '1', NULL, '系统管理员');
INSERT INTO `sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('1', '系统管理员', '1', 'user_type', NULL, NULL, '0', '2016-06-14 23:31:30', '2016-06-14 23:31:30', '1', NULL, '系统管理员');
INSERT INTO `sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('6', '商家管理', '2', 'user_type', NULL, NULL, '0', '2016-06-15 23:09:33', '2016-06-15 23:36:31', '1', '1', '商家管理');
INSERT INTO `sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('7', '政府管理', '3', 'user_type', NULL, NULL, '0', '2016-06-15 23:10:19', '2016-06-15 23:10:19', '1', NULL, '商家管理');
