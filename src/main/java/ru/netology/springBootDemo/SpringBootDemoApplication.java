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
			Person person5 = new Person("Алексей", "Алексеев", 40, "+7-999-777-88-99", "Москва");

			// Сохраняем в базу
			repository.save(person1);
			repository.save(person2);
			repository.save(person3);
			repository.save(person4);
			repository.save(person5);

			// Выводим все записи
			System.out.println("\n1. Все пользователи в базе:");
			repository.findAll().forEach(System.out::println);

			// Примеры JPQL запросов
			System.out.println("\n2. Пользователи из Москвы (JPQL):");
			repository.findByCity("Москва").forEach(System.out::println);

			System.out.println("\n3. Пользователи младше 30 лет с сортировкой (JPQL):");
			repository.findByAgeLessThanOrderByAgeAsc(30).forEach(System.out::println);

			System.out.println("\n4. Поиск по имени и фамилии (JPQL - Optional):");
			repository.findByNameAndSurname("Иван", "Иванов")
					.ifPresentOrElse(
							System.out::println,
							() -> System.out.println("Не найден")
					);

			System.out.println("\n5. Все пользователи отсортированные по имени (JPQL):");
			repository.findAllOrderByName().forEach(System.out::println);

			System.out.println("\n6. Пользователи с телефоном содержащим '999' (JPQL):");
			repository.findByPhoneContaining("999").forEach(System.out::println);

			System.out.println("\n7. Пользователи в возрасте 25-35 лет (JPQL):");
			repository.findByAgeBetween(25, 35).forEach(System.out::println);

			System.out.println("\n8. Самый старший пользователь (JPQL):");
			repository.findOldestPerson().forEach(System.out::println);

			System.out.println("\n9. Пользователи чье имя начинается на 'А' (JPQL):");
			repository.findByNameContained("А").forEach(System.out::println);

			System.out.println("\n=== Приложение запущено! ===");
			System.out.println("1. H2 Console: http://localhost:8080/h2-console");
			System.out.println("2. REST API: http://localhost:8080/api/persons");

		};
	}
}