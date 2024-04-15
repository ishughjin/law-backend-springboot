package com.incypio.law.LegalConsultationService.dto;

import com.incypio.law.LegalConsultationService.entity.Message;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ConversationResponseDto {
    private UUID id;
    private String title;
    private long userId ;
    private List<Message> messages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
