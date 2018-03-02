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


--角色主
INSERT INTO `gdata`.`sys_role_group` (`id`, `name`, `status`, `types`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('1', '系统角色组', '0', '1', '2017-08-29 11:52:29', '2017-08-29 11:53:22', NULL, NULL, '系统角色组');
INSERT INTO `gdata`.`sys_role_group` (`id`, `name`, `status`, `types`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('2', '游戏角色组', '0', '1', '2017-08-29 11:52:29', '2017-08-29 11:53:20', NULL, NULL, '游戏角色组');

--角色类型
INSERT INTO `gdata`.`sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('3', '系统管理员角色', '1', 'role_type', NULL, NULL, '0', '2016-06-14 23:47:07', '2017-10-09 10:14:45', '1', '2', '系统管理员');
INSERT INTO `gdata`.`sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('4', '普通角色', '2', 'role_type', NULL, NULL, '0', '2016-06-14 23:47:50', '2017-06-06 14:46:10', '1', '1', '普通角色');
INSERT INTO `gdata`.`sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('41', '战卢游戏', 'zl', 'role_type', NULL, NULL, '0', '2017-08-28 15:45:10', '2017-08-28 15:45:10', '3', NULL, '战卢游戏角色');
INSERT INTO `gdata`.`sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('42', '轩辕游戏角色', 'xy', 'role_type', NULL, NULL, '0', '2017-08-28 15:46:39', '2017-08-28 15:46:39', '3', NULL, '轩辕游戏角色');
INSERT INTO `gdata`.`sys_dict` (`id`, `name`, `value`, `type`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `create_by_id`, `update_by_id`, `remake`) VALUES ('43', '战卢游戏策划', 'zl_cehua', 'role_type', NULL, NULL, '0', '2017-09-19 17:08:36', '2017-09-19 17:08:36', '3', NULL, '战卢游戏策划');
