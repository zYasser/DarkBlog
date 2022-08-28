package com.DarkBlog.service;

import com.DarkBlog.config.PasswordEncoder;
import com.DarkBlog.entity.Role;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.EmailAlreadyExistException;
import com.DarkBlog.form.LoginForm;
import com.DarkBlog.repository.RoleRepository;
import com.DarkBlog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

@Service
@Transactional
@Slf4j
public class UserServiceImpl {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    public User register(User user) throws EmailAlreadyExistException {
        log.info("Currently in UserService");
        log.info("Saving a new User");
        Optional<User> exist = userRepository.findByEmail(user.getEmail());
        if (exist.isPresent()) {
            log.error("Account already exist with this email");
            throw new EmailAlreadyExistException("This Email Already Exist");
        }
        String encodedPassword = passwordEncoder.Argon2PasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        addRoleToUser(user);
        userRepository.saveAndFlush(user);
        return user;

    }
    @Transactional
    public void addRoleToUser(User user){
        Optional<Role> role=roleRepository.findByName("user");
        if(role.isEmpty()){
            throw new NullPointerException("Roll doesn't exist");

        }
        user.setRoles(new ArrayList<>(asList(role.get())));
        role.get().getUsers().add(user);
        roleRepository.save(role.get());
    }

    public boolean login(LoginForm loginForm, HttpServletResponse response) {
        log.info("Login a new User");
        System.out.println("loginForm = " + loginForm);
        if (loginForm.getUsername() == null || loginForm.getPassword() == null) {
            log.error("user or password is null");
            return false;
        }
        if (loginForm.getUsername().contains("@"))
            return loginWithEmail(loginForm.getUsername(), loginForm.getPassword());
        Optional<User> user = userRepository.findByUsername(loginForm.getUsername());
        System.out.println(user);
        if (user.isEmpty()) {
            log.error("user doesn't exist");
            return false;
        }
        if (!passwordEncoder.Argon2PasswordEncoder().matches(loginForm.getPassword(), user.get().getPassword())) {
            log.error("not correct password");
            return false;
        }
        return true;
    }

    public String getMe(Authentication authentication)   {
        log.info("get me ");
        return authentication.getName();
    }

    public List<User> getAllUsers() {
        log.info("fetching all user");
        return userRepository.findAll();
    }

    public User getUser(String username) {
        if (username != null) {
            return userRepository.findByUsername(username).orElse(null);

        }
        else {
            return null;
        }
    }
    public Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }


    private boolean loginWithEmail(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        System.out.println(user);
        if (user.isEmpty())
            return false;
        return passwordEncoder.Argon2PasswordEncoder().matches(password, user.get().getPassword());
    }
}