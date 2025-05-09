package com.etrivium.backend.controller.request;

import com.etrivium.backend.service.domain.CourseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateCourseRequest {
    String fileName;
    String fileStorePosition;
    CourseType courseType;
}
