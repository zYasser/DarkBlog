package com.DarkBlog.service;

import com.DarkBlog.entity.Post;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.form.PostForm;
import com.DarkBlog.form.PostPaginationForm;
import com.DarkBlog.repository.PostRepository;
import com.DarkBlog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Post createPost(PostForm postForm) throws DoesNotExistException {
        log.info("Currently in service to check whether user exist or no");
        Optional<User> user = userRepository.findById(postForm.getUserId());
        if(user.isEmpty()){
            log.error("User doesn't exist!");
            throw new DoesNotExistException("User Doesn't exist");
        }
        log.info("User exist currently adding user ");
        Post post=postForm.getPost();
        post.setId(user.get().getId());
        postRepository.saveAndFlush(post);
        log.info("User Added Successfully");
        return post;

    }

    @Override
    public boolean deletePost(String id) {
        return false;
    }

    @Override
    public boolean votePost(String id) {
        return false;
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findWithPagination(PostPaginationForm postPaginationForm) {
        Pageable page= PageRequest.of(postPaginationForm.getLimit() , 10);
        return postRepository.findAllPagination(page);
    }
}
