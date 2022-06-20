drop table if exists status;

drop table if exists payment;

drop table if exists shopping_cart;

drop table if exists shopping_item;

create table status (
id integer not null auto_increment,
status varchar(20),
primary key (id)) engine=InnoDB;

create table shopping_cart (
id integer not null auto_increment,
initiator_id integer not null,
primary key (id)) engine=InnoDB;

create table payment (
id integer not null auto_increment,
amount double not null,
method varchar(10) not null,
primary key (id)) engine=InnoDB;

create table shopping_item (
id integer not null auto_increment,
quantity integer not null,
parent_id Integer,
status_id Integer,
shopping_cart_id Integer,
user_id Integer,
product_id Integer,
primary key (id)) engine=InnoDB;