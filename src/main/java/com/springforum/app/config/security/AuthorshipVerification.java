package com.springforum.app.config.security;

import com.springforum.app.modules.posts.models.Post;
import com.springforum.app.modules.posts.repositories.PostRepository;
import com.springforum.app.modules.replies.model.Replies;
import com.springforum.app.modules.replies.repository.ReplyRepository;
import com.springforum.app.modules.user.model.User;
import com.springforum.app.modules.user.repository.UserRepository;
import com.springforum.app.Shared.Exceptions.PostNotFoundException;
import com.springforum.app.Shared.Exceptions.ReplyNotFoundException;
import com.springforum.app.Shared.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorshipVerification {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    public boolean verifyIsAccountOwner(Long userId, String authenticationUsername){
        User userQuery = userRepository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return userQuery.getUsername().equals(authenticationUsername);
    }

    public boolean verifyIsPostOwner(Long postId, String authenticationUsername){
        Post postQuery = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        return postQuery.getPostUsuario().getUsername().equals(authenticationUsername);
    }

    public boolean verifyIsReplyOwner(Long replyId, String authenticationUsername){
        Replies replyQuery = replyRepository.findById(replyId)
                .orElseThrow(ReplyNotFoundException::new);

        return replyQuery.getReplyUser().getUsername().equals(authenticationUsername);
    }
}
