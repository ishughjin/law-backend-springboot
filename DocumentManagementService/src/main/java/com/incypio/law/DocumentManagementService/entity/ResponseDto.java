package com.incypio.law.DocumentManagementService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ResponseDto {
    public String httpCode;
    public String httpMessage;
}
