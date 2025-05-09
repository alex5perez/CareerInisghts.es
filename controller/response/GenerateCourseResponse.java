package com.etrivium.backend.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GenerateCourseResponse {
    List<CourseSection> sections;
}
