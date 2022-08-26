create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

--1)  Триггер должен срабатывать после вставки данных для любого товара
--и просто насчитывать налог на товар. Действовать он должен не на
--каждый ряд, а на запрос (statement уровень)
create or replace function tax1()
    returns trigger as
$$
    BEGIN
        update products
        set price = price*1.2
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax1_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax1();

--2) Триггер должен срабатывать до вставки данных и высчитывать налог на товар.
--Здесь используем row уровень.
create or replace function tax2()
    returns trigger as
$$
    BEGIN
        update products
        set price = price*0.2
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax2_trigger
    before insert on products
    referencing new table as inserted
    for each row
    execute procedure tax2();

--3) Создайте таблицу:
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);
--Нужно написать триггер на row уровне, который при вставке продукта
--в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price.

create or replace function double_insert()
    returns trigger as
$$
$$
LANGUAGE 'plpgsql';

create trigger insert3_trigger
    alter insert on products
    for each row
    insert into history_of_price(name, price, date)
    values(new.name, new.price, new.date);

