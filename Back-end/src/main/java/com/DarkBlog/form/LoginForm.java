package com.DarkBlog.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginForm {
    private String username;
    private String password;
}
