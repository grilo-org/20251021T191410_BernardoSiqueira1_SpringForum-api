package com.springforum.app.modules.user.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotNull
        @NotEmpty
        String userName,

        @NotNull
        @NotEmpty
        String userEmail,

        @NotNull
        @NotEmpty
        @Size(min = 8, max = 16)
        String userPassword,

        @NotNull
        @NotEmpty
        String userImageURL
) {
}
