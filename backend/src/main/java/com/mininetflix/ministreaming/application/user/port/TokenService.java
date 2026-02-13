package com.mininetflix.ministreaming.application.user.port;

import java.util.List;
import java.util.Set;

import com.mininetflix.ministreaming.domain.user.UserRole;

public interface TokenService {

    String generateToken(String userId, Set<UserRole> roles);

    boolean validateToken(String token);

    String getSubject(String token);

    List<String> getRoles(String token);
}