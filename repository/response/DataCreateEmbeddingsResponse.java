package com.etrivium.backend.repository.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DataCreateEmbeddingsResponse {
    String embeddingsFileName;
}
