/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : challenge

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2024-07-14 10:59:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrators
-- ----------------------------
DROP TABLE IF EXISTS `administrators`;
CREATE TABLE `administrators` (
  `administrator_id` int(11) NOT NULL,
  `administrator_name` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`administrator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of administrators
-- ----------------------------
INSERT INTO `administrators` VALUES ('1', null, '12345');

-- ----------------------------
-- Table structure for challengeinfo
-- ----------------------------
DROP TABLE IF EXISTS `challengeinfo`;
CREATE TABLE `challengeinfo` (
  `challenge_id` int(11) NOT NULL,
  `challenge_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `host` varchar(20) CHARACTER SET utf8 NOT NULL,
  `organizer` varchar(20) CHARACTER SET utf8 NOT NULL,
  `whether_zudui` tinyint(1) NOT NULL,
  `description` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`challenge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of challengeinfo
-- ----------------------------
INSERT INTO `challengeinfo` VALUES ('1', '数学挑战赛', 'AB大学', 'ABCwdssa', '1', '一个具有挑战性的数学大赛');
INSERT INTO `challengeinfo` VALUES ('2', '科学挑战赛', 'B大学', 'Y系', '0', '一个具有挑战性的科学竞赛');
INSERT INTO `challengeinfo` VALUES ('3', '编程挑战赛', 'C大学', '计算机系', '1', '一个具有挑战性的编程竞赛');
INSERT INTO `challengeinfo` VALUES ('4', '物理挑战赛', 'test大学', '物理系', '1', '一个具有挑战性的物理竞赛');
INSERT INTO `challengeinfo` VALUES ('5', '化学挑战赛', 'E大学', '化学系', '1', '一个具有挑战性的化学竞赛');
INSERT INTO `challengeinfo` VALUES ('6', '生物挑战赛', 'F大学', '生物系', '0', '一个具有挑战性的生物竞赛');
INSERT INTO `challengeinfo` VALUES ('7', '工程挑战赛', 'G大学', '工程系', '1', '一个具有挑战性的工程竞赛');
INSERT INTO `challengeinfo` VALUES ('8', '机器人挑战赛', 'HZCU', '机器人系', '1', '一个具有挑战性的机器人竞赛');
INSERT INTO `challengeinfo` VALUES ('9', '数据科学挑战赛', 'I大学', '数据科学系', '1', '一个具有挑战性的数据科学竞赛');
INSERT INTO `challengeinfo` VALUES ('10', '人工智能挑战赛', 'J大学', '人工智能系', '0', '一个具有挑战性的人工智能竞赛');
INSERT INTO `challengeinfo` VALUES ('11', 'Challenge A', 'Host A', 'Organizer A', '1', 'Description A');
INSERT INTO `challengeinfo` VALUES ('12', 'Challenge B', 'Host B', 'Organizer B', '0', 'Description B');
INSERT INTO `challengeinfo` VALUES ('13', 'Challenge C', 'Host C', 'Organizer C', '1', 'Description C');

-- ----------------------------
-- Table structure for challenge_arrangementinfo
-- ----------------------------
DROP TABLE IF EXISTS `challenge_arrangementinfo`;
CREATE TABLE `challenge_arrangementinfo` (
  `competition_id` int(11) NOT NULL,
  `challenge_id` int(11) DEFAULT NULL,
  `competition_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `held_time` date NOT NULL,
  `held_address` varchar(20) NOT NULL,
  PRIMARY KEY (`competition_id`),
  KEY `FK_belong` (`challenge_id`),
  CONSTRAINT `FK_belong` FOREIGN KEY (`challenge_id`) REFERENCES `challengeinfo` (`challenge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of challenge_arrangementinfo
-- ----------------------------
INSERT INTO `challenge_arrangementinfo` VALUES ('1', '1', '2023数学竞赛', '2023-08-28', 'CCCCCsda');
INSERT INTO `challenge_arrangementinfo` VALUES ('2', '2', '2023科学竞赛', '2023-06-15', 'B');
INSERT INTO `challenge_arrangementinfo` VALUES ('3', '3', '2023编程竞赛', '2023-07-20', 'C');
INSERT INTO `challenge_arrangementinfo` VALUES ('4', '4', '2023物理竞赛', '2023-08-25', 'D');
INSERT INTO `challenge_arrangementinfo` VALUES ('5', '5', '2023化学竞赛', '2023-09-30', 'E');
INSERT INTO `challenge_arrangementinfo` VALUES ('6', '6', '2023生物竞赛', '2023-10-05', 'F');
INSERT INTO `challenge_arrangementinfo` VALUES ('7', '7', '2023工程竞赛', '2023-11-10', 'G');
INSERT INTO `challenge_arrangementinfo` VALUES ('8', '8', '2023机器人竞赛', '2023-12-15', 'H');
INSERT INTO `challenge_arrangementinfo` VALUES ('9', '9', '2022数据科学竞赛', '2022-01-20', 'HZUC');
INSERT INTO `challenge_arrangementinfo` VALUES ('10', '10', '2023人工智能竞赛', '2023-02-25', 'J');
INSERT INTO `challenge_arrangementinfo` VALUES ('11', '11', 'Competition A1', '2020-06-15', 'Address A1');
INSERT INTO `challenge_arrangementinfo` VALUES ('12', '12', 'Competition B1', '2019-08-10', 'Address B1');
INSERT INTO `challenge_arrangementinfo` VALUES ('13', '11', 'Competition A2', '2021-09-20', 'Address A2');
INSERT INTO `challenge_arrangementinfo` VALUES ('14', '1', '2022数学竞赛', '2022-01-01', 'a');
INSERT INTO `challenge_arrangementinfo` VALUES ('15', '1', '2021数学竞赛', '2021-01-01', 'b');
INSERT INTO `challenge_arrangementinfo` VALUES ('16', '1', '2018数学竞赛', '2019-01-01', '123');

-- ----------------------------
-- Table structure for individualteacher_awardinfo
-- ----------------------------
DROP TABLE IF EXISTS `individualteacher_awardinfo`;
CREATE TABLE `individualteacher_awardinfo` (
  `teacher_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `competition_id` int(11) NOT NULL,
  `award_level` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`teacher_id`,`student_id`,`competition_id`),
  KEY `FK_IndividualTeacher_AwardInfo2` (`student_id`),
  KEY `FK_IndividualTeacher_AwardInfo3` (`competition_id`),
  CONSTRAINT `FK_IndividualTeacher_AwardInfo` FOREIGN KEY (`teacher_id`) REFERENCES `teacherinfo` (`teacher_id`),
  CONSTRAINT `FK_IndividualTeacher_AwardInfo2` FOREIGN KEY (`student_id`) REFERENCES `studentinfo` (`student_id`),
  CONSTRAINT `FK_IndividualTeacher_AwardInfo3` FOREIGN KEY (`competition_id`) REFERENCES `challenge_arrangementinfo` (`competition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of individualteacher_awardinfo
-- ----------------------------
INSERT INTO `individualteacher_awardinfo` VALUES ('1', '1', '1', '一等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('1', '1', '2', '一等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('1', '1', '3', '一等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('1', '1', '4', '特等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('1', '3', '2', '一等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('1', '5', '1', '二等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('1', '5', '2', '二等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('2', '1', '14', '一等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('2', '2', '2', '二等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('2', '4', '5', '三等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('3', '1', '15', '二等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('3', '3', '3', '三等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('4', '1', '16', '二等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('4', '4', '4', '优秀奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('5', '5', '5', '一等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('6', '6', '6', '二等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('7', '7', '7', '三等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('8', '8', '8', '优秀奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('9', '9', '9', '一等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('11', '11', '11', '一等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('11', '13', '13', '三等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('12', '12', '12', '二等奖');
INSERT INTO `individualteacher_awardinfo` VALUES ('123', '1', '1', '特等奖');

-- ----------------------------
-- Table structure for studentinfo
-- ----------------------------
DROP TABLE IF EXISTS `studentinfo`;
CREATE TABLE `studentinfo` (
  `student_id` int(11) NOT NULL,
  `student_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `Enrollment_year` date NOT NULL,
  `class` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `grade` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `major` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `mobilephone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `qq` char(20) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of studentinfo
-- ----------------------------
INSERT INTO `studentinfo` VALUES ('1', '张三', '2023-11-08', '1班', 'B', '数学', '12154198', 'zhangsan@example.com', '1518915', '1');
INSERT INTO `studentinfo` VALUES ('2', '阿三', '2022-09-18', '1班', 'A', '数学', '12154556', 'lisi@example.com', '654321', '3');
INSERT INTO `studentinfo` VALUES ('3', '代六', '2023-11-15', '1班', 'B', '数学', '11223344551215', 'wangwu@example.com', '789012', '3');
INSERT INTO `studentinfo` VALUES ('4', '赵六', '2023-11-25', '1班', 'D', '物理', '2233445566', 'zhaoliu@example.com', '345678', '4');
INSERT INTO `studentinfo` VALUES ('5', '孙七', '2023-06-21', '5班', 'E', '化学', '3344556677', 'sunqi@example.com', '901234', '5');
INSERT INTO `studentinfo` VALUES ('6', '周八', '2023-01-26', '6班', 'F', '生物', '4455667788', 'zhouba@example.com', '567890', '6');
INSERT INTO `studentinfo` VALUES ('7', '吴九', '2022-09-30', '7班', 'G', '工程', '5566778899', 'wuj@example.com', '234567', '7');
INSERT INTO `studentinfo` VALUES ('8', '郑十', '2023-06-16', '8班', 'H', '机器人', '6677889900', 'zhengshi@example.com', '890123', '8');
INSERT INTO `studentinfo` VALUES ('9', '王十一', '2023-09-07', '9班', 'I', '数据科学', '7788990011', 'wangshiyi@example.co', '456789', '9');
INSERT INTO `studentinfo` VALUES ('11', 'A', '2017-09-01', '2班', 'Grade A', '数学', '1234567890', 'studenta@example.com', '111111', '');
INSERT INTO `studentinfo` VALUES ('12', 'B', '2018-09-01', '1班', 'Grade B', '数学', '1234567891', 'studentb@example.com', '222222', '');
INSERT INTO `studentinfo` VALUES ('13', 'C', '2019-09-01', '1班', 'Grade C', '数学', '1234567892', 'studentc@example.com', '333333', '');

-- ----------------------------
-- Table structure for teacherinfo
-- ----------------------------
DROP TABLE IF EXISTS `teacherinfo`;
CREATE TABLE `teacherinfo` (
  `teacher_id` int(11) NOT NULL,
  `teacher_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `mobilephone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `department` varchar(20) CHARACTER SET utf8 NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of teacherinfo
-- ----------------------------
INSERT INTO `teacherinfo` VALUES ('1', '王教授', '21313243432342', '数学系', 'wang@example.com');
INSERT INTO `teacherinfo` VALUES ('2', '李教授', '21111591116', '数学系', 'li@example.com');
INSERT INTO `teacherinfo` VALUES ('3', '张教授', '7778889999', '计算机系', 'zhang@example.com');
INSERT INTO `teacherinfo` VALUES ('4', '赵教授', '0001112222', '物理系', 'zhao@example.com');
INSERT INTO `teacherinfo` VALUES ('5', '孙教授', '3334445555', '化学系', 'sun@example.com');
INSERT INTO `teacherinfo` VALUES ('6', '代教授', '6667778888', '计算机系', 'zhou@example.com');
INSERT INTO `teacherinfo` VALUES ('7', '吴教授', '9990001111', '工程系', 'wu@example.com');
INSERT INTO `teacherinfo` VALUES ('8', '郑教授', '2223334444', '机器人系', 'zheng@example.com');
INSERT INTO `teacherinfo` VALUES ('9', '王助教', '5556667777', '数据科学系', 'wangz@example.com');
INSERT INTO `teacherinfo` VALUES ('10', '刘助教', '8889990000', '人工智能系', 'liu@example.com');
INSERT INTO `teacherinfo` VALUES ('11', 'A', '9876543210', 'Department A', 'teachera@example.com');
INSERT INTO `teacherinfo` VALUES ('12', 'B', '9876543211', 'Department B', 'teacherb@example.com');
INSERT INTO `teacherinfo` VALUES ('13', 'C', '9876543212', 'Department C', 'teacherc@example.com');
INSERT INTO `teacherinfo` VALUES ('123', 'eason', '', 'cs', '');

-- ----------------------------
-- Table structure for teaminfo
-- ----------------------------
DROP TABLE IF EXISTS `teaminfo`;
CREATE TABLE `teaminfo` (
  `team_id` int(11) NOT NULL,
  `competition_id` int(11) DEFAULT NULL,
  `team_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `team_name_eng` varchar(20) CHARACTER SET utf8 NOT NULL,
  `create_time` date NOT NULL,
  `member_counts` varchar(20) CHARACTER SET utf8 NOT NULL,
  `remark` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`team_id`),
  KEY `FK_参加` (`competition_id`),
  CONSTRAINT `FK_参加` FOREIGN KEY (`competition_id`) REFERENCES `challenge_arrangementinfo` (`competition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of teaminfo
-- ----------------------------
INSERT INTO `teaminfo` VALUES ('1', '1', '团队甲', 'Team Alpha', '2023-05-01', '5', '顶尖团队');
INSERT INTO `teaminfo` VALUES ('2', '2', '团队乙', 'Team Beta', '2023-06-01', '4', '优秀团队');
INSERT INTO `teaminfo` VALUES ('3', '3', '团队丙', 'Team Gamma', '2023-07-01', '6', '强力团队');
INSERT INTO `teaminfo` VALUES ('4', '4', '团队丁', 'Team Delta', '2023-08-01', '3', '活力团队');
INSERT INTO `teaminfo` VALUES ('5', '5', '团队戊', 'Team Epsilon', '2023-09-01', '5', '出色团队');
INSERT INTO `teaminfo` VALUES ('6', '6', '团队己', 'Team Zeta', '2023-10-01', '4', '精英团队');
INSERT INTO `teaminfo` VALUES ('7', '7', '团队庚', 'Team Eta', '2023-11-01', '6', '智慧团队');
INSERT INTO `teaminfo` VALUES ('8', '8', '团队W', 'Team Theta', '2023-12-01', '3', '创新团队');
INSERT INTO `teaminfo` VALUES ('9', '9', '团队壬', 'Team Iota', '2023-01-01', '5', '先进团队');
INSERT INTO `teaminfo` VALUES ('10', '10', '团队癸', 'Team Kappa', '2023-02-01', '4', '卓越团队');
INSERT INTO `teaminfo` VALUES ('11', '11', 'Team A', 'Team A Eng', '2020-01-15', '3', 'Remark A');
INSERT INTO `teaminfo` VALUES ('12', '12', 'Team B', 'Team B Eng', '2019-02-10', '4', 'Remark B');
INSERT INTO `teaminfo` VALUES ('13', '13', 'Team C', 'Team C Eng', '2021-03-20', '5', 'Remark C');
INSERT INTO `teaminfo` VALUES ('123214', '12', 'HZUC', 'test', '2022-10-15', '4', 'sda');

-- ----------------------------
-- Table structure for teammemberinfo
-- ----------------------------
DROP TABLE IF EXISTS `teammemberinfo`;
CREATE TABLE `teammemberinfo` (
  `student_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `isLeader` tinyint(1) NOT NULL,
  PRIMARY KEY (`student_id`,`team_id`),
  KEY `FK_TeamMemberInfo2` (`team_id`),
  CONSTRAINT `FK_TeamMemberInfo` FOREIGN KEY (`student_id`) REFERENCES `studentinfo` (`student_id`),
  CONSTRAINT `FK_TeamMemberInfo2` FOREIGN KEY (`team_id`) REFERENCES `teaminfo` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of teammemberinfo
-- ----------------------------
INSERT INTO `teammemberinfo` VALUES ('1', '1', '0');
INSERT INTO `teammemberinfo` VALUES ('1', '7', '0');
INSERT INTO `teammemberinfo` VALUES ('1', '8', '0');
INSERT INTO `teammemberinfo` VALUES ('1', '123214', '0');
INSERT INTO `teammemberinfo` VALUES ('2', '1', '0');
INSERT INTO `teammemberinfo` VALUES ('2', '2', '1');
INSERT INTO `teammemberinfo` VALUES ('2', '8', '0');
INSERT INTO `teammemberinfo` VALUES ('3', '1', '1');
INSERT INTO `teammemberinfo` VALUES ('3', '3', '1');
INSERT INTO `teammemberinfo` VALUES ('3', '7', '0');
INSERT INTO `teammemberinfo` VALUES ('3', '8', '0');
INSERT INTO `teammemberinfo` VALUES ('4', '1', '0');
INSERT INTO `teammemberinfo` VALUES ('4', '4', '1');
INSERT INTO `teammemberinfo` VALUES ('4', '7', '0');
INSERT INTO `teammemberinfo` VALUES ('5', '5', '1');
INSERT INTO `teammemberinfo` VALUES ('5', '123214', '1');
INSERT INTO `teammemberinfo` VALUES ('6', '6', '1');
INSERT INTO `teammemberinfo` VALUES ('6', '123214', '0');
INSERT INTO `teammemberinfo` VALUES ('7', '7', '1');
INSERT INTO `teammemberinfo` VALUES ('7', '123214', '0');
INSERT INTO `teammemberinfo` VALUES ('9', '9', '1');
INSERT INTO `teammemberinfo` VALUES ('11', '11', '1');
INSERT INTO `teammemberinfo` VALUES ('11', '13', '0');
INSERT INTO `teammemberinfo` VALUES ('12', '11', '0');
INSERT INTO `teammemberinfo` VALUES ('13', '12', '1');

-- ----------------------------
-- Table structure for teamteacher_awardinfo
-- ----------------------------
DROP TABLE IF EXISTS `teamteacher_awardinfo`;
CREATE TABLE `teamteacher_awardinfo` (
  `teacher_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `competition_id` int(11) NOT NULL,
  `award_level` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`teacher_id`,`team_id`,`competition_id`),
  KEY `FK_TeamTeacher_AwardInfo2` (`team_id`),
  KEY `FK_TeamTeacher_AwardInfo3` (`competition_id`),
  CONSTRAINT `FK_TeamTeacher_AwardInfo` FOREIGN KEY (`teacher_id`) REFERENCES `teacherinfo` (`teacher_id`),
  CONSTRAINT `FK_TeamTeacher_AwardInfo2` FOREIGN KEY (`team_id`) REFERENCES `teaminfo` (`team_id`),
  CONSTRAINT `FK_TeamTeacher_AwardInfo3` FOREIGN KEY (`competition_id`) REFERENCES `challenge_arrangementinfo` (`competition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of teamteacher_awardinfo
-- ----------------------------
INSERT INTO `teamteacher_awardinfo` VALUES ('1', '1', '1', '一等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('1', '4', '1', '二等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('1', '8', '1', '二等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('1', '10', '2', '二等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('2', '2', '2', '二等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('2', '2', '5', '一等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('3', '3', '3', '三等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('4', '4', '4', '优秀奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('5', '5', '3', '一等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('5', '5', '5', '一等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('6', '6', '6', '二等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('7', '7', '7', '三等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('8', '8', '8', '优秀奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('9', '9', '9', '一等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('10', '10', '10', '二等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('11', '11', '11', '一等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('12', '12', '12', '二等奖');
INSERT INTO `teamteacher_awardinfo` VALUES ('13', '13', '13', '三等奖');

-- ----------------------------
-- View structure for awardchallenge
-- ----------------------------
DROP VIEW IF EXISTS `awardchallenge`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `awardchallenge` AS select `cai`.`competition_name` AS `competition_name`,`ci`.`challenge_name` AS `challenge_name`,`si`.`student_name` AS `student_name`,`si`.`class` AS `class`,`itai`.`award_level` AS `award_level`,'个人赛' AS `competition_type`,`cai`.`held_time` AS `held_time` from (((`studentinfo` `si` join `individualteacher_awardinfo` `itai` on((`si`.`student_id` = `itai`.`student_id`))) join `challenge_arrangementinfo` `cai` on((`itai`.`competition_id` = `cai`.`competition_id`))) join `challengeinfo` `ci` on((`cai`.`challenge_id` = `ci`.`challenge_id`))) union all select `cai`.`competition_name` AS `competition_name`,`ci`.`challenge_name` AS `challenge_name`,`si`.`student_name` AS `student_name`,`si`.`class` AS `class`,`ttai`.`award_level` AS `award_level`,'组队赛' AS `competition_type`,`cai`.`held_time` AS `held_time` from (((((`studentinfo` `si` join `teammemberinfo` `tmi` on((`si`.`student_id` = `tmi`.`student_id`))) join `teaminfo` `ti` on((`tmi`.`team_id` = `ti`.`team_id`))) join `teamteacher_awardinfo` `ttai` on((`ti`.`team_id` = `ttai`.`team_id`))) join `challenge_arrangementinfo` `cai` on((`ttai`.`competition_id` = `cai`.`competition_id`))) join `challengeinfo` `ci` on((`cai`.`challenge_id` = `ci`.`challenge_id`))) ;

-- ----------------------------
-- View structure for awarddetails
-- ----------------------------
DROP VIEW IF EXISTS `awarddetails`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `awarddetails` AS select `si`.`student_id` AS `student_id`,`si`.`student_name` AS `student_name`,`si`.`class` AS `class`,`si`.`major` AS `major`,`cai`.`competition_name` AS `competition_name`,`itai`.`award_level` AS `award_level`,`cai`.`held_time` AS `held_time`,'个人赛' AS `competition_type` from ((`studentinfo` `si` join `individualteacher_awardinfo` `itai` on((`si`.`student_id` = `itai`.`student_id`))) join `challenge_arrangementinfo` `cai` on((`itai`.`competition_id` = `cai`.`competition_id`))) union all select `si`.`student_id` AS `student_id`,`si`.`student_name` AS `student_name`,`si`.`class` AS `class`,`si`.`major` AS `major`,`cai`.`competition_name` AS `competition_name`,`ttai`.`award_level` AS `award_level`,`cai`.`held_time` AS `held_time`,'组队赛' AS `competition_type` from ((((`studentinfo` `si` join `teammemberinfo` `tmi` on((`si`.`student_id` = `tmi`.`student_id`))) join `teaminfo` `ti` on((`tmi`.`team_id` = `ti`.`team_id`))) join `teamteacher_awardinfo` `ttai` on((`ti`.`team_id` = `ttai`.`team_id`))) join `challenge_arrangementinfo` `cai` on((`ttai`.`competition_id` = `cai`.`competition_id`))) ;

-- ----------------------------
-- View structure for awardview
-- ----------------------------
DROP VIEW IF EXISTS `awardview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `awardview` AS select `s`.`student_id` AS `student_id`,`s`.`student_name` AS `student_name`,`s`.`class` AS `class`,`s`.`major` AS `major`,`ca`.`competition_name` AS `competition_name`,`ci`.`challenge_name` AS `challenge_name`,`ita`.`award_level` AS `award_level`,`ca`.`held_time` AS `held_time`,'个人赛' AS `competition_type` from (((`studentinfo` `s` join `individualteacher_awardinfo` `ita` on((`s`.`student_id` = `ita`.`student_id`))) join `challenge_arrangementinfo` `ca` on((`ita`.`competition_id` = `ca`.`competition_id`))) join `challengeinfo` `ci` on((`ca`.`challenge_id` = `ci`.`challenge_id`))) union all select `s`.`student_id` AS `student_id`,`s`.`student_name` AS `student_name`,`s`.`class` AS `class`,`s`.`major` AS `major`,`ca`.`competition_name` AS `competition_name`,`ci`.`challenge_name` AS `challenge_name`,`tta`.`award_level` AS `award_level`,`ca`.`held_time` AS `held_time`,'组队赛' AS `competition_type` from (((((`studentinfo` `s` join `teammemberinfo` `tmi` on((`s`.`student_id` = `tmi`.`student_id`))) join `teaminfo` `ti` on((`tmi`.`team_id` = `ti`.`team_id`))) join `teamteacher_awardinfo` `tta` on((`ti`.`team_id` = `tta`.`team_id`))) join `challenge_arrangementinfo` `ca` on((`tta`.`competition_id` = `ca`.`competition_id`))) join `challengeinfo` `ci` on((`ca`.`challenge_id` = `ci`.`challenge_id`))) ;
