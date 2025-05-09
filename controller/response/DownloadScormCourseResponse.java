package com.etrivium.backend.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DownloadScormCourseResponse {
    String url;
    String fileName;
}
