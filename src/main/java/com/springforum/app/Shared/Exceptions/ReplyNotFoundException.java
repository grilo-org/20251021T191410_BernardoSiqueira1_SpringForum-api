package com.springforum.app.Shared.Exceptions;

public class ReplyNotFoundException extends RuntimeException {
    public ReplyNotFoundException() {
        super("Resposta solicitada não foi encontrada.");
    }
}
