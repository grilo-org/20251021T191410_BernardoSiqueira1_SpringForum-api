package com.springforum.app.modules.posts.controllers;

import com.springforum.app.modules.posts.dtos.NewPostDTO;
import com.springforum.app.modules.posts.services.PostServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostServices postServices;


    @PreAuthorize("@authorshipVerification.verifyIsAccountOwner(newPostDTO.originalPosterId, authentication.name)")
    @PostMapping("/create")
    public ResponseEntity<?> createNewPost(@Valid @RequestBody NewPostDTO newPostDTO){
        postServices.createNewPost(newPostDTO);

        return ResponseEntity.status(200).body("Post criado.");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable long postId){
        var response = postServices.getPostDetailsId(postId);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{topicName}/{postPage}")
    public ResponseEntity<?> getPostsByCategory(@PathVariable String topicName, @PathVariable int postPage){
        var response = postServices.getAllPostsPage(postPage, topicName);

        return ResponseEntity.status(200).body(response);
    }

    @PreAuthorize("@authorshipVerification.verifyIsPostOwner(postId, authentication.name)")
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable long postId){
        postServices.deletePostId(postId);

        return ResponseEntity.status(200).body("Seu post foi removido.");
    }
}
