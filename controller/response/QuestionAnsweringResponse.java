package com.etrivium.backend.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnsweringResponse {
    String query;
    String answer;
    List<String> source_documents;
    Integer input_tokens;
    Integer output_tokens;
    String model_id;
}
