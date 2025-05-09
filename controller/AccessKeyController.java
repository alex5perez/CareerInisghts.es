package com.etrivium.backend.controller;

import com.etrivium.backend.controller.request.CreateAccessKeyRequest;
import com.etrivium.backend.controller.response.CreateAccessKeyResponse;
import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.service.AccessKeyService;
import com.etrivium.backend.service.domain.AccessKey;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/access-key")
public class AccessKeyController {

    @Autowired
    AccessKeyService accessKeyService;


    @PostMapping("")
    public CreateAccessKeyResponse createAccessKey(HttpServletRequest request, @RequestBody CreateAccessKeyRequest createAccessKeyRequest) {
        UserEntity user = (UserEntity) request.getAttribute("user");
        UUID accessKeyId = accessKeyService.create(createAccessKeyRequest.getName(), user);
        return CreateAccessKeyResponse.builder().id(accessKeyId).build();
    }

    @GetMapping("/{accessKeyId}")
    public AccessKey getAccessKey(HttpServletRequest request, @PathVariable UUID accessKeyId) {
        UserEntity user = (UserEntity) request.getAttribute("user");
        return accessKeyService.get(accessKeyId, user);
    }

    @GetMapping("/list")
    public List<AccessKey> getAccessKeys(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getAttribute("user");
        return accessKeyService.list(user);
    }
}