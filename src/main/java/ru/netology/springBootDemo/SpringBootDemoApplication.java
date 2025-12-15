package ru.netology.springBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.netology.springBootDemo.entity.Person;
import ru.netology.springBootDemo.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@Bean
		public CommandLineRunner demo(PersonRepository repository) {
			return args -> {
				System.out.println("=== Инициализация тестовых данных ===");

				// Создаем тестовые данные
				Person person1 = new Person("Иван", "Иванов", 30, "+7-999-123-45-67", "Москва");
				Person person2 = new Person("Петр", "Петров", 25, "+7-999-987-65-43", "Санкт-Петербург");
				Person person3 = new Person("Анна", "Сидорова", 28, "+7-999-111-22-33", "Москва");
				Person person4 = new Person("Мария", "Кузнецова", 35, "+7-999-444-55-66", "Казань");

				// Сохраняем в базу
				repository.save(person1);
				repository.save(person2);
				repository.save(person3);
				repository.save(person4);

				// Выводим все записи
				System.out.println("Все пользователи в базе:");
				repository.findAll().forEach(System.out::println);

				// Пример поиска
				System.out.println("\nПользователи из Москвы:");
				repository.findByCityOfLiving("Москва").forEach(System.out::println);

				System.out.println("\nПользователи младше 30 лет:");
				repository.findByAgeLessThan(30).forEach(System.out::println);

				System.out.println("\nПриложение запущено! Проверьте:");
				System.out.println("1. H2 Console: http://localhost:8080/h2-console");
				System.out.println("2. REST API: http://localhost:8080/api/persons");
			};
		}
}
