package com.mininetflix.ministreaming.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mininetflix.ministreaming.application.user.port.PasswordEncoder;
import com.mininetflix.ministreaming.application.user.port.UserRepository;
import com.mininetflix.ministreaming.domain.user.User;
import com.mininetflix.ministreaming.domain.user.UserRole;

@Configuration
public class AdminMockConfig {

    @Bean
    public CommandLineRunner createAdminUser(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            String adminEmail = "admin@ministream.com";

            if (userRepository.existsByEmail(adminEmail)) {
                return;
            }

            User admin = new User(
                    "admin",
                    adminEmail,
                    passwordEncoder.encode("admin123"));

            admin.addRole(UserRole.ADMIN);

            userRepository.save(admin);

            System.out.println("ADMIN criado com sucesso!");
        };
    }
}