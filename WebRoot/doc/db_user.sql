
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for USER_DEFAULT
-- ----------------------------
DROP TABLE IF EXISTS `USER_DEFAULT`;
CREATE TABLE `USER_DEFAULT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `UUID` varchar(64) DEFAULT NULL COMMENT 'UUID',
  `USERNAME` varchar(256) DEFAULT NULL COMMENT '用户名称',
  `PASSWORD` varchar(512) NOT NULL COMMENT '密码',
  `PHONE` varchar(11) NOT NULL COMMENT '手机号',
  `SEX` int(1) DEFAULT '1' COMMENT '性别',
  `EMAIL` varchar(64) NOT NULL COMMENT '邮箱',
  `ISREALEMAIL` int(2) NOT NULL DEFAULT '0' COMMENT '是否认证邮箱',
  `CONTACTNAME` varchar(256) DEFAULT NULL COMMENT '联系人',
  `CONTACTPHONE` varchar(20) DEFAULT NULL COMMENT '联系人号码',
  `ADDRESS` varchar(512) DEFAULT NULL COMMENT '地址',
  `USERTYPE` varchar(1) DEFAULT '1' COMMENT '用户类别',
  `SIGNUPSOURCE` varchar(1) NOT NULL COMMENT '注册来源',
  `SIGNUPIP` varchar(60) DEFAULT NULL COMMENT '注册IP地址',
  `SIGNUPTIME` datetime NOT NULL COMMENT '注册时间',
  `UPDATATIME` datetime DEFAULT NULL COMMENT '最后修改时间',
  `REMARK` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`),
  KEY `index_id` (`ID`) USING BTREE,
  KEY `index_username` (`USERNAME`(255)) USING BTREE,
  KEY `index_username_password` (`USERNAME`(255),`PASSWORD`(255)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
