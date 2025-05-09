package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.ChatEntity;
import com.etrivium.backend.repository.entity.ChatFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatFileRepository extends JpaRepository<ChatFileEntity, UUID> {

}