package com.DarkBlog.repository;

import com.DarkBlog.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query(value = "Select * FROM posts ORDER BY created_at DESC" ,nativeQuery = true)
    List<Post> findAllPagination(Pageable pageable);

}
