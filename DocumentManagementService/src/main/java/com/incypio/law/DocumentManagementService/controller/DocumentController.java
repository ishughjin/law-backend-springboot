package com.incypio.law.DocumentManagementService.controller;

import com.incypio.law.DocumentManagementService.dto.DocumentDto;
import com.incypio.law.DocumentManagementService.entity.ResponseDto;
import com.incypio.law.DocumentManagementService.service.IDocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/document", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DocumentController {
    IDocumentService documentService;

    public DocumentController(IDocumentService documentService) {
        this.documentService = documentService;
    }

    @Value("${build.version}")
    private String buildVersion ;

    @PostMapping("/createDocument")
    public ResponseEntity<ResponseDto> createDocument(@RequestBody  DocumentDto documentDto) {
        documentService.createDocument(documentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("200" , "Document created successfully")
        );
    }

    @GetMapping("/getDocument")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.getDocument(id));
    }

    @PutMapping("/updateDocument")
    public ResponseEntity<ResponseDto> updateDocument(@RequestBody DocumentDto documentDto) {
        boolean isSuccess = documentService.updateDocument(documentDto);
        if(isSuccess) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("200", "Document updated successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("500", "Document not updated")
            );
        }

    }

    @GetMapping("/getAllDocuments")
    public ResponseEntity<List<DocumentDto>> getAllDocuments() {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.getAllDocuments());
    }
    @GetMapping(path="/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body("Build version: " + buildVersion);
    }

    @GetMapping("/testConnection")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.testConnection());
    }

    @GetMapping("/getDocumentsByUser")
    public ResponseEntity<Page<DocumentDto>> getDocumentsByUserId(@RequestParam int page, @RequestParam int size, @RequestParam Long userId, @RequestHeader("Authorization") String token) {
        Page<DocumentDto> documents = documentService.getDocuments(page, size, userId, token);

        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }



}
