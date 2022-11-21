--liquibase formatted sql

--changeset t1meal: insert_first_users_data

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













