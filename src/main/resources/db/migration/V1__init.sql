create table products
(
    id    bigserial primary key,
    title varchar(255),
    price int
);
insert into products (title, price)
values ('bread', 40),      //1
       ('bread', 100),     //2
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
       ('chicken', 250); //20
