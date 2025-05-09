package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.CourseEntity;
import com.etrivium.backend.repository.entity.CourseFileEmbeddingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseFileEmbeddingsRepository extends JpaRepository<CourseFileEmbeddingsEntity, UUID> {

}