package com.etrivium.backend.repository.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataGetCourseStructureRequest {
    String event_type;
    String file_to_resume_path;
    String file_to_resume_path_store_position;
    String course_id;
    String course_type;
}
