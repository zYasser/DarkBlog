package com.DarkBlog.service;

import com.DarkBlog.entity.Post;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.form.PostForm;
import com.DarkBlog.form.PostPaginationForm;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PostService {
    Post createPost(PostForm postForm, Authentication authentication) throws DoesNotExistException;
    boolean deletePost(String id);
    boolean votePost(String id);
    Post findPostById(Long id) throws DoesNotExistException;
    List<Post> findAllPost();

    List<Post> findWithPagination(Integer page);
}
