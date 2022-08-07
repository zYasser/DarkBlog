package com.DarkBlog.service;

import com.DarkBlog.config.PasswordEncoder;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.EmailAlreadyExistException;
import com.DarkBlog.repository.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User register(User user) throws EmailAlreadyExistException {
        Optional<User> exist =userRepository.findByEmail(user.getEmail());
        if(exist.isPresent())
            throw new EmailAlreadyExistException("This Email Already Exist");
        LOGGER.info("register Service");
        String encodedPassword =passwordEncoder.Argon2PasswordEncoder().encode(user.getPassword());
        System.out.println("password = " + encodedPassword);
        user.setPassword(encodedPassword);
        userRepository.saveAndFlush(user);
        return user;

    }
}
