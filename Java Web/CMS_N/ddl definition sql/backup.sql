/*
Navicat MySQL Data Transfer

Source Server         : 123.207.244.139
Source Server Version : 50173
Source Host           : 123.207.244.139:3306
Source Database       : db_classroom

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-07-11 15:06:21
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
  `extra` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES ('2', 'A110', '60', 'no');
INSERT INTO `classroom` VALUES ('6', 'A124', '60', 'hi');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of day
-- ----------------------------

-- ----------------------------
-- Table structure for hire
-- ----------------------------
DROP TABLE IF EXISTS `hire`;
CREATE TABLE `hire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classroom_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hire_classroom1_idx` (`classroom_id`),
  KEY `fk_hire_teacher1_idx` (`teacher_id`),
  CONSTRAINT `fk_hire_classroom1` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hire_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hire
-- ----------------------------
INSERT INTO `hire` VALUES ('4', '2', '2');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('2', 'ning', '123', 'english', 'professor');

-- ----------------------------
-- Table structure for time
-- ----------------------------
DROP TABLE IF EXISTS `time`;
CREATE TABLE `time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day_id` int(11) NOT NULL,
  `start_time` int(11) NOT NULL DEFAULT '0',
  `duration` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_time_day1_idx` (`day_id`),
  CONSTRAINT `fk_time_day1` FOREIGN KEY (`day_id`) REFERENCES `day` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of time
-- ----------------------------
