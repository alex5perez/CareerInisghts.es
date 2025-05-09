package com.etrivium.backend.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "events.queues")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventQueuesProperties {
    private String mainQueue;
    private String backendQueue;
}
