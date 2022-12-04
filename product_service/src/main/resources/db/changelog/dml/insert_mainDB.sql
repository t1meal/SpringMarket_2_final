--liquibase formatted sql

--changeset t1meal: insert_products_data

INSERT INTO categories (title)
VALUES ('Food'),
       ('Others');

insert into products (title, price, category_id)
values ('bread', 40.00, 1),
       ('bread1', 100.00, 1),
       ('cheese', 400.00, 1),
       ('coca-cola', 120.00, 1),
       ('pepsi', 110.00, 1),
       ('coffee', 550.00, 1),
       ('leaf', 50.00, 1),
       ('bear', 80.00, 1),
       ('beer', 150.00, 1),
       ('salt', 20.00, 1),
       ('bolt', 210.00, 2),
       ('resin', 60.00, 2),
       ('book', 400.00, 2),
       ('letter', 100.00, 2),
       ('oil', 1500.00, 2)
;
















