package com.incypio.law.UserService.service;

import com.incypio.law.UserService.dto.LoginDto;
import com.incypio.law.UserService.dto.ResponseDto;
import com.incypio.law.UserService.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface IUserService {
    void register(UserDto userDto);

    ResponseDto login(LoginDto user);

    UserDto getCurrentUser(String token) ;

    boolean updateCommunicationStatus(Long id) ;


}
