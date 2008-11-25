-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema jspam
--

CREATE DATABASE IF NOT EXISTS jspam;
USE jspam;

--
-- Definition of table `estadisticas`
--

DROP TABLE IF EXISTS `estadisticas`;
CREATE TABLE `estadisticas` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `totalMailsSpam` int(10) unsigned NOT NULL,
  `totalMailsNoSpam` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='estadisticas de mails leidos';

--
-- Dumping data for table `estadisticas`
--

/*!40000 ALTER TABLE `estadisticas` DISABLE KEYS */;
INSERT INTO `estadisticas` (`id`,`totalMailsSpam`,`totalMailsNoSpam`) VALUES 
 (6,2,1);
/*!40000 ALTER TABLE `estadisticas` ENABLE KEYS */;


--
-- Definition of table `word`
--

DROP TABLE IF EXISTS `word`;
CREATE TABLE `word` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `word` varchar(45) NOT NULL,
  `probabilidadSpam` decimal(10,4) NOT NULL,
  `probabilidadNoSpam` decimal(10,4) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_2` (`word`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='contiene las palabras y sus probabilidades';

--
-- Dumping data for table `word`
--

/*!40000 ALTER TABLE `word` DISABLE KEYS */;
INSERT INTO `word` (`id`,`word`,`probabilidadSpam`,`probabilidadNoSpam`) VALUES 
 (36,'www','0.5000','0.0000'),
 (37,'hay','0.5000','0.0000'),
 (38,'anda','0.5000','0.0000'),
 (39,'com','0.5000','0.0000'),
 (40,'no','0.5000','0.0000'),
 (41,'que','0.5000','0.0000'),
 (42,'mejor','0.5000','0.0000'),
 (43,'queres','0.5000','1.0000'),
 (44,'sexo','1.0000','0.0000'),
 (45,'probaste','0.5000','0.0000'),
 (46,'viagra','0.5000','0.0000'),
 (47,'citas','0.5000','0.0000'),
 (48,'nada','0.5000','0.0000'),
 (49,'tus','0.5000','0.0000'),
 (50,'el','0.5000','0.0000'),
 (51,'tener','0.5000','0.0000'),
 (52,'algun','0.0000','1.0000'),
 (53,'abrazo','0.0000','1.0000'),
 (54,'salida','0.0000','1.0000'),
 (55,'largo','0.0000','1.0000'),
 (56,'ir','0.0000','1.0000'),
 (57,'ganas','0.0000','1.0000'),
 (58,'Yo','0.0000','1.0000'),
 (59,'como','0.0000','1.0000'),
 (60,'lado','0.0000','1.0000'),
 (61,'tengo','0.0000','1.0000'),
 (62,'finde','0.0000','1.0000'),
 (63,'costa','0.0000','1.0000'),
 (64,'diciembre','0.0000','1.0000'),
 (65,'va','0.0000','1.0000');
INSERT INTO `word` (`id`,`word`,`probabilidadSpam`,`probabilidadNoSpam`) VALUES 
 (66,'de','0.0000','1.0000'),
 (67,'a','0.0000','1.0000'),
 (68,'para','0.0000','1.0000'),
 (69,'che','0.0000','1.0000');
/*!40000 ALTER TABLE `word` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
