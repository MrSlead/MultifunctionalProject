drop table if exists archive;

drop table if exists archive_seq;

create table archive (
    id bigint not null,
    uuid varchar(255) not null,
    first_name varchar(255),
    last_name varchar(255),
    patronymic varchar(255),
    date_of_birth date,
    place_of_residence varchar(255),
    serial_number varchar(255),
    primary key (id)
) engine=InnoDB;

create table archive_seq (
    next_val bigint
) engine=InnoDB;

INSERT INTO archive_seq values (1)