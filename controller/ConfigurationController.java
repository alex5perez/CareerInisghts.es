package com.etrivium.backend.controller;

import com.etrivium.backend.controller.request.CreateAllowedDomainRequest;
import com.etrivium.backend.controller.response.CreateAllowedDomainResponse;
import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.service.AllowedDomainsService;
import com.etrivium.backend.service.ConfigurationService;
import com.etrivium.backend.service.domain.AllowedDomains;
import com.etrivium.backend.service.domain.Configuration;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;

    @GetMapping("")
    public Configuration getAccessKeys(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getAttribute("user");
        return configurationService.getConfiguration(user.getCompany().getId());
    }
}