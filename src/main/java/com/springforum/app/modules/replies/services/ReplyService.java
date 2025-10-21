package com.springforum.app.modules.replies.services;

import com.springforum.app.modules.posts.models.Post;
import com.springforum.app.modules.posts.repositories.PostRepository;
import com.springforum.app.modules.replies.adapters.ReplyAdapter;
import com.springforum.app.modules.replies.dtos.NewReplyDTO;
import com.springforum.app.modules.replies.dtos.ReplyDetailsDTO;
import com.springforum.app.modules.replies.model.Replies;
import com.springforum.app.modules.replies.repository.ReplyRepository;
import com.springforum.app.modules.user.model.User;
import com.springforum.app.modules.user.repository.UserRepository;
import com.springforum.app.Shared.Exceptions.PostNotFoundException;
import com.springforum.app.Shared.Exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createReply(long postId, NewReplyDTO newReplyDTO){
        User userQuery = userRepository.findById(newReplyDTO.replyUserId()).orElseThrow(UserNotFoundException::new);
        Post postQuery = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        Replies newReply = ReplyAdapter.toReplyEntity(newReplyDTO, userQuery, postQuery);

        replyRepository.save(newReply);
    }

    public List<ReplyDetailsDTO> getPageRepliesByPost(long postId, int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<Replies> replyQuery = replyRepository.pageRepliesByPost(pageable, postId);

        return ReplyAdapter.toReplyDetails(replyQuery);
    }

    public List<ReplyDetailsDTO> getPageRepliesByUser(long userId, int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<Replies> replyQuery = replyRepository.pageRepliesByUser(pageable, userId);

        return ReplyAdapter.toReplyDetails(replyQuery);
    }

    @Transactional
    public void deleteReply(long replyId){
        replyRepository.deleteById(replyId);
    }

}
