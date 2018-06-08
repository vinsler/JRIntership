create database if not exists test
  default character set utf8;

use test;

create table if not exists notes (
  id          int         not null auto_increment,
  name        varchar(20) not null,
  description varchar(100),
  create_date datetime    not null,
  status      tinyint     not null default 0,
  primary key (id)
)