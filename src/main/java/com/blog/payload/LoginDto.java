package com.blog.payload;

import lombok.Getter;
import lombok.Setter;

import java.security.SecureRandom;

@Getter
@Setter

public class LoginDto {
    private String usernameOrEmail;
    private String password;

}
