package com.mininetflix.ministreaming.application.user.port;

public interface TokenService {
    String generateToken(String userId);

    boolean validateToken(String token);

    String getSubject(String token);
}
