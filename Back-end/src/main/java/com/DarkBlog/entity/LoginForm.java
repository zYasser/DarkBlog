package com.DarkBlog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginForm {
    private String password;
    private String usernameOrEmail;
}
