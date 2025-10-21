package com.springforum.app.Shared.Exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("Post solicitado não foi encontrado.");
    }
}
