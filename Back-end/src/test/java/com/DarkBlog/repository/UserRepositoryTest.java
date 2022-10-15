package com.DarkBlog.repository;

import com.DarkBlog.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deleteUserIfRoleDidNotAdd() {
    }

    @BeforeEach
    void setUp() {
    }
}