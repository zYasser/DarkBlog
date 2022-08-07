package com.DarkBlog.service;

import com.DarkBlog.entity.User;
import com.DarkBlog.error.EmailAlreadyExistException;

public interface UserService {
    User register(User user) throws EmailAlreadyExistException;
}
