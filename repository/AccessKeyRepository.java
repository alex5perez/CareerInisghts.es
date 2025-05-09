package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.AccessKeyEntity;
import com.etrivium.backend.repository.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccessKeyRepository extends JpaRepository<AccessKeyEntity, UUID> {
    List<AccessKeyEntity> findByCompany_Id(Integer companyId);
    AccessKeyEntity findByCompany_IdAndId(Integer companyId, UUID id);
    AccessKeyEntity findByKey(String key);
}