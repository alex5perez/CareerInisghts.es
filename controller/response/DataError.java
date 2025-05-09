package com.etrivium.backend.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataError {
    String type;
    String message;
    String stack_trace;
}
