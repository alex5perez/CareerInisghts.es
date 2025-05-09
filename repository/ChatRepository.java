package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.ChatEntity;
import com.etrivium.backend.repository.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, UUID> {
    List<ChatEntity> findByCompany_Id(Integer companyId);
    ChatEntity findByCompany_IdAndId(Integer companyId, UUID id);
}