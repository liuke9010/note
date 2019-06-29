/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.110.130
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 192.168.110.130:3306
 Source Schema         : cloudnote

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 29/06/2019 19:48:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cn_activity
-- ----------------------------
DROP TABLE IF EXISTS `cn_activity`;
CREATE TABLE `cn_activity`  (
  `cn_activity_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动ID',
  `cn_activity_title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动标题',
  `cn_activity_body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动介绍(html片段)',
  `cn_activity_end_time` bigint(20) NULL DEFAULT NULL COMMENT '活动结束时间',
  PRIMARY KEY (`cn_activity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_activity_status
-- ----------------------------
DROP TABLE IF EXISTS `cn_activity_status`;
CREATE TABLE `cn_activity_status`  (
  `cn_activity_status_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动状态ID',
  `cn_activity_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动ID',
  `cn_activity_status_code` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动状态Code',
  `cn_activity_status_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动状态名称',
  PRIMARY KEY (`cn_activity_status_id`) USING BTREE,
  INDEX `FK_Reference_9`(`cn_activity_id`) USING BTREE,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`cn_activity_id`) REFERENCES `cn_activity` (`cn_activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_note
-- ----------------------------
DROP TABLE IF EXISTS `cn_note`;
CREATE TABLE `cn_note`  (
  `cn_note_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '笔记ID',
  `cn_notebook_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记本ID',
  `cn_user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `cn_note_status_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记状态ID:备用',
  `cn_note_type_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记本类型ID：备用',
  `cn_note_title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记标题',
  `cn_note_body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '笔记内容',
  `cn_note_create_time` bigint(20) NULL DEFAULT NULL COMMENT '笔记创建时间',
  `cn_note_last_modify_time` bigint(20) NULL DEFAULT NULL COMMENT '笔记最近修改时间',
  PRIMARY KEY (`cn_note_id`) USING BTREE,
  INDEX `FK_Reference_2`(`cn_notebook_id`) USING BTREE,
  INDEX `FK_Reference_3`(`cn_user_id`) USING BTREE,
  INDEX `FK_Reference_7`(`cn_note_status_id`) USING BTREE,
  INDEX `FK_Reference_8`(`cn_note_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_note_activity
-- ----------------------------
DROP TABLE IF EXISTS `cn_note_activity`;
CREATE TABLE `cn_note_activity`  (
  `cn_note_activity_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投稿ID',
  `cn_activity_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动ID',
  `cn_note_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记ID',
  `cn_note_activity_up` int(11) NULL DEFAULT NULL COMMENT '投稿赞:增加数',
  `cn_note_activity_down` int(11) NULL DEFAULT NULL COMMENT '投稿踩:增加数',
  `cn_note_activity_title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cn_note_activity_body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`cn_note_activity_id`) USING BTREE,
  INDEX `FK_Reference_4`(`cn_activity_id`) USING BTREE,
  INDEX `FK_Reference_5`(`cn_note_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_note_status
-- ----------------------------
DROP TABLE IF EXISTS `cn_note_status`;
CREATE TABLE `cn_note_status`  (
  `cn_note_status_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '笔记状态ID',
  `cn_note_status_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记状态Code',
  `cn_note_status_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记状态名字',
  PRIMARY KEY (`cn_note_status_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_note_type
-- ----------------------------
DROP TABLE IF EXISTS `cn_note_type`;
CREATE TABLE `cn_note_type`  (
  `cn_note_type_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '笔记本类型ID',
  `cn_note_type_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记本类型Code',
  `cn_note_type_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记本类型名称',
  `cn_note_type_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '笔记本类型说明',
  PRIMARY KEY (`cn_note_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_notebook
-- ----------------------------
DROP TABLE IF EXISTS `cn_notebook`;
CREATE TABLE `cn_notebook`  (
  `cn_notebook_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '笔记本ID',
  `cn_user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `cn_notebook_type_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记本类型ID',
  `cn_notebook_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记本名',
  `cn_notebook_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '笔记本说明',
  `cn_notebook_createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`cn_notebook_id`) USING BTREE,
  INDEX `FK_Note_User_Reference`(`cn_user_id`) USING BTREE,
  INDEX `FK_Reference_6`(`cn_notebook_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_notebook_type
-- ----------------------------
DROP TABLE IF EXISTS `cn_notebook_type`;
CREATE TABLE `cn_notebook_type`  (
  `cn_notebook_type_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '笔记本类型ID',
  `cn_notebook_type_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记本类型Code',
  `cn_notebook_type_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记本类型名称',
  `cn_notebook_type_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '笔记本类型说明',
  PRIMARY KEY (`cn_notebook_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_share
-- ----------------------------
DROP TABLE IF EXISTS `cn_share`;
CREATE TABLE `cn_share`  (
  `cn_share_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '共享ID',
  `cn_share_title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '共享标题',
  `cn_share_body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '共享内容',
  `cn_note_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记id',
  PRIMARY KEY (`cn_share_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_user
-- ----------------------------
DROP TABLE IF EXISTS `cn_user`;
CREATE TABLE `cn_user`  (
  `cn_user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `cn_user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `cn_user_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `cn_user_token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '令牌',
  `cn_user_nick` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`cn_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
