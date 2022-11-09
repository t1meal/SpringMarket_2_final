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

CREATE TABLE users_roles
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


insert into products (title, price)
values ('bread', 40),      //1
       ('bread1', 100),    //2
       ('cheese', 400),    //3
       ('coca-cola', 120), //4
       ('pepsi', 110),     //5
       ('coffee', 550),    //6
       ('leaf', 50),       //7
       ('bear', 80),       //8
       ('salt', 20),       //9
       ('pasta', 70),      //10
       ('rice', 60),       //11
       ('meat', 400),      //12
       ('fish', 350),      //13
       ('ketchup', 55),    //14
       ('sous', 60),       //15
       ('sausage', 350),   //16
       ('yogurt', 70),     //17
       ('juice', 50),      //18
       ('eggs', 80),       //19
       ('chicken', 250),   //20
       ('meat1', 400),     //21
       ('fish1', 350),     //22
       ('ketchup1', 55),   //23
       ('sous1', 60),      //24
       ('sausage1', 350),  //25
       ('yogurt1', 70),    //26
       ('juice1', 50),     //27
       ('chicken1', 250); //28
