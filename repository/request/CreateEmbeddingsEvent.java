package com.etrivium.backend.repository.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmbeddingsEvent {
    String file_name;
    String collection_name;
    String event_type;
}
