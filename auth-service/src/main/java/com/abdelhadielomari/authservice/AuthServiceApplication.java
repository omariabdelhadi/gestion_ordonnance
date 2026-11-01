package com.abdelhadielomari.authservice;

import com.abdelhadielomari.authservice.entities.AppUser;
import com.abdelhadielomari.authservice.repositories.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AppUserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.save(new AppUser(null, "admin", encoder.encode("1234"), new HashSet<>(Set.of("ADMIN", "MEDECIN"))));
            repo.save(new AppUser(null, "medecin", encoder.encode("1234"), new HashSet<>(Set.of("MEDECIN"))));
        };
    }

}
