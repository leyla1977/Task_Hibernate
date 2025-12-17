Проверка:
http://localhost:8080/api/persons

http://localhost:8080/api/persons/city/Москва

http://localhost:8080/api/persons/age-less-than/30

http://localhost:8080/api/persons/search?name=Иван&surname=Иванов

http://localhost:8080/h2-console

url = jdbc:h2:mem:testdb

Driver Class = org.h2.Driver

User Name = sa

password = 

В окне вставить:
SELECT * FROM PERSONS WHERE city = 'Москва'; 

SELECT * FROM PERSONS WHERE age < 30;

SELECT * FROM PERSONS WHERE name = 'Иван' AND surname = 'Иванов';

SELECT name, surname, age FROM PERSONS;

SELECT * FROM PERSONS WHERE city LIKE '%Петер%';

SELECT * FROM PERSONS WHERE age BETWEEN 20 AND 30; 

SELECT * FROM PERSONS WHERE phone_number LIKE '%999%';
