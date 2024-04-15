package com.incypio.law.LegalConsultationService.service;

import com.incypio.law.LegalConsultationService.dto.*;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IConversationService {

    void createConversation(ConversationDto conversationDto);

    ConversationResponseDto getConversation(UUID id);

    Page<ConversationDto> getAllConversations(int page, long userId );
    public ModelResponseDto createConversationByMessage(String content, Long userId) ;

}
