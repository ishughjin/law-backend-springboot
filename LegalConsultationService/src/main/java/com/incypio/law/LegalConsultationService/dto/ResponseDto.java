package com.incypio.law.LegalConsultationService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ResponseDto {
    private String httpStatus;
    private String httpMessage;
}
