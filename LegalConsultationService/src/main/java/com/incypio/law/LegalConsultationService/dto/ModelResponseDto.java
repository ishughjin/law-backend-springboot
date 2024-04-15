package com.incypio.law.LegalConsultationService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ModelResponseDto {
    private UUID conversation_id;
    private String content;
    private String response;
}
