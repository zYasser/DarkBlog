package com.DarkBlog.repository;

import com.DarkBlog.entity.ForgetPasswordToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ForgetPasswordRepository extends CrudRepository<ForgetPasswordToken, String> {
    Optional<ForgetPasswordToken> findByUsername(String username);
}
