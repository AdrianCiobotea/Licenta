--alter table category drop foreign key FK_group_id
--
--alter table product drop foreign key FK_category_id

drop table if exists product;

drop table if exists category;

drop table if exists cat_group;

drop table if exists image;


create table cat_group (
id integer not null auto_increment,
name varchar(255),
primary key (id)) engine=InnoDB;

create table image (
id integer not null auto_increment,
image LONGBLOB,
primary key (id)) engine=InnoDB;

create table category (
    id integer not null auto_increment,
    name varchar(255) not null,
    group_id integer,
    primary key (id)) engine=InnoDB;

create table product (
    id integer not null auto_increment,
    name varchar(255) not null,
    price double precision not null,
    description varchar(255) not null,
    category_id integer not null,
    image_id integer,
    is_extra bit not null,
    primary key (id)) engine=InnoDB;

alter table category add constraint FK_group_id foreign key (group_id) references cat_group (id);

alter table product add constraint FK_category_id foreign key (category_id) references category (id);

alter table product add constraint FK_image_id foreign key (image_id) references image (id);