/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : elective

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-14 16:00:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrators
-- ----------------------------
DROP TABLE IF EXISTS `administrators`;
CREATE TABLE `administrators` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(30) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administrators
-- ----------------------------
INSERT INTO `administrators` VALUES ('11', '叶雨泽儿子', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('13', '威威', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('14', '张三', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('18', '邱文生', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('20', '雨泽', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('21', '叶', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('22', '雨', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('23', '叶雨泽', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('24', '雨泽儿子', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('27', '邱文生', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('28', '文生', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('29', '垃圾文生', '1bbd886460827015e5d605ed44252251');
INSERT INTO `administrators` VALUES ('30', '菜鸡文生', '1bbd886460827015e5d605ed44252251');

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `course_id` int(10) NOT NULL AUTO_INCREMENT,
  `plan_id` int(10) NOT NULL,
  `course_name` varchar(30) NOT NULL,
  `class_time` int(2) NOT NULL,
  `class_place` varchar(256) DEFAULT NULL,
  `total` int(10) NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `index_plan_id` (`plan_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`plan_id`) REFERENCES `elective_plans` (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('5', '13', '叶雨泽哈哈哈哈哈哈哈哈哈', '2', '', '2');
INSERT INTO `courses` VALUES ('22', '13', '22号课程', '3', '', '22222');
INSERT INTO `courses` VALUES ('23', '13', '2222', '3', '', '2222');
INSERT INTO `courses` VALUES ('24', '13', '22222', '3', '', '2222');
INSERT INTO `courses` VALUES ('25', '13', '22222', '4', '', '2222');
INSERT INTO `courses` VALUES ('26', '13', '叶雨泽', '4', '', '2222');
INSERT INTO `courses` VALUES ('27', '13', '2222', '3', '', '2222');
INSERT INTO `courses` VALUES ('28', '13', '2222', '4', '', '22222');
INSERT INTO `courses` VALUES ('29', '13', '2222', '3', '', '22222');
INSERT INTO `courses` VALUES ('30', '13', '22222', '7', '', '22222');
INSERT INTO `courses` VALUES ('31', '13', '这是新增的', '6', '', '555');
INSERT INTO `courses` VALUES ('32', '1017', '1测课程', '3', '', '1');
INSERT INTO `courses` VALUES ('33', '201756', '622选课计划', '7', '', '1');
INSERT INTO `courses` VALUES ('34', '20178', '雨泽爸爸', '4', '', '1');
INSERT INTO `courses` VALUES ('35', '2018', '雨泽666', '9', '', '1');

-- ----------------------------
-- Table structure for course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher`;
CREATE TABLE `course_teacher` (
  `course_id` int(10) NOT NULL,
  `teac_id` int(10) NOT NULL,
  PRIMARY KEY (`course_id`,`teac_id`),
  KEY `FK_Reference_3` (`teac_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`teac_id`) REFERENCES `teachers` (`teac_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_teacher
-- ----------------------------
INSERT INTO `course_teacher` VALUES ('5', '12');
INSERT INTO `course_teacher` VALUES ('32', '12');
INSERT INTO `course_teacher` VALUES ('33', '12');
INSERT INTO `course_teacher` VALUES ('34', '12');
INSERT INTO `course_teacher` VALUES ('35', '12');
INSERT INTO `course_teacher` VALUES ('5', '13');

-- ----------------------------
-- Table structure for elective_plans
-- ----------------------------
DROP TABLE IF EXISTS `elective_plans`;
CREATE TABLE `elective_plans` (
  `plan_id` int(10) NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(30) NOT NULL,
  `delay_drop_time` int(5) DEFAULT '60',
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=201757 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elective_plans
-- ----------------------------
INSERT INTO `elective_plans` VALUES ('11', 'a', '60');
INSERT INTO `elective_plans` VALUES ('13', '雨', '60');
INSERT INTO `elective_plans` VALUES ('15', '泽', '60');
INSERT INTO `elective_plans` VALUES ('16', 'a', '60');
INSERT INTO `elective_plans` VALUES ('1017', '选课系统1测', '5');
INSERT INTO `elective_plans` VALUES ('2014', '2014年下', '60');
INSERT INTO `elective_plans` VALUES ('2015', '2014年上', '60');
INSERT INTO `elective_plans` VALUES ('2018', '6666', '60');
INSERT INTO `elective_plans` VALUES ('20178', '雨泽爸爸选课计划', '60');
INSERT INTO `elective_plans` VALUES ('201756', '622选课计划', '60');

-- ----------------------------
-- Table structure for elective_records
-- ----------------------------
DROP TABLE IF EXISTS `elective_records`;
CREATE TABLE `elective_records` (
  `stu_id` int(10) NOT NULL,
  `course_id` int(10) NOT NULL,
  PRIMARY KEY (`stu_id`,`course_id`),
  KEY `index_stu_id` (`stu_id`),
  KEY `FK_Reference_6` (`course_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`stu_id`) REFERENCES `students` (`stu_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elective_records
-- ----------------------------
INSERT INTO `elective_records` VALUES ('11', '5');
INSERT INTO `elective_records` VALUES ('11', '22');
INSERT INTO `elective_records` VALUES ('11', '25');
INSERT INTO `elective_records` VALUES ('11', '32');
INSERT INTO `elective_records` VALUES ('12', '34');

-- ----------------------------
-- Table structure for elective_times
-- ----------------------------
DROP TABLE IF EXISTS `elective_times`;
CREATE TABLE `elective_times` (
  `time_id` int(10) NOT NULL AUTO_INCREMENT,
  `plan_id` int(10) NOT NULL,
  `register_date` date NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `max` int(11) NOT NULL,
  PRIMARY KEY (`time_id`),
  KEY `FK_Reference_1` (`plan_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`plan_id`) REFERENCES `elective_plans` (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elective_times
-- ----------------------------
INSERT INTO `elective_times` VALUES ('2', '11', '2017-04-19', '2017-04-19 10:39:45', '2017-04-19 10:39:45', '3');
INSERT INTO `elective_times` VALUES ('3', '11', '2017-04-19', '2017-04-19 10:42:01', '2017-04-19 10:42:01', '3');
INSERT INTO `elective_times` VALUES ('4', '11', '2017-04-19', '2017-04-19 10:42:07', '2017-04-19 10:42:07', '3');
INSERT INTO `elective_times` VALUES ('5', '11', '2017-04-19', '2017-04-19 10:42:10', '2017-04-19 10:42:10', '3');
INSERT INTO `elective_times` VALUES ('9', '2014', '2017-04-28', '2017-04-28 22:11:00', '2017-04-28 22:11:00', '3');
INSERT INTO `elective_times` VALUES ('10', '2014', '2017-04-28', '2017-04-28 22:11:00', '2017-04-28 22:11:00', '3');
INSERT INTO `elective_times` VALUES ('14', '2015', '2017-04-29', '2017-04-29 22:47:00', '2017-04-29 22:47:00', '3');
INSERT INTO `elective_times` VALUES ('15', '2015', '2017-04-29', '2017-04-29 22:48:00', '2017-04-29 22:48:00', '3');
INSERT INTO `elective_times` VALUES ('43', '13', '2017-05-05', '2017-05-05 10:57:00', '2017-05-06 10:57:00', '3');
INSERT INTO `elective_times` VALUES ('46', '201756', '2017-05-06', '2017-05-06 22:10:00', '2017-05-07 22:01:00', '1');
INSERT INTO `elective_times` VALUES ('48', '20178', '2017-05-07', '2017-05-07 09:35:00', '2017-05-07 15:35:00', '1');
INSERT INTO `elective_times` VALUES ('50', '2018', '2017-05-07', '2017-05-07 10:40:00', '2017-05-08 10:41:00', '1');
INSERT INTO `elective_times` VALUES ('51', '1017', '2013-09-01', '2017-05-06 09:30:00', '2017-05-10 16:00:00', '3');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `stu_id` int(10) NOT NULL,
  `stu_name` varchar(30) NOT NULL,
  `password` varchar(128) NOT NULL,
  `register_date` date NOT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('11', '梁', '1bbd886460827015e5d605ed44252251', '2013-04-11');
INSERT INTO `students` VALUES ('12', '叶', '1bbd886460827015e5d605ed44252251', '2017-04-28');
INSERT INTO `students` VALUES ('13', '雨', '1bbd886460827015e5d605ed44252251', '2017-04-15');
INSERT INTO `students` VALUES ('14', '泽', '1bbd886460827015e5d605ed44252251', '2017-04-15');
INSERT INTO `students` VALUES ('16', '傻逼', '1bbd886460827015e5d605ed44252251', '2017-04-28');
INSERT INTO `students` VALUES ('4444', '雨泽', '1bbd886460827015e5d605ed44252251', '2017-04-27');
INSERT INTO `students` VALUES ('8888', '管理员选课账号', '0ac4b128a442631229d0983799ad7213', '2013-09-01');
INSERT INTO `students` VALUES ('22222', '急急急', '1bbd886460827015e5d605ed44252251', '2017-04-27');

-- ----------------------------
-- Table structure for teachers
-- ----------------------------
DROP TABLE IF EXISTS `teachers`;
CREATE TABLE `teachers` (
  `teac_id` int(10) NOT NULL,
  `teac_name` varchar(30) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`teac_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teachers
-- ----------------------------
INSERT INTO `teachers` VALUES ('12', '雨泽泽', '1bbd886460827015e5d605ed44252251');
INSERT INTO `teachers` VALUES ('13', '李三', '1bbd886460827015e5d605ed44252251');
INSERT INTO `teachers` VALUES ('15', '小梁梁', '1bbd886460827015e5d605ed44252251');
INSERT INTO `teachers` VALUES ('110', '小贱贱', '1bbd886460827015e5d605ed44252251');
