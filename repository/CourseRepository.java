package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    List<CourseEntity> findAllByUserId(Integer userId);
    List<CourseEntity> findAllByUserIdOrderByCreateDateDesc(Integer userId);
    CourseEntity findByIdAndUserId(UUID id, Integer userId);
}