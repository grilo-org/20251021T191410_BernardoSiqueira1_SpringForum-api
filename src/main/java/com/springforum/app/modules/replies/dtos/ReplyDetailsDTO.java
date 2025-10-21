package com.springforum.app.modules.replies.dtos;

public record ReplyDetailsDTO(
        long replyId,
        String replyContent,
        long userId,
        String replyUser,
        String replyDate
) {
}
