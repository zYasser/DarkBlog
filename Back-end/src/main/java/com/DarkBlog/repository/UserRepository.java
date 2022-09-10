package com.DarkBlog.repository;

import com.DarkBlog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String Email);

    Optional<User> findByUsername(String Username);
    @Query(value = "SELECT username from users where user_id=:id" , nativeQuery = true)
    Optional<String> checkIfUserExist(@Param("id") Long id);
}
