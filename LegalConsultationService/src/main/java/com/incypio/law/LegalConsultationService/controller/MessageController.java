package com.incypio.law.LegalConsultationService.controller;

import com.incypio.law.LegalConsultationService.dto.MessageDto;
import com.incypio.law.LegalConsultationService.dto.MessageResponse;
import com.incypio.law.LegalConsultationService.dto.ModelResponseDto;
import com.incypio.law.LegalConsultationService.dto.ResponseDto;
import com.incypio.law.LegalConsultationService.service.IConversationService;
import com.incypio.law.LegalConsultationService.service.IMessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/api", produces = "application/json")
@AllArgsConstructor
public class MessageController {
    IConversationService iConversationService ;
    IMessageService iMessageService;
    @PostMapping("/createMessage")
    public ResponseEntity<ModelResponseDto> createMessage(@RequestBody MessageDto messageDto) {
        ModelResponseDto responseDto;
        try {
            responseDto = iMessageService.createMessage(messageDto);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating message");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                responseDto
        );
    }

    @GetMapping(path="/getMessages")
    public ResponseEntity<Page<MessageResponse>> getAllMessages(@RequestParam int page, @RequestParam UUID conversationId) {
        Page<MessageResponse> messageDtos = iMessageService.getAllMessages(page, conversationId);
        return ResponseEntity.status(HttpStatus.OK).body(
                messageDtos
                );
    }

}
