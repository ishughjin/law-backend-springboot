package com.incypio.law.LegalConsultationService.controller;

import com.incypio.law.LegalConsultationService.dto.*;
import com.incypio.law.LegalConsultationService.service.IConversationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/api", produces = "application/json")
public class ConsultController {
    IConversationService iConversationService ;
    public ConsultController(IConversationService iConversationService) {
        this.iConversationService = iConversationService;
    }

    @Value("${build.version}")
    private String buildVersion ;

    @PostMapping("/createConversation")
    public ResponseEntity<ResponseDto> createConversation(@RequestBody ConversationDto conversationDto) {
        iConversationService.createConversation(conversationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(HttpStatus.CREATED.toString(), "Consulting Conversation created successfully"
                ));
    }

    @PostMapping("/createConversationByMessage")
    public ResponseEntity<ModelResponseDto> createConversationByMessage(@RequestParam String content, @RequestParam Long userId) {
        ModelResponseDto result = iConversationService.createConversationByMessage(content, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                result
        );
    }

    @GetMapping("getConversation")
    public ResponseEntity<ConversationResponseDto> getConversation(@RequestParam UUID id) {
        ConversationResponseDto conversationDto = iConversationService.getConversation(id);
        return ResponseEntity.status(HttpStatus.OK).body(conversationDto);
    }

    @GetMapping(path="/getConversation/all")
    public ResponseEntity<Page<ConversationDto>> getAllConversations(@RequestParam int page, @RequestParam long userId) {
        Page<ConversationDto> conversationDtos = iConversationService.getAllConversations(page, userId);
        return ResponseEntity.status(HttpStatus.OK).body(conversationDtos);
    }

    @GetMapping(path="/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body("Build version: " + buildVersion);
    }

}
