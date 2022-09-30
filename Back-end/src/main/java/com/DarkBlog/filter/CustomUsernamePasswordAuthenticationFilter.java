package com.DarkBlog.filter;

import com.DarkBlog.provider.UsernamePasswordAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomUsernamePasswordAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return  !request.getServletPath()
                .equals("/api/login") ;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

        log.info(
                    "username is {}",
                    username
            );
        Authentication a =new UsernamePasswordAuthentication(username,password);
        try {
            var user =authenticationManager.authenticate(a);
            if(user.isAuthenticated()){
                response.setStatus(HttpServletResponse.SC_OK);
                log.info("Authenticated!");
                SecurityContextHolder.getContext().setAuthentication(a);

                filterChain.doFilter(request, response); // only when authentication worked


            }
            else{
                response.sendError(403 , "Check Your Email and Password");

            }

        }catch (AuthenticationException authenticationException){
            response.sendError(403 , "Check Your Email/Username and Password");

        }
    }
}
