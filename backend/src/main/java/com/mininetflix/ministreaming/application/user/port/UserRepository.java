package com.mininetflix.ministreaming.application.user.port;

import java.util.Optional;

import com.mininetflix.ministreaming.domain.user.User;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    boolean existsByEmail(String email);

    boolean existsByName(String name);

}
