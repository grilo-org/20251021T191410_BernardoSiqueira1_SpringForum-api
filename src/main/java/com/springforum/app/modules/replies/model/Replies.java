package com.springforum.app.modules.replies.model;

import com.springforum.app.modules.posts.models.Post;
import com.springforum.app.modules.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Replies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long replyId;

    @Column(nullable = false)
    private String replyContent;

    @Column
    private LocalDateTime timeOfReply;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    User replyUser;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    Post post;

    public Replies(String replyContent, User replyUser, Post post){
        this.replyContent = replyContent;
        this.replyUser = replyUser;
        this.post = post;
        this.timeOfReply = LocalDateTime.now();
    }
}
