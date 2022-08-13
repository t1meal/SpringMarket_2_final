--liquibase formatted sql

--changeset t1meal: add_data_mainDB
insert into products (title, price)
values
    ('bread', 40),
    ('bread1', 100),
    ('cheese', 400),
    ('coca-cola', 120),
    ('pepsi', 110),
    ('coffee', 550),
    ('leaf', 50),
    ('bear', 80),
    ('salt', 20),
    ('pasta', 70),
    ('rice', 60),
    ('meat', 400),
    ('fish', 350),
    ('ketchup', 55),
    ('sous', 60),
    ('sausage', 350),
    ('yogurt', 70),
    ('juice', 50),
    ('eggs', 80),
    ('chicken', 250),
    ('meat1', 400),
    ('fish1', 350),
    ('ketchup1', 55),
    ('sous1', 60),
    ('sausage1', 350),
    ('yogurt1', 70),
    ('juice1', 50),
    ('chicken1', 250);

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


