package com.etrivium.backend.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UploadFileResponse {
    UUID id;
    String url;
    String fileName;
    String storedFileName;
}
