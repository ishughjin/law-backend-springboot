package com.incypio.law.LegalConsultationService.service;

import com.incypio.law.LegalConsultationService.dto.*;
import com.incypio.law.LegalConsultationService.entity.Message;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IMessageService {
    ModelResponseDto createMessage(MessageDto messageDto);
    Page<MessageResponse> getAllMessages(int page, UUID conversationId );

}
