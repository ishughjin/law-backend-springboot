package com.incypio.law.DocumentManagementService.service.impl;

import com.incypio.law.DocumentManagementService.dto.DocumentDto;
import com.incypio.law.DocumentManagementService.dto.UserDto;
import com.incypio.law.DocumentManagementService.entity.Document;
import com.incypio.law.DocumentManagementService.mapper.DocumentMapper;
import com.incypio.law.DocumentManagementService.repository.DocumentRepository;
import com.incypio.law.DocumentManagementService.service.IDocumentService;
import com.incypio.law.DocumentManagementService.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@AllArgsConstructor
public class IDocumentImpl implements IDocumentService {
    DocumentRepository documentRepository;
    private UserFeignClient userFeignClient;
    public void createDocument(DocumentDto documentDto) {
        Document document = DocumentMapper.mapToDocument(documentDto, new Document());
        long randomId = 1000000000L + new Random().nextInt(900000000);
        document.setCreatedBy("admin");
        document.setCreatedAt(LocalDateTime.now());
        document.setId(randomId);
        System.out.println("user_id_id:" +  documentDto.getUserId());
        documentRepository.save(document);
    }

    public DocumentDto getDocument(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Document not found"));
        return DocumentMapper.mapToDocumentDto(new DocumentDto(), document);
    }

    public List<DocumentDto> getAllDocuments() {
        List<Document> documents = documentRepository.findAll();
        List<DocumentDto> documentDtos = new ArrayList<>();
        for (Document document : documents) {
            documentDtos.add(DocumentMapper.mapToDocumentDto(new DocumentDto(), document));
        }
        return documentDtos;
    }



    public boolean updateDocument(DocumentDto documentDto) {
        Document document = documentRepository.findById(documentDto.getId()).orElseThrow(() -> new RuntimeException("Document not found"));
        DocumentDto updatedDocument = DocumentMapper.mapToDocumentDto(documentDto, document);
        documentRepository.save(document);
        return true;
    }



    public String testConnection() {
        ResponseEntity<String> response = userFeignClient.textConnection();
        return response.getBody();
    }

    public Page<DocumentDto> getDocuments(int page , int size, long userId, String token) {
        Pageable pageable = PageRequest.of(page, size);
        long currentUserId = Objects.requireNonNull(userFeignClient.getCurrentUser(token).getBody()).getId();
        if (currentUserId != userId) {
            throw new RuntimeException("User not authorized to view documents");
        }

        Page<Document> documents = documentRepository.findAllByUserId(userId,  pageable);
        return  documents.map(document -> DocumentMapper.mapToDocumentDto(new DocumentDto(), document));
    }


}
