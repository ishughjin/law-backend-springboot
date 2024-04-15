package com.incypio.law.UserService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String username;
    private String password;
    private String email;
    @Column(name= "communication_sw")
    private Boolean communicationSw;
    private String salt = UUID.randomUUID().toString().replace("-", "");
}
