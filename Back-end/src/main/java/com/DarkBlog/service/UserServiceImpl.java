package com.DarkBlog.service;

import com.DarkBlog.config.PasswordEncoder;
import com.DarkBlog.entity.ForgetPasswordToken;
import com.DarkBlog.entity.Role;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.error.EmailAlreadyExistException;
import com.DarkBlog.error.PasswordMatchException;
import com.DarkBlog.form.ChangePasswordForm;
import com.DarkBlog.form.LoginForm;
import com.DarkBlog.repository.ForgetPasswordRepository;
import com.DarkBlog.repository.RoleRepository;
import com.DarkBlog.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static java.util.Arrays.asList;

@Service
@Transactional
@Slf4j
@Data
public class UserServiceImpl {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ForgetPasswordRepository forgetPasswordTokenRepo;

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
    public void addRoleToUser(User user) {
        Optional<Role> role = roleRepository.findByName("User");
        if (role.isEmpty()) {
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

    public String getMe(Authentication authentication) {
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

    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
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

    public User getUser(Long id) throws DoesNotExistException {
        return userRepository.findById(id).orElseThrow(() -> new DoesNotExistException("User doesn't exist"));
    }
    public User getUserByUsername(String username) throws DoesNotExistException {
        log.info("received request to get user {} details " , username);
        return userRepository.findByUsername(username).orElseThrow(() -> new DoesNotExistException("User doesn't exist"));

    }

    public String forgetPassword(String email) {
        log.info(email);
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            log.error("User doesn't exist, End forget password operation");
            return "";
        }
        log.info("email exist currently creating token");
        String username = user.get().getUsername();
        String generatedToken = UUID.randomUUID().toString();
        ForgetPasswordToken token = ForgetPasswordToken.builder().username(username).token(generatedToken).build();
        forgetPasswordTokenRepo.save(token);
        log.info("Generated token has been saved into Database");
        return generatedToken;
    }

    public boolean changePassword(String token,
            ChangePasswordForm changePasswordForm) throws PasswordMatchException, DoesNotExistException {
        String password = changePasswordForm.getPassword();
        if (!password.equals(changePasswordForm.getConfirmedPassword())) {
            log.error("The password and confirm password doesn't match, end Changing password process");
            throw new PasswordMatchException("The password and confirm password doesn't match");
        }
        ForgetPasswordToken _user = forgetPasswordTokenRepo.findById(token).orElseThrow(() -> {
            log.error("token doesn't exist/expired");
            return new DoesNotExistException("token doesn't exist/expired");
        });
        User user = userRepository.findByUsername(_user.username).orElseThrow(() -> {
            log.error("user doesn't exist");
            return new DoesNotExistException("user doesn't exist");

        });
        password = passwordEncoder.Argon2PasswordEncoder().encode(password);
        user.setPassword(password);
        userRepository.saveAndFlush(user);
        forgetPasswordTokenRepo.delete(_user);
        return true;
    }

}