package com.DarkBlog.control;

import com.DarkBlog.form.LoginForm;
import com.DarkBlog.entity.Role;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.EmailAlreadyExistException;
import com.DarkBlog.form.RoleToUserForm;
import com.DarkBlog.service.UserServiceImpl;
import com.DarkBlog.utility.JwtTokenVerifier;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserControl {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) throws EmailAlreadyExistException {
        return userService.register(user);
    }
//
//    @GetMapping("/login")
//    public boolean login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
//        boolean res = userService.login(loginForm, response);
//        System.out.println(response.toString());
//        return res;
//    }

    @GetMapping("/me")
    public String  me(HttpServletRequest request) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = request.getReader()) {
            char[] buf = new char[4096];
            for (int len; (len = in.read(buf)) > 0; )
                builder.append(buf, 0, len);
        }
        String requestBody = builder.toString();
        log.info("Request body: " + requestBody);
        return "abc";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.createRole(role));
    }
    @PostMapping("/user/addRule")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                JwtTokenVerifier tokenVerifier = new JwtTokenVerifier(refreshToken, "secret");
                DecodedJWT decodedJWT = tokenVerifier.getDecodedJWT();
                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                System.out.println("username = " + username);
                User user=userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI())
                        .withClaim(
                                "roles",
                                user.getRoles().stream().map(Role::getName).collect(Collectors.toList())
                        )
                        .sign(tokenVerifier.getAlgorithm());

            } catch (Exception e) {
                log.error("Error logging in! : {}", e.getMessage());
                response.setHeader("error" ,e.getMessage());
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
            throw new RuntimeException("Refresh Token is expired");

        }
    }

}
