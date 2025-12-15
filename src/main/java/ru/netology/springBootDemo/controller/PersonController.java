package ru.netology.springBootDemo.controller;

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

    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // ВАЖНО: Используем Person.PersonId
    @GetMapping("/{name}/{surname}/{age}")
    public ResponseEntity<Person> getPersonById(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable int age) {

        // Создаем экземпляр составного ключа
        Person.PersonId id = new Person.PersonId(name, surname, age);
        Optional<Person> person = personRepository.findById(id);

        return person.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/city/{city}")
    public List<Person> getPersonsByCity(@PathVariable String city) {
        return personRepository.findByCity(city);
    }

    @GetMapping("/age-less-than/{age}")
    public List<Person> getPersonsAgeLessThan(@PathVariable int age) {
        return personRepository.findByAgeLessThan(age);
    }
}