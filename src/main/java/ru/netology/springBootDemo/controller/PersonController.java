package ru.netology.springBootDemo.controller;

import org.springframework.http.HttpStatus;
import ru.netology.springBootDemo.entity.Person;
import ru.netology.springBootDemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // 1. Получить всех пользователей
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return ResponseEntity.ok(persons);
    }

    // 2. Получить пользователя по составному ID
    @GetMapping("/{name}/{surname}/{age}")
    public ResponseEntity<Person> getPersonById(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable int age) {

        Person.PersonId id = new Person.PersonId(name, surname, age);
        Optional<Person> person = personRepository.findById(id);

        return person.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // 3. Обновить пользователя
    @PutMapping("/{name}/{surname}/{age}")
    public ResponseEntity<Person> updatePerson(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable int age,
            @RequestBody Person personDetails) {

        Person.PersonId id = new Person.PersonId(name, surname, age);

        return personRepository.findById(id)
                .map(existingPerson -> {
                    existingPerson.setPhoneNumber(personDetails.getPhoneNumber());
                    existingPerson.setCity(personDetails.getCity());
                    Person updatedPerson = personRepository.save(existingPerson);
                    return ResponseEntity.ok(updatedPerson);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // 4. Поиск по городу (JPQL) - GET /api/persons/city/{city}
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Person>> getPersonsByCity(@PathVariable String city) {
        List<Person> persons = personRepository.findByCity(city);
        return persons.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(persons);
    }

    // 5. Поиск по возрасту с сортировкой (JPQL) - GET /api/persons/age-less-than/{age}
    @GetMapping("/age-less-than/{age}")
    public ResponseEntity<List<Person>> getPersonsAgeLessThan(@PathVariable int age) {
        List<Person> persons = personRepository.findByAgeLessThanOrderByAgeAsc(age);
        return ResponseEntity.ok(persons);
    }

    // 6. Поиск по имени и фамилии (JPQL) - GET /api/persons/search
    @GetMapping("/search")
    public ResponseEntity<Person> getPersonByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname) {

        Optional<Person> person = personRepository.findByNameAndSurname(name, surname);
        return person.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 7. Получить всех с сортировкой по имени (JPQL) - GET /api/persons/sorted
    @GetMapping("/sorted")
    public ResponseEntity<List<Person>> getAllPersonsSorted() {
        List<Person> persons = personRepository.findAllOrderByName();
        return ResponseEntity.ok(persons);
    }

    // 8. Поиск по телефону (JPQL) - GET /api/persons/search/phone
    @GetMapping("/search/phone")
    public ResponseEntity<List<Person>> searchByPhone(@RequestParam String phonePart) {
        List<Person> persons = personRepository.findByPhoneContaining(phonePart);
        return ResponseEntity.ok(persons);
    }

    // 9. Поиск по нескольким городам (JPQL) - GET /api/persons/cities
    @GetMapping("/cities")
    public ResponseEntity<List<Person>> getPersonsByCities(@RequestParam List<String> cities) {
        List<Person> persons = personRepository.findByCities(cities);
        return ResponseEntity.ok(persons);
    }


    // 10. Поиск по диапазону возрастов (JPQL) - GET /api/persons/age-range
    @GetMapping("/age-range")
    public ResponseEntity<List<Person>> getPersonsByAgeRange(
            @RequestParam int minAge,
            @RequestParam int maxAge) {

        List<Person> persons = personRepository.findByAgeBetween(minAge, maxAge);
        return ResponseEntity.ok(persons);
    }

    // 11. Поиск самого старшего пользователя (JPQL) - GET /api/persons/oldest
    @GetMapping("/oldest")
    public ResponseEntity<List<Person>> getOldestPerson() {
        List<Person> persons = personRepository.findOldestPerson();
        return ResponseEntity.ok(persons);
    }

    // 12. Поиск по  букве имени (JPQL) - GET /api/persons/name-contains-with
    @GetMapping("/name-starts-with")
    public ResponseEntity<List<Person>> findByNameStartedBy(@RequestParam String prefix) {
        List<Person> persons = personRepository.findByNameContained(prefix);
        return ResponseEntity.ok(persons);
    }


}