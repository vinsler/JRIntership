create database if not exists test default character set utf8 collate utf8_general_ci;;

use test;

drop table if exists note;
drop table if exists user;

create table if not exists user (

  id            int(11) auto_increment,
  name          varchar(20) not null,
  login         varchar(20) not null unique,
  password      varchar(20) not null,

  primary key (id)
) default character set utf8;

create database if not exists test default character set utf8 collate utf8_general_ci;

use test;

create table if not exists note (

  id            int(11) auto_increment,
  name          varchar(20) not null,
  description   varchar(100),
  create_date   datetime not null,
  status        tinyint(1) not null default 0,
  user_id      int not null,

  primary key (id),
  foreign key (user_id) references user(id)
) default character set utf8;

insert into user (name, login, password) values ('test', 'test', 'test');
insert into user (name, login, password) values ('root', 'root', 'root');
insert into user (name, login, password) values ('Лёха', 'vins', 'root');
insert into user (name, login, password) values ('Мама', 'murzik', 'toot');
insert into user (name, login, password) values ('Папа', 'bill', 'gei');
insert into user (name, login, password) values ('Вован', 'ganben', 'nabegn');
insert into user (name, login, password) values ('Маня', 'lake','hren');

insert into note (name, description, create_date, user_id)
values ('Детям','Посмотреть книжки и наклейки в магазине.', '2018-04-22', 1);

insert into note (name, description, create_date, user_id)
values ('Пиво', 'Купить пшеничного пива в Градусах, кстати недорого. :-)))', '2018-06-22', 1);

insert into note (name, description, create_date, user_id)
values ('JDBC SQL', 'Посмотреть видео разных реализаций по технологии.', '2018-05-22', 2);

insert into note (name, description, create_date, user_id)
values ('SQL-EX', 'Почитать несколько уроков по учебнику, выполнить пару задач.', '2018-03-12', 2);

insert into note (name, description, create_date, user_id)
values ('Гараж', 'Сьездить в гараж, забрать ЦПС, отвезти тестю.', '2018-04-17', 3);

insert into note (name, description, create_date, user_id)
values ('Spring','Почитать про введение в спринг.', '2018-04-22', 1);

insert into note (name, description, create_date, user_id)
values ('Hibenate', 'Посмотреть реализацию хибернейта.', '2018-06-22', 1);

insert into note (name, description, create_date, user_id)
values ('Футбол', 'Сходить поиграть в футбол вечером.', '2018-05-22', 1);

insert into note (name, description, create_date, user_id)
values ('MD', 'Написать файл readme.md.', '2018-03-12', 1);

insert into note (name, description, create_date, user_id)
values ('md deep', 'Найти реализации и документацию к markdown.', '2018-04-17', 1);

insert into note (name, description, create_date, user_id)
values ('Test','Протестировать приложение.', '2018-04-22', 1);

insert into note (name, description, create_date, user_id)
values ('SIA', 'Найти книгу и начать читать спринг в действии.', '2018-06-22', 1);

insert into note (name, description, create_date, user_id)
values ('SprVid', 'Поискать видео по спрингу.', '2018-05-22', 1);

insert into note (name, description, create_date, user_id)
values ('Sof', 'Задать вопрос по схеме спринга.', '2018-03-12', 1);

insert into note (name, description, create_date, user_id)
values ('JR', 'Мониторить начало стажировки на сайте.', '2018-04-17', 1);