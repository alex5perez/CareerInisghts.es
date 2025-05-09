package com.etrivium.backend.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Configuration")
public class ConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer totalCoursesAllowed;

    private Integer totalDownloadsAllowed;

    private Integer chatQueriesPerMonth;

    @OneToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}