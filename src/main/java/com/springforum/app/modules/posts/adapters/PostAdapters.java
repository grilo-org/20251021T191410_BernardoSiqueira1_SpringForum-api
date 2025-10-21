package com.springforum.app.modules.posts.adapters;

import com.springforum.app.modules.posts.dtos.NewPostDTO;
import com.springforum.app.modules.posts.dtos.PostDetailsDTO;
import com.springforum.app.modules.posts.models.Post;
import com.springforum.app.modules.topics.model.Topics;
import com.springforum.app.modules.user.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public class PostAdapters {

    public static Post toPostEntity(NewPostDTO newPostDTO, User originalPoster, Topics postTopic){

        return new Post(
                newPostDTO.postTitle(),
                newPostDTO.postImageUrl(),
                newPostDTO.postDescription(),
                originalPoster,
                postTopic
        );

    }

    public static PostDetailsDTO toPostDetailsDTO(Post post){
        return new PostDetailsDTO(post.getPostId(),
                post.getPostTitle(),
                post.getPostDescription(),
                post.getPostImageUrl(),
                post.getPostUsuario().getUserId(),
                post.getPostUsuario().getUsername(),
                post.getTopics().getTopicName()
        );
    }

    public static List<PostDetailsDTO> toPostDetailsDTO(Page<Post> postPage){
        return postPage.stream().map( post -> {
            return new PostDetailsDTO(post.getPostId(),
                    post.getPostTitle(),
                    post.getPostDescription(),
                    post.getPostImageUrl(),
                    post.getPostUsuario().getUserId(),
                    post.getPostUsuario().getUsername(),
                    post.getTopics().getTopicName()
            );
        }).toList();
    }
}
