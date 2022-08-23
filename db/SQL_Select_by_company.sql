CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);
CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'EFKO');
insert into company (id, name) values (2, 'KFC');
insert into company (id, name) values (3, 'Softline');
insert into company (id, name) values (4, 'EPAM');
insert into company (id, name) values (5, 'HP');

insert into person (id, name, company_id) values (1, 'person1', 1);
insert into person (id, name, company_id) values (2, 'person2', 1);
insert into person (id, name, company_id) values (3, 'person3', 1);
insert into person (id, name, company_id) values (4, 'person4', 1);
insert into person (id, name, company_id) values (5, 'person5', 1);
insert into person (id, name, company_id) values (6, 'person6', 2);
insert into person (id, name, company_id) values (7, 'person7', 2);
insert into person (id, name, company_id) values (8, 'person8', 2);
insert into person (id, name, company_id) values (9, 'person9', 2);
insert into person (id, name, company_id) values (10, 'person10', 2);
insert into person (id, name, company_id) values (11, 'person11', 3);
insert into person (id, name, company_id) values (12, 'person12', 3);
insert into person (id, name, company_id) values (13, 'person13', 3);
insert into person (id, name, company_id) values (14, 'person14', 3);
insert into person (id, name, company_id) values (15, 'person15', 3);
insert into person (id, name, company_id) values (16, 'person16', 4);
insert into person (id, name, company_id) values (17, 'person17', 4);
insert into person (id, name, company_id) values (18, 'person18', 4);
insert into person (id, name, company_id) values (19, 'person19', 4);
insert into person (id, name, company_id) values (20, 'person20', 4);
insert into person (id, name, company_id) values (21, 'person21', 5);
insert into person (id, name, company_id) values (22, 'person22', 5);
insert into person (id, name, company_id) values (23, 'person23', 5);
insert into person (id, name, company_id) values (24, 'person24', 5);
insert into person (id, name, company_id) values (25, 'person25', 5);
insert into person (id, name, company_id) values (26, 'person26', 5);
insert into person (id, name, company_id) values (27, 'person27', 5);
insert into person (id, name, company_id) values (28, 'person28', 4);
insert into person (id, name, company_id) values (29, 'person29', 3);

/*имена всех person, которые не состо€т в компании с id = 5;
название компании дл€ каждого человека.*/
select person.name, company.name from person inner join company
on person.company_id = company.id
where company_id !=5;

/*Ќеобходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании
нужно учесть, что таких компаний может быть несколько, и вывести надо информацию о каждой компании.*/

select company.name, count(person.company_id) as count from person join company
on person.company_id=company.id
group by company.id
order by company.id desc;

/* ≈сть результат запроса, полученный в виде таблицы.  ак мне обратитьс€ к этой
временной таблице, чтобы получить максимум по ней?*/
select table.name, max(table.count) from (select company.name, count(person.company_id) as count from person join company
on person.company_id=company.id
group by company.id
order by company.id desc) as table;


