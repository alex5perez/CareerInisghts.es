package com.etrivium.backend.controller;

import com.etrivium.backend.controller.request.GenerateCourseRequest;
import com.etrivium.backend.controller.request.DownloadScormCourseRequest;
import com.etrivium.backend.controller.request.SaveCourseRequest;
import com.etrivium.backend.controller.request.VideoConversionCourseRequest;
import com.etrivium.backend.controller.response.*;
import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.service.VideoConversionService;
import com.etrivium.backend.service.domain.Course;
import com.etrivium.backend.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CourseController {



    @Autowired
    VideoConversionService videoConversionService;



}