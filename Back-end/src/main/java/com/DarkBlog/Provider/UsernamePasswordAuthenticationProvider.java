package com.DarkBlog.Provider;

import com.DarkBlog.config.PasswordEncoder;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.UserNotExistException;
import com.DarkBlog.repository.UserRepository;
import com.DarkBlog.service.UserDetailImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=String.valueOf(authentication.getCredentials());
        System.out.println("password = " + password);
        log.info("here from UsernamePasswordAuthenticationProvider ");
        Optional<User> u =userRepository.findByUsername(username);
        if(u.isEmpty()){
            return null;
        }
        if(checkPassword(password,u.get().getPassword())) {
            UserDetails user = new UserDetailImpl(u.get());
            log.info("Successfully log in");

            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        }
        else{
            throw new BadCredentialsException("Please Check Password and Username");
        }

    }

    private boolean checkPassword(String password ,String encodedPassword){
        if(!encoder.Argon2PasswordEncoder().matches(password,encodedPassword)){
            return false;
        }
        return true;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
