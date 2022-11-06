--liquibase formatted sql

--changeset t1meal: add_data_mainDB
insert into products (title, price)
values ('bread', 40),
       ('bread1', 100),
       ('cheese', 400),
       ('coca-cola', 120),
       ('pepsi', 110),
       ('coffee', 550),
       ('leaf', 50),
       ('bear', 80),
       ('beer', 150),
       ('salt', 20),
       ('bolt', 210),
       ('resin', 60),
       ('book', 400),
       ('letter', 100),
       ('oil', 1500)
;

insert into users (username, password, email)
values ('user', '$2a$12$AzQ.SQRayi4zzUZD0FziheAIQqtqzIeL2GyRi6wxw59X5XB5W.0ci', 'user@gmail.com'),
       ('admin', '$2a$12$6TnMBKtQeEuAwgV9c55PA.D80dLJtuVDSafVVCgTi7s16VgG2s4aK', 'admin@gmail.com');

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_EDITOR'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPERADMIN');

insert into privileges (name)
values ('VIEW'),
       ('EDIT'),
       ('USER_INFO'),
       ('CREATE'),
       ('DELETE'),
       ('MODER');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 3);

insert into roles_privileges (role_id, privilege_id)
values (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4),
       (4, 1),
       (4, 2),
       (4, 3),
       (4, 4),
       (4, 5),
       (4, 6);

--changeset t1meal: add_categories_to_products

INSERT INTO categories (title)
VALUES ('Food'),
       ('Others');

UPDATE products SET category_id = 1 WHERE id < 10;
UPDATE products SET category_id = 2 WHERE id > 9;













