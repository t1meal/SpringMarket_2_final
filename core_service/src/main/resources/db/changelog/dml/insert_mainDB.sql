--liquibase formatted sql

--changeset t1meal: insert_core_data

INSERT INTO categories (title)
VALUES ('Food'),
       ('Others');

insert into products (title, price, category_id)
values ('bread', 40, 1),
       ('bread1', 100, 1),
       ('cheese', 400, 1),
       ('coca-cola', 120, 1),
       ('pepsi', 110, 1),
       ('coffee', 550, 1),
       ('leaf', 50, 1),
       ('bear', 80, 1),
       ('beer', 150, 1),
       ('salt', 20, 1),
       ('bolt', 210, 2),
       ('resin', 60, 2),
       ('book', 400, 2),
       ('letter', 100, 2),
       ('oil', 1500, 2)
;
















