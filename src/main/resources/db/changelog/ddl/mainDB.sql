--liquibase formatted sql

--changeset t1meal: create_main_table
create table products
(
    id    bigserial primary key,
    title varchar(255),
    price int
);

create table users
(
    id         bigserial primary key,
    username   varchar(30) not null unique,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         serial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table privileges
(
    id         serial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table roles_privileges
(
    role_id      int not null,
    privilege_id int not null,
    primary key (role_id, privilege_id),
    foreign key (role_id) references roles (id),
    foreign key (privilege_id) references privileges (id)
);

