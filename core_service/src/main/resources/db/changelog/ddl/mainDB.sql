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

--changeset t1meal: add_orders

create table orders
(
    id          bigserial,
    user_id     bigint not null,
    total_price int    not null,
    order_items varchar(255),
    primary key (id),
    foreign key (user_id) references users (id)
);

--changeset t1meal: refactor_orders

drop table orders;

create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    total_price int    not null,
    user_email  varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    count             int    not null,
    price_per_product int    not null,
    sum               int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp

);
--changeset t1meal: add_categories_to_products

create table categories
(
    id         bigserial primary key,
    title      varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
alter table products add column category_id bigint references categories (id);
alter table products add column created_at timestamp default current_timestamp;
alter table products add column updated_at timestamp default current_timestamp;




