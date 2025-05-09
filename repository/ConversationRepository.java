package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.AllowedDomainsEntity;
import com.etrivium.backend.repository.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity, UUID> {
    ConversationEntity findByCompanyIdAndId(Integer company_id, UUID id);

    List<ConversationEntity> findByCompanyId(Integer company_id);
}