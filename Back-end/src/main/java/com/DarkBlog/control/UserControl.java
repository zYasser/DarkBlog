package com.DarkBlog.control;

import com.DarkBlog.entity.User;
import com.DarkBlog.error.EmailAlreadyExistException;
import com.DarkBlog.form.LoginForm;
import com.DarkBlog.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserControl {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) throws EmailAlreadyExistException {
        log.info("Currently Registering a user");
        return userService.register(user);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        return userService.login(loginForm, response);
    }

    @GetMapping("/me")
    public String me(Authentication authentication) throws IOException {

        return userService.getMe(authentication);
    }

}