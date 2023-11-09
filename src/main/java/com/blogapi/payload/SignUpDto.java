package com.blogapi.payload;

import lombok.Data;
@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}

//LoginDto
//AuthController
//CustomeUserDetailsService
//SecurityConfig
//TODO: these are the four layer we require to build a signup feature.
