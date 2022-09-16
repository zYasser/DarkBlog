package com.DarkBlog.service;

import com.DarkBlog.entity.Post;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.form.PostForm;
import com.DarkBlog.form.PostPaginationForm;

import java.util.List;

public interface PostService {
    Post createPost(PostForm postForm) throws DoesNotExistException;
    boolean deletePost(String id);
    boolean votePost(String id);

    List<Post> findAllPost();

    List<Post> findWithPagination(Integer page);
}
