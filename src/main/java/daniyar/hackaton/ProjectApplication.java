package daniyar.hackaton;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import daniyar.hackaton.data.User;
import daniyar.hackaton.enums.UserRole;
import daniyar.hackaton.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectApplication {
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner generateUsers() {
		return args -> {
			User user1 = new User(UUID.randomUUID(), UserRole.ADMIN, "John", "john@example.com",
					passwordEncoder.encode("password1"));
			User user2 = new User(UUID.randomUUID(), UserRole.USER, "Jane", "jane@example.com",
					passwordEncoder.encode("password2"));
			User user3 = new User(UUID.randomUUID(), UserRole.ADMIN, "Ryan", "gosling@example.com",
					passwordEncoder.encode("password3"));

			repository.save(user1);
			repository.save(user2);
			repository.save(user3);
		};
	}

}
