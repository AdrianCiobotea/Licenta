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

create table shopping_items (
id integer not null auto_increment,
quantity integer not null,
parent_id Integer,
primary key (id)) engine=InnoDB;