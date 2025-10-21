package com.springforum.app.modules.user.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EditUserCredentialsDTO(
        @NotNull
        @NotEmpty
        String newUserEmail,

        @NotNull
        @NotEmpty
        String newUserPassword
)
{}
