drop table if exists user;

create table user (
id integer not null auto_increment,
phone_number varchar(255),
password varchar(255),
user_role varchar(15),
primary key (id)) engine=InnoDB;
