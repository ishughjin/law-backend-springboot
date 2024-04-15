package com.incypio.law.LegalConsultationService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import  lombok.* ;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class BaseEntity {
    @Column(updatable = false)
    public LocalDateTime createdAt;
    @Column(updatable = false)
    public String createdBy;
    public LocalDateTime updatedAt;
    @Column(insertable = false)
    public String updatedBy;
}
