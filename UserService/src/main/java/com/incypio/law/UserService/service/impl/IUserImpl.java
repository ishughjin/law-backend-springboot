package com.incypio.law.UserService.service.impl;

import com.incypio.law.UserService.dto.LoginDto;
import com.incypio.law.UserService.dto.ResponseDto;
import com.incypio.law.UserService.dto.UserDto;
import com.incypio.law.UserService.dto.UserMsgDto;
import com.incypio.law.UserService.entity.UserEntity;
import com.incypio.law.UserService.mapper.UserMapper;
import com.incypio.law.UserService.repository.UserRepository;
import com.incypio.law.UserService.service.IUserService;
import com.incypio.law.UserService.util.JwtUtils;
import com.incypio.law.UserService.util.UserThreadLocal;
import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
 @AllArgsConstructor
public class IUserImpl implements IUserService {
    private static final Logger log = LoggerFactory.getLogger(IUserImpl.class);

    private UserRepository userRepository ;
    private final StreamBridge streamBridge;
    private void sendCommunication(UserEntity user) {
        var userMsgDto = new UserMsgDto(user.getId(), user.getEmail(), user.getUsername());
        log.info("Sending communication request for the details: {}" , user);
        var result = streamBridge.send("sendCommunication-out-0", userMsgDto);
        log.info("Message sent to kafka topic, {}", result);
    }
    @Override
    public void register(UserDto userDto) {
        UserEntity user = UserMapper.toUserEntity(new UserEntity(), userDto);
        userRepository.findByEmail(userDto.getEmail()).ifPresent(userEntity -> {
            throw new RuntimeException("User already exists");
        });
        userRepository.findByUsername(userDto.getUsername()).ifPresent(userEntity -> {
            throw new RuntimeException("Username already exists");
        });
        String md5Password = DigestUtils.md5DigestAsHex((userDto.getPassword() + user.getSalt()).getBytes());
        user.setPassword(md5Password);
        long randomId = 1000000000L + new Random().nextInt(900000000);
        user.setId(randomId);
        UserEntity savedUser = userRepository.save(user);
        sendCommunication(savedUser);

    }

    @Override
    public ResponseDto login(LoginDto user) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(user.getEmail());
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            String md5Password = DigestUtils.md5DigestAsHex((user.getPassword() + userEntity.getSalt()).getBytes());
            if (md5Password.equals(userEntity.getPassword())) {
                String token = JwtUtils.createJtw(userEntity);
                return new ResponseDto("200", token);
            } else {
                return new ResponseDto("401", "Invalid credentials");
            }
        } else {
            return new ResponseDto("404", "User not found");
        }
    }

    @Override
        public UserDto getCurrentUser(String token) {
            UserEntity  user = JwtUtils.parseJwt(token);
        System.out.println("user_Entity:" + user.getId());

        return UserMapper.toUserDto(user, new UserDto());
        }

    @Override
    public boolean updateCommunicationStatus(Long id) {
        boolean isUpdated = false ;
        System.out.println("IsUpdated: "+  isUpdated);

        if(id != null) {
            UserEntity user =  userRepository.findById(id).orElseThrow(
                    () -> new NoSuchElementException("Account Not Found " + id.toString() )
            );
            user.setCommunicationSw(true);
            userRepository.save(user) ;
            isUpdated = true;
            System.out.println("IsUpdated: "+  isUpdated);

        }
        return isUpdated;

    }

}
