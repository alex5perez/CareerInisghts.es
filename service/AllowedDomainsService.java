package com.etrivium.backend.service;

import com.etrivium.backend.repository.AllowedDomainsRepository;
import com.etrivium.backend.repository.entity.AllowedDomainsEntity;
import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.service.domain.AllowedDomains;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AllowedDomainsService {

    private static final Logger logger = LoggerFactory.getLogger(AllowedDomainsService.class);

    @Autowired
    AllowedDomainsRepository allowedDomainsRepository;

    public List<AllowedDomains> getAllAllowedDomains() {
        return allowedDomainsRepository.findAll().stream().map(this::allowedDomainsMapper
        ).toList();
    }

    public UUID create(String name, UserEntity user) {
        AllowedDomainsEntity allowedDomainsEntity = AllowedDomainsEntity.builder().company(user.getCompany()).domainName(name).build();
        allowedDomainsRepository.save(allowedDomainsEntity);
        return allowedDomainsEntity.getId();
    }

    public AllowedDomains get(UUID allowedDomainsId, UserEntity user) {
        AllowedDomainsEntity allowedDomainsEntity = allowedDomainsRepository.findByCompanyIdAndId(user.getCompany().getId(), allowedDomainsId);
        return allowedDomainsMapper(allowedDomainsEntity);
    }

    public List<AllowedDomains> list(UserEntity user) {
        List<AllowedDomainsEntity> allowedDomainsEntities = allowedDomainsRepository.findByCompanyId(user.getCompany().getId());
        return allowedDomainsEntities.stream().map(this::allowedDomainsMapper).toList();

    }

    private AllowedDomains allowedDomainsMapper (AllowedDomainsEntity allowedDomainsEntity) {
        return AllowedDomains.builder()
                .domainName(allowedDomainsEntity.getDomainName())
                .companyId(allowedDomainsEntity.getCompany().getId())
                .id(allowedDomainsEntity.getId())
                .build();
    }

    public boolean isOriginAllowedForCompany(String domainName, Integer companyId) {
        return allowedDomainsRepository.findByCompanyIdAndDomainName(companyId, domainName).isPresent();
    }
}
