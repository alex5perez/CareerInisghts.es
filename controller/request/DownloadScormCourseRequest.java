package com.etrivium.backend.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DownloadScormCourseRequest {
    private List<Section> sections;
    private List<String> scormHtml;
    private DownloadScormCoursePersonalizationRequest personalization;
    private UUID courseId;
}
