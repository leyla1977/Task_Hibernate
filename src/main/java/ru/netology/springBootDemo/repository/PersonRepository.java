package ru.netology.springBootDemo.repository;

import ru.netology.springBootDemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Person.PersonId> {


    // 1. CRUD методы (наследуются от JpaRepository)
     // save(), findById(), findAll(), deleteById(),
    // count(), existsById(), delete(), deleteAll()
    // уже доступны без объявления


    // 2. Поиск по городу
    List<Person> findByCity(String city);


    // 3. Поиск по возрасту с сортировкой
    @Query("SELECT p FROM Person p WHERE p.age < :age ORDER BY p.age ASC")
    List<Person> findByAgeLessThan(@Param("age") int age);


    // 4. Поиск по имени и фамилии
    Optional<Person> findByNameAndSurname(String name, String surname);

    // Дополнительный метод
    @Query("SELECT p FROM Person p WHERE p.name = :name AND p.surname = :surname")
    Optional<Person> findByNameAndSurnameCustom(
            @Param("name") String name,
            @Param("surname") String surname);
}