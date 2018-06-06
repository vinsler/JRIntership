CREATE SHEMA IF NOT EXIST 'test' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE test;

CREATE TABLE IF NOT EXIST `notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createDate` date NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;