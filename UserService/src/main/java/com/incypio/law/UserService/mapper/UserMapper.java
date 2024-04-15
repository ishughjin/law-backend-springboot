package com.incypio.law.UserService.mapper;

import com.incypio.law.UserService.dto.UserDto;
import com.incypio.law.UserService.entity.UserEntity;

public class UserMapper {
    public static UserDto toUserDto(UserEntity user, UserDto userDto ){
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public static UserEntity toUserEntity(UserEntity user, UserDto userDto) {
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        return user;
    }
}
