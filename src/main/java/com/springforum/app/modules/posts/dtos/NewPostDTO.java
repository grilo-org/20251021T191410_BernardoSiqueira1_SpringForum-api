package com.springforum.app.modules.posts.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record NewPostDTO (

        @NotNull(message = "É necessário um título para a postagem")
        @Size(max = 80)
        @NotEmpty
        String postTitle,

        String postImageUrl,

        @NotNull(message = "É necessário um assunto para a postagem")
        @NotEmpty
        String postDescription,

        @NotNull(message = "Necessário fornecer o tópico no qual o post pertence")
        @NotEmpty
        long topicId,

        @NotNull(message = "Necessário fornecer o usuário que fez o post")
        @NotEmpty
        long originalPosterId

) implements Serializable {}
