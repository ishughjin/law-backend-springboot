package com.incypio.law.LegalConsultationService.service.impl;

import com.incypio.law.LegalConsultationService.dto.*;
import com.incypio.law.LegalConsultationService.entity.Conversation;
import com.incypio.law.LegalConsultationService.entity.Message;
import com.incypio.law.LegalConsultationService.mapper.ConversationMapper;
import com.incypio.law.LegalConsultationService.mapper.MessageMapper;
import com.incypio.law.LegalConsultationService.mapper.ModelMapper;
import com.incypio.law.LegalConsultationService.repository.ConversationRepository;
import com.incypio.law.LegalConsultationService.repository.MessageRepository;
import com.incypio.law.LegalConsultationService.service.IConversationService;
import com.incypio.law.LegalConsultationService.service.IMessageService;
import com.incypio.law.LegalConsultationService.service.client.ModelFeignClient;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IConversationImpl implements IConversationService {

    ConversationRepository conversationRepository ;
    IMessageService iMessageService;
    MessageRepository messageRepository;
    ModelFeignClient modelFeignClient;

    @Override
    public void createConversation(ConversationDto conversationDto) {
        Conversation conversation = ConversationMapper.mapToConversationEntity(conversationDto, new Conversation());
        conversation.setCreatedAt(LocalDateTime.now());
        conversation.setUpdatedAt(LocalDateTime.now());
        conversation.setCreatedBy("Admin");
        System.out.println("Conversation： "  + conversation);
        conversationRepository.save(conversation);
    }

    @Override
    public ConversationResponseDto getConversation(UUID id) {
        Conversation conversation = conversationRepository.findById(id).orElseThrow();
        ConversationResponseDto conversationDto = ConversationMapper.mapToConversationResponseDto(new ConversationResponseDto(), conversation);
        List<Message> allMessages = messageRepository.findAllByConversationId(id).orElseThrow();
        conversationDto.setMessages(allMessages);
        return conversationDto;

    }

    @Override
    public Page<ConversationDto> getAllConversations(int page, long userId ) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt");
        Pageable pageable = PageRequest.of(page, 5, sort);
        Page<Conversation> conversations = conversationRepository.findAllByUserId(userId, pageable);
        return conversations.map(conversation -> ConversationMapper.mapToConversationDto(conversation, new ConversationDto()));
    }

    @Override
    public ModelResponseDto createConversationByMessage(String content, Long userId) {
        // Create Conversation By Model
        ModelTitleResponse title_response = modelFeignClient.createConversation(userId);
        Conversation conversation = ConversationMapper.titleModelMapToConversationEntity(title_response, new Conversation());
        conversation.setCreatedAt(LocalDateTime.now());
        conversation.setCreatedBy("Admin");
        conversation.setUpdatedAt(LocalDateTime.now());
        // Save to Database
        System.out.println(conversation.getId());
        conversationRepository.save(conversation);
        System.out.println("Conversation Has been Created");
        System.out.println(conversation.getId());
        MessageDto messageDto = new MessageDto() ;
        messageDto.setConversationId(conversation.getId());
        messageDto.setContent(content);

        return iMessageService.createMessage(messageDto);



    }


}
