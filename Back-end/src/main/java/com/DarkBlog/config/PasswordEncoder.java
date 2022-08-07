package com.DarkBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Configuration
public class PasswordEncoder {
    @Bean
    public Argon2PasswordEncoder Argon2PasswordEncoder(){
        return new Argon2PasswordEncoder();
    }
}
