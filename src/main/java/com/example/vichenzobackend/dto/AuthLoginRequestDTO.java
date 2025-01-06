package com.example.vichenzobackend.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO(@NotBlank String username, @NotBlank String password) {
    public AuthLoginRequestDTO(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public @NotBlank String username() {
        return this.username;
    }

    public @NotBlank String password() {
        return this.password;
    }
}