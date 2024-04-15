package com.incypio.law.UserService.controller;

import com.incypio.law.UserService.dto.LoginDto;
import com.incypio.law.UserService.dto.ResponseDto;
import com.incypio.law.UserService.dto.UserDto;
import com.incypio.law.UserService.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/v1/api" , produces ={MediaType.APPLICATION_JSON_VALUE})
public class UserController {
    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Value("${build.version}")
    private String buildVersion ;

    @Autowired
    private Environment environment;

    @PostMapping(path="/register")
    public ResponseEntity<ResponseDto> register(@RequestBody UserDto userDto){
        userService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201","User registered successfully"));
    }

    @PostMapping(path="/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginDto userDto){
        ResponseDto response = userService.login(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping(path="/current")
    public ResponseEntity<UserDto> getCurrentUser(@RequestHeader("Authorization") String token){
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(token);
        UserDto user = userService.getCurrentUser(token);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping(path="/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body("Build version: " + buildVersion);
    }

    @GetMapping(path="/env-info")
    public ResponseEntity<String> getEnvInfo() {
        return ResponseEntity.status(HttpStatus.OK).body("Marven profile: " + environment.getProperty("MAVEN_HOME"));
    }
    @GetMapping(path="/test")
    public ResponseEntity<String> textConnection() {
        return ResponseEntity.status(HttpStatus.OK).body("Test User API Connection");
    }










}
