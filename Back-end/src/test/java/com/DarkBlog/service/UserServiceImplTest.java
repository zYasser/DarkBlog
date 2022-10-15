package com.DarkBlog.service;

import com.DarkBlog.config.PasswordEncoder;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.EmailAlreadyExistException;
import com.DarkBlog.repository.RoleRepository;
import com.DarkBlog.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc(addFilters = false)
class UserServiceImplTest {
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl();
        userService.setUserRepository(userRepository);
        userService.setRoleRepository(roleRepository);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        userService.setPasswordEncoder(passwordEncoder);

    }

    @Test
    void deleteUserIfRoleDidNotAdd() throws EmailAlreadyExistException {
        when(roleRepository.findByName(anyString())).thenReturn(Optional.empty());
        String username = "klo1jfasd";
        User user = User.builder().username(username).email("kasdksa@Live.com").password("kasdkas").build();
        try {
            userService.register(user);
        } catch (Exception e) {
        }
        Mockito.verify((userRepository),times(0)).saveAndFlush(any());

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
}