package com.DarkBlog.service;

import com.DarkBlog.entity.Post;
import com.DarkBlog.error.AuthorizationException;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.form.PostForm;
import com.DarkBlog.form.PostPaginationForm;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PostService {
    Post createPost(PostForm postForm) throws DoesNotExistException;
    boolean deletePost(Long postId, String userId) throws DoesNotExistException, AuthorizationException;
    boolean votePost(String id);
    Post findPostById(Long id) throws DoesNotExistException;
    List<Post> findAllPost();

    List<Post> findWithPagination(Integer page);
}
