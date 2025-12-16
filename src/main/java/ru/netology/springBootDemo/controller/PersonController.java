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

    // 3. Создать нового пользователя
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            Person savedPerson = personRepository.save(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 4. Обновить существующего пользователя
    @PutMapping("/{name}/{surname}/{age}")
    public ResponseEntity<Person> updatePerson(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable int age,
            @RequestBody Person personDetails) {

        Person.PersonId id = new Person.PersonId(name, surname, age);

        return personRepository.findById(id)
                .map(existingPerson -> {
                    // Обновляем поля, кроме составного ключа
                    if (personDetails.getPhoneNumber()!= null) {
                        existingPerson.setPhoneNumber(personDetails.getPhoneNumber());
                    }
                    if (personDetails.getCity() != null) {
                        existingPerson.setCity(personDetails.getCity());
                    }
                    if (personDetails.getAge() != age) {
                        existingPerson.setAge(personDetails.getAge());
                        // Если возраст изменился, нужно создать новый ID
                        Person updatedPerson = new Person(
                                existingPerson.getName(),
                                existingPerson.getSurname(),
                                personDetails.getAge(),
                                existingPerson.getPhoneNumber(),
                                existingPerson.getCity()
                        );
                        // Удаляем старую запись и сохраняем новую
                        personRepository.delete(existingPerson);
                        return ResponseEntity.ok(personRepository.save(updatedPerson));
                    }
                    return ResponseEntity.ok(personRepository.save(existingPerson));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Удалить пользователя
    @DeleteMapping("/{name}/{surname}/{age}")
    public ResponseEntity<Void> deletePerson(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable int age) {

        Person.PersonId id = new Person.PersonId(name, surname, age);

        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 6. Получить пользователей по городу (новый метод)
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Person>> getPersonsByCity(@PathVariable String city) {
        List<Person> persons = personRepository.findByCity(city);
        if (persons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persons);
    }

    // 7. Получить пользователей младше указанного возраста с сортировкой (новый метод)
    @GetMapping("/age-less-than/{age}")
    public ResponseEntity<List<Person>> getPersonsAgeLessThan(@PathVariable int age) {
        List<Person> persons = personRepository.findByAgeLessThan(age);
        if (persons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persons);
    }

    // 8. Найти пользователя по имени и фамилии (новый метод, возвращает Optional)
    @GetMapping("/search")
    public ResponseEntity<Person> getPersonByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname) {

        Optional<Person> person = personRepository.findByNameAndSurname(name, surname);
        return person.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 9. Получить пользователей по городу с сортировкой по имени
    @GetMapping("/city/{city}/sorted")
    public ResponseEntity<List<Person>> getPersonsByCitySorted(@PathVariable String city) {
        List<Person> persons = personRepository.findByCity(city);
        if (persons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persons);
    }


}