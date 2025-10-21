package com.springforum.app.modules.authentication.dtos;

public record AuthenticationDetailsDTO(
        Long userId,
        String username,
        String email,
        String authToken
) {}
