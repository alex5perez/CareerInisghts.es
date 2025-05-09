package com.etrivium.backend.service;

import com.etrivium.backend.controller.response.QuestionAnsweringResponse;
import com.etrivium.backend.repository.*;
import com.etrivium.backend.repository.entity.*;
import com.etrivium.backend.service.domain.AccessKey;
import com.etrivium.backend.service.domain.Chat;
import com.etrivium.backend.service.domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class AccessKeyService {



    @Autowired
    AccessKeyRepository accessKeyRepository;


    public UUID create(String name, UserEntity user) {
        AccessKeyEntity accessKey = AccessKeyEntity.builder()
                .name(name)
                .key(generateUniqueKey())
                .company(user.getCompany())
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        accessKeyRepository.save(accessKey);
        return accessKey.getId();
    }

    private static final String API_KEY_PREFIX = "pk-";
    private static final int KEY_LENGTH = 32;  // Length of the random part


    private String generateUniqueKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[KEY_LENGTH];
        random.nextBytes(bytes);
        String key = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        return API_KEY_PREFIX + key;
    }

    public AccessKey get(UUID accessKeyId, UserEntity user) {
        AccessKeyEntity accessKeyEntity = accessKeyRepository.findByCompany_IdAndId(user.getCompany().getId(), accessKeyId);
        AccessKey accessKey = accessKeyMapper(accessKeyEntity);
        return accessKey;
    }

    private static AccessKey accessKeyMapper(AccessKeyEntity accessKeyEntity) {
        return AccessKey.builder()
                .id(accessKeyEntity.getId())
                .companyId(accessKeyEntity.getCompany().getId())
                .name(accessKeyEntity.getName())
                .key(accessKeyEntity.getKey())
                .createDate(accessKeyEntity.getCreateDate())
                .updateDate(accessKeyEntity.getUpdateDate())
                .build();
    }

    public List<AccessKey> list(UserEntity user) {
        return accessKeyRepository.findByCompany_Id(user.getCompany().getId())
                .stream().map(AccessKeyService::accessKeyMapper).toList();
    }
}
