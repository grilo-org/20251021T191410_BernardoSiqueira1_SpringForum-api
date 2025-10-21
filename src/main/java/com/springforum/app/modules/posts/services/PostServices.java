package com.springforum.app.modules.posts.services;

import com.springforum.app.modules.posts.adapters.PostAdapters;
import com.springforum.app.modules.posts.dtos.NewPostDTO;
import com.springforum.app.modules.posts.dtos.PostDetailsDTO;
import com.springforum.app.modules.posts.models.Post;
import com.springforum.app.modules.posts.repositories.PostRepository;
import com.springforum.app.modules.topics.model.Topics;
import com.springforum.app.modules.topics.repository.TopicsRepository;
import com.springforum.app.modules.user.model.User;
import com.springforum.app.modules.user.repository.UserRepository;
import com.springforum.app.Shared.Exceptions.PostNotFoundException;
import com.springforum.app.Shared.Exceptions.TopicNotFoundException;
import com.springforum.app.Shared.Exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServices {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicsRepository topicsRepository;

    @Transactional
    public void createNewPost(NewPostDTO newPostDTO){
        User userQuery = userRepository.findById(newPostDTO.originalPosterId()).orElseThrow(UserNotFoundException::new);
        Topics topicsQuery = topicsRepository.findById(newPostDTO.topicId()).orElseThrow(TopicNotFoundException::new);

        Post newPost = PostAdapters.toPostEntity(newPostDTO, userQuery, topicsQuery);

        postRepository.save(newPost);
    }

    @Transactional
    public void changePostTopic(String topicName, long postId){
        Post postQuery = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Topics topicsQuery = topicsRepository.findByTopicName(topicName).orElseThrow(TopicNotFoundException::new);

        postQuery.setTopics(topicsQuery);
        postRepository.save(postQuery);
    }

    @Cacheable("posts")
    public PostDetailsDTO getPostDetailsId(long postId){
        Post postQuery = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        return PostAdapters.toPostDetailsDTO(postQuery);
    }

    @Cacheable("posts")
    public List<PostDetailsDTO> getAllPostsPage(int pageNumber, String topicName){
        PageRequest queryPage = PageRequest.of((int)pageNumber, 10);
        Page<Post> postPage = postRepository.pagePostsByTopic(queryPage, topicName);

        return PostAdapters.toPostDetailsDTO(postPage);
    }

    @Transactional
    public void deletePostId(long postId){
        postRepository.deleteById(postId);
    }
}
