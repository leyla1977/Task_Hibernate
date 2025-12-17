**Проверка:**
Поиск по городу: http://localhost:8080/api/persons/city/Москва

Поиск по возрасту: http://localhost:8080/api/persons/age-less-than/30

Поиск по имени и фамилии: http://localhost:8080/api/persons/search?name=Иван&surname=Иванов

Сортировка по имени: http://localhost:8080/api/persons/sorted

Поиск по телефону: http://localhost:8080/api/persons/search/phone?phonePart=999

Поиск по диапазону возрастов: http://localhost:8080/api/persons/age-range?minAge=25&maxAge=35

Самый старший: http://localhost:8080/api/persons/oldest

Поиск по начальной букве: http://localhost:8080/api/persons/name-starts-with?prefix=И

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
