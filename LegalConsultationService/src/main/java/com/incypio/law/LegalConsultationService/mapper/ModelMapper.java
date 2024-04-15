package com.incypio.law.LegalConsultationService.mapper;

import com.incypio.law.LegalConsultationService.dto.ConversationDto;
import com.incypio.law.LegalConsultationService.dto.MessageDto;
import com.incypio.law.LegalConsultationService.dto.ModelDto;
import com.incypio.law.LegalConsultationService.dto.ModelResponseDto;
import com.incypio.law.LegalConsultationService.entity.Message;

public class ModelMapper {
    public static ModelDto mapToModelDto(MessageDto messageDto, ModelDto modelDto){
        modelDto.setConversation_id(messageDto.getConversationId());
        modelDto.setContent(messageDto.getContent());
        return modelDto;
    }

    public static Message mapToMessage (Message message , ModelResponseDto modelResponseDto) {
        message.setConversationId(modelResponseDto.getConversation_id());
        message.setContent(modelResponseDto.getResponse());
        message.setCreatedBy("INCYPIO");
        return message;
    }
}
