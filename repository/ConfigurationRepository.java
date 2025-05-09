package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.CompanyEntity;
import com.etrivium.backend.repository.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, UUID> {
    ConfigurationEntity findByCompany_Id(Integer company_id);
}