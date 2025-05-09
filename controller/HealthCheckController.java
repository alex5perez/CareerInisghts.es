package com.etrivium.backend.controller;

import com.etrivium.backend.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckController {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}