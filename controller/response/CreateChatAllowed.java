package com.etrivium.backend.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateChatAllowed {
    Boolean isCreateChatAllowed;
}
