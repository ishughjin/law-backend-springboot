package com.incypio.law.LegalConsultationService.dto;

import com.incypio.law.LegalConsultationService.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ConversationDto {
    private UUID id;
    private String title;
    private Long userId ;
    private List<Message> messages;
}



