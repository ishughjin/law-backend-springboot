package com.incypio.law.LegalConsultationService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ModelTitleResponse {
    private Long userId;
    private String title;
}
