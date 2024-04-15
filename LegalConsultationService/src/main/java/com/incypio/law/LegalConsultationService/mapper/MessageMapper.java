package com.incypio.law.LegalConsultationService.mapper;

import com.incypio.law.LegalConsultationService.dto.MessageDto;
import com.incypio.law.LegalConsultationService.dto.MessageResponse;
import com.incypio.law.LegalConsultationService.entity.Message;

public class MessageMapper {
    public static MessageDto mapToMessageDto(Message message , MessageDto messageDto){
        messageDto.setContent(message.getContent());
        messageDto.setConversationId(message.getConversationId());
        return messageDto;
    }

    public static Message mapToMessageEntity(MessageDto messageDto , Message message){
        message.setContent(messageDto.getContent());
        message.setConversationId(messageDto.getConversationId());
        return message;
    }

    public static MessageResponse mapToMessageResponse(Message message){
        return new MessageResponse(message.getContent(), message.getConversationId(), message.getCreatedAt(), message.getCreatedBy());
    }

}
