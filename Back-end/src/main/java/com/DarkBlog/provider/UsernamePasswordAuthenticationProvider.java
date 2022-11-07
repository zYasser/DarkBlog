package com.DarkBlog.provider;

import com.DarkBlog.config.PasswordEncoder;
import com.DarkBlog.entity.User;
import com.DarkBlog.repository.UserRepository;
import com.DarkBlog.service.UserDetailImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {



    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=String.valueOf(authentication.getCredentials());
        if(username==null || password==null){
            throw new BadCredentialsException("Please Check Password and Username");

        }
        System.out.println("password = " + password);
        log.info("here from UsernamePasswordAuthenticationProvider ");
        Optional<User> u =userRepository.findByUsername(username);
        if(u.isEmpty()){
            log.error("there is no user exist");
            return null;
        }
        if(checkPassword(password,u.get().getPassword())) {
            UserDetails user = new UserDetailImpl(u.get());
            log.info("Successfully log in");

            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        }
        else{
            log.error("password is incorrect ");
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
