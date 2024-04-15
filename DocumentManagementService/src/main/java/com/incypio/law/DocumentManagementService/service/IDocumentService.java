package com.incypio.law.DocumentManagementService.service;

import com.incypio.law.DocumentManagementService.dto.DocumentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface  IDocumentService {
    void createDocument(DocumentDto documentDto);
    DocumentDto getDocument(Long id) ;
    boolean updateDocument(DocumentDto documentDto);

    List<DocumentDto> getAllDocuments();

    String testConnection();

    public Page<DocumentDto> getDocuments(int page , int size, long userId, String token) ;
}
