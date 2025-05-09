package com.etrivium.backend.repository.response;

import com.etrivium.backend.controller.response.CourseSection;
import com.etrivium.backend.controller.response.DataError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCourseStructureEvent {
    String event_type;
    String course_id;
    @Builder.Default
    List<CourseSection> sections = new ArrayList<>();
}
