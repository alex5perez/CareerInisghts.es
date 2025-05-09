package com.etrivium.backend.repository;

import com.etrivium.backend.repository.entity.AllowedDomainsEntity;
import com.etrivium.backend.repository.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AllowedDomainsRepository extends JpaRepository<AllowedDomainsEntity, UUID> {
    List<AllowedDomainsEntity> findAllowedDomainsEntitiesByCompanyId(Integer company_id);
    AllowedDomainsEntity findByCompanyIdAndId(Integer company_id, UUID id);

    List<AllowedDomainsEntity> findByCompanyId(Integer company_id);

    Optional<AllowedDomainsEntity> findByCompanyIdAndDomainName(Integer CompanyId, String domainName);
}