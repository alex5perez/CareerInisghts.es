package com.etrivium.backend.service;

import com.etrivium.backend.controller.response.CourseSection;
import com.etrivium.backend.controller.response.GetCourseStructureResponse;
import com.etrivium.backend.repository.*;
import com.etrivium.backend.repository.entity.ConfigurationEntity;
import com.etrivium.backend.repository.entity.CourseEntity;
import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.service.domain.Configuration;
import com.etrivium.backend.service.domain.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConfigurationService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    @Autowired
    ConfigurationRepository configurationRepository;

    public Configuration getConfiguration(Integer companyId) {
        ConfigurationEntity configurationEntity = configurationRepository.findByCompany_Id(companyId);
        return Configuration.builder().id(configurationEntity.getId())
                .totalCoursesAllowed(configurationEntity.getTotalCoursesAllowed())
                .totalDownloadsAllowed(configurationEntity.getTotalDownloadsAllowed())
                .chatQueriesPerMonth(configurationEntity.getChatQueriesPerMonth())
                .build();
    }
}
