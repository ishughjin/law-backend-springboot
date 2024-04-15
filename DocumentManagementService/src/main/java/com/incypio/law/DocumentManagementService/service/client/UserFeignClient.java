package com.incypio.law.DocumentManagementService.service.client;

import com.incypio.law.DocumentManagementService.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user")
public interface UserFeignClient {
    @GetMapping(value = "/v1/user/test",consumes = "application/json")
    public ResponseEntity<String> textConnection() ;

    @GetMapping(value = "/v1/user/current",consumes = "application/json")
    public ResponseEntity<UserDto> getCurrentUser(@RequestHeader("Authorization") String token);
    }
