/*
Navicat MySQL Data Transfer

Source Server         : ning
Source Server Version : 50173
Source Host           : 123.207.244.139:3306
Source Database       : db_classroom

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-19 17:58:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(8) NOT NULL DEFAULT '',
  `capacity` int(11) NOT NULL DEFAULT '0',
  `extra` varchar(45) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES ('2', 'A110', '60', '这是一间多媒体教室，有话筒，音响等设备');
INSERT INTO `classroom` VALUES ('6', 'A124', '40', '普通教室');
INSERT INTO `classroom` VALUES ('7', 'A112', '50', 'test room');

-- ----------------------------
-- Table structure for day
-- ----------------------------
DROP TABLE IF EXISTS `day`;
CREATE TABLE `day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hire_id` int(11) NOT NULL,
  `day` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_day_hire1_idx` (`hire_id`),
  CONSTRAINT `fk_day_hire1` FOREIGN KEY (`hire_id`) REFERENCES `hire` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of day
-- ----------------------------
INSERT INTO `day` VALUES ('2', '4', '2017-07-11');

-- ----------------------------
-- Table structure for hire
-- ----------------------------
DROP TABLE IF EXISTS `hire`;
CREATE TABLE `hire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) NOT NULL,
  `classroom_id` int(11) NOT NULL,
  `time_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hire_classroom1_idx` (`classroom_id`),
  KEY `fk_hire_teacher1_idx` (`teacher_id`),
  KEY `fk_hire_time1` (`time_id`),
  CONSTRAINT `fk_hire_classroom1` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hire_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hire_time1` FOREIGN KEY (`time_id`) REFERENCES `time` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hire
-- ----------------------------
INSERT INTO `hire` VALUES ('4', '2', '2', '2');
INSERT INTO `hire` VALUES ('20', '2', '6', '13');
INSERT INTO `hire` VALUES ('24', '2', '2', '17');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(8) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL,
  `course` varchar(45) NOT NULL DEFAULT '',
  `career_title` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`,`name`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('2', 'ning', '123', 'java', 'professor');
INSERT INTO `teacher` VALUES ('5', 'li', '123', 'MySQL', 'professor');

-- ----------------------------
-- Table structure for time
-- ----------------------------
DROP TABLE IF EXISTS `time`;
CREATE TABLE `time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` date NOT NULL,
  `time` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_time_day1_idx` (`day`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of time
-- ----------------------------
INSERT INTO `time` VALUES ('2', '2017-12-20', '[\"2\",\"5\"]');
INSERT INTO `time` VALUES ('13', '2017-12-19', '[\"7\",\"11\", \"15\"]');
INSERT INTO `time` VALUES ('14', '2017-12-19', '[\"6\",\"7\",\"8\",\"9\",\"10\"]');
INSERT INTO `time` VALUES ('15', '2017-12-19', '[\"18\",\"19\"]');
INSERT INTO `time` VALUES ('16', '2017-12-21', '[\"6\",\"7\",\"8\"]');
INSERT INTO `time` VALUES ('17', '2017-12-21', '[\"9\"]');
