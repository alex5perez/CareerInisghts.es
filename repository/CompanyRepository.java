package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.CompanyEntity;
import com.etrivium.backend.repository.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
}