package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.ConversationEntity;
import com.etrivium.backend.repository.entity.ConversationQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationQueryRepository extends JpaRepository<ConversationQueryEntity, UUID> {

    List<ConversationQueryEntity> findByCompanyId(Integer company_id);
    List<ConversationQueryEntity> findByCompanyIdAndConversation_Id(Integer company_id, UUID conversation_id);

    @Query("SELECT COUNT(cq) FROM ConversationQueryEntity cq WHERE cq.company.id = :companyId AND cq.createDate >= :startOfMonth AND cq.createDate < :startOfNextMonth")
    Long countByCompanyIdAndCurrentMonth(Integer companyId, LocalDateTime startOfMonth, LocalDateTime startOfNextMonth);
}