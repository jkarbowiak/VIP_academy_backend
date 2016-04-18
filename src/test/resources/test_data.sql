-- AUTHOR 1 DATA

-- libraries

insert into library (id, name) values (1, 'Biblioteka miejska 1');
insert into library (id, name) values (2, 'Biblioteka miejska 2');
insert into library (id, name) values (3, 'Biblioteka miejska 3');

-- authors
insert into author (id, nick_name, birth_date, first_name, last_name, version) values (1, 'Harco', TO_DATE('1962/01/04', 'YYYY/MM/DD'), 'Harlan', 'Coben', 0);
insert into author (id, nick_name, birth_date, first_name, last_name, version) values (2, 'Moli', TO_DATE('1622/01/15', 'YYYY/MM/DD'), 'Jean', 'Molier', 0);
insert into author (id, nick_name, birth_date, first_name, last_name, version) values (3, 'Rataj',TO_DATE('1949/02/11', 'YYYY/MM/DD'), 'Janusz', 'Ratajczak', 0);

-- books
insert into book (id, title, version, library_id, book_year) values (1, 'Nie mów nikomu', 0, 1, 1999);
insert into book (id, title, version, library_id, book_year) values (2, 'Wszyscy mamy tajemnice', 0, 1, 2000);
insert into book (id, title, version, library_id, book_year) values (3, 'Schronienie', 0, 1, 2001);
insert into book (id, title, version, library_id, book_year) values (4, 'Mistyfikacja', 0, 1, 2002);

insert into book (id, title, version, library_id, book_year) values (5, 'Rogacz z urojenia', 0, 2, 2003);
insert into book (id, title, version, library_id, book_year) values (6, 'Szkoła mężów', 0, 2, 2004);
insert into book (id, title, version, library_id, book_year) values (7, 'Szkoła żon', 0, 2, 2005);
insert into book (id, title, version, library_id, book_year) values (8, 'Świętoszek', 0, 3, 2006);

-- book_author
insert into book_author (author_id, book_id) values (1, 1);
insert into book_author (author_id, book_id) values (1, 2);
insert into book_author (author_id, book_id) values (1, 3);
insert into book_author (author_id, book_id) values (1, 4);
insert into book_author (author_id, book_id) values (2, 5);
insert into book_author (author_id, book_id) values (2, 6);
insert into book_author (author_id, book_id) values (2, 7);
insert into book_author (author_id, book_id) values (2, 8);

-- reviews
insert into book_review(id, book_fk, content) values (1, 1, 'Od śmierci Elizabeth z rąk seryjnego zabójcy minęło osiem lat, ale młody lekarz David Beck nie potrafi o niej zapomnieć.');
