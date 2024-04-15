package com.incypio.law.UserService.functions;

import com.incypio.law.UserService.entity.UserEntity;
import com.incypio.law.UserService.service.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import org.slf4j.Logger; // Changed to SLF4J
import org.slf4j.LoggerFactory; // Ensure this import is correct
// Logger

@Configuration
public class UserFunctions {
    public static final Logger log = (Logger) LoggerFactory.getLogger(UserFunctions.class);
    @Bean
    public Consumer<Long> updateCommunication (IUserService iUserService) {
        return id -> {
            log.info("Updating communication status for the Id:" + id.toString());
            iUserService.updateCommunicationStatus(id);
        };

    }
}
