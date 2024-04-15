package com.incypio.law.DocumentManagementService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter @Getter
public class BaseEntity {
    @Column(updatable = false)
    private String createdBy;
    @Column(updatable = false)

    private LocalDateTime createdAt;
    @Column(insertable = false)
    private String updatedBy;
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
