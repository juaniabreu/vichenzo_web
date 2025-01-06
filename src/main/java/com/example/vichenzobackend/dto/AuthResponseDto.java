package com.example.vichenzobackend.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponseDto(String username, String message, String jwt, boolean status) {
    public AuthResponseDto(String username, String message, String jwt, boolean status) {
        this.username = username;
        this.message = message;
        this.jwt = jwt;
        this.status = status;
    }

    public String username() {
        return this.username;
    }

    public String message() {
        return this.message;
    }

    public String jwt() {
        return this.jwt;
    }

    public boolean status() {
        return this.status;
    }
}