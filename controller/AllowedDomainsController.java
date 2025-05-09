package com.etrivium.backend.controller;

import com.etrivium.backend.controller.request.CreateAccessKeyRequest;
import com.etrivium.backend.controller.request.CreateAllowedDomainRequest;
import com.etrivium.backend.controller.response.CreateAccessKeyResponse;
import com.etrivium.backend.controller.response.CreateAllowedDomainResponse;
import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.service.AccessKeyService;
import com.etrivium.backend.service.AllowedDomainsService;
import com.etrivium.backend.service.domain.AccessKey;
import com.etrivium.backend.service.domain.AllowedDomains;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/allowed-domains")
public class AllowedDomainsController {

    @Autowired
    AllowedDomainsService allowedDomainsService;


    @PostMapping("")
    public CreateAllowedDomainResponse createAllowedDomains(HttpServletRequest request, @RequestBody CreateAllowedDomainRequest createAllowedDomainRequest) {
        UserEntity user = (UserEntity) request.getAttribute("user");
        UUID allowedDomainId = allowedDomainsService.create(createAllowedDomainRequest.getName(), user);
        return CreateAllowedDomainResponse.builder().id(allowedDomainId).build();
    }

    @GetMapping("/{allowedDomainsId}")
    public AllowedDomains getAllowedDomainsId(HttpServletRequest request, @PathVariable UUID allowedDomainsId) {
        UserEntity user = (UserEntity) request.getAttribute("user");
        return allowedDomainsService.get(allowedDomainsId, user);
    }

    @GetMapping("/list")
    public List<AllowedDomains> getAccessKeys(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getAttribute("user");
        return allowedDomainsService.list(user);
    }
}