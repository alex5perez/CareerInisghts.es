package com.etrivium.backend.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ChatUsageResponse {
    private UUID chatId;
    private String chatName;
    private String inputCost;
    private String outputCost;
    private String totalCost;
}
