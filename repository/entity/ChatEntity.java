package com.etrivium.backend.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @OneToMany
    @JoinColumn(name = "chat_file_id")
    private List<ChatFileEntity> files;

    @OneToMany
    @JoinColumn(name = "chat_id")
    private List<ConversationEntity> conversations;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}