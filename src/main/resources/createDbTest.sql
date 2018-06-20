create database if not exists test default character set utf8 collate utf8_general_ci;

use test;

create table if not exists notes (

  id            int(11) auto_increment,
  name          varchar(20) not null,
  description   varchar(100),
  create_date   datetime not null,
  status        tinyint(1) not null default 0,
  users_id      int not null,

  primary key (id),
  foreign key (users_id) references users(id)
) default character set utf8;