package com.etrivium.backend.repository.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataQuestionAnsweringRequest {
    String event_type;
    String file_name;
    String query;
}
