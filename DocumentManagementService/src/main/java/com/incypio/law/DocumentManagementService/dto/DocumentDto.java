package com.incypio.law.DocumentManagementService.dto;

import lombok.Data;

@Data
public class DocumentDto {
    private String title;
    private String url ;
    private String content;
    private Long id;
    private Long userId;
}
