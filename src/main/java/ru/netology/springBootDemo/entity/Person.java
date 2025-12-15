package ru.netology.springBootDemo.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "PERSONS")
@IdClass(Person.PersonId.class)
public class Person {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @Column(name = "surname", nullable = false)
    private String surname;

    @Id
    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city_of_living")
    private String cityOfLiving;

    // Конструкторы
    public Person() {}

    public Person(String name, String surname, int age, String phoneNumber, String cityOfLiving) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.cityOfLiving = cityOfLiving;
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCityOfLiving() { return cityOfLiving; }
    public void setCityOfLiving(String cityOfLiving) { this.cityOfLiving = cityOfLiving; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', surname='" + surname + "', age=" + age +
                ", phoneNumber='" + phoneNumber + "', city='" + cityOfLiving + "'}";
    }

    // ВАЖНО: Класс должен быть public static
    public static class PersonId implements Serializable {
        private String name;
        private String surname;
        private int age;

        // Обязательно нужен конструктор без параметров
        public PersonId() {}

        public PersonId(String name, String surname, int age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
        }

        // Геттеры и сеттеры для полей ключа
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getSurname() { return surname; }
        public void setSurname(String surname) { this.surname = surname; }

        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonId personId = (PersonId) o;
            return age == personId.age &&
                    Objects.equals(name, personId.name) &&
                    Objects.equals(surname, personId.surname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, surname, age);
        }

        @Override
        public String toString() {
            return "PersonId{name='" + name + "', surname='" + surname + "', age=" + age + "}";
        }
    }
}