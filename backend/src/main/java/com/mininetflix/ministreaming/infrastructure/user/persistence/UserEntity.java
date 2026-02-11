package com.mininetflix.ministreaming.infrastructure.user.persistence;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mininetflix.ministreaming.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String PasswordHash;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserEntity fromDomain(User user) {
        UserEntity e = new UserEntity();
        e.name = user.getName();
        e.email = user.getEmail();
        e.PasswordHash = user.getPasswordHash();
        e.createdAt = user.getCreateAt();
        e.updatedAt = user.getUpdateAt();
        return e;
    }

    public User toDomain() {
        return new User(
                this.id,
                this.name,
                this.email,
                this.PasswordHash,
                this.createdAt,
                this.updatedAt);
    }
}
