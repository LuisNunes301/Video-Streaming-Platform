package com.mininetflix.ministreaming.application.user.usecase;

import org.springframework.stereotype.Service;

import com.mininetflix.ministreaming.application.user.dto.AuthenticateUserInput;
import com.mininetflix.ministreaming.application.user.dto.AuthenticateUserOutput;
import com.mininetflix.ministreaming.application.user.port.PasswordEncoder;
import com.mininetflix.ministreaming.application.user.port.TokenService;
import com.mininetflix.ministreaming.application.user.port.UserRepository;

@Service
public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthenticateUserUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public AuthenticateUserOutput execute(AuthenticateUserInput input) {
        // Busca o usuário pelo email
        var user = userRepository.findByEmail(input.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // Verifica a senha
        if (!passwordEncoder.matches(input.password(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Gera o token JWT
        String token = tokenService.generateToken(user.getId().toString());

        return new AuthenticateUserOutput(token);
    }
}