DROP SEQUENCE IF EXISTS author_id_seq, book_id_seq CASCADE;
DROP TABLE IF EXISTS authors, books CASCADE;

CREATE SEQUENCE IF NOT EXISTS author_id_seq;
CREATE SEQUENCE IF NOT EXISTS book_id_seq;

create table IF NOT EXISTS authors  (
    id integer default nextval('author_id_seq') not null primary key,
    surname varchar,
    name varchar,
    patronymic varchar
);

create table IF NOT EXISTS books  (
    id integer default nextval('book_id_seq') not null primary key,
    name varchar,
    date_publication date,
    author_id integer,
    FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);
