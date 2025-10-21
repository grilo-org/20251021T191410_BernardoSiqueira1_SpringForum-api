package com.springforum.app.modules.topics.services;

import com.springforum.app.modules.topics.adapters.TopicAdapters;
import com.springforum.app.modules.topics.dtos.GetTopicDTO;
import com.springforum.app.modules.topics.dtos.NewTopicDTO;
import com.springforum.app.modules.topics.model.Topics;
import com.springforum.app.modules.topics.repository.TopicsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicsService {

    @Autowired
    private TopicsRepository topicsRepository;

    @Transactional
    public void createNewTopic(NewTopicDTO newTopicDTO){

        Topics newTopic = TopicAdapters.toTopicEntity(newTopicDTO.topicName());

        topicsRepository.save(newTopic);

    }

    public List<GetTopicDTO> getTopicsPage(int pageNumber){
        Pageable queryPage = PageRequest.of(pageNumber, 5);

        return TopicAdapters.toTopicDTOList(topicsRepository.findAll(queryPage));
    }

    @Transactional
    public void deleteExistingTopic(long topicId){
        topicsRepository.deleteById(topicId);
    }

}
