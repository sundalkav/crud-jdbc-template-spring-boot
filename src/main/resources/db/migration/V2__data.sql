INSERT INTO authors (surname,name,patronymic) VALUES ('СТРУГАЦКИХ','БОРИС','НАТАНОВИЧ');
INSERT INTO books (name, date_publication, author_id)
            (SELECT 'МАЛЫШ', '1971-01-01', id FROM authors WHERE surname='СТРУГАЦКИХ' and name='БОРИС' LIMIT 1);
COMMIT;
