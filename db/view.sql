create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('���� ������');
insert into students (name) values ('���� ������');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('��������� ������');
insert into authors (name) values ('������� ������');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('������� ������', 1);
insert into books (name, author_id) values ('����������� �����', 1);
insert into books (name, author_id) values ('����������', 1);
insert into books (name, author_id) values ('������� ����', 2);
insert into books (name, author_id) values ('���', 2);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

create view show_students_with_2_or_more_books
    as select s.name as student, count(a.name), a.name as author from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
         group by (s.name, a.name) having count(a.name) >= 2;

select * from show_students_with_2_or_more_books;

alter view show_students_with_2_or_more_books rename to new_name_view;
alter view new_name_view rename student to new_name_view_column1;

drop view new_name_view cascade;

select authors.name, count(books.name) from authors join books
on authors.id = books.author_id
group by authors.name
having count(books.name) > 2

create view jov_view as
select authors.name, count(books.name) from authors join books
on authors.id = books.author_id
group by authors.name
having count(books.name) > 2;