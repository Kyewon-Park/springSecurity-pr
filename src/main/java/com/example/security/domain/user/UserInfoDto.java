package com.example.security.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
    private String username;
    private String password;
    private String auth;
}
