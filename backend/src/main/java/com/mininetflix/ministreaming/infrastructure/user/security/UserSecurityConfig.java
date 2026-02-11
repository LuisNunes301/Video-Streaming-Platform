package com.mininetflix.ministreaming.infrastructure.user.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mininetflix.ministreaming.application.user.port.PasswordEncoder;
import com.mininetflix.ministreaming.application.user.port.TokenService;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class UserSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoderAdapter();
    }

    @Bean
    public TokenService tokenService(JwtProperties properties) {
        return new JwtTokenService(properties);
    }
}
