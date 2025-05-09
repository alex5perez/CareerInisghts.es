package com.etrivium.backend.controller.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSectionKey {
    Integer id;
    String key;
    String value;
    Boolean isCorrectAnswer = null;
}
