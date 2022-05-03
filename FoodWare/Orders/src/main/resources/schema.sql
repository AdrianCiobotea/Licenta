drop table if exists status;

drop table if exists payment;

drop table if exists restaurant_order;

drop table if exists restaurant_order_item;

drop table if exists restaurant_sub_order;

drop table if exists restaurant_table;

create table status (
id integer not null auto_increment,
status varchar(20),
primary key (id)) engine=InnoDB;

create table restaurant_order (
id integer not null auto_increment,
initiator_id integer not null,
sub_order_id integer not null,
table_id integer not null,
primary key (id)) engine=InnoDB;

create table restaurant_sub_order (
id integer not null auto_increment,
payment_id integer not null,
user_id integer not null,
primary key (id)) engine=InnoDB;

create table restaurant_table(
id integer not null auto_increment,
table_id varchar(150) not null,
table_status integer not null,
primary key (id)) engine=InnoDB;

create table payment (
id integer not null auto_increment,
amount DOUBLE not null,
method varchar(10) not null,
primary key (id)) engine=InnoDB;

create table restaurant_order_item (
id integer not null auto_increment,
quantity integer not null,
status_id Integer,
sub_order_id Integer,
product_id Integer,
primary key (id)) engine=InnoDB;