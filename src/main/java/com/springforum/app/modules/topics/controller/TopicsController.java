package com.springforum.app.modules.topics.controller;

import com.springforum.app.modules.topics.dtos.NewTopicDTO;
import com.springforum.app.modules.topics.services.TopicsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    TopicsService topicsService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createNewTopic(@RequestBody @Valid NewTopicDTO newTopicDTO){
        topicsService.createNewTopic(newTopicDTO);

        return ResponseEntity.status(200).body("Tópico criado");
    }

    @GetMapping("/{pageNum}")
    public ResponseEntity<?> getAllTopics(@PathVariable int pageNum){
        var response = topicsService.getTopicsPage(pageNum);

        return ResponseEntity.status(200).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/remove/{topicId}")
    public ResponseEntity<?> deleteExistingTopic(@PathVariable long topicId){
        topicsService.deleteExistingTopic(topicId);

        return ResponseEntity.status(200).body("Tópico removido");
    }

}
