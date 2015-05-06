/*
SQLyog Enterprise - MySQL GUI v8.18 
MySQL - 5.1.36-community : Database - edbarcode_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`edbarcode_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `edbarcode_demo`;

/*Table structure for table `ed_entrepreneur` */

DROP TABLE IF EXISTS `ed_entrepreneur`;

CREATE TABLE `ed_entrepreneur` (
  `entrepreneur_id` int(11) NOT NULL AUTO_INCREMENT,
  `tax_no` varchar(15) DEFAULT NULL,
  `license_allowed_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`entrepreneur_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `ed_entrepreneur` */

insert  into `ed_entrepreneur`(`entrepreneur_id`,`tax_no`,`license_allowed_name`) values (1,'3-1015-1763-7','บริษัท สยามไวเนอรี่ จำกัด'),(2,NULL,'ห้างหุ้นส่วน ตะวันแดงสุรากลั่น จำกัด');

/*Table structure for table `ed_entrepreneur_product_mapping` */

DROP TABLE IF EXISTS `ed_entrepreneur_product_mapping`;

CREATE TABLE `ed_entrepreneur_product_mapping` (
  `entreprenrur_product_mapping_id` int(11) NOT NULL AUTO_INCREMENT,
  `entrepreneur_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`entreprenrur_product_mapping_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `ed_entrepreneur_product_mapping` */

insert  into `ed_entrepreneur_product_mapping`(`entreprenrur_product_mapping_id`,`entrepreneur_id`,`product_id`) values (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,3),(6,2,5),(7,1,8),(8,1,9),(9,1,10),(10,1,11),(11,1,12),(12,1,13);

/*Table structure for table `ed_factory` */

DROP TABLE IF EXISTS `ed_factory`;

CREATE TABLE `ed_factory` (
  `factory_id` int(11) NOT NULL AUTO_INCREMENT,
  `entrepreneur_id` int(11) DEFAULT NULL,
  `factory_name` varchar(200) DEFAULT NULL,
  `licenseNo` varchar(20) DEFAULT NULL,
  `licenseStartDate` varchar(10) DEFAULT NULL,
  `licenseEndDate` varchar(10) DEFAULT NULL,
  `factory_address` text,
  PRIMARY KEY (`factory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `ed_factory` */

insert  into `ed_factory`(`factory_id`,`entrepreneur_id`,`factory_name`,`licenseNo`,`licenseStartDate`,`licenseEndDate`,`factory_address`) values (1,1,'บริษัท สยามไวเนอรี่ จำกัด','2558/70605817600002','01/01/2558','31/12/2558','9/2 หมู่ 3 ต.บางโทรัด อ.เมืองสมุทรสาคร จ.สมุทรสาคร 74000'),(2,1,'หัวหินฮิลส์ วินยาร์ด','2558/19002900800001','01/01/2558','31/12/2558','204 บ้านคอกช้างพัฒนา ต.หนองพลับ อ.หัวหิน จ.ประจวบคีรีขันธ์');

/*Table structure for table `ed_product` */

DROP TABLE IF EXISTS `ed_product`;

CREATE TABLE `ed_product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_group_code` varchar(4) DEFAULT NULL,
  `product_group_name` varchar(50) DEFAULT NULL,
  `product_code` varchar(8) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `brand_major_code` varchar(3) DEFAULT NULL,
  `brand_major_name` varchar(50) DEFAULT NULL,
  `brand_minor_code` varchar(2) DEFAULT NULL,
  `brand_minor_name` varchar(50) DEFAULT NULL,
  `model_code` varchar(3) DEFAULT NULL,
  `model_name` varchar(50) DEFAULT NULL,
  `size_code` varchar(3) DEFAULT NULL,
  `size_value` decimal(6,3) DEFAULT NULL,
  `unit_code` varchar(2) DEFAULT NULL,
  `unit_name` varchar(50) DEFAULT NULL,
  `degree_code` varchar(2) DEFAULT NULL,
  `degree_value` decimal(10,2) DEFAULT NULL,
  `tax_by_value` decimal(10,2) DEFAULT NULL,
  `tax_by_capacity` decimal(10,2) DEFAULT NULL,
  `tax_by_liter` decimal(10,2) DEFAULT NULL,
  `lowest_degree_no_tax` decimal(10,2) DEFAULT NULL,
  `tax_plus_by_degree` decimal(10,2) DEFAULT NULL,
  `lowest_selling_price_no_tax` decimal(10,2) DEFAULT NULL,
  `tax_plus_by_selling_price` decimal(10,2) DEFAULT NULL,
  `announce_price_date` varchar(10) DEFAULT NULL,
  `announce_price_value` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `ed_product` */

insert  into `ed_product`(`product_id`,`product_group_code`,`product_group_name`,`product_code`,`product_name`,`brand_major_code`,`brand_major_name`,`brand_minor_code`,`brand_minor_name`,`model_code`,`model_name`,`size_code`,`size_value`,`unit_code`,`unit_name`,`degree_code`,`degree_value`,`tax_by_value`,`tax_by_capacity`,`tax_by_liter`,`lowest_degree_no_tax`,`tax_plus_by_degree`,`lowest_selling_price_no_tax`,`tax_plus_by_selling_price`,`announce_price_date`,`announce_price_value`) values (1,'7001','สุราแช่','70010101','เบียร์ ลีโอ','','ลีโอ','',NULL,'000','-','001','0.100','04','ขวด','08','10.00','48.00','155.00','8.00','7.00','3.00','0.00','0.00',NULL,NULL),(2,'7001','สุราแช่','70010101','เบียร์ ลีโอ','','ลีโอ','',NULL,'000','-','002',NULL,'04','ขวด','08','6.40','48.00','155.00','8.00','7.00','3.00','0.00','0.00',NULL,NULL),(3,'7001','สุราแช่','70010101','เบียร์ ลีโอ','','ลีโอ','',NULL,'000','-','003',NULL,'04','ขวด','08','5.00','48.00','155.00','8.00','7.00','3.00','0.00','0.00',NULL,NULL),(4,'7001','สุราแช่','70010101','เบียร์ ลีโอ','','ลีโอ','',NULL,'000','-','004',NULL,'04','ขวด','08','5.00','48.00','155.00','8.00','7.00','3.00','0.00','0.00',NULL,NULL),(5,'7001','สุราแช่','70010101','เบียร์ ลีโอ','','ลีโอ','',NULL,'000','-','005',NULL,'04','ขวด','08','5.00','48.00','155.00','8.00','7.00','3.00','0.00','0.00',NULL,NULL),(6,'7001','สุราแช่','70010101','เบียร์ ลีโอ','','ลีโอ','',NULL,'000','-','005',NULL,'04','ขวด','08','5.00','48.00','155.00','8.00','7.00','3.00','0.00','0.00',NULL,NULL),(7,'7001','สุราแช่','70010101','เบียร์ ลีโอ','','ลีโอ','',NULL,'000','-','005',NULL,'04','ขวด','08','5.00','48.00','155.00','8.00','7.00','3.00','0.00','0.00',NULL,NULL),(8,'7001','สุราแช่','70010101','สุราแช่ผลไม้ Berri Estates Five Star White\r\n',NULL,NULL,NULL,NULL,'000','-','005','3.000','04','ขวด','08','12.50','22.74','30.00','10.00','10.00','10.00','0.00','0.00',NULL,NULL),(9,'7001','สุราแช่','70010101','สุราแช่ผลไม้ Fincade Malpica Smooth Red\r\n',NULL,NULL,NULL,NULL,'000','-','005','0.750','04','ขวด','08','13.50','10.48','7.50','10.00','10.00','10.00','0.00','0.00',NULL,NULL),(10,'7001','สุราแช่','70010101','สุราแช่ผลไม้ Fincade Malpica Fresh White\r\n',NULL,NULL,NULL,NULL,'000','-','005','0.750','04','ขวด','08','12.50','10.48','7.50','10.00','10.00','10.00','0.00','0.00',NULL,NULL),(11,'7001','สุราแช่','70010101','สุราแช่ผลไม้ Fincade Malpica Full Red\r\n',NULL,NULL,NULL,NULL,'000','-','005','0.750','04','ขวด','08','13.50','10.48','7.50','10.00','10.00','10.00','0.00','0.00',NULL,NULL),(12,'7001','สุราแช่','70010101','สุราแช่ผลไม้ Fincade Malpica Smooth Red\r\n',NULL,NULL,NULL,NULL,'000','-','005','4.000','04','ขวด','08','13.50','28.00','40.00','10.00','10.00','10.00','0.00','0.00',NULL,NULL),(13,'7001','สุราแช่','70010101','สุราแช่ผลไม้ Fincade Malpica Fresh White\r\n',NULL,NULL,NULL,NULL,'000','-','005','4.000','04','ขวด','08','12.50','28.00','40.00','10.00','10.00','10.00','0.00','0.00',NULL,NULL);

/*Table structure for table `ed_tmp_tax_doc_detail` */

DROP TABLE IF EXISTS `ed_tmp_tax_doc_detail`;

CREATE TABLE `ed_tmp_tax_doc_detail` (
  `ed_tmp_tax_doc_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `ed_tmp_tax_doc_master_id` int(11) DEFAULT NULL,
  `product_code` varchar(10) DEFAULT NULL,
  `product_piece` int(11) DEFAULT NULL,
  `selling_price_by_owner` decimal(10,2) DEFAULT NULL,
  `selling_price_by_department` decimal(10,2) DEFAULT NULL,
  `tax_by_value` decimal(10,2) DEFAULT NULL,
  `tax_by_capacity` decimal(10,2) DEFAULT NULL,
  `tax_by_value_plus` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ed_tmp_tax_doc_detail_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `ed_tmp_tax_doc_detail` */

insert  into `ed_tmp_tax_doc_detail`(`ed_tmp_tax_doc_detail_id`,`ed_tmp_tax_doc_master_id`,`product_code`,`product_piece`,`selling_price_by_owner`,`selling_price_by_department`,`tax_by_value`,`tax_by_capacity`,`tax_by_value_plus`) values (1,3,'P0001',10,'40.00','0.00','20.00','25.00','0.00'),(2,3,'P0002',5,'35.00','0.00','15.00','30.00','0.00'),(3,4,'0000000001',10000,'454.89','0.00','22.74','30.00','0.00'),(4,4,'0000000002',5000,'209.57','0.00','10.48','7.50','0.00'),(5,4,'0000000003',6000,'209.57','0.00','10.48','7.50','0.00'),(6,4,'0000000004',5000,'209.57','0.00','10.48','7.50','0.00'),(7,4,'0000000005',20000,'560.02','0.00','28.00','40.00','0.00'),(8,4,'0000000006',20000,'560.02','0.00','28.00','40.00','0.00');

/*Table structure for table `ed_tmp_tax_doc_master` */

DROP TABLE IF EXISTS `ed_tmp_tax_doc_master`;

CREATE TABLE `ed_tmp_tax_doc_master` (
  `ed_tmp_tax_doc_master_id` int(11) NOT NULL AUTO_INCREMENT,
  `reference_code` varchar(20) DEFAULT NULL,
  `license_no` varchar(20) DEFAULT NULL,
  `sum_tax_by_value` decimal(10,2) DEFAULT NULL,
  `sum_tax_by_capacity` decimal(10,2) DEFAULT NULL,
  `receipt` varchar(20) DEFAULT NULL,
  `reduce_tax_product_baht` decimal(10,2) DEFAULT NULL,
  `reduce_tax_by_dep_book_no_baht` decimal(10,2) DEFAULT NULL,
  `tax_by_moi` decimal(10,2) DEFAULT NULL,
  `tax_by_thai_health` decimal(10,2) DEFAULT NULL,
  `tax_by_thai_pbs` decimal(10,2) DEFAULT NULL,
  `tax_by_nsdf` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ed_tmp_tax_doc_master_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `ed_tmp_tax_doc_master` */

insert  into `ed_tmp_tax_doc_master`(`ed_tmp_tax_doc_master_id`,`reference_code`,`license_no`,`sum_tax_by_value`,`sum_tax_by_capacity`,`receipt`,`reduce_tax_product_baht`,`reduce_tax_by_dep_book_no_baht`,`tax_by_moi`,`tax_by_thai_health`,`tax_by_thai_pbs`,`tax_by_nsdf`) values (4,'REF12345ABCD','2558/70605817600002','1515141.00','2020000.00','','0.00','0.00','353514.10','70702.82','53027.12','70702.82'),(3,'12345ABCDE','2558/70605817600002','35.00','55.00','','0.00','0.00','9.00','3.00','2.00','3.00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
