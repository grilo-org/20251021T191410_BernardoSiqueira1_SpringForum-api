package com.springforum.app.modules.topics.model;

import com.springforum.app.modules.posts.models.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long topicId;

    @Column(nullable = false, unique = true)
    private String topicName;

    @JoinColumn
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Post> relatedPosts;

    public Topics(String topicName){
        this.topicName = topicName;
    }
}
