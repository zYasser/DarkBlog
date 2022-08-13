package com.DarkBlog.service;

import com.DarkBlog.form.LoginForm;
import com.DarkBlog.entity.Role;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.EmailAlreadyExistException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    User register(User user) throws EmailAlreadyExistException;
    boolean login(LoginForm loginForm, HttpServletResponse response);
    boolean addRoleToUser(String username , String roleName);
    Role createRole(Role role);
    User getMe(HttpServletRequest request);
    List<User> getAllUsers();

}
