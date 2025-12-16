package ru.netology.springBootDemo.controller;

import org.springframework.http.ResponseEntity;
import ru.netology.springBootDemo.entity.Person;
import ru.netology.springBootDemo.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> getAllPersons(@RequestParam(required = false) String city) {
        if (city != null && !city.isEmpty()) {
            return personRepository.findByCity(city);
        }
        return personRepository.findAll();
    }

    // В контроллере или сервисе
    @GetMapping("/{name}/{surname}")
    public List<Person> getPersonsByNameAndSurname(@PathVariable String name,
                                                   @PathVariable String surname) {
        List<Person> persons = personRepository.findByNameAndSurname(name, surname);
        if (persons.isEmpty()) {
            throw new RuntimeException("No persons found with name: " + name + " and surname: " + surname);
        }
        return persons;
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
    public List<Person> getPersonsByAgeLessThan(@PathVariable int age) {
        return personRepository.findByAgeLessThan(age);
    }
    // 5. Поиск только по имени
    @GetMapping("/name/{name}")
    public List<Person> getByName(@PathVariable String name) {
        return personRepository.findByName(name);
    }

    // 6. Поиск только по фамилии
    @GetMapping("/surname/{surname}")
    public List<Person> getBySurname(@PathVariable String surname) {
        return personRepository.findBySurname(surname);
    }
    // 7. Комбинированный поиск через параметры
    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String city) {

        if (name != null && surname != null) {
            return ResponseEntity.ok(personRepository.findByNameAndSurname(name, surname));
        } else if (name != null) {
            return ResponseEntity.ok(personRepository.findByName(name));
        } else if (surname != null) {
            return ResponseEntity.ok(personRepository.findBySurname(surname));
        } else if (city != null) {
            return ResponseEntity.ok(personRepository.findByCity(city));
        } else {
            return ResponseEntity.badRequest().body("Укажите параметры поиска: name, surname или city");
        }
    }
}
