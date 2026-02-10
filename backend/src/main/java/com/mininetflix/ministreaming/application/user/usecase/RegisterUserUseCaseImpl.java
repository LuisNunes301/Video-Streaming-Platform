package com.mininetflix.ministreaming.application.user.usecase;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mininetflix.ministreaming.application.user.dto.RegisterUserInput;
import com.mininetflix.ministreaming.application.user.port.PasswordEncoder;
import com.mininetflix.ministreaming.application.user.port.UserRepository;
import com.mininetflix.ministreaming.domain.user.User;

@Service
public class RegisterUserUseCaseImpl
        implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void execute(RegisterUserInput input) {

        if (userRepository.existsByEmail(input.email())) {
            throw new RuntimeException("Email already in use");
        }

        if (userRepository.existsByName(input.name())) {
            throw new RuntimeException("Username already in use");
        }

        User user = new User(
                UUID.randomUUID(),
                input.name(),
                input.email(),
                passwordEncoder.encode(input.password()),
                LocalDateTime.now(),
                LocalDateTime.now());

        userRepository.save(user);
    }
}