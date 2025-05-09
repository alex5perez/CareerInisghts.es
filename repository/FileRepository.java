package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.ChatEntity;
import com.etrivium.backend.repository.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {
    List<FileEntity> findByCompany_Id(Integer companyId);
    FileEntity findByCompany_IdAndId(Integer companyId, UUID id);
}