package com.springforum.app.Shared.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuário solicitado não foi encontrado.");
    }
}
