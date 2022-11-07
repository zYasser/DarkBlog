package com.DarkBlog.control;

import com.DarkBlog.entity.User;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.error.EmailAlreadyExistException;
import com.DarkBlog.error.PasswordMatchException;
import com.DarkBlog.form.ChangePasswordForm;
import com.DarkBlog.form.LoginForm;
import com.DarkBlog.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }

    @PostMapping("/login")
    public boolean login() {
        return true;
    }

    @GetMapping("/me")
    public User me(Authentication authentication) throws DoesNotExistException {

        return userService.getUserByUsername(authentication.getName());
    }
    @GetMapping("/forgetPassword")
    public String forgetPassword(@RequestParam("email") String email){

         return userService.forgetPassword(email);
    }
    @PostMapping("/changePassword/reset")
    public boolean changePassword(@RequestParam("token") String token , @RequestBody ChangePasswordForm form) throws PasswordMatchException, DoesNotExistException {
        return userService.changePassword(token,form);
    }
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) throws DoesNotExistException {

        return userService.getUser((long)id);
    }

}