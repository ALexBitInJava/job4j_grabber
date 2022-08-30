create table cars(
    id serial primary key,
    name varchar(50),
    Country_manufacturer varchar(50),
    annual_release integer,
    price integer
);

insert into cars(name, Country_manufacturer, annual_release, price) values ('Ford', 'USA', 1000, 15000);
insert into cars(name, Country_manufacturer, annual_release, price) values ('Mitsubishi', 'Japan', 1200, 11000);
insert into cars(name, Country_manufacturer, annual_release, price) values ('Toyota', 'Japan', 1100, 13000);
insert into cars(name, Country_manufacturer, annual_release, price) values ('Lexus', 'Japan', 800, 30000);
insert into cars(name, Country_manufacturer, annual_release, price) values ('Dodge', 'USA', 750, 35000);
insert into cars(name, Country_manufacturer, annual_release, price) values ('Audi', 'Germany', 900, 25000);
insert into cars(name, Country_manufacturer, annual_release, price) values ('BMW', 'Germany', 800, 28000);

begin transaction isolation level serializable;

insert into cars(name, Country_manufacturer, annual_release, price) values ('Lada', 'Russia', 1000, 10000);
delete from cars where price >30000;
update cars set annual_release = 1000 where annual_release < 1000;
select sum(annual_release) from cars;
update cars set annual_release = annual_release * 1.5 where annual_release = 1000;

update cars set annual_release = annual_release * 1.3 where annual_release > 1050;