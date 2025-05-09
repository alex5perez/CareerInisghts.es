package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.CompanyUserCourseDownloadEntity;
import com.etrivium.backend.repository.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyUserCourseDownloadRepository extends JpaRepository<CompanyUserCourseDownloadEntity, UUID> {
    CompanyUserCourseDownloadEntity findByCompany_Id(Integer company_id);
    void deleteAllByCourse_Id(UUID course_id);
    Integer countCompanyUserCourseDownloadEntitiesByCompany_Id(Integer company_id);
}