package com.etrivium.backend.repository;

import com.etrivium.backend.configuration.EventQueuesProperties;
import com.etrivium.backend.repository.request.CreateEmbeddingsEvent;
import com.etrivium.backend.service.CourseService;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatPublisher {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private SqsTemplate sqsTemplate;

    @Autowired
    private EventQueuesProperties eventQueuesProperties;

    public void createEmbeddings(String file_name) {
        CreateEmbeddingsEvent createEmbeddingsEvent = CreateEmbeddingsEvent.builder().event_type("EMBEDDING_FILES").file_name(file_name).build();
        sqsTemplate.send(to -> to.queue(eventQueuesProperties.getMainQueue())
                .payload(createEmbeddingsEvent));
        logger.info("Event published for queue:{} with payload: {}", eventQueuesProperties.getMainQueue(), createEmbeddingsEvent);
    }

    public void createEmbeddings(UUID chatId, String fileName) {
        CreateEmbeddingsEvent createEmbeddingsEvent = CreateEmbeddingsEvent.builder()
                .event_type("EMBEDDING_FILES")
                .file_name(fileName)
                .collection_name(chatId.toString())
                .build();
        sqsTemplate.send(to -> to.queue(eventQueuesProperties.getMainQueue())
                .payload(createEmbeddingsEvent));
        logger.info("Event published for queue:{} with payload: {}", eventQueuesProperties.getMainQueue(), createEmbeddingsEvent);
    }
}
