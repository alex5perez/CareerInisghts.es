package com.etrivium.backend.repository;

import com.etrivium.backend.configuration.EventQueuesProperties;
import com.etrivium.backend.repository.request.DataGetCourseStructureRequest;
import com.etrivium.backend.service.CourseService;
import com.etrivium.backend.service.domain.CourseType;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CoursePublisher {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private SqsTemplate sqsTemplate;

    @Autowired
    private EventQueuesProperties eventQueuesProperties;

    private static final String EVENT_TYPE_GET_COURSE_STRUCTURE = "GET_COURSE_STRUCTURE";



    public void getCourseStructure(String fileName, String fileStorePosition, UUID courseId, CourseType courseType) {
        var dataGetCourseStructureRequest = DataGetCourseStructureRequest.builder()
                .event_type(EVENT_TYPE_GET_COURSE_STRUCTURE)
                .file_to_resume_path(fileName)
                .file_to_resume_path_store_position(fileStorePosition)
                .course_id(String.valueOf(courseId))
                .course_type(courseType.name())
                .build();
        sqsTemplate.send(to -> to.queue(eventQueuesProperties.getMainQueue())
                .payload(dataGetCourseStructureRequest));
        logger.info("Event published for queue:{} with payload: {}", eventQueuesProperties.getMainQueue(), dataGetCourseStructureRequest);
    }
}
