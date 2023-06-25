package daniyar.hackaton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import daniyar.hackaton.data.Event;
import daniyar.hackaton.data.User;
import daniyar.hackaton.enums.UserRole;
import daniyar.hackaton.repository.EventRepository;
import daniyar.hackaton.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectApplication {
	private final UserRepository userRepository;
	private final EventRepository eventRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner generateUsers() {
		return args -> {

			// User user1 = new User(UUID.randomUUID(), UserRole.ADMIN, "John",
			// "john@example.com",
			// passwordEncoder.encode("password1"));
			// User user2 = new User(UUID.randomUUID(), UserRole.USER, "Jane",
			// "jane@example.com",
			// passwordEncoder.encode("password2"));
			// User user3 = new User(UUID.randomUUID(), UserRole.ADMIN, "Ryan",
			// "gosling@example.com",
			// passwordEncoder.encode("password3"));

			// user1 = userRepository.save(user1);
			// userRepository.save(user2);
			// userRepository.save(user3);

			// User user1 = new User(UUID.randomUUID(), new ArrayList<>(), UserRole.ADMIN,
			// "John", "john@example.com",
			// passwordEncoder.encode("password1"));
			// User user2 = new User(UUID.randomUUID(), new ArrayList<>(), UserRole.USER,
			// "Jane", "jane@example.com",
			// passwordEncoder.encode("password2"));
			// User user3 = new User(UUID.randomUUID(), new ArrayList<>(), UserRole.ADMIN,
			// "Ryan", "gosling@example.com",
			// passwordEncoder.encode("password3"));

			// user1 = userRepository.save(user1);
			// userRepository.save(user2);
			// userRepository.save(user3);

			// Event event = new Event(UUID.randomUUID(),
			// user1,
			// "Event " + new Random().nextInt(100),
			// "some description",
			// 500,
			// false,
			// 10,
			// 20,
			// Arrays.asList("Case 1", "Case 2", "Case 3"),
			// false);

			// eventRepository.save(event);

			User user1 = new User(UUID.fromString("db6da312-80dd-4845-9cf6-9a19c0cdc551"), new ArrayList<>(),
					new ArrayList<>(), UserRole.ADMIN,
					"John", "john@example.com",
					passwordEncoder.encode("password1"));
			User user2 = new User(UUID.fromString("624f3f3e-131e-11ee-be56-0242ac120002"), new ArrayList<>(),
					new ArrayList<>(), UserRole.USER,
					"Jane", "jane@example.com",
					passwordEncoder.encode("password2"));
			User user3 = new User(UUID.fromString("705d1e66-131e-11ee-be56-0242ac120002"), new ArrayList<>(),
					new ArrayList<>(), UserRole.ADMIN,
					"Ryan", "gosling@example.com",
					passwordEncoder.encode("password3"));

			user1 = userRepository.save(user1);
			user2 = userRepository.save(user2);
			user3 = userRepository.save(user3);

			Event event1 = new Event(UUID.fromString("843edadc-131e-11ee-be56-0242ac120002"),
					user1,
					new ArrayList<>(),
					"Event " + new Random().nextInt(100),
					"some description",
					Long.valueOf(300),
					Long.valueOf(30),
					Long.valueOf(0),
					false,
					10,
					20,
					Arrays.asList("Case 1", "Case 2", "Case 3"),
					false);

			Event event2 = new Event(UUID.fromString("d2bcb782-1a62-4060-b66c-5cd0fd0bb73c"),
					user1,
					new ArrayList<User>(),
					"Event " + new Random().nextInt(100),
					"some description",
					Long.valueOf(500),
					Long.valueOf(50),
					Long.valueOf(0),
					false,
					10,
					20,
					Arrays.asList("Case 1", "Case 2", "Case 3"),
					false);

			event2.getEnrolledUsers().add(user2);
			eventRepository.save(event1);
			eventRepository.save(event2);
		};
	}

}
