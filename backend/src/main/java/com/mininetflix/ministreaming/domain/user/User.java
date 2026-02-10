package com.mininetflix.ministreaming.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String name;
    private String email;
    private String passwordHash;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
