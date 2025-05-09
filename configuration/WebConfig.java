package com.etrivium.backend.configuration;

import com.etrivium.backend.service.AllowedDomainsService;
import com.etrivium.backend.service.domain.AllowedDomains;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    AllowedDomainsService allowedDomainsService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        List<String> allAllowedDomains = new java.util.ArrayList<>(allowedDomainsService.getAllAllowedDomains()
                .stream().map(AllowedDomains::getDomainName).toList());

        allAllowedDomains.add("https://prod.etrivium3l.com");
        allAllowedDomains.add("https://develop.etrivium3l.com");

        registry.addMapping("/**")
                .allowedOrigins(allAllowedDomains.toArray(new String[0])
                )
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
}