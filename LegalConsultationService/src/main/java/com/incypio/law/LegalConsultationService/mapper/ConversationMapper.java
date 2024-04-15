package com.incypio.law.LegalConsultationService.mapper;

import com.incypio.law.LegalConsultationService.dto.ConversationDto;
import com.incypio.law.LegalConsultationService.dto.ConversationResponseDto;
import com.incypio.law.LegalConsultationService.dto.ModelTitleResponse;
import com.incypio.law.LegalConsultationService.entity.Conversation;


public class ConversationMapper {
    public static ConversationDto mapToConversationDto(Conversation conversation , ConversationDto conversationDto){
        conversationDto.setId(conversation.getId());
        conversationDto.setTitle(conversation.getTitle());
        conversationDto.setUserId(conversation.getUserId());

        return conversationDto;
    }

    public static Conversation mapToConversationEntity(ConversationDto conversationDto , Conversation conversation){
        conversation.setTitle(conversationDto.getTitle());
        conversation.setUserId(conversationDto.getUserId());
        return conversation;
    }

    public static ConversationResponseDto mapToConversationResponseDto(ConversationResponseDto conversationResponseDto , Conversation conversation) {
        conversationResponseDto.setId(conversation.getId());
        conversationResponseDto.setTitle(conversation.getTitle());
        conversationResponseDto.setCreatedAt(conversation.getCreatedAt());
        conversationResponseDto.setUpdatedAt(conversation.getUpdatedAt());
        conversationResponseDto.setCreatedBy(conversation.getCreatedBy());
        conversationResponseDto.setUpdatedBy(conversation.getUpdatedBy());
        return conversationResponseDto;
    }
    public static Conversation titleModelMapToConversationEntity(ModelTitleResponse modelTitleResponse , Conversation conversation){
        conversation.setTitle(modelTitleResponse.getTitle());
        conversation.setUserId(modelTitleResponse.getUserId());
        return conversation;
    }
}
