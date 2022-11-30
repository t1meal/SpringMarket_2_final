--liquibase formatted sql

--changeset t1meal: init_products_table

create table categories
(
    id         bigserial primary key,
    title      varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table products
(
    id          bigserial primary key,
    category_id bigint references categories (id),
    title       varchar(255),
    price       numeric (8, 2),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp

);






