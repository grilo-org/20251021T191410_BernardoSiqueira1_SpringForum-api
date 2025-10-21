package com.springforum.app.modules.posts.controllers;

import com.springforum.app.modules.posts.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public class AdminPostsController {

    @Autowired
    private PostServices postServices;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/move/{postId}/{topicName}")
    public ResponseEntity<?> changePostTopic(@PathVariable long postId, @PathVariable  String topicName){
        postServices.changePostTopic(topicName, postId);

        return ResponseEntity.status(200).body("O tópico foi alterado para: " + topicName);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable long postId){
        postServices.deletePostId(postId);

        return ResponseEntity.status(200).body("Seu post foi removido.");
    }

}
