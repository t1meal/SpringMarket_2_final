--liquibase formatted sql

--changeset t1meal: init_core_table

create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price numeric (8, 2),
    user_email  varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_title     varchar(255) not null,
    order_id          bigint       not null references orders (id),
    count             int          not null,
    price_per_product numeric (8, 2),
    sum               numeric (8, 2),
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp

);






