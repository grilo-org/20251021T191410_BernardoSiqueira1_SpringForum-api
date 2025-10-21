package com.springforum.app.modules.topics.adapters;

import com.springforum.app.modules.topics.dtos.GetTopicDTO;
import com.springforum.app.modules.topics.model.Topics;
import org.springframework.data.domain.Page;

import java.util.List;

public class TopicAdapters {

    public static Topics toTopicEntity(String topicName){
        return new Topics(topicName);
    }

    public static List<GetTopicDTO> toTopicDTOList(Page<Topics> topicsPage){

        return topicsPage.stream().map( topic ->
                new GetTopicDTO(topic.getTopicId(), topic.getTopicName()))
                .toList();

    }

}
