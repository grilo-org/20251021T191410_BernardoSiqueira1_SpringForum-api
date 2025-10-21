package com.springforum.app.modules.posts.models;

import com.springforum.app.modules.topics.model.Topics;
import com.springforum.app.modules.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postImageUrl;

    @Column(nullable = false)
    private String postDescription;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    Topics topics;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    User postUsuario;

    public Post(String postTitle, String postImageUrl, String postDescription, User originalPoster, Topics postTopic){
        this.postTitle = postTitle;
        this.postImageUrl = postImageUrl;
        this.postDescription = postDescription;
        this.postUsuario = originalPoster;
        this.topics = postTopic;
    }
}
