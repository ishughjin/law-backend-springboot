package com.incypio.message.function;

import com.incypio.message.dto.UserMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {
    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);
    @Bean
    public Function<UserMsgDto, UserMsgDto>email()  {
        return userMsgDto -> {
            log.info("Email function called" + userMsgDto.toString());
            return userMsgDto;
        };
    }

    @Bean
    public Function<UserMsgDto, Long> sms() {
        return userMsgDto -> {
            log.info("SMS function called");
            return userMsgDto.id();
        };
    }
}
