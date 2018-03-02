/*
Navicat MySQL Data Transfer

Source Server         : 111.231.84.136web_admin
Source Server Version : 50638
Source Host           : 111.231.84.136:3306
Source Database       : gdata

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-03-02 10:15:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for game_dict
-- ----------------------------
DROP TABLE IF EXISTS `game_dict`;
CREATE TABLE `game_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `value` varchar(100) DEFAULT NULL COMMENT '健=key',
  `name` varchar(100) DEFAULT NULL COMMENT '名称=value',
  `arg1` varchar(255) DEFAULT NULL,
  `arg2` varchar(255) DEFAULT NULL,
  `arg3` varchar(255) DEFAULT NULL,
  `arg4` varchar(255) DEFAULT NULL,
  `arg5` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id',
  `status` int(11) unsigned zerofill NOT NULL DEFAULT '00000000000' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) DEFAULT NULL COMMENT '更新者',
  `remake` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `dict_Index_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1185 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of game_dict
-- ----------------------------
INSERT INTO `game_dict` VALUES ('1000', 'zl_money_end_type', 'dungeon_end', '完成副本结算', 'reward', '', '', '', '', null, null, '00000000000', '2017-09-25 21:29:45', '2017-09-25 21:30:38', null, null, '');
INSERT INTO `game_dict` VALUES ('1001', 'zl_money_end_type', 'dungeon_reward', '副本奖励和id', ' dungeon_id ', '', '', '', '', null, null, '00000000000', '2017-09-25 21:29:45', '2017-09-25 21:31:34', null, null, '');
INSERT INTO `game_dict` VALUES ('1002', 'zl_money_end_type', 'quest', '主线任务', ' main', '', '', '', '', null, null, '00000000000', '2017-09-25 21:29:45', '2017-09-25 21:32:49', null, null, '');
INSERT INTO `game_dict` VALUES ('1003', 'zl_money_end_type', 'goal', ' 杀怪收集', 'kill_collect', '', '', '', '', null, null, '00000000000', '2017-09-25 21:29:45', '2017-09-27 15:12:40', null, null, '');
INSERT INTO `game_dict` VALUES ('1004', 'zl_money_end_type', 'equip', '装备', '', '', '', '', '', null, null, '00000000000', '2017-09-25 21:29:45', '2017-09-26 11:23:44', null, null, '');
INSERT INTO `game_dict` VALUES ('1005', 'zl_money_end_type', 'trigram_rune', '符文', '', '', '', '', '', null, null, '00000000000', '2017-09-25 21:29:45', '2017-09-25 21:33:07', null, null, '');
INSERT INTO `game_dict` VALUES ('1006', 'zl_money_end_type', 'revive', '复活', '', '', '', '', '', null, null, '00000000000', '2017-09-25 21:29:45', '2017-09-26 11:22:05', null, null, '');
INSERT INTO `game_dict` VALUES ('1007', 'zl_money_end_type', 'soul', '武魂', '', '', '', '', '', null, null, '00000000000', '2017-09-25 21:29:45', '2017-09-26 11:22:12', null, null, '');
INSERT INTO `game_dict` VALUES ('1008', 'zl_money_end_type', 'goods', '物品', '', '', '', '', '', null, null, '00000000000', '2017-09-25 21:29:45', '2017-09-26 11:24:31', null, null, '');
INSERT INTO `game_dict` VALUES ('1099', 'zl_money_type', '-1', '全部', 'MONEY_x', '', '', '', '', '1', null, '00000000000', '2017-09-25 21:29:45', '2017-09-26 11:21:58', null, null, '');
INSERT INTO `game_dict` VALUES ('1100', 'zl_money_type', '11', '金币', 'MONEY_COIN', '', '', '', '', '2', null, '00000000000', '2017-09-25 21:29:45', '2017-09-26 10:49:01', null, null, '');
INSERT INTO `game_dict` VALUES ('1101', 'zl_money_type', '12', '绑定金币', 'MONEY_COIN_BIND', '', '', '', '', '3', null, '00000000000', '2017-09-26 09:52:49', '2017-09-26 10:49:02', null, null, '');
INSERT INTO `game_dict` VALUES ('1102', 'zl_money_type', '13', '金币或绑金', 'MONEY_COIN_ANY', '', '', '', '', '4', null, '00000000000', '2017-09-26 09:52:49', '2017-09-26 10:49:04', null, null, '');
INSERT INTO `game_dict` VALUES ('1103', 'zl_money_type', '14', '钻石', 'MONEY_DIAMOND', '', '', '', '', '5', null, '00000000000', '2017-09-26 09:52:49', '2017-09-26 10:49:06', null, null, '');
INSERT INTO `game_dict` VALUES ('1104', 'zl_money_type', '15', '绑定钻石', 'MONEY_DIAMOND_BIND', '', '', '', '', '6', null, '00000000000', '2017-09-26 09:52:49', '2017-09-26 10:49:07', null, null, '');
INSERT INTO `game_dict` VALUES ('1105', 'zl_money_type', '16', '钻石或绑钻', 'MONEY_DIAMOND_ANY', '', '', '', '', '7', null, '00000000000', '2017-09-26 09:52:49', '2017-09-26 10:49:08', null, null, '');
INSERT INTO `game_dict` VALUES ('1106', 'zl_money_type', '17', '八卦牌货币', 'MONEY_TRIGRAM_SP', '', '', '', '', '8', null, '00000000000', '2017-09-26 09:52:50', '2017-09-26 10:49:09', null, null, '');
INSERT INTO `game_dict` VALUES ('1107', 'zl_money_type', '18', '公会贡献', 'MONEY_GUILD_CTB', '', '', '', '', '9', null, '00000000000', '2017-09-26 09:52:50', '2017-09-26 10:49:11', null, null, '');
INSERT INTO `game_dict` VALUES ('1108', 'zl_money_type', '19', '武魂精髓', 'MONEY_SOUL_CREAM', '', '', '', '', '10', null, '00000000000', '2017-09-26 09:52:50', '2017-09-26 10:49:12', null, null, '');
INSERT INTO `game_dict` VALUES ('1109', 'zl_money_type', '20', '武魂碎片', 'MONEY_SOUL_CHIP', '', '', '', '', '11', null, '00000000000', '2017-09-26 09:52:50', '2017-09-26 10:49:13', null, null, '');
INSERT INTO `game_dict` VALUES ('1110', 'zl_money_type', '101', '公会资金', 'MONEY_GUILD_FUND', '', '', '', '', '12', null, '00000000000', '2017-09-26 09:53:00', '2017-09-26 10:49:14', null, null, '');
INSERT INTO `game_dict` VALUES ('1111', 'zl_money_type', '102', '公会经验', 'MONEY_GUILD_EXP', '', '', '', '', '13', null, '00000000000', '2017-09-26 09:53:05', '2017-09-26 10:49:18', null, null, '');
INSERT INTO `game_dict` VALUES ('1116', 'zl_money_end_action', 'main', '主线任务', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1117', 'zl_money_end_action', 'branch', '支线任务', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1118', 'zl_money_end_action', 'sg', '赏金任务', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1119', 'zl_money_end_action', 'guild', '帮派任务', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1120', 'zl_money_end_action', 'guild_quest ', '帮派任务额外', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1121', 'zl_money_end_action', 'sg_quest', '赏金任务额外', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1122', 'zl_money_end_action', 'kill_collect ', '目标收集', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1123', 'zl_money_end_action', 'retrieve', '资源找回', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1124', 'zl_money_end_action', 'box_reward ', '签到额外', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1125', 'zl_money_end_action', 'attend ', '每日签到', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1126', 'zl_money_end_action', 'patch_reward', '分包奖励', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1127', 'zl_money_end_action', 'day_reward  ', '七日日奖励', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1128', 'zl_money_end_action', 'index_reward ', '七日单项', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1129', 'zl_money_end_action', 'use', '使用物品', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1130', 'zl_money_end_action', 'compose ', '物品合成', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1131', 'zl_money_end_action', 'worship ', '盛会膜拜', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1132', 'zl_money_end_action', 'extra_reward ', '盛会膜拜额外', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1133', 'zl_money_end_action', 'answer', '盛会答题', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1134', 'zl_money_end_action', 'meeting_exp', '盛会挂机', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1135', 'zl_money_end_action', 'sweep    ', '副本扫荡', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1136', 'zl_money_end_action', 'reward ', '副本完成', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1137', 'zl_money_end_action', 'ready_exp ', '帮派boss准备', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1138', 'zl_money_end_action', 'submit_fund ', '帮派提交资金', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1139', 'zl_money_end_action', 'reward_perday', '帮派每日奖励', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1140', 'zl_money_end_action', 'lv_up ', '破碎级别升级', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1141', 'zl_money_end_action', 'pick', '自动拾取', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1142', 'zl_money_end_action', 'recycle', '装备回收', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1143', 'zl_money_end_action', 'soul_resolve ', '武魂分解', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1144', 'zl_money_end_action', 'liveness_reward ', '活动活跃度', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1145', 'zl_money_end_action', 'buy', '商城购买', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1146', 'zl_money_end_action', 'extra_reward', '离线挂机额外', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1147', 'zl_money_end_action', 'guild_quest ', '帮派任务立即完成', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1148', 'zl_money_end_action', 'sg_quest', '赏金任务立即完成', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1149', 'zl_money_end_action', 'extar', '签到补签', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1150', 'zl_money_end_action', 'retrieve', '资源找回', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1151', 'zl_money_end_action', 'horn_chat', '喇叭', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1152', 'zl_money_end_action', 'cost', '商店购买', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1153', 'zl_money_end_action', 'change_name', '角色改名', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1154', 'zl_money_end_action', 'add_size', '开格子', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1155', 'zl_money_end_action', 'sell', '出售', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1156', 'zl_money_end_action', 'use', '使用', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1157', 'zl_money_end_action', 'compose ', '合成', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1158', 'zl_money_end_action', 'do_baptize', '装备洗练', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1159', 'zl_money_end_action', 'strength', '装备强化', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1160', 'zl_money_end_action', 'compose_inlay', '合成镶嵌', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1161', 'zl_money_end_action', 'recycle', '装备回收', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1162', 'zl_money_end_action', 'submit', '装备上交', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1163', 'zl_money_end_action', 'do_forge', '套装锻造', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1164', 'zl_money_end_action', 'soul_lv_up', '升级武魂', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1165', 'zl_money_end_action', 'soul_resolve', '武魂分解', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1166', 'zl_money_end_action', 'add_exp', '坐骑经验', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1167', 'zl_money_end_action', 'unlock_skin', '坐骑解锁', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1168', 'zl_money_end_action', 'add_exp', '魔仆解锁', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1169', 'zl_money_end_action', 'step_exp', '魔仆进阶', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1170', 'zl_money_end_action', 'unlock_skin', '魔仆解锁', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1171', 'zl_money_end_action', 'add_exp', '圣痕经验', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1172', 'zl_money_end_action', 'jump', '跳场景', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1173', 'zl_money_end_action', 'boss_home', '进入boss之家', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1174', 'zl_money_end_action', 'ticket', '副本门票', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1175', 'zl_money_end_action', 'corsair', '海盗船扫荡', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1176', 'zl_money_end_action', 'inspire', '仙灵山谷鼓舞', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1177', 'zl_money_end_action', 'inspire', '试练boss鼓舞', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1178', 'zl_money_end_action', 'hang', '离线挂机', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1179', 'zl_money_end_action', 'in_place', '原地复活', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1180', 'zl_money_end_action', 'build', '创建帮派', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1181', 'zl_money_end_action', 'alter_word', '帮派公告', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1182', 'zl_money_end_action', 'submit_fund', '提交帮派资金', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1183', 'zl_money_end_action', 'open_skill', '帮派技能', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');
INSERT INTO `game_dict` VALUES ('1184', 'zl_money_end_action', 'skill_lv_up', '帮派技能升级', '', '', '', '', '', null, null, '00000000000', '2018-01-22 09:53:05', '2018-01-22 09:53:05', '3', null, '1');

-- ----------------------------
-- Table structure for game_dict2
-- ----------------------------
DROP TABLE IF EXISTS `game_dict2`;
CREATE TABLE `game_dict2` (
  `id` int(11) NOT NULL,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_dict2
-- ----------------------------

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id',
  `parent_ids` varchar(200) DEFAULT NULL COMMENT '所有父id',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) DEFAULT NULL COMMENT '更新者',
  `remake` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dep_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '技术部', '1', '0', null, '0', '2018-01-08 15:39:19', '2018-01-08 15:39:19', null, null, 'remake');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(100) DEFAULT NULL COMMENT '值',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) DEFAULT NULL COMMENT '更新者',
  `remake` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `dict_Index_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '系统管理员用户', '1', 'user_type', null, null, '0', '2016-06-14 23:31:30', '2017-06-12 09:45:24', '1', '1', '系统管理员');
INSERT INTO `sys_dict` VALUES ('3', '系统管理员角色', '1', 'role_type', null, null, '0', '2016-06-14 23:47:07', '2017-10-09 10:14:45', '1', '2', '系统管理员');
INSERT INTO `sys_dict` VALUES ('4', '普通角色', '2', 'role_type', null, null, '0', '2016-06-14 23:47:50', '2017-06-06 14:46:10', '1', '1', '普通角色');
INSERT INTO `sys_dict` VALUES ('7', '普通用户', '3', 'user_type', null, null, '0', '2016-06-15 23:10:19', '2017-06-12 09:42:42', '1', '1', '普通用户');
INSERT INTO `sys_dict` VALUES ('8', '体验服', '61', 'servers_type', '1', null, '0', '2017-06-20 15:07:01', '2018-01-08 16:00:09', '3', '2', '服务器');
INSERT INTO `sys_dict` VALUES ('41', '战卢游戏', 'zl', 'role_type', null, null, '0', '2017-08-28 15:45:10', '2017-08-28 15:45:10', '3', null, '战卢游戏角色');
INSERT INTO `sys_dict` VALUES ('42', '轩辕游戏角色', 'xy', 'role_type', null, null, '0', '2017-08-28 15:46:39', '2017-08-28 15:46:39', '3', null, '轩辕游戏角色');
INSERT INTO `sys_dict` VALUES ('43', '战卢游戏策划', 'zl_cehua', 'role_type', null, null, '0', '2017-09-19 17:08:36', '2017-09-19 17:08:36', '3', null, '战卢游戏策划');
INSERT INTO `sys_dict` VALUES ('44', '全部', 'all', 'game_os', null, null, '0', '2017-09-25 14:40:33', '2017-09-25 17:42:26', '3', null, '游戏平台');
INSERT INTO `sys_dict` VALUES ('45', '安卓', 'android', 'game_os', null, null, '0', '2017-09-25 14:41:32', '2017-09-25 14:41:32', '3', null, '游戏平台');
INSERT INTO `sys_dict` VALUES ('46', '苹果', 'ios', 'game_os', null, null, '0', '2017-09-25 14:41:58', '2017-09-25 14:41:58', '3', null, '游戏平台');
INSERT INTO `sys_dict` VALUES ('47', '使用', 'use', 'zl_money_end_action', null, null, '0', '2018-01-22 14:16:16', '2018-01-22 14:16:16', '1', null, '物品产出来源，acion');
INSERT INTO `sys_dict` VALUES ('48', '测试', '1017', 'zl_money_end_action', null, null, '0', '2018-01-22 14:37:52', '2018-01-22 14:37:52', '1', null, '物品产出来源，acion');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '菜单',
  `type` char(1) DEFAULT NULL COMMENT '类型',
  `ip` varchar(100) DEFAULT NULL COMMENT '操作ip',
  `method` varchar(50) DEFAULT '0' COMMENT '请求方式',
  `uri` varchar(200) DEFAULT NULL COMMENT 'uri',
  `parameters` varchar(2000) DEFAULT NULL COMMENT '参数',
  `agent` varchar(300) DEFAULT NULL COMMENT '代理',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `remake` varchar(300) DEFAULT NULL COMMENT '备注',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `result` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user` (`create_by_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(200) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `types` varchar(200) DEFAULT NULL,
  `sort_no` int(11) DEFAULT NULL,
  `parent_id` varchar(200) DEFAULT NULL,
  `parent_ids` varchar(200) DEFAULT NULL,
  `permission` varchar(200) DEFAULT NULL COMMENT '权限',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `icon` varchar(200) DEFAULT NULL COMMENT '图标',
  `href` varchar(200) DEFAULT NULL COMMENT '链接',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  KEY `menu_Index_create` (`create_time`),
  KEY `menu_Index_sort` (`sort_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('combat', '战斗分析', null, '10', '', null, null, '0', null, '', '2018-01-08 15:39:03', '2018-01-08 15:39:03', null, null);
INSERT INTO `sys_menu` VALUES ('combat:log', '战斗日志', null, '1', 'combat', null, null, '0', null, '/views/modules/gdata/combat/combatLog', '2018-01-08 15:39:09', '2018-01-08 15:39:09', null, null);
INSERT INTO `sys_menu` VALUES ('crash', '异常分析', null, '10', '', null, null, '0', null, '', '2018-01-08 15:39:02', '2018-01-08 15:39:02', null, null);
INSERT INTO `sys_menu` VALUES ('crash:gcrash', '游戏蹦溃分析', null, '1', 'crash', null, null, '0', null, '/views/modules/gdata/crash/gcrashList', '2018-01-08 15:39:18', '2018-01-08 15:39:18', null, null);
INSERT INTO `sys_menu` VALUES ('game:dict:add', '添加', null, '6', 'sys:gamedict', null, null, '1', null, null, '2018-01-08 15:39:15', '2018-01-08 15:39:15', null, null);
INSERT INTO `sys_menu` VALUES ('game:dict:del', '删除', null, '6', 'sys:gamedict', null, null, '1', null, null, '2018-01-08 15:39:09', '2018-01-08 15:39:09', null, null);
INSERT INTO `sys_menu` VALUES ('game:dict:get', '查看详情', null, '6', 'sys:gamedict', null, null, '1', null, null, '2018-01-08 15:39:02', '2018-01-08 15:39:02', null, null);
INSERT INTO `sys_menu` VALUES ('game:dict:list', '查询列表', null, '6', 'sys:gamedict', null, null, '1', null, null, '2018-01-08 15:39:10', '2018-01-08 15:39:10', null, null);
INSERT INTO `sys_menu` VALUES ('game:dict:update', '修改', null, '6', 'sys:gamedict', null, null, '1', null, null, '2018-01-08 15:39:11', '2018-01-08 15:39:11', null, null);
INSERT INTO `sys_menu` VALUES ('publ', '游戏共性数据', null, '5', '', null, null, '0', null, '', '2018-01-08 15:39:07', '2018-01-08 15:39:07', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj', '常用数据', null, '20', 'publ', null, null, '0', null, '', '2018-01-08 15:39:15', '2018-01-08 15:39:15', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:lcl', '留存类', null, '2', 'publ:cysj', null, null, '0', null, '', '2018-01-08 15:39:10', '2018-01-08 15:39:10', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:lcl:account_retain', '账号留存', null, '1', 'publ:cysj:lcl', null, null, '0', null, '/views/modules/gdata/publ/retain/account_retain', '2018-01-08 15:39:17', '2018-01-08 15:39:17', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:lcl:device_reg', '设备激活注册率', null, '4', 'publ:cysj:lcl', null, null, '0', null, '/views/modules/gdata/publ/device/device_reg', '2018-01-08 15:39:17', '2018-01-08 15:39:17', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:lcl:lv_drain', '等级流失', null, '4', 'publ:cysj:lcl', null, null, '0', null, '/views/modules/gdata/publ/retain/lv_drain', '2018-01-08 15:39:11', '2018-01-08 15:39:11', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:lcl:role_retain', '角色留存', null, '2', 'publ:cysj:lcl', null, null, '0', null, '/views/modules/gdata/publ/retain/role_retain', '2018-01-08 15:39:09', '2018-01-08 15:39:09', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:yhl', '用户类', null, '1', 'publ:cysj', null, null, '0', null, '', '2018-01-08 15:39:04', '2018-01-08 15:39:04', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:yhl:day_total', '当日总况', null, '1', 'publ:cysj:yhl', null, null, '0', null, '/views/modules/gdata/publ/away/day_total', '2018-01-08 15:39:10', '2018-01-08 15:39:10', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:yhl:month_away', '月总况', null, '4', 'publ:cysj:yhl', null, null, '0', null, '/views/modules/gdata/publ/away/week_away.html?flg=month_away', '2018-01-08 15:38:57', '2018-01-08 15:38:57', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:yhl:week_away', '周总况', null, '2', 'publ:cysj:yhl', null, null, '0', null, '/views/modules/gdata/publ/away/week_away.html?flg=week_away', '2018-01-08 15:39:10', '2018-01-08 15:39:10', null, null);
INSERT INTO `sys_menu` VALUES ('publ:cysj:yhl:week_away_double', '双周总况', null, '3', 'publ:cysj:yhl', null, null, '0', null, '/views/modules/gdata/publ/away/week_away.html?flg=week_away_double', '2018-01-08 15:39:14', '2018-01-08 15:39:14', null, null);
INSERT INTO `sys_menu` VALUES ('publ:index:data_index', '主页', null, '1', 'publ', null, null, '0', null, '/views/modules/gdata/publ/index/data_index', '2018-01-08 15:39:04', '2018-01-08 15:39:04', null, null);
INSERT INTO `sys_menu` VALUES ('publ:sssj', '实时数据', null, '10', 'publ', null, null, '0', null, '', '2018-01-08 15:39:09', '2018-01-08 15:39:09', null, null);
INSERT INTO `sys_menu` VALUES ('publ:sssj:new_register', '每小时注册', null, '1', 'publ:sssj', null, null, '0', null, '/views/modules/gdata/publ/realtime/new_register', '2018-01-08 15:39:06', '2018-01-08 15:39:06', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl', '用户类', null, '40', 'publ', null, null, '0', null, '', '2018-01-08 15:39:13', '2018-01-08 15:39:13', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:acu_equality', 'ACU等效用户', null, '2', 'publ:yhl', null, null, '0', null, '/views/modules/gdata/publ/account/acu_equality', '2018-01-08 15:39:12', '2018-01-08 15:39:12', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:rzyh', '注册用户', null, '1', 'publ:yhl', null, null, '0', null, '', '2018-01-08 15:39:03', '2018-01-08 15:39:03', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:rzyh:mobile', '玩家机型分布', null, '3', 'publ:yhl:rzyh', null, null, '0', null, '/views/modules/gdata/publ/register/mobile', '2018-01-08 15:39:16', '2018-01-08 15:39:16', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:rzyh:provider', '新注册渠道分布', null, '2', 'publ:yhl:rzyh', null, null, '0', null, '/views/modules/gdata/publ/register/provider', '2018-01-08 15:38:59', '2018-01-08 15:38:59', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:rzyh:reg_net_distr', '新注册网络分布', null, '4', 'publ:yhl:rzyh', null, null, '0', null, '/views/modules/gdata/publ/register/reg_net_distr', '2018-01-08 15:39:14', '2018-01-08 15:39:14', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:userdoubleweek', '用户双周总况', null, '1', 'publ:yhl', null, null, '0', null, '/views/modules/pro/merproduct/list', '2018-01-08 15:39:09', '2018-01-08 15:39:09', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:xyqk', '活跃情况', null, '2', 'publ:yhl', null, null, '0', null, '', '2018-01-08 15:39:05', '2018-01-08 15:39:05', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:xyqk:dau', '活跃用户数', null, '1', 'publ:yhl:xyqk', null, null, '0', null, '/views/modules/pro/merproduct/list', '2018-01-08 15:39:08', '2018-01-08 15:39:08', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:yxyh', '有效用户', null, '3', 'publ:yhl', null, null, '0', null, '', '2018-01-08 15:39:11', '2018-01-08 15:39:11', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yhl:yxyh:login_tank', '手游登录比汇总', null, '1', 'publ:yhl:yxyh', null, null, '0', null, '/views/modules/gdata/publ/account/login_tank', '2018-01-08 15:39:13', '2018-01-08 15:39:13', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yuzk', '运营总况', null, '9', 'publ', null, null, '0', null, '', '2018-01-08 15:39:01', '2018-01-08 15:39:01', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yuzk:operate_info', '运营总况', null, '4', 'publ:yuzk', null, null, '0', null, '/views/modules/gdata/publ/operate/operate_info', '2018-01-08 15:39:00', '2018-01-08 15:39:00', null, null);
INSERT INTO `sys_menu` VALUES ('publ:yxst', '游戏生态', null, '50', 'publ', null, null, '0', null, '', '2018-01-08 15:39:10', '2018-01-08 15:39:10', null, null);
INSERT INTO `sys_menu` VALUES ('publ:zxl', '在线类', null, '30', 'publ', null, null, '0', null, '', '2018-01-08 15:39:06', '2018-01-08 15:39:06', null, null);
INSERT INTO `sys_menu` VALUES ('publ:zxl:zxfx', '在线分析', null, '1', 'publ:zxl', null, null, '0', null, '', '2018-01-08 15:39:00', '2018-01-08 15:39:00', null, null);
INSERT INTO `sys_menu` VALUES ('publ:zxl:zxfx:lv_distribution', '在线等级分布', null, '4', 'publ:zxl:zxfx', null, null, '0', null, '/views/modules/gdata/publ/online/lv_distribution', '2018-01-08 15:39:08', '2018-01-08 15:39:08', null, null);
INSERT INTO `sys_menu` VALUES ('publ:zxl:zxfx:onlie_report', '在线/实时总况', null, '1', 'publ:zxl:zxfx', null, null, '0', null, '/views/modules/gdata/publ/online/onlie_report', '2018-01-08 15:39:13', '2018-01-08 15:39:13', null, null);
INSERT INTO `sys_menu` VALUES ('publ:zxl:zxfx:online_duration', '在线/在线时长分布', null, '2', 'publ:zxl:zxfx', null, null, '0', null, '/views/modules/gdata/publ/online/online_duration', '2018-01-08 15:38:59', '2018-01-08 15:38:59', null, null);
INSERT INTO `sys_menu` VALUES ('publ:zxl:zxfx:time_distribution', '平均在线区间分布', null, '3', 'publ:zxl:zxfx', null, null, '0', null, '/views/modules/gdata/publ/online/time_distribution', '2018-01-08 15:39:18', '2018-01-08 15:39:18', null, null);
INSERT INTO `sys_menu` VALUES ('setting', '基本设置', null, '4', '', null, null, '0', null, '', '2018-01-08 15:39:04', '2018-01-08 15:39:04', null, null);
INSERT INTO `sys_menu` VALUES ('setting:personal', '个人信息', null, '1', 'setting', null, null, '0', null, '/views/modules/setting/personal/personalUpdate', '2018-01-08 15:39:05', '2018-01-08 15:39:05', null, null);
INSERT INTO `sys_menu` VALUES ('setting:personal:get', '查看编辑', null, '1', 'setting:personal', null, null, '1', null, null, '2018-01-08 15:39:05', '2018-01-08 15:39:05', null, null);
INSERT INTO `sys_menu` VALUES ('setting:personal:update', '修改个人信息', null, '1', 'setting:personal', null, null, '1', null, null, '2018-01-08 15:39:17', '2018-01-08 15:39:17', null, null);
INSERT INTO `sys_menu` VALUES ('setting:personal:updatePassword', '修改密码', null, '1', 'setting:personal', null, null, '1', null, null, '2018-01-08 15:39:15', '2018-01-08 15:39:15', null, null);
INSERT INTO `sys_menu` VALUES ('sys', '系统管理', null, '1', '', null, null, '0', null, '', '2018-01-08 15:39:17', '2018-01-08 15:39:17', null, null);
INSERT INTO `sys_menu` VALUES ('sys:department', '部门管理', null, '4', 'sys', null, null, '2', null, '/views/modules/sys/departmentlist', '2018-01-08 15:39:02', '2018-01-08 15:39:02', null, null);
INSERT INTO `sys_menu` VALUES ('sys:department:add', '添加', null, '4', 'sys:department', null, null, '1', null, null, '2018-01-08 15:39:09', '2018-01-08 15:39:09', null, null);
INSERT INTO `sys_menu` VALUES ('sys:department:del', '删除', null, '4', 'sys:department', null, null, '1', null, null, '2018-01-08 15:39:13', '2018-01-08 15:39:13', null, null);
INSERT INTO `sys_menu` VALUES ('sys:department:get', '查询', null, '4', 'sys:department', null, null, '1', null, null, '2018-01-08 15:38:59', '2018-01-08 15:38:59', null, null);
INSERT INTO `sys_menu` VALUES ('sys:department:update', '修改', null, '4', 'sys:department', null, null, '1', null, null, '2018-01-08 15:39:06', '2018-01-08 15:39:06', null, null);
INSERT INTO `sys_menu` VALUES ('sys:dict', '字典管理', null, '5', 'sys', null, null, '0', null, '/views/modules/sys/dict/dictlist', '2018-01-08 15:39:01', '2018-01-08 15:39:01', null, null);
INSERT INTO `sys_menu` VALUES ('sys:dict:add', '添加', null, '5', 'sys:dict', null, null, '1', null, null, '2018-01-08 15:39:14', '2018-01-08 15:39:14', null, null);
INSERT INTO `sys_menu` VALUES ('sys:dict:del', '删除', null, '5', 'sys:dict', null, null, '1', null, null, '2018-01-08 15:39:12', '2018-01-08 15:39:12', null, null);
INSERT INTO `sys_menu` VALUES ('sys:dict:get', '查看详情', null, '5', 'sys:dict', null, null, '1', null, null, '2018-01-08 15:38:58', '2018-01-08 15:38:58', null, null);
INSERT INTO `sys_menu` VALUES ('sys:dict:list', '查询列表', null, '5', 'sys:dict', null, null, '1', null, null, '2018-01-08 15:38:58', '2018-01-08 15:38:58', null, null);
INSERT INTO `sys_menu` VALUES ('sys:dict:update', '修改', null, '5', 'sys:dict', null, null, '1', null, null, '2018-01-08 15:39:01', '2018-01-08 15:39:01', null, null);
INSERT INTO `sys_menu` VALUES ('sys:druid', '数据库监控', null, '7', 'sys', null, null, '0', null, '/druid/index', '2018-01-08 15:39:03', '2018-01-08 15:39:03', null, null);
INSERT INTO `sys_menu` VALUES ('sys:gamedict', '游戏字典管理', null, '6', 'sys', null, null, '0', null, '/views/modules/sys/gamedict/dictlist', '2018-01-08 15:39:06', '2018-01-08 15:39:06', null, null);
INSERT INTO `sys_menu` VALUES ('sys:log', '系统日志', null, '6', 'sys', null, null, '0', null, '/views/modules/sys/log/loglist', '2018-01-08 15:39:00', '2018-01-08 15:39:00', null, null);
INSERT INTO `sys_menu` VALUES ('sys:log:list', '查询列表', null, '6', 'sys:log', null, null, '1', null, null, '2018-01-08 15:39:02', '2018-01-08 15:39:02', null, null);
INSERT INTO `sys_menu` VALUES ('sys:menu', '菜单管理', null, '3', 'sys', null, null, '0', null, '/views/modules/sys/menu/menulist', '2018-01-08 15:39:13', '2018-01-08 15:39:13', null, null);
INSERT INTO `sys_menu` VALUES ('sys:menu:add', '添加', null, '3', 'sys:menu', null, null, '1', null, null, '2018-01-08 15:38:58', '2018-01-08 15:38:58', null, null);
INSERT INTO `sys_menu` VALUES ('sys:menu:del', '删除', null, '3', 'sys:menu', null, null, '1', null, null, '2018-01-08 15:39:17', '2018-01-08 15:39:17', null, null);
INSERT INTO `sys_menu` VALUES ('sys:menu:get', '查询', null, '3', 'sys:menu', null, null, '1', null, null, '2018-01-08 15:39:12', '2018-01-08 15:39:12', null, null);
INSERT INTO `sys_menu` VALUES ('sys:menu:update', '修改', null, '3', 'sys:menu', null, null, '1', null, null, '2018-01-08 15:39:13', '2018-01-08 15:39:13', null, null);
INSERT INTO `sys_menu` VALUES ('sys:parameter', '系统参数配置', null, '6', 'sys', null, null, '0', null, '/views/modules/sys/parameter/parameterlist', '2018-01-08 15:39:05', '2018-01-08 15:39:05', null, null);
INSERT INTO `sys_menu` VALUES ('sys:parameter:add', '添加', null, '6', 'sys:parameter', null, null, '1', null, null, '2018-01-08 15:39:00', '2018-01-08 15:39:00', null, null);
INSERT INTO `sys_menu` VALUES ('sys:parameter:del', '删除', null, '6', 'sys:parameter', null, null, '1', null, null, '2018-01-08 15:39:15', '2018-01-08 15:39:15', null, null);
INSERT INTO `sys_menu` VALUES ('sys:parameter:get', '查询', null, '6', 'sys:parameter', null, null, '1', null, null, '2018-01-08 15:39:15', '2018-01-08 15:39:15', null, null);
INSERT INTO `sys_menu` VALUES ('sys:parameter:update', '修改', null, '6', 'sys:parameter', null, null, '1', null, null, '2018-01-08 15:38:59', '2018-01-08 15:38:59', null, null);
INSERT INTO `sys_menu` VALUES ('sys:role', '角色管理', null, '2', 'sys', null, null, '0', null, '/views/modules/sys/role/rolelist', '2018-01-08 15:39:16', '2018-01-08 15:39:16', null, null);
INSERT INTO `sys_menu` VALUES ('sys:role:add', '添加', null, '2', 'sys:role', null, null, '1', null, null, '2018-01-08 15:39:18', '2018-01-08 15:39:18', null, null);
INSERT INTO `sys_menu` VALUES ('sys:role:del', '删除', null, '2', 'sys:role', null, null, '1', null, null, '2018-01-08 15:39:18', '2018-01-08 15:39:18', null, null);
INSERT INTO `sys_menu` VALUES ('sys:role:get', '查看', null, '2', 'sys:role', null, null, '1', null, null, '2018-01-08 15:39:11', '2018-01-08 15:39:11', null, null);
INSERT INTO `sys_menu` VALUES ('sys:role:list', '查看列表', null, '2', 'sys:role', null, null, '1', null, null, '2018-01-08 15:38:57', '2018-01-08 15:38:57', null, null);
INSERT INTO `sys_menu` VALUES ('sys:role:update', '修改', null, '2', 'sys:role', null, null, '1', null, null, '2018-01-08 15:39:16', '2018-01-08 15:39:16', null, null);
INSERT INTO `sys_menu` VALUES ('sys:user', '用户管理', null, '1', 'sys', null, null, '0', null, '/views/modules/sys/user/userlist', '2018-01-08 15:39:00', '2018-01-08 15:39:00', null, null);
INSERT INTO `sys_menu` VALUES ('sys:user:add', '添加', null, '1', 'sys:user', null, null, '1', null, null, '2018-01-08 15:39:01', '2018-01-08 15:39:01', null, null);
INSERT INTO `sys_menu` VALUES ('sys:user:del', '删除', null, '1', 'sys:user', null, null, '1', null, null, '2018-01-08 15:39:14', '2018-01-08 15:39:14', null, null);
INSERT INTO `sys_menu` VALUES ('sys:user:get', '查看详情', null, '1', 'sys:user', null, null, '1', null, null, '2018-01-08 15:39:16', '2018-01-08 15:39:16', null, null);
INSERT INTO `sys_menu` VALUES ('sys:user:list', '查询列表', null, '1', 'sys:user', null, null, '1', null, null, '2018-01-08 15:39:04', '2018-01-08 15:39:04', null, null);
INSERT INTO `sys_menu` VALUES ('sys:user:update', '修改', null, '1', 'sys:user', null, null, '1', null, null, '2018-01-08 15:39:08', '2018-01-08 15:39:08', null, null);
INSERT INTO `sys_menu` VALUES ('xy', '轩辕特性数据', null, '7', '', null, null, '0', null, '', '2018-01-08 15:39:03', '2018-01-08 15:39:03', null, null);
INSERT INTO `sys_menu` VALUES ('xy:data', '战斗日志', null, '1', 'xydata', null, null, '0', null, '/views/modules/pro/merproduct/list', '2018-01-08 15:39:07', '2018-01-08 15:39:07', null, null);
INSERT INTO `sys_menu` VALUES ('xydata', '二级菜单', null, '1', 'xy', null, null, '0', null, '', '2018-01-08 15:39:17', '2018-01-08 15:39:17', null, null);
INSERT INTO `sys_menu` VALUES ('zl', '战卢特性数据', null, '6', '', null, null, '0', null, '', '2018-01-08 15:38:58', '2018-01-08 15:38:58', null, null);
INSERT INTO `sys_menu` VALUES ('zl:gwsj', '怪物数据', null, '20', 'zl', null, null, '0', null, '', '2018-01-08 15:38:58', '2018-01-08 15:38:58', null, null);
INSERT INTO `sys_menu` VALUES ('zl:gwsj:jekyll_blame', '盗宝怪', null, '4', 'zl:gwsj', null, null, '0', null, '/views/modules/gdata/zl/monster/jekyll_blame', '2018-01-08 15:39:18', '2018-01-08 15:39:18', null, null);
INSERT INTO `sys_menu` VALUES ('zl:gwsj:world_boss', 'boss死亡日志', null, '1', 'zl:gwsj', null, null, '0', null, '/views/modules/gdata/zl/monster/world_boss', '2018-01-08 15:39:12', '2018-01-08 15:39:12', null, null);
INSERT INTO `sys_menu` VALUES ('zl:gwsj:world_boss_fight', 'boss争夺率', null, '2', 'zl:gwsj', null, null, '0', null, '/views/modules/gdata/zl/monster/world_boss_fight', '2018-01-08 15:39:01', '2018-01-08 15:39:01', null, null);
INSERT INTO `sys_menu` VALUES ('zl:gwsj:world_boss_Lifetime', 'boss生存期分析', null, '3', 'zl:gwsj', null, null, '0', null, '/views/modules/gdata/zl/monster/world_boss_lifetime', '2018-01-08 15:39:02', '2018-01-08 15:39:02', null, null);
INSERT INTO `sys_menu` VALUES ('zl:hbsj', '货币数据', null, '10', 'zl', null, null, '0', null, '', '2018-01-08 15:39:06', '2018-01-08 15:39:06', null, null);
INSERT INTO `sys_menu` VALUES ('zl:hbsj:goods', '物品产出消耗', null, '3', 'zl:hbsj', null, null, '0', null, '/views/modules/gdata/zl/currency/goods', '2018-01-08 15:39:00', '2018-01-08 15:39:00', null, null);
INSERT INTO `sys_menu` VALUES ('zl:hbsj:output_input', '货币产出消耗', null, '1', 'zl:hbsj', null, null, '0', null, '/views/modules/gdata/zl/currency/money_data', '2018-01-08 15:38:59', '2018-01-08 15:38:59', null, null);
INSERT INTO `sys_menu` VALUES ('zl:hbsj:repertory', '货币-库存分析', null, '2', 'zl:hbsj', null, null, '0', null, '/views/modules/gdata/zl/currency/repertory', '2018-01-08 15:39:14', '2018-01-08 15:39:14', null, null);
INSERT INTO `sys_menu` VALUES ('zl:jnfx', '技能分析', null, '90', 'zl', null, null, '0', null, '', '2018-01-08 15:39:12', '2018-01-08 15:39:12', null, null);
INSERT INTO `sys_menu` VALUES ('zl:jnfx:jnpm', '技能排名', null, '80', 'zl:jnfx', null, null, '0', null, '', '2018-01-08 15:39:16', '2018-01-08 15:39:16', null, null);
INSERT INTO `sys_menu` VALUES ('zl:jnfx:jnpm:test', '测试图表', null, '1', 'zl:jnfx:jnpm', null, null, '0', null, '/views/modules/gdata/zl/skill/zl_skill_data', '2018-01-08 15:39:03', '2018-01-08 15:39:03', null, null);
INSERT INTO `sys_menu` VALUES ('zl:jnfx:jnpm:test:data', '测试数据图表', null, '1', 'zl:jnfx:jnpm', null, null, '0', null, '/views/modules/gdata/zl/skill/zl_skill_top', '2018-01-08 15:39:11', '2018-01-08 15:39:11', null, null);

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- ----------------------------
-- Records of sys_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '角色名称',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `types` varchar(100) DEFAULT NULL COMMENT '类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) DEFAULT NULL COMMENT '更新者',
  `group_id` int(11) DEFAULT NULL COMMENT '归属机构',
  `remake` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理', '0', '1', '2018-01-08 15:39:19', '2018-01-08 15:51:08', '1', null, '1', 'remake');
INSERT INTO `sys_role` VALUES ('2', '商家角色', '0', null, '2018-01-08 15:39:27', '2018-01-08 15:51:10', null, null, '1', 'remake');
INSERT INTO `sys_role` VALUES ('3', '战卢游戏', '0', 'zl', '2017-08-28 15:39:57', '2018-01-08 16:08:04', null, '3', '2', '战卢游戏');
INSERT INTO `sys_role` VALUES ('4', '轩辕游戏', '0', 'xy', '2017-08-28 15:55:29', '2017-08-29 11:55:18', null, '3', '2', '轩辕游戏');
INSERT INTO `sys_role` VALUES ('6', '战卢游戏策划', '0', 'zl_cehua', '2017-08-28 15:55:29', '2018-01-08 16:08:08', null, '3', '2', '战卢游戏策划');

-- ----------------------------
-- Table structure for sys_role_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_group`;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色组';

-- ----------------------------
-- Records of sys_role_group
-- ----------------------------
INSERT INTO `sys_role_group` VALUES ('1', '系统角色组', '0', '1', '2017-08-29 11:52:29', '2017-08-29 11:53:22', null, null, '系统角色组');
INSERT INTO `sys_role_group` VALUES ('2', '游戏角色组', '0', '1', '2017-08-29 11:52:29', '2017-08-29 11:53:20', null, null, '游戏角色组');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=367 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', 'sys:role:list');
INSERT INTO `sys_role_menu` VALUES ('2', '1', 'publ:cysj:yhl:month_away');
INSERT INTO `sys_role_menu` VALUES ('3', '1', 'zl:gwsj');
INSERT INTO `sys_role_menu` VALUES ('4', '1', 'sys:dict:get');
INSERT INTO `sys_role_menu` VALUES ('5', '1', 'sys:menu:add');
INSERT INTO `sys_role_menu` VALUES ('6', '1', 'zl');
INSERT INTO `sys_role_menu` VALUES ('7', '1', 'sys:dict:list');
INSERT INTO `sys_role_menu` VALUES ('8', '1', 'zl:hbsj:output_input');
INSERT INTO `sys_role_menu` VALUES ('9', '1', 'sys:department:get');
INSERT INTO `sys_role_menu` VALUES ('10', '1', 'sys:parameter:update');
INSERT INTO `sys_role_menu` VALUES ('11', '1', 'publ:zxl:zxfx:online_duration');
INSERT INTO `sys_role_menu` VALUES ('12', '1', 'publ:yhl:rzyh:provider');
INSERT INTO `sys_role_menu` VALUES ('13', '1', 'sys:log');
INSERT INTO `sys_role_menu` VALUES ('14', '1', 'publ:zxl:zxfx');
INSERT INTO `sys_role_menu` VALUES ('15', '1', 'zl:hbsj:goods');
INSERT INTO `sys_role_menu` VALUES ('16', '1', 'sys:user');
INSERT INTO `sys_role_menu` VALUES ('17', '1', 'publ:yuzk:operate_info');
INSERT INTO `sys_role_menu` VALUES ('18', '1', 'sys:parameter:add');
INSERT INTO `sys_role_menu` VALUES ('19', '1', 'publ:yuzk');
INSERT INTO `sys_role_menu` VALUES ('20', '1', 'sys:user:add');
INSERT INTO `sys_role_menu` VALUES ('21', '1', 'sys:dict');
INSERT INTO `sys_role_menu` VALUES ('22', '1', 'sys:dict:update');
INSERT INTO `sys_role_menu` VALUES ('23', '1', 'zl:gwsj:world_boss_fight');
INSERT INTO `sys_role_menu` VALUES ('24', '1', 'crash');
INSERT INTO `sys_role_menu` VALUES ('25', '1', 'game:dict:get');
INSERT INTO `sys_role_menu` VALUES ('26', '1', 'zl:gwsj:world_boss_Lifetime');
INSERT INTO `sys_role_menu` VALUES ('27', '1', 'sys:department');
INSERT INTO `sys_role_menu` VALUES ('28', '1', 'sys:log:list');
INSERT INTO `sys_role_menu` VALUES ('29', '1', 'zl:jnfx:jnpm:test');
INSERT INTO `sys_role_menu` VALUES ('30', '1', 'combat');
INSERT INTO `sys_role_menu` VALUES ('31', '1', 'xy');
INSERT INTO `sys_role_menu` VALUES ('32', '1', 'publ:yhl:rzyh');
INSERT INTO `sys_role_menu` VALUES ('33', '1', 'sys:druid');
INSERT INTO `sys_role_menu` VALUES ('34', '1', 'setting');
INSERT INTO `sys_role_menu` VALUES ('35', '1', 'sys:user:list');
INSERT INTO `sys_role_menu` VALUES ('36', '1', 'publ:index:data_index');
INSERT INTO `sys_role_menu` VALUES ('37', '1', 'publ:cysj:yhl');
INSERT INTO `sys_role_menu` VALUES ('38', '1', 'publ:yhl:xyqk');
INSERT INTO `sys_role_menu` VALUES ('39', '1', 'sys:parameter');
INSERT INTO `sys_role_menu` VALUES ('40', '1', 'setting:personal');
INSERT INTO `sys_role_menu` VALUES ('41', '1', 'setting:personal:get');
INSERT INTO `sys_role_menu` VALUES ('42', '1', 'zl:hbsj');
INSERT INTO `sys_role_menu` VALUES ('43', '1', 'sys:gamedict');
INSERT INTO `sys_role_menu` VALUES ('44', '1', 'publ:sssj:new_register');
INSERT INTO `sys_role_menu` VALUES ('45', '1', 'sys:department:update');
INSERT INTO `sys_role_menu` VALUES ('46', '1', 'publ:zxl');
INSERT INTO `sys_role_menu` VALUES ('47', '1', 'xy:data');
INSERT INTO `sys_role_menu` VALUES ('48', '1', 'publ');
INSERT INTO `sys_role_menu` VALUES ('49', '1', 'publ:yhl:xyqk:dau');
INSERT INTO `sys_role_menu` VALUES ('50', '1', 'publ:zxl:zxfx:lv_distribution');
INSERT INTO `sys_role_menu` VALUES ('51', '1', 'sys:user:update');
INSERT INTO `sys_role_menu` VALUES ('52', '1', 'publ:yhl:userdoubleweek');
INSERT INTO `sys_role_menu` VALUES ('53', '1', 'publ:cysj:lcl:role_retain');
INSERT INTO `sys_role_menu` VALUES ('54', '1', 'publ:sssj');
INSERT INTO `sys_role_menu` VALUES ('55', '1', 'sys:department:add');
INSERT INTO `sys_role_menu` VALUES ('56', '1', 'game:dict:del');
INSERT INTO `sys_role_menu` VALUES ('57', '1', 'combat:log');
INSERT INTO `sys_role_menu` VALUES ('58', '1', 'publ:cysj:lcl');
INSERT INTO `sys_role_menu` VALUES ('59', '1', 'publ:cysj:yhl:week_away');
INSERT INTO `sys_role_menu` VALUES ('60', '1', 'game:dict:list');
INSERT INTO `sys_role_menu` VALUES ('61', '1', 'publ:cysj:yhl:day_total');
INSERT INTO `sys_role_menu` VALUES ('62', '1', 'publ:yxst');
INSERT INTO `sys_role_menu` VALUES ('63', '1', 'sys:role:get');
INSERT INTO `sys_role_menu` VALUES ('64', '1', 'game:dict:update');
INSERT INTO `sys_role_menu` VALUES ('65', '1', 'zl:jnfx:jnpm:test:data');
INSERT INTO `sys_role_menu` VALUES ('66', '1', 'publ:cysj:lcl:lv_drain');
INSERT INTO `sys_role_menu` VALUES ('67', '1', 'publ:yhl:yxyh');
INSERT INTO `sys_role_menu` VALUES ('68', '1', 'zl:jnfx');
INSERT INTO `sys_role_menu` VALUES ('69', '1', 'sys:dict:del');
INSERT INTO `sys_role_menu` VALUES ('70', '1', 'sys:menu:get');
INSERT INTO `sys_role_menu` VALUES ('71', '1', 'publ:yhl:acu_equality');
INSERT INTO `sys_role_menu` VALUES ('72', '1', 'zl:gwsj:world_boss');
INSERT INTO `sys_role_menu` VALUES ('73', '1', 'sys:menu:update');
INSERT INTO `sys_role_menu` VALUES ('74', '1', 'sys:department:del');
INSERT INTO `sys_role_menu` VALUES ('75', '1', 'sys:menu');
INSERT INTO `sys_role_menu` VALUES ('76', '1', 'publ:zxl:zxfx:onlie_report');
INSERT INTO `sys_role_menu` VALUES ('77', '1', 'publ:yhl');
INSERT INTO `sys_role_menu` VALUES ('78', '1', 'publ:yhl:yxyh:login_tank');
INSERT INTO `sys_role_menu` VALUES ('79', '1', 'sys:dict:add');
INSERT INTO `sys_role_menu` VALUES ('80', '1', 'zl:hbsj:repertory');
INSERT INTO `sys_role_menu` VALUES ('81', '1', 'sys:user:del');
INSERT INTO `sys_role_menu` VALUES ('82', '1', 'publ:cysj:yhl:week_away_double');
INSERT INTO `sys_role_menu` VALUES ('83', '1', 'publ:yhl:rzyh:reg_net_distr');
INSERT INTO `sys_role_menu` VALUES ('84', '1', 'setting:personal:updatePassword');
INSERT INTO `sys_role_menu` VALUES ('85', '1', 'publ:cysj');
INSERT INTO `sys_role_menu` VALUES ('86', '1', 'sys:parameter:get');
INSERT INTO `sys_role_menu` VALUES ('87', '1', 'game:dict:add');
INSERT INTO `sys_role_menu` VALUES ('88', '1', 'sys:parameter:del');
INSERT INTO `sys_role_menu` VALUES ('89', '1', 'sys:role:update');
INSERT INTO `sys_role_menu` VALUES ('90', '1', 'publ:yhl:rzyh:mobile');
INSERT INTO `sys_role_menu` VALUES ('91', '1', 'sys:role');
INSERT INTO `sys_role_menu` VALUES ('92', '1', 'zl:jnfx:jnpm');
INSERT INTO `sys_role_menu` VALUES ('93', '1', 'sys:user:get');
INSERT INTO `sys_role_menu` VALUES ('94', '1', 'xydata');
INSERT INTO `sys_role_menu` VALUES ('95', '1', 'setting:personal:update');
INSERT INTO `sys_role_menu` VALUES ('96', '1', 'publ:cysj:lcl:account_retain');
INSERT INTO `sys_role_menu` VALUES ('97', '1', 'publ:cysj:lcl:device_reg');
INSERT INTO `sys_role_menu` VALUES ('98', '1', 'sys:menu:del');
INSERT INTO `sys_role_menu` VALUES ('99', '1', 'sys');
INSERT INTO `sys_role_menu` VALUES ('100', '1', 'crash:gcrash');
INSERT INTO `sys_role_menu` VALUES ('101', '1', 'zl:gwsj:jekyll_blame');
INSERT INTO `sys_role_menu` VALUES ('102', '1', 'publ:zxl:zxfx:time_distribution');
INSERT INTO `sys_role_menu` VALUES ('103', '1', 'sys:role:del');
INSERT INTO `sys_role_menu` VALUES ('104', '1', 'sys:role:add');
INSERT INTO `sys_role_menu` VALUES ('105', '6', 'setting');
INSERT INTO `sys_role_menu` VALUES ('106', '6', 'setting:personal');
INSERT INTO `sys_role_menu` VALUES ('107', '6', 'setting:personal:get');
INSERT INTO `sys_role_menu` VALUES ('108', '6', 'setting:personal:update');
INSERT INTO `sys_role_menu` VALUES ('109', '6', 'setting:personal:updatePassword');
INSERT INTO `sys_role_menu` VALUES ('110', '6', 'sys:department:add');
INSERT INTO `sys_role_menu` VALUES ('111', '6', 'sys:department:del');
INSERT INTO `sys_role_menu` VALUES ('112', '6', 'sys:department:get');
INSERT INTO `sys_role_menu` VALUES ('113', '6', 'sys:department:update');
INSERT INTO `sys_role_menu` VALUES ('114', '6', 'publ');
INSERT INTO `sys_role_menu` VALUES ('115', '6', 'publ:index:data_index');
INSERT INTO `sys_role_menu` VALUES ('116', '6', 'publ:yuzk');
INSERT INTO `sys_role_menu` VALUES ('117', '6', 'publ:yuzk:operate_info');
INSERT INTO `sys_role_menu` VALUES ('118', '6', 'publ:sssj');
INSERT INTO `sys_role_menu` VALUES ('119', '6', 'publ:sssj:new_register');
INSERT INTO `sys_role_menu` VALUES ('120', '6', 'publ:cysj');
INSERT INTO `sys_role_menu` VALUES ('121', '6', 'publ:cysj:yhl');
INSERT INTO `sys_role_menu` VALUES ('122', '6', 'publ:cysj:yhl:day_total');
INSERT INTO `sys_role_menu` VALUES ('123', '6', 'publ:cysj:yhl:week_away');
INSERT INTO `sys_role_menu` VALUES ('124', '6', 'publ:cysj:yhl:week_away_double');
INSERT INTO `sys_role_menu` VALUES ('125', '6', 'publ:cysj:yhl:month_away');
INSERT INTO `sys_role_menu` VALUES ('126', '6', 'publ:cysj:lcl');
INSERT INTO `sys_role_menu` VALUES ('127', '6', 'publ:cysj:lcl:account_retain');
INSERT INTO `sys_role_menu` VALUES ('128', '6', 'publ:cysj:lcl:role_retain');
INSERT INTO `sys_role_menu` VALUES ('129', '6', 'publ:cysj:lcl:lv_drain');
INSERT INTO `sys_role_menu` VALUES ('130', '6', 'publ:cysj:lcl:device_reg');
INSERT INTO `sys_role_menu` VALUES ('131', '6', 'publ:zxl');
INSERT INTO `sys_role_menu` VALUES ('132', '6', 'publ:zxl:zxfx');
INSERT INTO `sys_role_menu` VALUES ('133', '6', 'publ:zxl:zxfx:onlie_report');
INSERT INTO `sys_role_menu` VALUES ('134', '6', 'publ:zxl:zxfx:online_duration');
INSERT INTO `sys_role_menu` VALUES ('135', '6', 'publ:zxl:zxfx:time_distribution');
INSERT INTO `sys_role_menu` VALUES ('136', '6', 'publ:zxl:zxfx:lv_distribution');
INSERT INTO `sys_role_menu` VALUES ('137', '6', 'publ:yhl');
INSERT INTO `sys_role_menu` VALUES ('138', '6', 'publ:yhl:rzyh');
INSERT INTO `sys_role_menu` VALUES ('139', '6', 'publ:yhl:rzyh:provider');
INSERT INTO `sys_role_menu` VALUES ('140', '6', 'publ:yhl:rzyh:mobile');
INSERT INTO `sys_role_menu` VALUES ('141', '6', 'publ:yhl:rzyh:reg_net_distr');
INSERT INTO `sys_role_menu` VALUES ('142', '6', 'publ:yhl:userdoubleweek');
INSERT INTO `sys_role_menu` VALUES ('143', '6', 'publ:yhl:xyqk');
INSERT INTO `sys_role_menu` VALUES ('144', '6', 'publ:yhl:xyqk:dau');
INSERT INTO `sys_role_menu` VALUES ('145', '6', 'publ:yhl:acu_equality');
INSERT INTO `sys_role_menu` VALUES ('146', '6', 'publ:yhl:yxyh');
INSERT INTO `sys_role_menu` VALUES ('147', '6', 'publ:yhl:yxyh:login_tank');
INSERT INTO `sys_role_menu` VALUES ('148', '6', 'publ:yxst');
INSERT INTO `sys_role_menu` VALUES ('149', '6', 'zl');
INSERT INTO `sys_role_menu` VALUES ('150', '6', 'zl:hbsj');
INSERT INTO `sys_role_menu` VALUES ('151', '6', 'zl:hbsj:output_input');
INSERT INTO `sys_role_menu` VALUES ('152', '6', 'zl:hbsj:repertory');
INSERT INTO `sys_role_menu` VALUES ('153', '6', 'zl:hbsj:goods');
INSERT INTO `sys_role_menu` VALUES ('154', '6', 'zl:gwsj');
INSERT INTO `sys_role_menu` VALUES ('155', '6', 'zl:gwsj:world_boss');
INSERT INTO `sys_role_menu` VALUES ('156', '6', 'zl:gwsj:world_boss_fight');
INSERT INTO `sys_role_menu` VALUES ('157', '6', 'zl:gwsj:world_boss_Lifetime');
INSERT INTO `sys_role_menu` VALUES ('158', '6', 'zl:gwsj:jekyll_blame');
INSERT INTO `sys_role_menu` VALUES ('159', '6', 'zl:jnfx');
INSERT INTO `sys_role_menu` VALUES ('160', '6', 'zl:jnfx:jnpm');
INSERT INTO `sys_role_menu` VALUES ('161', '6', 'zl:jnfx:jnpm:test');
INSERT INTO `sys_role_menu` VALUES ('162', '6', 'zl:jnfx:jnpm:test:data');
INSERT INTO `sys_role_menu` VALUES ('320', '3', 'setting');
INSERT INTO `sys_role_menu` VALUES ('321', '3', 'setting:personal');
INSERT INTO `sys_role_menu` VALUES ('322', '3', 'setting:personal:get');
INSERT INTO `sys_role_menu` VALUES ('323', '3', 'setting:personal:update');
INSERT INTO `sys_role_menu` VALUES ('324', '3', 'setting:personal:updatePassword');
INSERT INTO `sys_role_menu` VALUES ('325', '3', 'publ');
INSERT INTO `sys_role_menu` VALUES ('326', '3', 'publ:index:data_index');
INSERT INTO `sys_role_menu` VALUES ('327', '3', 'publ:yuzk');
INSERT INTO `sys_role_menu` VALUES ('328', '3', 'publ:yuzk:operate_info');
INSERT INTO `sys_role_menu` VALUES ('329', '3', 'publ:sssj');
INSERT INTO `sys_role_menu` VALUES ('330', '3', 'publ:sssj:new_register');
INSERT INTO `sys_role_menu` VALUES ('331', '3', 'publ:cysj');
INSERT INTO `sys_role_menu` VALUES ('332', '3', 'publ:cysj:yhl');
INSERT INTO `sys_role_menu` VALUES ('333', '3', 'publ:cysj:yhl:day_total');
INSERT INTO `sys_role_menu` VALUES ('334', '3', 'publ:cysj:yhl:week_away');
INSERT INTO `sys_role_menu` VALUES ('335', '3', 'publ:cysj:yhl:week_away_double');
INSERT INTO `sys_role_menu` VALUES ('336', '3', 'publ:cysj:yhl:month_away');
INSERT INTO `sys_role_menu` VALUES ('337', '3', 'publ:cysj:lcl');
INSERT INTO `sys_role_menu` VALUES ('338', '3', 'publ:cysj:lcl:account_retain');
INSERT INTO `sys_role_menu` VALUES ('339', '3', 'publ:cysj:lcl:role_retain');
INSERT INTO `sys_role_menu` VALUES ('340', '3', 'publ:cysj:lcl:lv_drain');
INSERT INTO `sys_role_menu` VALUES ('341', '3', 'publ:cysj:lcl:device_reg');
INSERT INTO `sys_role_menu` VALUES ('342', '3', 'publ:zxl');
INSERT INTO `sys_role_menu` VALUES ('343', '3', 'publ:zxl:zxfx');
INSERT INTO `sys_role_menu` VALUES ('344', '3', 'publ:zxl:zxfx:onlie_report');
INSERT INTO `sys_role_menu` VALUES ('345', '3', 'publ:zxl:zxfx:online_duration');
INSERT INTO `sys_role_menu` VALUES ('346', '3', 'publ:zxl:zxfx:time_distribution');
INSERT INTO `sys_role_menu` VALUES ('347', '3', 'publ:zxl:zxfx:lv_distribution');
INSERT INTO `sys_role_menu` VALUES ('348', '3', 'publ:yhl');
INSERT INTO `sys_role_menu` VALUES ('349', '3', 'publ:yhl:rzyh');
INSERT INTO `sys_role_menu` VALUES ('350', '3', 'publ:yhl:rzyh:provider');
INSERT INTO `sys_role_menu` VALUES ('351', '3', 'publ:yhl:rzyh:mobile');
INSERT INTO `sys_role_menu` VALUES ('352', '3', 'publ:yhl:rzyh:reg_net_distr');
INSERT INTO `sys_role_menu` VALUES ('353', '3', 'publ:yhl:acu_equality');
INSERT INTO `sys_role_menu` VALUES ('354', '3', 'publ:yhl:yxyh');
INSERT INTO `sys_role_menu` VALUES ('355', '3', 'publ:yhl:yxyh:login_tank');
INSERT INTO `sys_role_menu` VALUES ('356', '3', 'publ:yxst');
INSERT INTO `sys_role_menu` VALUES ('357', '3', 'zl');
INSERT INTO `sys_role_menu` VALUES ('358', '3', 'zl:hbsj');
INSERT INTO `sys_role_menu` VALUES ('359', '3', 'zl:hbsj:output_input');
INSERT INTO `sys_role_menu` VALUES ('360', '3', 'zl:hbsj:repertory');
INSERT INTO `sys_role_menu` VALUES ('361', '3', 'zl:hbsj:goods');
INSERT INTO `sys_role_menu` VALUES ('362', '3', 'zl:gwsj');
INSERT INTO `sys_role_menu` VALUES ('363', '3', 'zl:gwsj:world_boss');
INSERT INTO `sys_role_menu` VALUES ('364', '3', 'zl:gwsj:world_boss_fight');
INSERT INTO `sys_role_menu` VALUES ('365', '3', 'zl:gwsj:world_boss_Lifetime');
INSERT INTO `sys_role_menu` VALUES ('366', '3', 'zl:gwsj:jekyll_blame');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) DEFAULT NULL COMMENT '部门id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '登陆名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `full_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) DEFAULT NULL COMMENT '更新者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remake` varchar(200) DEFAULT NULL COMMENT '备注',
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登陆时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', 'a10fb27fd79ce8191ed4165d0e9791d5', '林冲', '1', '1067165280@qq.com', '0', '1', '1', '2018-01-08 15:39:28', '2018-01-08 15:55:31', '系统初始用户', '2018-01-08 15:39:27');
INSERT INTO `sys_user` VALUES ('2', null, 'op', '260bb5e7fe30bcb71c545b370a1ab6fb', '运营1', '3', '1067165280@qq.com', '0', '1', '1', '2018-01-08 15:55:11', '2018-01-08 15:55:57', '运营1', '2018-01-08 15:55:11');

-- ----------------------------
-- Table structure for sys_user_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_department`;
CREATE TABLE `sys_user_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_department
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('3', '1', '1');
INSERT INTO `sys_user_role` VALUES ('6', '3', '3');
INSERT INTO `sys_user_role` VALUES ('7', '4', '3');
INSERT INTO `sys_user_role` VALUES ('8', '2', '3');
INSERT INTO `sys_user_role` VALUES ('9', '5', '3');
INSERT INTO `sys_user_role` VALUES ('10', '6', '3');
INSERT INTO `sys_user_role` VALUES ('11', '7', '3');
INSERT INTO `sys_user_role` VALUES ('12', '8', '3');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `name-k` varchar(255) DEFAULT NULL,
  `name-v` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
