package com.incypio.law.LegalConsultationService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private String content;
    private UUID conversationId;
    private LocalDateTime createdAt;
    private String createdBy;
}
