package com.springforum.app.modules.topics.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewTopicDTO(
        @NotNull
        @NotEmpty
        @Size(min = 6, max = 20)
        String topicName
){}
