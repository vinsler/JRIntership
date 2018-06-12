CREATE DATABASE IF NOT EXISTS test DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE test;

CREATE TABLE IF NOT EXISTS notes (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(20) not null,
  description varchar(100),
  create_date datetime NOT NULL,
  status tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
) default character set utf8;