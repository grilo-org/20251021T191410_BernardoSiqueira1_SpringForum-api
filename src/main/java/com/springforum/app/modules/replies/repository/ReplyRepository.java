package com.springforum.app.modules.replies.repository;

import com.springforum.app.modules.replies.model.Replies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Replies, Long> {

    @Query("SELECT r from Replies r WHERE r.post.postId = :postId")
    Page<Replies> pageRepliesByPost(Pageable pageable, @Param("postId") long postId);

    @Query("SELECT r from Replies r WHERE r.replyUser.userId = :userId")
    Page<Replies> pageRepliesByUser(Pageable pageable, @Param("userId") long userId);

}
