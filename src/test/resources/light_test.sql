/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80003
Source Host           : 127.0.0.1:3306
Source Database       : light_test

Target Server Type    : MYSQL
Target Server Version : 80003
File Encoding         : 65001

Date: 2018-12-29 15:57:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户id',
  `nodeid` int(11) NOT NULL COMMENT '节点id',
  `authority` int(11) NOT NULL COMMENT '权限（4位二进制的十进制值）',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `realname` varchar(255) NOT NULL COMMENT '姓名',
  `duty` varchar(255) DEFAULT NULL COMMENT '职务',
  `company` varchar(255) DEFAULT NULL COMMENT '单位',
  `phone` varchar(255) NOT NULL COMMENT '联系方式',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(255) NOT NULL DEFAULT '0' COMMENT '是否已经被删除（0：未被删除；1已被删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for devicedata
-- ----------------------------
DROP TABLE IF EXISTS `devicedata`;
CREATE TABLE `devicedata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(255) NOT NULL COMMENT '发送源',
  `target` varchar(255) NOT NULL COMMENT '目标',
  `data_type` int(11) NOT NULL COMMENT '数据类型（0：上报且发送改变的数据、1：下发的控制指令）',
  `content` varchar(255) NOT NULL COMMENT '数据内容',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `result` varchar(255) NOT NULL COMMENT '数据结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for light
-- ----------------------------
DROP TABLE IF EXISTS `light`;
CREATE TABLE `light` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attr_belonging` varchar(255) NOT NULL COMMENT '归属名称',
  `attr_num` varchar(255) NOT NULL COMMENT '灯具编号',
  `attr_phone` varchar(255) NOT NULL COMMENT '手机号',
  `attr_deadtime` datetime NOT NULL COMMENT '过期时间',
  `attr_nodeid` int(11) NOT NULL COMMENT '线路节点id',
  `attr_longitude` double(11,0) NOT NULL COMMENT '经度',
  `attr_latitude` double(11,0) NOT NULL COMMENT '纬度',
  `gps_longitude` double(11,0) DEFAULT NULL COMMENT 'GPS上报经度',
  `gps_latitude` double(11,0) DEFAULT NULL COMMENT 'GPS上报纬度',
  `gps_elevation` int(11) DEFAULT NULL COMMENT 'GPS上报海拔',
  `gps_lock` tinyint(4) DEFAULT NULL COMMENT '锁定指示（0：未锁定；1：已锁定）',
  `4g_signal` varchar(255) DEFAULT NULL COMMENT '4G强度',
  `4g_iccid` varchar(255) DEFAULT NULL COMMENT 'ICCID码',
  `total_electricity` int(11) DEFAULT NULL COMMENT '总电流(单位毫安）',
  `lamps_electricity` int(11) DEFAULT NULL COMMENT '灯具电流',
  `total_voltage` int(11) DEFAULT NULL COMMENT '总电压(单位mv）',
  `lamps_voltage` int(11) DEFAULT NULL COMMENT '灯具电压',
  `temperature` int(11) DEFAULT NULL COMMENT '温度',
  `fault_indicate` int(11) DEFAULT NULL COMMENT '故障指示',
  `day_indicate` int(11) DEFAULT NULL COMMENT '白天夜间指示',
  `heartfrequency` int(11) DEFAULT NULL COMMENT '心跳频率上报值',
  `lamp_day_frequency` int(11) DEFAULT NULL COMMENT '白天灯具频率上报值',
  `lamp_night_frequency` int(11) DEFAULT NULL COMMENT '夜间灯具频率上报值',
  `lamp_day_state` int(11) DEFAULT NULL COMMENT '白天灯具状态上报值',
  `lamp_night_state` int(11) DEFAULT NULL COMMENT '夜间灯具状态上报值',
  `lamp_buzzer_day` int(11) DEFAULT NULL COMMENT '白天蜂鸣器状态上报值',
  `lamp_buzzer_night` int(11) DEFAULT NULL COMMENT '夜间蜂鸣器状态上报值',
  `set_day_frequency` int(11) DEFAULT NULL COMMENT '白天心跳设定值',
  `set_night_frequency` int(11) DEFAULT NULL COMMENT '夜间心跳设定值',
  `set_day_state` int(11) DEFAULT NULL COMMENT '白天灯具状态设定状态',
  `set_night_state` int(11) DEFAULT NULL COMMENT '夜间灯具状态设定值',
  `set_day_buzzer` int(11) DEFAULT NULL COMMENT '白天蜂鸣器状态设定值',
  `set_night_buzzer` int(11) DEFAULT NULL COMMENT '夜间蜂鸣器状态设定值',
  `set_heartfrequency` int(11) DEFAULT NULL COMMENT '心跳发送频率设置值',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `heartupdatetime` datetime DEFAULT NULL COMMENT '心跳更新时间',
  `updatetime` datetime DEFAULT NULL COMMENT '灯具修改时间',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否被删除（0：未被删除；1：已经被删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_equipment
-- ----------------------------
DROP TABLE IF EXISTS `log_equipment`;
CREATE TABLE `log_equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(255) NOT NULL COMMENT '日志编号',
  `content` varchar(255) NOT NULL COMMENT '日志内容',
  `lampnum` varchar(255) NOT NULL COMMENT '灯具编号',
  `lampname` varchar(255) NOT NULL COMMENT '灯具名称',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `result` varchar(255) NOT NULL COMMENT '操作结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_sys
-- ----------------------------
DROP TABLE IF EXISTS `log_sys`;
CREATE TABLE `log_sys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(255) NOT NULL COMMENT '日志编号',
  `content` varchar(255) NOT NULL COMMENT '日志内容',
  `ipaddress` varchar(255) NOT NULL COMMENT 'IP地址',
  `operator` varchar(255) NOT NULL COMMENT '操作人',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `result` varchar(255) NOT NULL COMMENT '操作结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromid` int(11) NOT NULL COMMENT '发送人id',
  `toid` int(11) NOT NULL COMMENT '接收人id',
  `content` varchar(255) NOT NULL COMMENT '发送的消息',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nodename` varchar(255) NOT NULL COMMENT '节点名称',
  `fid` int(11) NOT NULL COMMENT '父节点id',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否被删除（0：未；1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for node_contact
-- ----------------------------
DROP TABLE IF EXISTS `node_contact`;
CREATE TABLE `node_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactid` int(11) NOT NULL COMMENT '联系人id',
  `nodeid` int(11) NOT NULL COMMENT '节点id',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `userpass` varchar(255) NOT NULL COMMENT '密码',
  `realname` varchar(255) NOT NULL COMMENT '姓名',
  `duty` varchar(255) DEFAULT NULL COMMENT '职务',
  `company` varchar(255) DEFAULT NULL COMMENT '单位',
  `phone` varchar(255) NOT NULL COMMENT '联系方式',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否被删除（0：未被删除；1：已被删除）',
  `is_admin` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否是超级管理员（0：不是；1：是）',
  `mesg_authority` int(11) NOT NULL DEFAULT '3' COMMENT '消息权限（0:都不能；1:接收；2:可发送；3：都可以）',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
