package com.incypio.law.LegalConsultationService.dto;

import lombok.*;

import java.util.UUID;

@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ModelDto {
    private UUID conversation_id;
    private String content;
}
