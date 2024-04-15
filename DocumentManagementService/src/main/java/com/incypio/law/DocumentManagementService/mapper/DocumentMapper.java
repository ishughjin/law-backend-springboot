package com.incypio.law.DocumentManagementService.mapper;

import com.incypio.law.DocumentManagementService.dto.DocumentDto;
import com.incypio.law.DocumentManagementService.entity.Document;

public class DocumentMapper {
    public static Document mapToDocument(DocumentDto documentDto , Document document) {
        document.setUrl(documentDto.getUrl());
        document.setTitle(documentDto.getTitle());
        document.setId(documentDto.getId());
        document.setContent(documentDto.getContent());
        document.setUserId(documentDto.getUserId());
        return document ;
    }

    public static DocumentDto mapToDocumentDto(DocumentDto documentDto, Document Document) {
        documentDto.setId(Document.getId());
        documentDto.setUrl(Document.getUrl());
        documentDto.setTitle(Document.getTitle());
        documentDto.setContent(Document.getContent());
        documentDto.setUserId(Document.getUserId());
        return documentDto ;
    }


}
