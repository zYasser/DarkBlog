package com.DarkBlog.filter;

import com.DarkBlog.utility.JwtTokenVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("login") || request.getServletPath().equals("register") || request.getServletPath().equals("/api/token/refresh")) {
            filterChain.doFilter(request, response);
        }
        else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    JwtTokenVerifier tokenVerifier = new JwtTokenVerifier(token, "secret");
                    DecodedJWT decodedJWT = tokenVerifier.getDecodedJWT();
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username,
                            "",
                            authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                } catch (Exception e) {
                    log.error("Error logging in! : {}", e.getMessage());
                    response.setHeader("error" ,e.getMessage());
//                    response.sendError(FORBIDDEN.value()); Another way to do the code below
                    response.setStatus(400);
                    Map<String, String> errors = new HashMap<>();
                    errors.put(
                            "error_message",
                            e.getMessage()
                    );
                    response.setContentType(APPLICATION_JSON_VALUE);
                    e.printStackTrace();
                    new ObjectMapper().writeValue(response.getOutputStream(),errors);


                }
            }
            else{
                filterChain.doFilter(request,response);

            }
        }
    }
}
