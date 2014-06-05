/*
Navicat MySQL Data Transfer

Source Server         : 172.21.1.100
Source Server Version : 50532
Source Host           : 172.21.1.100:3306
Source Database       : glp_new

Target Server Type    : MYSQL
Target Server Version : 50532
File Encoding         : 65001

Date: 2014-05-06 16:58:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `GLP_3PARTY`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_3PARTY`;
CREATE TABLE `GLP_3PARTY` (
  `USER_ID` bigint(20) NOT NULL,
  `3PARTY_USER_ID` varchar(30) NOT NULL,
  `3PARTY_TYPE` varchar(1) NOT NULL COMMENT '1，facebook；2，twitter；3，linkedin；4，google+',
  `CREATE_DATE` datetime NOT NULL COMMENT 'CURRENT_TIMESTAMP'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_3PARTY
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS`;
CREATE TABLE `GLP_BUSINESS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `CATEGORY` varchar(50) NOT NULL,
  `ROLE_ID` varchar(1) NOT NULL,
  `INTERNAL_ROLE_ID` varchar(10) DEFAULT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  `CITY_NAME` varchar(100) DEFAULT NULL,
  `CITY` bigint(20) DEFAULT NULL,
  `PROVINCE` bigint(20) DEFAULT NULL,
  `ZIP_CODE` varchar(10) NOT NULL,
  `COUNTRY_ID` bigint(20) NOT NULL,
  `PHONE_NUMBER` varchar(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `LOGO` varchar(255) DEFAULT NULL,
  `DESCRIPTION` mediumtext,
  `CONTACT_NAME` varchar(100) DEFAULT NULL,
  `CONTACT_PHONE_NUMBER` varchar(20) DEFAULT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `TYPE` varchar(1) NOT NULL DEFAULT '1' COMMENT '1,表示Business;2,表示Chain',
  `BUSINESS_ID` bigint(20) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT '1' COMMENT '0，未审核；1，审核；2，逻辑删除；',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CHECK_DATE` datetime DEFAULT NULL,
  `DELETE_DATE` datetime DEFAULT NULL,
  `LNG` varchar(20) DEFAULT NULL,
  `LAT` varchar(20) DEFAULT NULL,
  `PROVINCE_NAME` varchar(50) DEFAULT NULL,
  `COUNTRY_NAME` varchar(50) DEFAULT NULL,
  `COMPANY_TYPE` varchar(1) DEFAULT NULL,
  `DBA` varchar(100) DEFAULT NULL,
  `ESTABLISHED_DATE` int(4) DEFAULT NULL,
  `NUMBER_OF_LOCATIONS` int(11) DEFAULT NULL,
  `CONTACT_TITLE` varchar(100) DEFAULT NULL,
  `ADDRESS2` varchar(255) DEFAULT NULL,
  `BUSINESS_TYPE` varchar(1) NOT NULL DEFAULT '1',
  `PHONE_TYPE` varchar(10) DEFAULT NULL,
  `PHONE_COUNTRY_CODE` varchar(10) DEFAULT NULL,
  `PHONE_COUNTRY_ID` bigint(20) DEFAULT NULL,
  `COMPANY_SIZE` varchar(50) DEFAULT NULL,
  `CAPITAL` varchar(50) DEFAULT NULL,
  `SCOPE` varchar(255) DEFAULT NULL,
  `CAPITAL_CURRENCY` varchar(2) DEFAULT NULL,
  `CATEGORY_NAME` varchar(255) DEFAULT NULL,
  `MOBILEPHONE` varchar(15) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_CODE` varchar(10) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_ACTIVITY`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_ACTIVITY`;
CREATE TABLE `GLP_BUSINESS_ACTIVITY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUSINESS_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `TYPE_ID` varchar(10) NOT NULL,
  `PRICE` bigint(20) NOT NULL DEFAULT '0' COMMENT '单位是分',
  `DESCRIPTION` mediumtext,
  `URL` varchar(255) NOT NULL,
  `IS_ONLINE` tinyint(1) NOT NULL DEFAULT '1',
  `STATUS` varchar(1) NOT NULL DEFAULT '1' COMMENT '0，未审核；1，审核；2，删除',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CHECK_DATE` datetime NOT NULL,
  `DELETE_DATE` datetime DEFAULT NULL,
  `BEGIN_DATE` date NOT NULL,
  `END_DATE` date NOT NULL,
  `CODE_NUMBER` varchar(15) DEFAULT NULL,
  `CURRENCY` varchar(2) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_ACTIVITY
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_ACTIVITY_TYPE`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_ACTIVITY_TYPE`;
CREATE TABLE `GLP_BUSINESS_ACTIVITY_TYPE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_ACTIVITY_TYPE
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_CATEGORY`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_CATEGORY`;
CREATE TABLE `GLP_BUSINESS_CATEGORY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PARENT_CATEGORY_ID` bigint(20) DEFAULT NULL,
  `STATUS` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_CATEGORY
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_CERTIFICATION`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_CERTIFICATION`;
CREATE TABLE `GLP_BUSINESS_CERTIFICATION` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUSINESS_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `TYPE` varchar(1) NOT NULL COMMENT '类型具体待定',
  `TYPE_NAME` varchar(50) DEFAULT NULL COMMENT '分类为其他的时候，用户输入的分类名称',
  `NAME` varchar(255) NOT NULL,
  `REFERENCE` varchar(50) NOT NULL,
  `ORGANIZATION` varchar(1) DEFAULT NULL COMMENT '组织分类：1，Government；2，Trademark；3，Brand Owner',
  `VALID_BEGIN_DATE` date NOT NULL,
  `VALID_END_DATE` date NOT NULL,
  `DESCRIPTION` mediumtext,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_CERTIFICATION
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_FILE`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_FILE`;
CREATE TABLE `GLP_BUSINESS_FILE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REF_ID` bigint(20) DEFAULT NULL,
  `REF_TYPE` varchar(1) NOT NULL,
  `FILE_PATH` varchar(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `USER_ID` bigint(20) NOT NULL,
  `BUSINESS_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_FILE
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_IMPORT`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_IMPORT`;
CREATE TABLE `GLP_BUSINESS_IMPORT` (
  `ID` bigint(20) DEFAULT NULL,
  `NAME` varchar(765) DEFAULT NULL,
  `CATEGORY` varchar(150) DEFAULT NULL,
  `ROLE_ID` varchar(3) DEFAULT NULL,
  `INTERNAL_ROLE_ID` varchar(30) DEFAULT NULL,
  `ADDRESS` varchar(765) DEFAULT NULL,
  `CITY_NAME` varchar(300) DEFAULT NULL,
  `CITY` bigint(20) DEFAULT NULL,
  `PROVINCE` bigint(20) DEFAULT NULL,
  `ZIP_CODE` varchar(30) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `PHONE_NUMBER` varchar(60) DEFAULT NULL,
  `EMAIL` varchar(765) DEFAULT NULL,
  `FAX` varchar(60) DEFAULT NULL,
  `WEBSITE` varchar(300) DEFAULT NULL,
  `LOGO` varchar(765) DEFAULT NULL,
  `DESCRIPTION` text,
  `CONTACT_NAME` varchar(300) DEFAULT NULL,
  `CONTACT_PHONE_NUMBER` varchar(60) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `TYPE` varchar(3) DEFAULT NULL,
  `BUSINESS_ID` bigint(20) DEFAULT NULL,
  `STATUS` varchar(3) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CHECK_DATE` datetime DEFAULT NULL,
  `DELETE_DATE` datetime DEFAULT NULL,
  `LNG` varchar(60) DEFAULT NULL,
  `LAT` varchar(60) DEFAULT NULL,
  `PROVINCE_NAME` varchar(150) DEFAULT NULL,
  `COUNTRY_NAME` varchar(150) DEFAULT NULL,
  `COMPANY_TYPE` varchar(3) DEFAULT NULL,
  `DBA` varchar(300) DEFAULT NULL,
  `ESTABLISHED_DATE` int(4) DEFAULT NULL,
  `NUMBER_OF_LOCATIONS` int(11) DEFAULT NULL,
  `CONTACT_TITLE` varchar(300) DEFAULT NULL,
  `ADDRESS2` varchar(765) DEFAULT NULL,
  `BUSINESS_TYPE` varchar(3) DEFAULT NULL,
  `PHONE_TYPE` varchar(30) DEFAULT NULL,
  `PHONE_COUNTRY_CODE` varchar(30) DEFAULT NULL,
  `PHONE_COUNTRY_ID` bigint(20) DEFAULT NULL,
  `COMPANY_SIZE` varchar(150) DEFAULT NULL,
  `CAPITAL` varchar(150) DEFAULT NULL,
  `SCOPE` varchar(765) DEFAULT NULL,
  `CAPITAL_CURRENCY` varchar(6) DEFAULT NULL,
  `CATEGORY_NAME` varchar(765) DEFAULT NULL,
  `MOBILEPHONE` varchar(45) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_CODE` varchar(30) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_IMPORT
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_PRODUCT`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_PRODUCT`;
CREATE TABLE `GLP_BUSINESS_PRODUCT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUSINESS_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `TYPE` varchar(100) NOT NULL COMMENT '1,表示互动;5349,表示服务分类之间用半角","分开',
  `NAME` varchar(100) NOT NULL,
  `BRAND` varchar(100) NOT NULL,
  `PRICE` bigint(20) NOT NULL DEFAULT '0' COMMENT '精确到美分',
  `ITEM_NUMBER` varchar(100) NOT NULL,
  `DESCRIPTION` mediumtext,
  `STATUS` varchar(1) NOT NULL DEFAULT '1' COMMENT '0，未审核；1，审核；2，逻辑删除',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CHECK_DATE` datetime DEFAULT NULL,
  `DELETE_DATE` datetime DEFAULT NULL,
  `IS_PRODUCT` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'true,product;false,service',
  `TYPE_NAME` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `PRICE_END` bigint(20) DEFAULT NULL,
  `CURRENCY` varchar(2) NOT NULL DEFAULT '1',
  `MIN_QTY_PER_ORDER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_PRODUCT
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_PRODUCT_ACTIVITY`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_PRODUCT_ACTIVITY`;
CREATE TABLE `GLP_BUSINESS_PRODUCT_ACTIVITY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUSINESS_PRODUCT_ID` bigint(20) NOT NULL,
  `BUSINESS_ACTIVITY_ID` bigint(20) NOT NULL,
  `CREATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_PROCUT_ACTIVITY_PRODUCT` (`BUSINESS_PRODUCT_ID`) USING BTREE,
  KEY `FK_PRODUCT_ACTIIVTY_ACTIVITY` (`BUSINESS_ACTIVITY_ID`) USING BTREE,
  CONSTRAINT `FK_PROCUT_ACTIVITY_PRODUCT` FOREIGN KEY (`BUSINESS_PRODUCT_ID`) REFERENCES `GLP_BUSINESS_PRODUCT` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_PRODUCT_ACTIIVTY_ACTIVITY` FOREIGN KEY (`BUSINESS_ACTIVITY_ID`) REFERENCES `GLP_BUSINESS_ACTIVITY` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_PRODUCT_ACTIVITY
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_PRODUCT_TEMP`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_PRODUCT_TEMP`;
CREATE TABLE `GLP_BUSINESS_PRODUCT_TEMP` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `TYPE` varchar(100) DEFAULT NULL,
  `TYPE_NAME` varchar(255) DEFAULT NULL,
  `BRAND` varchar(100) DEFAULT NULL,
  `CURRENCY` varchar(2) DEFAULT '1' COMMENT '默认是美元',
  `PRICE` bigint(20) DEFAULT '0' COMMENT '精确到美分',
  `MIN_QTY_PER_ORDER` int(11) DEFAULT NULL,
  `ITEM_NUMBER` varchar(100) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `BUSINESS_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IS_FILL` enum('0','1') DEFAULT '0' COMMENT '0，不完整；1，完整',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_PRODUCT_TEMP
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_REFERENCE`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_REFERENCE`;
CREATE TABLE `GLP_BUSINESS_REFERENCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUSINESS_ID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `CATEGORY` varchar(50) NOT NULL,
  `TYPE` varchar(1) NOT NULL COMMENT '1,Provider companies;2,Customer companies',
  `ROLE_ID` varchar(1) NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  `CITY_NAME` varchar(100) DEFAULT NULL,
  `CITY` bigint(20) DEFAULT NULL,
  `PROVINCE` bigint(20) DEFAULT NULL,
  `ZIP_CODE` varchar(20) NOT NULL,
  `COUNTRY_ID` bigint(20) NOT NULL,
  `PHONE_NUMBER` varchar(20) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `CONTACT_NAME` varchar(100) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `STATUS` varchar(1) NOT NULL DEFAULT '1' COMMENT '0，未审核；1，审核；2，逻辑删除',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CHECK_DATE` datetime DEFAULT NULL,
  `DELETE_DATE` datetime DEFAULT NULL,
  `LNG` varchar(20) DEFAULT NULL,
  `LAT` varchar(20) DEFAULT NULL,
  `PROVINCE_NAME` varchar(50) DEFAULT NULL,
  `COUNTRY_NAME` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(255) DEFAULT NULL,
  `BUSINESS_TYPE` varchar(1) NOT NULL DEFAULT '1',
  `PHONE_TYPE` varchar(10) DEFAULT NULL,
  `ADDRESS2` varchar(255) DEFAULT NULL,
  `NUMBER_OF_LOCATIONS` int(11) DEFAULT '0',
  `CONTACT_TITLE` varchar(100) DEFAULT NULL,
  `PHONE_COUNTRY_CODE` varchar(10) DEFAULT NULL,
  `PHONE_COUNTRY_ID` bigint(20) DEFAULT NULL,
  `CATEGORY_NAME` varchar(255) DEFAULT NULL,
  `MOBILEPHONE` varchar(15) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_CODE` varchar(10) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_REFERENCE
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_ROLE`;
CREATE TABLE `GLP_BUSINESS_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_ROLE
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_TEMP`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_TEMP`;
CREATE TABLE `GLP_BUSINESS_TEMP` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DBA` varchar(100) DEFAULT NULL COMMENT 'Do Business As',
  `ESTABLISHED_DATE` int(4) DEFAULT NULL,
  `COMPANY_TYPE` varchar(1) DEFAULT NULL COMMENT '公司类型：合资，独资，or 有限公司',
  `BUSINESS_TYPE` varchar(1) DEFAULT '1' COMMENT '1,product;2,service',
  `CATEGORY` varchar(50) DEFAULT NULL,
  `CATEGORY_NAME` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(1) DEFAULT NULL,
  `COMPANY_SIZE` varchar(50) DEFAULT NULL,
  `CAPITAL` varchar(50) DEFAULT NULL,
  `CAPITAL_CURRENCY` varchar(2) DEFAULT NULL,
  `SCOPE` varchar(255) DEFAULT NULL,
  `DESCRIPTION` mediumtext,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_NAME` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `ADDRESS2` varchar(255) DEFAULT NULL,
  `PROVINCE` bigint(20) DEFAULT NULL,
  `PROVINCE_NAME` varchar(50) DEFAULT NULL,
  `CITY` bigint(20) DEFAULT NULL,
  `CITY_NAME` varchar(100) DEFAULT NULL,
  `ZIP_CODE` varchar(10) DEFAULT NULL,
  `LNG` varchar(20) DEFAULT NULL,
  `LAT` varchar(20) DEFAULT NULL,
  `PHONE_TYPE` varchar(10) DEFAULT NULL,
  `PHONE_COUNTRY_ID` bigint(20) DEFAULT NULL,
  `PHONE_COUNTRY_CODE` varchar(10) DEFAULT NULL,
  `PHONE_NUMBER` varchar(50) DEFAULT NULL,
  `MOBILEPHONE` varchar(50) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_ID` bigint(20) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_CODE` varchar(10) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `CONTACT_NAME` varchar(100) DEFAULT NULL,
  `CONTACT_TITLE` varchar(100) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `NUMBER_OF_LOCATIONS` int(11) DEFAULT '0',
  `USER_ID` bigint(20) DEFAULT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_TEMP
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_BUSINESS_TRADEMARK`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_BUSINESS_TRADEMARK`;
CREATE TABLE `GLP_BUSINESS_TRADEMARK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUSINESS_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `REFERENCE` varchar(50) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `VALID_BEGIN_DATE` datetime NOT NULL,
  `VALID_END_DATE` datetime NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DESCRIPTION` mediumtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_BUSINESS_TRADEMARK
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_CODE`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_CODE`;
CREATE TABLE `GLP_CODE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(32) NOT NULL,
  `CODE_VALUE` varchar(10) NOT NULL,
  `CREATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `VALID_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_CODE
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_COMMON_DATA`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_COMMON_DATA`;
CREATE TABLE `GLP_COMMON_DATA` (
  `ID` varchar(10) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `PARENT_ID` varchar(10) DEFAULT NULL,
  `IORDER` int(11) NOT NULL DEFAULT '0',
  `TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`ID`,`TYPE_ID`),
  KEY `FK_DATA_DATA_TYPE` (`TYPE_ID`) USING BTREE,
  CONSTRAINT `FK_DATA_DATA_TYPE` FOREIGN KEY (`TYPE_ID`) REFERENCES `GLP_COMMON_DATA_TYPE` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_COMMON_DATA
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_COMMON_DATA_TYPE`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_COMMON_DATA_TYPE`;
CREATE TABLE `GLP_COMMON_DATA_TYPE` (
  `ID` varchar(4) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_COMMON_DATA_TYPE
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_COUNTRY`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_COUNTRY`;
CREATE TABLE `GLP_COUNTRY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `A2` varchar(20) NOT NULL,
  `A3` varchar(20) NOT NULL,
  `CODE` varchar(3) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PHONE_CODE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_COUNTRY
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_COUNTRY_STATE`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_COUNTRY_STATE`;
CREATE TABLE `GLP_COUNTRY_STATE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ABBREVIATION` varchar(20) NOT NULL,
  `CODE` varchar(20) NOT NULL,
  `NUMBERICAL_CODE` varchar(3) DEFAULT NULL,
  `NAME` varchar(50) NOT NULL,
  `COUNTRY_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_COUNTRY_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_FAQ`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_FAQ`;
CREATE TABLE `GLP_FAQ` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) NOT NULL,
  `CONTENT` mediumtext,
  `PARENT_ID` int(11) DEFAULT NULL,
  `I_ORDER` int(11) NOT NULL DEFAULT '1',
  `CREATOR_USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `MODIFY_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `MODIFIER_USER_ID` bigint(20) DEFAULT NULL,
  `STATUS` varchar(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_FAQ
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_INFORMATION_IMPORT_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_INFORMATION_IMPORT_LOG`;
CREATE TABLE `GLP_INFORMATION_IMPORT_LOG` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `WEBSITE_TYPE_ID` varchar(10) NOT NULL,
  `WEBSITE` varchar(255) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `BUSINESS_ID` bigint(20) DEFAULT NULL,
  `UPDATESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_INFORMATION_IMPORT_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_REFER_BUSINESS`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_REFER_BUSINESS`;
CREATE TABLE `GLP_REFER_BUSINESS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `BUSINESS_TYPE` varchar(1) DEFAULT '1' COMMENT '1,product;2,service',
  `ROLE_ID` varchar(1) NOT NULL,
  `CATEGORY` varchar(50) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `ADDRESS2` varchar(255) DEFAULT NULL,
  `CITY_NAME` varchar(100) DEFAULT NULL,
  `CITY` bigint(20) DEFAULT NULL,
  `PROVINCE` bigint(20) DEFAULT NULL,
  `ZIP_CODE` varchar(10) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) NOT NULL,
  `PHONE_TYPE` varchar(10) DEFAULT NULL,
  `PHONE_COUNTRY_ID` bigint(20) DEFAULT NULL,
  `PHONE_COUNTRY_CODE` varchar(10) DEFAULT NULL,
  `PHONE_NUMBER` varchar(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `DESCRIPTION` mediumtext,
  `CONTACT_NAME` varchar(100) DEFAULT NULL,
  `CONTACT_TITLE` varchar(100) DEFAULT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `COUNTRY_NAME` varchar(50) DEFAULT NULL,
  `PROVINCE_NAME` varchar(50) DEFAULT NULL,
  `NUMBER_OF_LOCATIONS` int(11) DEFAULT '0',
  `CATEGORY_NAME` varchar(255) DEFAULT NULL,
  `MOBILEPHONE` varchar(15) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_CODE` varchar(10) DEFAULT NULL,
  `MOBILEPHONE_COUNTRY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_REFER_BUSINESS
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_REVIEW`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_REVIEW`;
CREATE TABLE `GLP_REVIEW` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REF_TYPE` varchar(1) NOT NULL COMMENT '1,Productl;2,Service;3,Activity',
  `REF_ID` bigint(20) NOT NULL,
  `REF_USER_ID` bigint(20) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `GLP_COMMENTS` mediumtext NOT NULL COMMENT '记录Activity、Service、Product的评价内容',
  `RATING` smallint(6) NOT NULL DEFAULT '0',
  `IP` varchar(15) NOT NULL,
  `USER_ID` bigint(20) DEFAULT '0',
  `STATUS` varchar(1) DEFAULT '1' COMMENT '0，未审核；1，审核；2，删除',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CHECK_DATE` datetime DEFAULT NULL,
  `DELETE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_REVIEW
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_USER`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_USER`;
CREATE TABLE `GLP_USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  `MD5_PASSWORD` varchar(32) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `STATUS` varchar(1) NOT NULL DEFAULT '0' COMMENT '0，未验证；1，已经验证；2，逻辑删除',
  `IP` varchar(15) NOT NULL,
  `ROLE` varchar(1) NOT NULL DEFAULT '5' COMMENT '1,admin;2,seller;3,vip_seller;4,buyer;5,general',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DELETE_DATE` datetime DEFAULT NULL,
  `VERIFY_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `AK_UNIQUE` (`EMAIL`) USING BTREE,
  KEY `UNI_EMAIL` (`EMAIL`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_USER
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_USER_3RDPARTY`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_USER_3RDPARTY`;
CREATE TABLE `GLP_USER_3RDPARTY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_TYPE` varchar(1) NOT NULL,
  `ACCOUNT_EMAIL` varchar(255) NOT NULL,
  `ACCOUNT_PWD` varchar(100) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `UPDATE_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNI_TYPE_EMAIL_USER_ID` (`ACCOUNT_TYPE`,`ACCOUNT_EMAIL`,`USER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_USER_3RDPARTY
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_USER_INFOS`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_USER_INFOS`;
CREATE TABLE `GLP_USER_INFOS` (
  `USER_ID` bigint(20) NOT NULL,
  `AGE` tinyint(4) NOT NULL DEFAULT '0',
  `POINT` int(11) NOT NULL DEFAULT '0',
  `GENDER` varchar(1) NOT NULL DEFAULT '0' COMMENT '0，未知；1，男；2，女',
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `LAST_LOGIN_DATE` datetime DEFAULT NULL,
  `LAST_LOGIN_IP` varchar(15) DEFAULT NULL,
  `SECURITY_QUESTION_1` varchar(255) DEFAULT NULL,
  `SECURITY_ANSWER_1` varchar(255) DEFAULT NULL,
  `SECURITY_QUESTION_2` varchar(255) DEFAULT NULL,
  `SECURITY_ANSWER_2` varchar(255) DEFAULT NULL,
  `LOGIN_COUNTS` int(11) DEFAULT '0',
  `TELE_PHONE` varchar(30) DEFAULT NULL,
  `MOBILE_PHONE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_USER_INFOS
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_USER_LOGIN_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_USER_LOGIN_LOG`;
CREATE TABLE `GLP_USER_LOGIN_LOG` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `ACTION_TYPE` varchar(1) NOT NULL DEFAULT '0' COMMENT '0,login;1,login with facebook;2,login with twitter;3,login with linkedin;4,login with google+;5,logout ',
  `ACTION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IP` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_USER_LOGIN_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_VERIFY_CODE`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_VERIFY_CODE`;
CREATE TABLE `GLP_VERIFY_CODE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `CODE` varchar(32) NOT NULL COMMENT '保存32位MD5验证码',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VALID_DATE` datetime NOT NULL,
  `CODE_TYPE` varchar(1) NOT NULL COMMENT '验证类型：1，表示注册验证；2，找回密码验证',
  `LONG_TIME` bigint(20) NOT NULL COMMENT 'java系统的currentmill',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_VERIFY_CODE
-- ----------------------------

-- ----------------------------
-- Table structure for `GLP_VERIFY_CODE_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `GLP_VERIFY_CODE_LOG`;
CREATE TABLE `GLP_VERIFY_CODE_LOG` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `CODE` varchar(32) NOT NULL,
  `CODE_CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CODE_VALID_DATE` datetime NOT NULL,
  `CODE_TYPE` varchar(1) NOT NULL,
  `USE_DATE` datetime NOT NULL,
  `LONG_TIME` bigint(20) NOT NULL COMMENT 'java系统的currentmill',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of GLP_VERIFY_CODE_LOG
-- ----------------------------

/******** add user operate log table **********/
DROP TABLE IF EXISTS GLP_USER_OPERATE_LOG;

/*==============================================================*/
/* Table: GLP_USER_OPERATE_LOG                                  */
/*==============================================================*/
CREATE TABLE GLP_USER_OPERATE_LOG
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT,
   USER_ID              BIGINT NOT NULL,
   OBJECT_ID            BIGINT NOT NULL,
   OBJECT_TYPE          VARCHAR(2) NOT NULL,
   ACTION_TYPE          VARCHAR(1) NOT NULL,
   CREATE_DATE          DATETIME NOT NULL,
   PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE GLP_USER_OPERATE_LOG COMMENT 'add user operate log table';

drop table if exists GLP_MAIL_DOMAIN;

/*==============================================================*/
/* Table: GLP_MAIL_DOMAIN                                       */
/*==============================================================*/
create table GLP_EMAIL_DOMAIN
(
   ID                   int not null auto_increment,
   DOMAIN               varchar(255) not null comment 'mail域名',
   MAIL_DOMAIN          varchar(255) not null comment '要转到的域名',
   primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table GLP_EMAIL_DOMAIN comment 'mail域名转换';

/*==============================================================*/
/* Index: UNI_EMAIL                                             */
/*==============================================================*/
create unique index UNI_EMAIL on GLP_EMAIL_DOMAIN
(
   DOMAIN
);

insert into `GLP_EMAIL_DOMAIN` (`ID`, `DOMAIN`, `MAIL_DOMAIN`) values('1','gmail.com','http://mail.google.com');

insert into `GLP_EMAIL_DOMAIN` (`ID`, `DOMAIN`, `MAIL_DOMAIN`) values('2','hotmail.com','http://mail.msn.com');

insert into `GLP_EMAIL_DOMAIN` (`ID`, `DOMAIN`, `MAIL_DOMAIN`) values('3','mail.com','http://www.mail.com');




