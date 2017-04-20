-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: windchimp
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `banner_slider_master`
--

DROP TABLE IF EXISTS `banner_slider_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner_slider_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `banner_id` varchar(45) NOT NULL,
  `banner_name` varchar(45) DEFAULT NULL,
  `banner_link` varchar(500) DEFAULT NULL,
  `location` varchar(1000) DEFAULT NULL,
  `discription` varchar(1000) DEFAULT NULL,
  `others` varchar(1000) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_id_UNIQUE` (`banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner_slider_master`
--

LOCK TABLES `banner_slider_master` WRITE;
/*!40000 ALTER TABLE `banner_slider_master` DISABLE KEYS */;
INSERT INTO `banner_slider_master` VALUES (1,'windchim_1','banner1','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=1.jpg','Lucknow','windchim school','www.windchimeseducation.com','SYSTEM','2017-01-22 18:30:00',NULL,NULL,'F'),(2,'windchim_2','banner2','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=2.jpg','Lucknow','windchim school','www.windchimeseducation.com','SYSTEM','2017-01-22 18:30:00',NULL,NULL,'F'),(3,'windchim_3','banner3','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=3.jpg','Lucknow','windchim school','www.windchimeseducation.com','SYSTEM','2017-01-22 18:30:00',NULL,NULL,'F'),(4,'windchim_4','banner4','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=4.jpg','Lucknow','windchim school','www.windchimeseducation.com','SYSTEM','2017-01-22 18:30:00',NULL,NULL,'F');
/*!40000 ALTER TABLE `banner_slider_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch_master`
--

DROP TABLE IF EXISTS `branch_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` varchar(4) NOT NULL,
  `branch_address` varchar(200) DEFAULT NULL,
  `latitude` varchar(45) DEFAULT NULL,
  `longitude` varchar(45) DEFAULT NULL,
  `branch_type` varchar(45) DEFAULT NULL,
  `mobile_no` varchar(15) DEFAULT NULL,
  `email_id` varchar(100) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `branch_id_UNIQUE` (`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_master`
--

LOCK TABLES `branch_master` WRITE;
/*!40000 ALTER TABLE `branch_master` DISABLE KEYS */;
INSERT INTO `branch_master` VALUES (1,'1','B1/244 Sec G,Jankipuram, Luknow',NULL,NULL,'Head Branch','8400512444','info@windchimeseducation.com','SYSTEM','2017-03-03 07:59:04','SYSTEM','2017-03-03 07:59:04','F'),(2,'2','House no-133, Sec-8B,Vrindavan Yojna-2,Near Shaheed Path, Lucknow',NULL,NULL,'Franchisee Office','7880670854','info@windchimeseducation.com','SYSTEM','2017-03-03 07:59:04','SYSTEM','2017-03-03 07:59:04','F'),(3,'3','C133, Sec G, in front of Multu Activity Park,Near Zonal Park, Lda Road, Lucknow',NULL,NULL,'Franchisee Office','91701411111','info@windchimeseducation.com','SYSTEM','2017-03-03 07:59:04','SYSTEM','2017-03-03 07:59:04','F'),(4,'4','1284, Sector 6,Bahadurgarh - 124507 Haryana',NULL,NULL,'Franchisee Office','9215155333','info@windchimeseducation.com','SYSTEM','2017-03-03 07:59:04','SYSTEM','2017-03-03 07:59:04','F');
/*!40000 ALTER TABLE `branch_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `broadcast_topic_master`
--

DROP TABLE IF EXISTS `broadcast_topic_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `broadcast_topic_master` (
  `id` varchar(255) NOT NULL,
  `broadcast_id` varchar(255) NOT NULL,
  `broadcast_topic_name` varchar(255) NOT NULL,
  `broadcast_topic` varchar(255) NOT NULL,
  `broadcast_topic_discription` varchar(255) DEFAULT NULL,
  `others` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_id_UNIQUE` (`broadcast_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `broadcast_topic_master`
--

LOCK TABLES `broadcast_topic_master` WRITE;
/*!40000 ALTER TABLE `broadcast_topic_master` DISABLE KEYS */;
INSERT INTO `broadcast_topic_master` VALUES ('1','1','General Broadcast','/topics/general','General broadcast',NULL,'2017-03-03 13:29:04','SYSTEM','SYSTEM','2017-03-03 13:29:04','F'),('2','2','Promotional Broadcast','/topics/promotion','Promotion broadcast',NULL,'2017-03-03 13:29:04','SYSTEM','SYSTEM','2017-03-03 13:29:04','F'),('3','3','Event Broadcast','/topics/event','Event broadcast',NULL,'2017-03-03 13:29:04','SYSTEM','SYSTEM','2017-03-03 13:29:04','F');
/*!40000 ALTER TABLE `broadcast_topic_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_master`
--

DROP TABLE IF EXISTS `class_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` varchar(255) NOT NULL,
  `class_name` varchar(20) NOT NULL,
  `discription` varchar(10) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_master`
--

LOCK TABLES `class_master` WRITE;
/*!40000 ALTER TABLE `class_master` DISABLE KEYS */;
INSERT INTO `class_master` VALUES (1,'1','KG','KG','SYSTEM',NULL,'SYSTEM',NULL,'F');
/*!40000 ALTER TABLE `class_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connect_master`
--

DROP TABLE IF EXISTS `connect_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connect_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email_id` varchar(45) DEFAULT NULL,
  `mobile_no` varchar(500) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connect_master`
--

LOCK TABLES `connect_master` WRITE;
/*!40000 ALTER TABLE `connect_master` DISABLE KEYS */;
INSERT INTO `connect_master` VALUES (8,'1485933486777','info.paalan@gmail.com','8828228072','nice to have paalan','SYSTEM','2017-03-05 13:40:08','SYSTEM','2017-03-05 13:40:08','F');
/*!40000 ALTER TABLE `connect_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_details_master`
--

DROP TABLE IF EXISTS `device_details_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_details_master` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `imei_no` varchar(30) NOT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `device_model` varchar(255) DEFAULT NULL,
  `notification_id` varchar(255) DEFAULT NULL,
  `os_version` varchar(255) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_device_id_UNIQUE` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_details_master`
--

LOCK TABLES `device_details_master` WRITE;
/*!40000 ALTER TABLE `device_details_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `device_details_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_master`
--

DROP TABLE IF EXISTS `event_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_id` varchar(45) DEFAULT NULL,
  `event_title` varchar(45) DEFAULT NULL,
  `event_sub_title` varchar(1000) DEFAULT NULL,
  `event_category` varchar(1000) DEFAULT NULL,
  `event_discription` varchar(1000) DEFAULT NULL,
  `event_startdt` timestamp NULL DEFAULT NULL,
  `event_enddt` timestamp NULL DEFAULT NULL,
  `event_location` varchar(2000) DEFAULT NULL,
  `event_img1` varchar(500) DEFAULT NULL,
  `event_img2` varchar(500) DEFAULT NULL,
  `event_img3` varchar(500) DEFAULT NULL,
  `event_img4` varchar(500) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `event_id_UNIQUE` (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_master`
--

LOCK TABLES `event_master` WRITE;
/*!40000 ALTER TABLE `event_master` DISABLE KEYS */;
INSERT INTO `event_master` VALUES (1,'1','Mothers day','Celebration at lucknow branch','Ethnic','Mother\'s day','2017-03-03 07:59:04','2017-03-03 12:59:04','Lucknow branch','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=1.jpg','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=1.jpg','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=1.jpg','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=1.jpg','SYSTEM','2017-03-03 07:59:04','SYSTEM','2017-03-03 07:59:04','F'),(2,'2','Father\'s day','Celebration at lucknow branch','Ethnic','Father\'s day','2017-03-07 07:59:04','2017-03-07 12:59:04','Lucknow branch','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=1.jpg','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=1.jpg','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=1.jpg','http://192.168.0.237:8090/windchim/ImageHandlerServlet?entity=slider&img=1.jpg','SYSTEM','2017-03-03 07:59:04','SYSTEM','2017-03-03 07:59:04','F');
/*!40000 ALTER TABLE `event_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `general_broadcast_master`
--

DROP TABLE IF EXISTS `general_broadcast_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `general_broadcast_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `sub_title` varchar(100) DEFAULT NULL,
  `discription` varchar(1000) NOT NULL,
  `branch_name` varchar(500) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gbm_id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_broadcast_master`
--

LOCK TABLES `general_broadcast_master` WRITE;
/*!40000 ALTER TABLE `general_broadcast_master` DISABLE KEYS */;
INSERT INTO `general_broadcast_master` VALUES (6,'dfg','dfg','	dfgfd							  ','B1/244 Sec G,Jankipuram, Luknow','dfgdf','2017-04-20 08:30:55','SYSTEM','2017-04-20 08:30:55','F'),(7,'asdsadasd','sadasdsad','Hi this is holi','B1/244 Sec G,Jankipuram, Luknow','SYSTEM','2017-04-20 08:37:01','SYSTEM','2017-04-20 08:37:01','F'),(8,'ggh','fghfg','fdgh','B1/244 Sec G,Jankipuram, Luknow','fdgh','2017-04-20 08:55:07','SYSTEM','2017-04-20 08:55:07','F'),(9,'fdfhgfd','fdhfgd','fdghfd','B1/244 Sec G,Jankipuram, Luknow','fghdh','2017-04-20 08:56:34','SYSTEM','2017-04-20 08:56:34','F'),(10,'asdsa','asdas','dasdsa','House no-133, Sec-8B,Vrindavan Yojna-2,Near Shaheed Path, Lucknow','sadad','2017-04-20 09:08:27','SYSTEM','2017-04-20 09:08:27','F'),(11,'sadfs','safsadf','sadf','B1/244 Sec G,Jankipuram, Luknow','sadf','2017-04-20 09:13:41','SYSTEM','2017-04-20 09:13:41','F'),(12,'sadfs','safsadf','sadf','B1/244 Sec G,Jankipuram, Luknow','sadf','2017-04-20 09:14:57','SYSTEM','2017-04-20 09:14:57','F'),(13,'Diwali celebration','Diwali','Windchimes celebrates diwali at it\'s lucknow center. Please come.','B1/244 Sec G,Jankipuram, Luknow','Admin','2017-04-20 09:18:46','SYSTEM','2017-04-20 09:18:46','F'),(14,'Developer','safsadf','sdf','B1/244 Sec G,Jankipuram, Luknow','sdf','2017-04-20 09:47:06','SYSTEM','2017-04-20 09:47:06','F'),(15,'sadf','sfddf','sadfsdf','B1/244 Sec G,Jankipuram, Luknow','sdfdf','2017-04-20 09:47:21','SYSTEM','2017-04-20 09:47:21','F'),(16,'sdfsdf','sdfs','safdf','B1/244 Sec G,Jankipuram, Luknow','sdfg','2017-04-20 09:47:34','SYSTEM','2017-04-20 09:47:34','F');
/*!40000 ALTER TABLE `general_broadcast_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `home_work_broadcast_master`
--

DROP TABLE IF EXISTS `home_work_broadcast_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_work_broadcast_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `sub_title` varchar(20) NOT NULL,
  `discription` varchar(10) NOT NULL,
  `class_name` varchar(10) NOT NULL,
  `branch_id` varchar(10) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hwbm_id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `home_work_broadcast_master`
--

LOCK TABLES `home_work_broadcast_master` WRITE;
/*!40000 ALTER TABLE `home_work_broadcast_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `home_work_broadcast_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_broadcast_master`
--

DROP TABLE IF EXISTS `student_broadcast_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_broadcast_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enrollment_id` varchar(45) NOT NULL,
  `branch_id` varchar(45) DEFAULT NULL,
  `class_name` varchar(500) DEFAULT NULL,
  `imei_no` varchar(1000) DEFAULT NULL,
  `device_id` varchar(1000) DEFAULT NULL,
  `notification_id` varchar(1000) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `windchimp_student_notifi_uni_key` (`enrollment_id`,`branch_id`,`class_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_broadcast_master`
--

LOCK TABLES `student_broadcast_master` WRITE;
/*!40000 ALTER TABLE `student_broadcast_master` DISABLE KEYS */;
INSERT INTO `student_broadcast_master` VALUES (1,'WCPS306','1','PG','','7c6e7e6acd3eb403','ez5TOtg13cQ:APA91bFNOlMlOp44gh1-5c0sM5NXezZwd5_OrsgzeTk4k0wDv8A_7o8vNIaTJgxwZjMa2N_7rOP7JF42dvnBBQboXJZvtb2P9wi-fLnF6Xxfvr58LiqQutc7AgfUD60jlx9BMb6CSM1A','SYSTEM','2017-03-19 09:18:56','SYSTEM','2017-03-19 12:47:40','F');
/*!40000 ALTER TABLE `student_broadcast_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_homework_master`
--

DROP TABLE IF EXISTS `student_homework_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_homework_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `homework_id` varchar(45) NOT NULL,
  `branch_id` varchar(5) DEFAULT NULL,
  `class_name` varchar(5) DEFAULT NULL,
  `homework_dt` timestamp NULL DEFAULT NULL,
  `homework_discription` varchar(2000) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `windchimp_student_homework_uni_key` (`homework_id`,`branch_id`,`class_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_homework_master`
--

LOCK TABLES `student_homework_master` WRITE;
/*!40000 ALTER TABLE `student_homework_master` DISABLE KEYS */;
INSERT INTO `student_homework_master` VALUES (1,'PG_1','1','PG','2017-03-03 07:59:04','Solve two puzzles, do homework of english','SYSTEM','2017-03-03 07:59:04',NULL,NULL,'F');
/*!40000 ALTER TABLE `student_homework_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_profile_master`
--

DROP TABLE IF EXISTS `student_profile_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_profile_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enrollment_id` varchar(45) NOT NULL,
  `branch_id` varchar(5) DEFAULT NULL,
  `class_name` varchar(5) DEFAULT NULL,
  `father_no` varchar(10) DEFAULT NULL,
  `father_name` varchar(100) DEFAULT NULL,
  `mother_no` varchar(10) DEFAULT NULL,
  `mother_name` varchar(100) DEFAULT NULL,
  `joining_dt` varchar(15) DEFAULT NULL,
  `birth_dt` varchar(15) DEFAULT NULL,
  `permanent_address` varchar(500) DEFAULT NULL,
  `temp_address` varchar(500) DEFAULT NULL,
  `is_day_boarding` varchar(2) DEFAULT NULL,
  `is_van` varchar(2) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  `student_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `windchimp_student_profile_uni_key` (`enrollment_id`,`branch_id`,`class_name`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_profile_master`
--

LOCK TABLES `student_profile_master` WRITE;
/*!40000 ALTER TABLE `student_profile_master` DISABLE KEYS */;
INSERT INTO `student_profile_master` VALUES (1,'WCPS320','1','TOD','7800008978','Mhmd.Shamsh Reza','7275772878','Shamsm Reza','05/02/16','12/09/13','B1/57 Sec-G Jankipuram  LKO','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Ibrahim Shamsh Reza'),(2,'WCPS326','1','TOD','9415180073','Fakhruddin Ali','9415765778','Samreen Margoob','30/03/2016','19/01/2014','A-26  Alisha Nagar  Sec- I  Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aayat'),(3,'WCPS331','1','TOD','9838588625','Rajeev Kumar Mishra','9026222221','Janhvi Mohan','08/04/16','19/01/2014','12/7  Sahara State','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Atharv Mohan Kuma'),(4,'WCPS344','1','TOD','8009075600','Amit Kumar Singh','8853485124','Devyani Dayal','04/08/16','15/06/2014','I-98 647-B Jankipuram Garden  Near Apex Trauma Centre  lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Atharv Singh'),(5,'WCPS345','1','TOD','8726127730','Pradeep Kumar C.G','8564909662','Banu K.P','04/07/16','24/11/2014','C-34  Eldeco City  IIM Road  lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Yajath'),(6,'WCPS327','1','TOD','9760279786','Aasif Ahmed','9456048786','','30/03/2016','20/11/2013','A-26  Alisha Nagar  Sec-I Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Arsh Adil'),(7,'WCPS348','1','TOD','9415608218','Manoj Kumar','9170604283','Rekha','30/6/2016','10/02/14','647B/B31  JankiGarden Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Arva Pratap'),(8,'WCPS270','1','PG','8795253777','Vincent Anthony','9792520917','Neeta Anthony','21/04/2015','13/11/2013','Flat No.802 S-2 Eldeco Eden Park Apartments Kursi Road lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Monisha Anthony'),(9,'WCPS277','1','PG','9565670721','Narendra Kumar','8765740279','Meenu Baudh','26/06/2015','25/05/2013','DS-2/233 SEC -C Jankipuram Kursi Road LKO','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Agriya'),(10,'WCPS306','1','PG','9792972333','Rohit kr. Sirsat','9807070735','Suvidhi Verma','07/08/15','14/03/2013','C2/323 Sec -F  Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Ridhima Sirsat'),(11,'WCPS318','1','PG','9450649006','Ravi Shankar Jaiswal','7275705784','Shweta Jaiswal','25/01/2016','25/04/2013','H.no.643 M/846 Aziz Nagar Colony Madiyaon Thana Sitapur Road','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aryaan Jaiswal'),(12,'WCPS319','1','PG','7275772878','Mhmd.Shamsh Reza','7275772878','Shamsm Reza','05/02/16','26/06/2013','B1/57 Sec-G Jankipuram  LKO','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Alisha Shamsh Reza'),(13,'WCPS321','1','PG','9451948972','Amritansh','9918204309','Tinu  Katiya','19/02/2016','18/07/2013','D-2-494  Sec-F  Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Yash Chawdhary'),(14,'WCPS322','1','PG','8005180676','Nikhil Kumar Singh','8573029483','Jyoti Singh','29/02/2016','21/12/2013','E-2/374  Sec-F  Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aarav  Singh'),(15,'WCPS324','1','PG','9450025395','Ishwar Chandra Chaurasia','9839888839','Seema Chaurasia','14/03/2016','08/02/13','35 A Adil Nagar Kursi Road','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Kartik Chauasia'),(16,'WCPS329','1','PG','9792100226','Arun Singh','9005348635','Deepmala Verma','06/04/16','18/05/2013','645-A/426  Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Saanvi Singh'),(17,'WCPS330','1','PG','9936199364','Anku Chopra','8960817140','Priyanka Chopra','07/04/16','08/02/13','H.No. 208  Keshau Nagar Colony','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Saanvika Chopra'),(18,'WCPS332','1','PG','9935132555','Vijay Pratap','9935164222','Alka Devi Verma','12/04/16','04/02/14','30-B  Alisha Nagar  Sec -I Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Vatsal Patel'),(19,'WCPS333','1','PG','9450217273','Udit Narayan Singh','9450619909','Neetu Singh','13/04/2016','12/06/13','H.No- 647 B/337A  Jankipuram Garden LKO','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Atharv Singh'),(20,'WCPS334','1','PG','8601901111','Arvind Kumar Singh','9648270357','Vibha Singh','13/04/2016','19/04/2013','Deort Ruichara P.O Arjunpur Bakshi Ka Talab','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aadya Singh'),(21,'WCPS338','1','PG','9838244417','Praveen Singh Rathore','9044623633','Seema Singh','20/04/2016','05/01/14','647B/A33   Jankipuram   Lucknow','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aaradhya Singh'),(22,'WCPS340','1','PG','0','','8876231056','Dhanshree Basumatari','04/06/16','12/10/11','7/502  Sahara Grace  Malhar Deluxe  lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Adam Basumatari'),(23,'WCPS341','1','PG','7376307391','Narendra Mishra','9696116966','Namita Mishra','30/06/2016','18/12/2013','A-33 Kalyanpur Shivpuri  lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aayushman Mishra'),(24,'WCPS342','1','PG','9415548660','Shikar Srivastava','9450703831','Pallavi Srivastava','02/07/16','25/08/2013','M-2/65 Sec-I  Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Samriddhi Srivastava'),(25,'WCPS316','1','PG','9450769806','Manoj Kumar Shukla','8931824029','Richa Shukla','NULL','NULL','SS-56 55 Sec-D  Near Ring Road Jankipuram LKO','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Anika Shukla'),(26,'WCPS337','1','PG','7080806422','Atif Hussain','8853164066','Dr. Nausheen Atif','','','302  Mumtaz Apartment  Eden Clave Kursi Road  LKO.','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Nyra Atif'),(27,'WCPS349','1','PG','9335492044','Rajesh Kumar Singh','8004709522','Arti Singh','15/07/2016','28/06/2013','2/227  Sector -H  Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Akshit Kumar Singh'),(28,'WCPS350','1','PG','9236805907','Manas Nirmal','7607855805','Alka Kothari','25/07/2016','06/03/14','A-1/34  sec-I  Near capital convent School  Prabhat Chauraha  Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Rudraksh Singh'),(29,'WCPS355','1','PG','9161711820','Vinamra Sharma','7318257174','Shikha Shrama','','17/01/2014','HI -191 Sec- I Jankipuram near prabhat chaura lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Rytham Bhatt'),(30,'WCPS261','1','PN','7617870628','Satish Kumar','9889970828','Vandana Pandey','04/06/15','19/7/2012','Plot.no.7 Phool Bagh Enclave Opp.Gudumba Thana Kursi Road lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Himanya Pandey'),(31,'WCPS282','1','PN','8004971257','Ajay Kumar Verma','9005569423','Neelam Soni','06/07/15','13/03/2013','D-1/199 Sec- F Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Navya Verma'),(32,'WCPS299','1','PN','8853265807','Dr. Mohammad Kalim Ahmed Khan','9936420402','Afsana','22/07/2015','17/07/2013','Flat No. 303 Mumtaz Apartment  Eden Enclave Kursi Road lko.','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Mohammad Zohair Ahmed Khan'),(33,'WCPS305','1','PN','9654890640','Prabhat Kumar','8874266322','Archana Sharma','05/08/15','03/01/13','`3/101 Malhar Sahara Grace Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Sanvi Karn'),(34,'WCPS280','1','PN','0','Pawan Chandel','9450790512','Himani Singh','01/07/15','25/02/2013','Sec -I Jankipuram Behind Capital Convent School Prabhat Chauraha lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Arav Singh Chandel'),(35,'WCPS285','1','PN','9839315780','Anand Khare','8601988892','Shweta Prakash','08/07/15','23/04/2013','7/403 Sahara Grace Malhar Deluxe  Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Arnav Khare'),(36,'WCPS311','1','PN','9839649281','Hemant Kumar Singh','0','Sudha Singh','03/11/15','07/04/13','C-1/405 Sector -G  Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Shreyansh Singh'),(37,'WCPS315','1','PN','7408432762','Pankaj Srivastava','7408432763','Reena Srivastava','14/12/2015','17/10/2012','D-1/159  Sector-F  Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Anika Srivastava'),(38,'WCPS317','1','PN','9415088669','Kavindra Jain','9454888974','Anubha Jain','2018-01-16','12/02/13','C-2/174  Sec -F Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Sarvagya Jain'),(39,'WCPS276','1','PN','9455710833','Rahul Tiwari','7379753184','Lalita Tiwari','08/06/15','08/01/13','sec-8/417  Indra Nagar','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Sanidhya Tiwari'),(40,'WCPS323','1','PN','0','Jitendra Tomar','9792199341','Pooja Tomar','12/03/16','05/05/13','Villa no.-426  Eldeco City Near IIM','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Myra Singh'),(41,'WCPS267','1','PN','9839823512','Mohd.Zafar Hamid','9452536818','Dr.Shahla Parveen','17/04/2015','29/03/2013','29 Jankipuram Garden Near Capital Public School Jankipuram Sec-J lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Mohammad Alman Zafar'),(42,'WCPS253','1','PN','7071605054','Mr Praveen rastogi','9565573731','Mrs Diksha','03/12/15','25/9/2012','Village and Post Mondiyon Jakipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aradhya Rastogi'),(43,'WCPS290','1','PN','9125812099','Ashish Kumar','9125812049','Kavita Das Gautam','07/07/15','04/09/12','C-2/252 Sec-F Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Shagun'),(44,'WCPS301','1','PN','9305208613','DR.D.N Mishra','8687823700','Amrita Mishra','27/07/2015','','1 Prem Nagar near Genetis club  Behind Blue cross Hospital Kursi road lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Kashvi Mishra'),(45,'WCPS335','1','PN','9648404040','Akshay Singh','9648984040','Sapna Singh','2016-04-16','24/02/2013','401 Tower-S2 Eldeco Eden Park Estate Kursi Road','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Kabir Singh'),(46,'WCPS346','1','PN','9005526786','Ratnakar Singh','9935513400','Savita Singh','08/07/16','21/12/2012','E-2/436  Sec-F  Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Kartikey Singh'),(47,'WCPS347','1','PN','7897131530','Dr. Arun Kumar Maurya','7054528276','Arti Maurya','11/07/16','15/11/2012','1/18  Sec- J  Jankipuram Extention lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Anveshika Maurya'),(48,'WCPS279','1','PN','7786967781','Prakash Kumar Mishra','0','Dr.Supriya Bharti','30/06/2015','20/02/2013','C-2/204 Sec-F Jankipuram behind Icon Hospital LKO','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aarush Kumar Mishra'),(49,'WCPS351','1','PN','9410003313','Uday Mani Patel','9410000513','Pooja Patel','27/7/2016','02/11/12','16  Phool Bagh Enclave near Gudamba Police Station lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aarna Patel'),(50,'WCPS353','1','PN','9267355311','Manoj Kumar','8400552255','Upma','09/08/16','20/09/2013','Type- 2/18  Sec -D  Sachivalya colony Aliganj  lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Shivansh Chaudhary'),(51,'WCPS220','1','NUR','9634032834','Mr Atul Shukla','9454287608','Mrs Preeti Shukla','07/12/14','23/12/2011','Pawan kutir D-1/121 Sec- F jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Bheeni Shukla'),(52,'WCPS266','1','NUR','9919356204','Aneesh  Sachan','7068882318','Minakshi Sachan','13/04/2015','31/12/2011','D2/553 Sec-F  Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aryan Sachan'),(53,'WCPS268','1','NUR','7704067828','Ankit Dalela','9415467828','Parul','18/14/2015','06/02/12','2/627 Vikas Nagar Lucknow','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Adya Dalela'),(54,'WCPS248','1','NUR','9918226600','Mr. Aman Bond','9918442200','Mrs. Sonia Oliver','02/27/15','25/8/2011','10/41 Bahar-A Sahara State Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Jason Bond'),(55,'WCPS242','1','NUR','9454667199','Mr Dilip Kumar','8382851699','Mrs Sarita yadav','01/12/15','27/10/2011','3/906 Sector-H jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Anvi Yadav'),(56,'WCPS295','1','NUR','9838383780','Meraj Ahmed Khan','8004743539','Shabana khan','14/07/2015','08/08/12','Flat no.-404 Eden Enclave Mumtaz Apartment Kursi Road lko.','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Mysha Meraj'),(57,'WCPS255','1','NUR','9792000522','Mr Bhupendra Singh','9919100522','Mrs Reeta singh','18/3/2015','08/18/12','A-37 Shivpuri near Metro hospital Kalyanpur Lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Maulik Singh Bhakuni'),(58,'WCPS246','1','NUR','9415470330','Sheel kumar mishra','9598737034','monika mishra','01/19/15','25/03/2012','4/73 sc-4 jankipuram vistaar lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Ansh Mishra'),(59,'WCPS292','1','NUR','9450400009','Vinay Kumar Singh','9721500009','Nidhi Singh','09/07/15','26/03/2013','10/101 Malhar Delux Sahara Grace','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Ansh Vardhan Singh'),(60,'WCPS250','1','NUR','8090555135','Mr Dharmendra Soni','8090408363','Mrs Jodhi rastogi','03/02/15','20/9/2012','H.No.21 Eldeco Town IIM road in front of bal bharti school','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Yatharth'),(61,'WCPS251','1','NUR','9454659413','MrVivek Kr.Verma','9935480726','Mrs Ankita Verma','03/03/15','27/8/2012','D-1/46 sector -F jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Avyan'),(62,'WCPS254','1','NUR','9415003216','Mr Amit Kumar','9452971919','Adwita','03/14/15','02/05/12','7/201 Deepak Deluxe sahara grace jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Abhinav Singh'),(63,'WCPS281','1','NUR','9335226968','Abhay Kumar Suman','9696028560','Anupama Singha','04/07/15','12/06/12','Anupam Villa 140 Chudiyapurwa Sec -G  Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Aradhya'),(64,'WCPS298','1','NUR','8874509636','Dr. R.S. Rajpoot','9889436360','Dr. Stuti Tondon','22/7/2015','27/10/2012','1/501 Malhar Deluxe Sahara Grace Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Triaaksha Vikram Singh 27/10/2012'),(65,'WCPS328','1','NUR','9415596782','Ashish Bhadauria','7376124893','Jyoti','06/04/16','03/11/11','M-11/33  Sec-I  Jankipurram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Virat Singh'),(66,'WCPS352','1','NUR','7080529341','Rajesh Kumar Verma','8417023083','Kabita Devi','01/08/16','29/6/2012','H.N-616/52A  Khadri  Sitapur Road  lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Angel Verma'),(67,'WCPS354','1','NUR','9919881010','Amar Raghuvanshi','896021720','Beenu Raghuvanshi','12/08/16','21/11/2012','12/15 Yaman Sahara States Jankipuram lko','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Sadhan Raghuvanshi'),(68,'WCPS116','1','KG','9415892381','Mr Ajay Kr Singh','9415892379','Mrs Madhurima','07/31/12','24/11/2010','645-A/572 Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Veer KeshwerPratap Singh'),(69,'WCPS186','1','KG','7800007474','Mr Rochak tondon','9415104388','Mrs Shipra Tondon','12/17/13','23/9/2011','B-1/98-A Sec-G Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Manoshree Tandon'),(70,'WCPS187','1','KG','9936148700','Mr Sunil Kr Gupta','7505655106','Mrs Raksha Gupta','01/27/14','18/12/2010','4/345 Sec-H Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Abhinav Gupta'),(71,'WCPS131','1','KG','9415424451','Santosh Kumar Yadav','8004531547','Archana Yadav','10/08/15','26/02/2011','252  Keshav Nagar  Near Surbhi Public School  Near sitaour Raod  Lucknow','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Anshika'),(72,'WCPS203','1','KG','8604987576','Mrs Ashish Tripathi','7376333000','Mrs Anjali Tripathi','03/26/14','10/09/11','B-1/76 Sec-G jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Ananya Tripathi'),(73,'WCPS214','1','KG','9918000165','Mr Arvind Kr Nanda','9415465090','Mrs Jyoti Saroj','07/05/14','28/9/2011','E-2/369 Sec-F Jankipuram','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Rishit Nanda'),(74,'WCPS102','1','KG','9838653777','Ravindra Kumarv Yadav','9984653777','Namita Yadav','07/07/14','2022-09-09','22  Rahul Bhawan  Paharpur Chauraha  Kursi Road','NULL','N','N','SYSTEM',NULL,'SYSTEM',NULL,'F','Gauri Yadav');
/*!40000 ALTER TABLE `student_profile_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_register_master`
--

DROP TABLE IF EXISTS `student_register_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_register_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enrollment_id` varchar(45) NOT NULL,
  `branch_id` varchar(45) DEFAULT NULL,
  `class_name` varchar(500) DEFAULT NULL,
  `father_no` varchar(1000) DEFAULT NULL,
  `discription` varchar(1000) DEFAULT NULL,
  `mother_no` varchar(1000) DEFAULT NULL,
  `password` varchar(1000) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `windchimp_reg_uni_key` (`enrollment_id`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_register_master`
--

LOCK TABLES `student_register_master` WRITE;
/*!40000 ALTER TABLE `student_register_master` DISABLE KEYS */;
INSERT INTO `student_register_master` VALUES (1,'WCPS320','1','TOD','7800008978','NULL','7275772878','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(2,'WCPS326','1','TOD','9415180073','NULL','9415765778','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(3,'WCPS331','1','TOD','9838588625','NULL','9026222221','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(4,'WCPS344','1','TOD','8009075600','NULL','8853485124','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(5,'WCPS345','1','TOD','8726127730','NULL','8564909662','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(6,'WCPS327','1','TOD','9760279786','NULL','9456048786','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(7,'WCPS348','1','TOD','9415608218','NULL','9170604283','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(8,'WCPS270','1','PG','8795253777','NULL','9792520917','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(9,'WCPS277','1','PG','9565670721','NULL','8765740279','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(10,'WCPS306','1','PG','9792972333','NULL','9807070735','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(11,'WCPS318','1','PG','9450649006','NULL','7275705784','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(12,'WCPS319','1','PG','7275772878','NULL','7275772878','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(13,'WCPS321','1','PG','9451948972','NULL','9918204309','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(14,'WCPS322','1','PG','8005180676','NULL','8573029483','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(15,'WCPS324','1','PG','9450025395','NULL','9839888839','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(16,'WCPS329','1','PG','9792100226','NULL','9005348635','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(17,'WCPS330','1','PG','9936199364','NULL','8960817140','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(18,'WCPS332','1','PG','9935132555','NULL','9935164222','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(19,'WCPS333','1','PG','9450217273','NULL','9450619909','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(20,'WCPS334','1','PG','8601901111','NULL','9648270357','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(21,'WCPS338','1','PG','9838244417','NULL','9044623633','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(22,'WCPS340','1','PG','0','NULL','8876231056','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(23,'WCPS341','1','PG','7376307391','NULL','9696116966','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(24,'WCPS342','1','PG','9415548660','NULL','9450703831','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(25,'WCPS316','1','PG','9450769806','NULL','8931824029','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(26,'WCPS337','1','PG','7080806422','NULL','8853164066','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(27,'WCPS349','1','PG','9335492044','NULL','8004709522','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(28,'WCPS350','1','PG','9236805907','NULL','7607855805','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(29,'WCPS355','1','PG','9161711820','NULL','7318257174','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(30,'WCPS261','1','PN','7617870628','NULL','9889970828','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(31,'WCPS282','1','PN','8004971257','NULL','9005569423','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(32,'WCPS299','1','PN','8853265807','NULL','9936420402','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(33,'WCPS305','1','PN','9654890640','NULL','8874266322','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(34,'WCPS280','1','PN','0','NULL','9450790512','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(35,'WCPS285','1','PN','9839315780','NULL','8601988892','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(36,'WCPS311','1','PN','9839649281','NULL','0','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(37,'WCPS315','1','PN','7408432762','NULL','7408432763','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(38,'WCPS317','1','PN','9415088669','NULL','9454888974','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(39,'WCPS276','1','PN','9455710833','NULL','7379753184','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(40,'WCPS323','1','PN','0','NULL','9792199341','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(41,'WCPS267','1','PN','9839823512','NULL','9452536818','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(42,'WCPS253','1','PN','7071605054','NULL','9565573731','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(43,'WCPS290','1','PN','9125812099','NULL','9125812049','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(44,'WCPS301','1','PN','9305208613','NULL','8687823700','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(45,'WCPS335','1','PN','9648404040','NULL','9648984040','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(46,'WCPS346','1','PN','9005526786','NULL','9935513400','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(47,'WCPS347','1','PN','7897131530','NULL','7054528276','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(48,'WCPS279','1','PN','7786967781','NULL','0','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(49,'WCPS351','1','PN','9410003313','NULL','9410000513','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(50,'WCPS353','1','PN','9267355311','NULL','8400552255','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(51,'WCPS220','1','NUR','9634032834','NULL','9454287608','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(52,'WCPS266','1','NUR','9919356204','NULL','7068882318','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(53,'WCPS268','1','NUR','7704067828','NULL','9415467828','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(54,'WCPS248','1','NUR','9918226600','NULL','9918442200','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(55,'WCPS242','1','NUR','9454667199','NULL','8382851699','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(56,'WCPS295','1','NUR','9838383780','NULL','8004743539','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(57,'WCPS255','1','NUR','9792000522','NULL','9919100522','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(58,'WCPS246','1','NUR','9415470330','NULL','9598737034','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(59,'WCPS292','1','NUR','9450400009','NULL','9721500009','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(60,'WCPS250','1','NUR','8090555135','NULL','8090408363','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(61,'WCPS251','1','NUR','9454659413','NULL','9935480726','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(62,'WCPS254','1','NUR','9415003216','NULL','9452971919','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(63,'WCPS281','1','NUR','9335226968','NULL','9696028560','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(64,'WCPS298','1','NUR','8874509636','NULL','9889436360','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(65,'WCPS328','1','NUR','9415596782','NULL','7376124893','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(66,'WCPS352','1','NUR','7080529341','NULL','8417023083','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(67,'WCPS354','1','NUR','9919881010','NULL','8960217206','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(68,'WCPS116','1','KG','9415892381','NULL','9415892379','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(69,'WCPS186','1','KG','7800007474','NULL','9415104388','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(70,'WCPS187','1','KG','9936148700','NULL','7505655106','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(71,'WCPS131','1','KG','9415424451','NULL','8004531547','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(72,'WCPS203','1','KG','8604987576','NULL','7376333000','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(73,'WCPS214','1','KG','9918000165','NULL','9415465090','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F'),(74,'WCPS102','1','KG','9838653777','NULL','9984653777','NULL','SYSTEM',NULL,'SYSTEM',NULL,'F');
/*!40000 ALTER TABLE `student_register_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_master`
--

DROP TABLE IF EXISTS `survey_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email_id` varchar(45) DEFAULT NULL,
  `mobile_no` varchar(12) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `child_name` varchar(100) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_master`
--

LOCK TABLES `survey_master` WRITE;
/*!40000 ALTER TABLE `survey_master` DISABLE KEYS */;
INSERT INTO `survey_master` VALUES (9,'raman gupta','info.paalan@gmail.com','8828228072','nice to have paalan','abahy gupta','412-413, Palm spring, malad west','SYSTEM','2017-03-19 10:57:41','SYSTEM','2017-03-19 10:57:41','F');
/*!40000 ALTER TABLE `survey_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_master`
--

DROP TABLE IF EXISTS `user_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `code` varchar(20) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `login` varchar(10) NOT NULL,
  `middle_name` varchar(255) NOT NULL,
  `password` varchar(10) NOT NULL,
  `phome_number` varchar(10) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_dt` timestamp NULL DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_id_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master`
--

LOCK TABLES `user_master` WRITE;
/*!40000 ALTER TABLE `user_master` DISABLE KEYS */;
INSERT INTO `user_master` VALUES (1,'Mumabi','Admin','7709642004','raman@mobiowin.me','raman','gupta','admin','priyadarshi','admin','-','SYSTEM',NULL,'SYSTEM',NULL,'F'),(2,'Lcknow','user','-','manas@windchimes.com','manas','shukla','manas','-','admin123','-','SYSTEM',NULL,'SYSTEM',NULL,'F');
/*!40000 ALTER TABLE `user_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `welcome_screen_master`
--

DROP TABLE IF EXISTS `welcome_screen_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `welcome_screen_master` (
  `id` varchar(255) NOT NULL,
  `screen_id` varchar(255) DEFAULT NULL,
  `screen_name` varchar(255) DEFAULT NULL,
  `screen_text` varchar(255) DEFAULT NULL,
  `screen_img_link` varchar(255) DEFAULT NULL,
  `screen_seq` varchar(255) DEFAULT NULL,
  `text1` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_dt` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `welcome_screen_master`
--

LOCK TABLES `welcome_screen_master` WRITE;
/*!40000 ALTER TABLE `welcome_screen_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `welcome_screen_master` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-20 20:11:44
