package com.etrivium.backend.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnsweringRequest {
    String fileName;
    String query;
    UUID conversationId;
    UUID chatId;
}
