package com.springforum.app.modules.posts.dtos;

import java.io.Serializable;

// Record de retorno, não há motivo para validação.
public record PostDetailsDTO(

        long postId,
        String postTitle,
        String postContent,
        String imageUrl,
        Long originalPosterId,
        String originalPosterName,
        String topicName

) implements Serializable {}
