package ru.netology.springBootDemo.repository;

import ru.netology.springBootDemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface PersonRepository extends JpaRepository<Person, Person.PersonId> {

    // Поиск по городу проживания
    List<Person> findByCityOfLiving(String city);

    // Поиск по возрасту меньше указанного
    List<Person> findByAgeLessThan(int age);

    // Поиск по имени и фамилии
    List<Person> findByNameAndSurname(String name, String surname);
}