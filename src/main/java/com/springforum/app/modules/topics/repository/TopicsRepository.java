package com.springforum.app.modules.topics.repository;

import com.springforum.app.modules.topics.model.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicsRepository extends JpaRepository<Topics, Long> {

    @Query("SELECT t from Topics t where t.topicName = :topicName")
    public Optional<Topics> findByTopicName(@Param("topicName") String topicName);

}
