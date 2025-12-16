**Проверка:**
http://localhost:8080/api/persons/

http://localhost:8080/api/persons/city/Moscow

http://localhost:8080/api/persons/age-less-than/30

http://localhost:8080/api/persons/name/Ivan

http://localhost:8080/api/persons/surname/Ivanov

http://localhost:8080/h2-console/
url = jdbc:h2:mem:testdb

Driver Class = org.h2.Driver

User Name = sa

password =

SELECT * FROM PERSONS WHERE city = 'Moscow';
SELECT * FROM PERSONS WHERE age < 30;
SELECT * FROM PERSONS WHERE name = 'Ivan' AND surname = 'Ivanov';
SELECT name, surname, age FROM PERSONS;
SELECT * FROM PERSONS WHERE city LIKE '%Peter%';
SELECT * FROM PERSONS WHERE age BETWEEN 20 AND 30;
SELECT * FROM PERSONS WHERE phone_number LIKE '%999%';
