create database if not exists test;

use test;

create table if not exists user (

  id            int(11) auto_increment,
  name          varchar(20) not null,
  login         varchar(20) not null unique,
  password      varchar(20) not null,

  primary key (id)
) default character set utf8;