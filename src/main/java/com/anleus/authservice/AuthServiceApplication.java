package com.anleus.authservice;

import com.anleus.authservice.models.AppUser;
import com.anleus.authservice.models.Role;
import com.anleus.authservice.repositories.RoleRepository;
import com.anleus.authservice.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole = roleRepository.saveAndFlush(new Role("ADMIN"));
			roleRepository.saveAndFlush(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			AppUser admin = new AppUser(1L, timestamp, "admin", "mail@mail.com", encoder.encode("password"), roles);
			userRepository.saveAndFlush(admin);
		};
	}
}
