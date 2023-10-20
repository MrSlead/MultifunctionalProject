drop table if exists products;

drop table if exists products_seq;

create table products (
    id bigint not null,
    uuid varchar(255),
    date_time_purchase datetime(6),
    name varchar(255),
    description text,
    primary key (id)
) engine=InnoDB;

create table products_seq (
    next_val bigint
) engine=InnoDB;

insert into products_seq values (1);