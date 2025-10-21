package com.springforum.app.Shared.Exceptions;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException() {
        super("Tópico solicitado não foi encontrado.");
    }
}
