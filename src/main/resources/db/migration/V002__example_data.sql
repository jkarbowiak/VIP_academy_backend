-- libraries
insert into library (id, name) values (1, 'Biblioteka 1');
insert into library (id, name) values (2, 'Biblioteka 2');

-- authors
insert into author (id, first_name, last_name, birth_date, version) values (1, 'Harlan', 'Coben', DATE('1962-01-04'), 0);
insert into author (id, first_name, last_name, birth_date, version) values (2, 'Donald', 'Knuth', DATE('1938-01-10'), 0);
insert into author (id, first_name, last_name, nick_name, birth_date, version) values (3, 'Robert C.', 'Martin', 'Uncle Bob', DATE('1952-01-01'), 0);

-- books
insert into book (id, genre, title, version, library_id, book_year) values (1, 'CRIME', 'Nie mów nikomu', 0, 1, 2003);
insert into book (id, genre, title, version, library_id, book_year) values (2, 'CRIME', 'Wszyscy mamy tajemnice', 0, 1, 2006);
insert into book (id, genre, title, version, library_id, book_year) values (3, 'IT', 'Clean Code', 0, 2, 2004);
insert into book (id, genre, title, version, library_id, book_year) values (4, 'IT', 'Sztuka programowania', 0, 2, 1992);

-- book_author
insert into book_author (author_id, book_id) values (1, 1);
insert into book_author (author_id, book_id) values (1, 2);
insert into book_author (author_id, book_id) values (2, 3);
insert into book_author (author_id, book_id) values (3, 4);
