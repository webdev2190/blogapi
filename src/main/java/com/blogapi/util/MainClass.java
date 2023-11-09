package com.blogapi.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainClass {//This perticuler class belongs to the Spring Security.
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println("passwordEncoder.encode(rawPassword:\"test\")");
        System.out.println("Encoded Password: " + passwordEncoder.encode("test"));

    }
}
