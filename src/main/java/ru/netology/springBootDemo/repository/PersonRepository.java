package ru.netology.springBootDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.springBootDemo.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByCity(String city);

    List<Person> findByAgeLessThan(int age);

    List<Person> findByNameAndSurname(String name, String surname);

    List<Person> findBySurname(String surname);

    List<Person> findByName(String name);
}