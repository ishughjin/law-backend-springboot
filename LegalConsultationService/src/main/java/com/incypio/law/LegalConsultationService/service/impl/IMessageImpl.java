package com.incypio.law.LegalConsultationService.service.impl;

import com.incypio.law.LegalConsultationService.dto.MessageDto;
import com.incypio.law.LegalConsultationService.dto.MessageResponse;
import com.incypio.law.LegalConsultationService.dto.ModelDto;
import com.incypio.law.LegalConsultationService.dto.ModelResponseDto;
import com.incypio.law.LegalConsultationService.entity.Conversation;
import com.incypio.law.LegalConsultationService.entity.Message;
import com.incypio.law.LegalConsultationService.mapper.MessageMapper;
import com.incypio.law.LegalConsultationService.mapper.ModelMapper;
import com.incypio.law.LegalConsultationService.repository.ConversationRepository;
import com.incypio.law.LegalConsultationService.repository.MessageRepository;
import com.incypio.law.LegalConsultationService.service.IMessageService;
import com.incypio.law.LegalConsultationService.service.client.ModelFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IMessageImpl implements IMessageService {
    MessageRepository messageRepository;
    ConversationRepository conversationRepository ;
    ModelFeignClient modelFeignClient;


    @Override
    public ModelResponseDto createMessage(MessageDto messageDto) {
        Message message = MessageMapper.mapToMessageEntity(messageDto, new Message());
        message.setCreatedAt(LocalDateTime.now());
        message.setCreatedBy("Admin");
        // Update Conversation
        Conversation conversation = conversationRepository.findById(messageDto.getConversationId()).orElseThrow();
        conversation.setUpdatedAt(LocalDateTime.now());
        conversation.setUpdatedBy("Admin");
        conversationRepository.save(conversation);
        // Save to Message database
        messageRepository.save(message);
        ModelDto modelDto = ModelMapper.mapToModelDto(messageDto, new ModelDto());
        ModelResponseDto result = modelFeignClient.chat_response(modelDto);
        Message response = ModelMapper.mapToMessage(new Message(), result);
        response.setCreatedAt(LocalDateTime.now());
        messageRepository.save(response);

        return result;
    }

    @Override
    public Page<MessageResponse> getAllMessages(int page, UUID conversationId) {
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("createdAt").ascending());
        Page<Message> message = messageRepository.findByConversationId(conversationId, pageRequest).orElseThrow();
        return message.map(MessageMapper::mapToMessageResponse);
    }
}
