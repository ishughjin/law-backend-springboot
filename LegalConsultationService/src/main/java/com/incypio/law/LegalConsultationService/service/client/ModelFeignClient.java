package com.incypio.law.LegalConsultationService.service.client;

import com.incypio.law.LegalConsultationService.dto.ConversationDto;
import com.incypio.law.LegalConsultationService.dto.ModelDto;
import com.incypio.law.LegalConsultationService.dto.ModelResponseDto;
import com.incypio.law.LegalConsultationService.dto.ModelTitleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "modelservice")
public interface ModelFeignClient {
    @PostMapping(value = "api/v1/model" , consumes = "application/json")
    public ModelResponseDto chat_response(@RequestBody ModelDto modelDto) ;

    @PostMapping(value= "api/v1/createConversation", consumes = "application/json")
    public ModelTitleResponse createConversation(@RequestParam Long userId);
}