package com.etrivium.backend.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatsUsageResponse {
    private String inputCost;
    private String outputCost;
    private String totalCost;
    private List<ChatUsageResponse> chats;
}
