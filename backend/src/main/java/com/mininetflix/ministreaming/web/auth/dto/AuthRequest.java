package com.mininetflix.ministreaming.web.auth.dto;

public record AuthRequest(
        String email,
        String password) {
}
