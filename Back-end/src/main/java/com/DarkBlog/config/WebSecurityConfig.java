package com.DarkBlog.config;

import com.DarkBlog.provider.UsernamePasswordAuthenticationProvider;
import com.DarkBlog.filter.CustomUsernamePasswordAuthenticationFilter;
import com.DarkBlog.handler.CustomLogoutSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@Slf4j
class WebSecurityConfig {
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    private UsernamePasswordAuthenticationProvider provider;
    @Autowired
    private CustomUsernamePasswordAuthenticationFilter filter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf().disable().build();
//        http.authorizeRequests().antMatchers("/api/register", "/logout" , "/api/forgetPassword" , "/api/changePassword/**" , "/api/login" , "/api/post/posts").permitAll();
//        http.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class);
//        http.authorizeRequests().anyRequest().authenticated();
//
//
//        http.logout().deleteCookies().logoutSuccessHandler(customLogoutSuccessHandler);
//        http
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                );
//
//        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

