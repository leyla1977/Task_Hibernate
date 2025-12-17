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

    // 1. Поиск по городу через JPQL
    @Query("SELECT p FROM Person p WHERE p.city = :city")
    List<Person> findByCity(@Param("city") String city);

    // 2. Поиск по возрасту меньше указанного с сортировкой через JPQL
    @Query("SELECT p FROM Person p WHERE p.age < :age ORDER BY p.age ASC")
    List<Person> findByAgeLessThanOrderByAgeAsc(@Param("age") int age);

    // 3. Поиск по имени и фамилии через JPQL (возвращает Optional)
    @Query("SELECT p FROM Person p WHERE p.name = :name AND p.surname = :surname")
    Optional<Person> findByNameAndSurname(
            @Param("name") String name,
            @Param("surname") String surname);

    // 4. Получить всех пользователей с сортировкой по имени (пример)
    @Query("SELECT p FROM Person p ORDER BY p.name ASC, p.surname ASC")
    List<Person> findAllOrderByName();

    // 5. Поиск по телефону (LIKE запрос)
    @Query("SELECT p FROM Person p WHERE p.phoneNumber LIKE %:phonePart%")
    List<Person> findByPhoneContaining(@Param("phonePart") String phonePart);

    // 6. Поиск по нескольким городам (IN оператор)
    @Query("SELECT p FROM Person p WHERE p.city IN :cities")
    List<Person> findByCities(@Param("cities") List<String> cities);


    // 7. Поиск по диапазону возрастов
    @Query("SELECT p FROM Person p WHERE p.age BETWEEN :minAge AND :maxAge")
    List<Person> findByAgeBetween(
            @Param("minAge") int minAge,
            @Param("maxAge") int maxAge);

    // 8. Поиск самого старшего пользователя
    @Query("SELECT p FROM Person p WHERE p.age = (SELECT MAX(p2.age) FROM Person p2)")
    List<Person> findOldestPerson();

    // 9. Поиск пользователей с определенной буквой в имени
    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    List<Person> findByNameContained(@Param("namePart") String namePart);

}