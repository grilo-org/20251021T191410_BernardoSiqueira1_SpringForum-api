package com.springforum.app.modules.replies.controller;

import com.springforum.app.modules.replies.dtos.NewReplyDTO;
import com.springforum.app.modules.replies.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @PostMapping(value = "/replyto/{postId}")
    public ResponseEntity<?> replyToPost(@PathVariable long userId, @PathVariable long postId, @RequestBody NewReplyDTO newReplyDTO){
        replyService.createReply(userId, newReplyDTO);

        return ResponseEntity.status(200).body("Resposta criada para a postagem");
    }

    @GetMapping("/{postid}/{pageNumber}")
    public ResponseEntity<?> getRepliesByPost(@PathVariable long postId, @PathVariable int pageNumber){
        var response = replyService.getPageRepliesByPost(postId, pageNumber);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/delete/{replyId}")
    @PreAuthorize("@authorshipVerification.verifyIsReplyOwner(#replyId, authentication.name)")
    public ResponseEntity<?> deleteReply(@PathVariable long replyId) {
        replyService.deleteReply(replyId);

        return ResponseEntity.status(200).body("Resposta deletada com sucesso.");
    }
}
