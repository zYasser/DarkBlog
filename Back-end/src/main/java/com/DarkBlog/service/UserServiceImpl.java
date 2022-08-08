package com.DarkBlog.service;

import com.DarkBlog.config.PasswordEncoder;
import com.DarkBlog.entity.LoginForm;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.EmailAlreadyExistException;
import com.DarkBlog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) throws EmailAlreadyExistException {
        Optional<User> exist = userRepository.findByEmail(user.getEmail());
        if (exist.isPresent())
            throw new EmailAlreadyExistException("This Email Already Exist");
        LOGGER.info("register Service");
        String encodedPassword = passwordEncoder.Argon2PasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.saveAndFlush(user);
        return user;

    }

    @Override
    public boolean login(LoginForm loginForm) {
        if (loginForm.getUsernameOrEmail().contains("@"))
            return loginWithEmail(loginForm.getUsernameOrEmail(), loginForm.getPassword());
        Optional<User> user = userRepository.findByUsername(loginForm.getUsernameOrEmail());
        System.out.println(user);
        if (user.isEmpty())
            return false;
        LOGGER.info("login Service");
        return passwordEncoder.Argon2PasswordEncoder().matches(loginForm.getPassword(), user.get().getPassword());
    }

    private boolean loginWithEmail(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        System.out.println(user);
        if (user.isEmpty())
            return false;
        LOGGER.info("login Service");
        return passwordEncoder.Argon2PasswordEncoder().matches(password, user.get().getPassword());
    }

}
