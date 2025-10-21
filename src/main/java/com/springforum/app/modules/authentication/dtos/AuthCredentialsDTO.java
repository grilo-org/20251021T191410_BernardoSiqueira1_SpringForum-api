package com.springforum.app.modules.authentication.dtos;

public record AuthCredentialsDTO(
        String userLogin,
        String userPassword
){}
