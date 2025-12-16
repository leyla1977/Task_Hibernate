-- V1__create_persons_table.sql
CREATE TABLE IF NOT EXISTS PERSONS (
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    phone_number VARCHAR(255),
    city VARCHAR(255),
    PRIMARY KEY (name, surname, age)
);

-- Группировка по городу
SELECT city, COUNT(*) as count FROM PERSONS GROUP BY city;

-- Средний возраст
SELECT AVG(age) as average_age FROM PERSONS;

-- Самый старший и младший
SELECT
    MIN(age) as min_age,
    MAX(age) as max_age,
    AVG(age) as avg_age
FROM PERSONS;

-- Проверить индексный составной ключ
SELECT * FROM INFORMATION_SCHEMA.INDEXES WHERE TABLE_NAME = 'PERSONS';