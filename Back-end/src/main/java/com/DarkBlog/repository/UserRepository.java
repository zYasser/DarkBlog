package com.DarkBlog.repository;

import com.DarkBlog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users u WHERE u.email= ?1" ,nativeQuery = true)
    Optional<User> findByEmail(String Email);
    Optional<User> findByUsername(String Username);
}
