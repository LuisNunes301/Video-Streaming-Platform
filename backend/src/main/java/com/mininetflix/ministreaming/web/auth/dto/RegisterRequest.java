package com.mininetflix.ministreaming.web.auth.dto;

public record RegisterRequest(
        String name,
        String email,
        String password) {
}