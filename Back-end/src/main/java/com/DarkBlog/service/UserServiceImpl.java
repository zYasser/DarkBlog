package com.DarkBlog.service;

import com.DarkBlog.config.PasswordEncoder;
import com.DarkBlog.form.LoginForm;
import com.DarkBlog.entity.Role;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.EmailAlreadyExistException;
import com.DarkBlog.repository.RoleRepository;
import com.DarkBlog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User register(User user) throws EmailAlreadyExistException {
        log.info("Saving a new User");
        Optional<User> exist = userRepository.findByEmail(user.getEmail());
        if (exist.isPresent())
            throw new EmailAlreadyExistException("This Email Already Exist");
        String encodedPassword = passwordEncoder.Argon2PasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.saveAndFlush(user);
        return user;

    }

    @Override
    public boolean login(LoginForm loginForm, HttpServletResponse response) {
        log.info("Login a new User");
        if (loginForm.getUsernameOrEmail() == null || loginForm.getPassword() == null)
            return false;
        if (loginForm.getUsernameOrEmail().contains("@"))
            return loginWithEmail(loginForm.getUsernameOrEmail(), loginForm.getPassword());
        Optional<User> user = userRepository.findByUsername(loginForm.getUsernameOrEmail());
        System.out.println(user);
        if (user.isEmpty())
            return false;
        if (!passwordEncoder.Argon2PasswordEncoder().matches(loginForm.getPassword(), user.get().getPassword()))
            return false;
        createCookie(response, user.get().getUsername());
        return true;
    }

    @Override
    public boolean addRoleToUser(String username, String roleName) {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isEmpty())
            return false;
        Role role=roleRepository.findByName(roleName);
        user.get().getRoles().add(role);
        return true;
    }

    @Override
    public Role createRole(Role role) {
            return roleRepository.save(role);
    }

    @Override
    public User getMe(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    System.out.println(cookie.getValue());
                }
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("fetching all user");
        return userRepository.findAll();
    }

    private void createCookie(HttpServletResponse response, String user) {
        Cookie cookie = new Cookie("username", user);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    private boolean loginWithEmail(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        System.out.println(user);
        if (user.isEmpty())
            return false;
        return passwordEncoder.Argon2PasswordEncoder().matches(password, user.get().getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =userRepository.findByUsername(username);
        if(user.isEmpty()){
            log.error("User not found in the databae");
            throw new UsernameNotFoundException("User doesn't exist");
        }


        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        user.get().getRoles().forEach(role-> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),user.get().getPassword() , authorities);

    }
}
