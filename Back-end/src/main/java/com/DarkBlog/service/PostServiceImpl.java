package com.DarkBlog.service;

import com.DarkBlog.entity.Post;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.AuthorizationException;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.form.PostForm;
import com.DarkBlog.repository.PostRepository;
import com.DarkBlog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        log.info("Currently checking if account still available");
        Optional<User> user = userRepository.findById(postForm.getUser_id());
        if(user.isEmpty()){
            log.error("user doesn't exist");
            throw new DoesNotExistException("User doesn't exist");
        }
        log.info("User exist currently adding user ");
        Post post = postForm.getPost();
        post.setUserId(user.get());
        postRepository.saveAndFlush(post);
        log.info("Post Added Successfully");
        return post;

    }

    @Override
    public boolean deletePost(Long postId ,String userId) throws DoesNotExistException, AuthorizationException {
        Post post=postRepository.findById(postId).orElseThrow(()->{
            log.error("There's No Post With this id {}",postId);
            return new DoesNotExistException("There's no existed post with this id");
        });
        if(!post.getUserId().getUsername().equals(userId)){
            log.error("{} user not authorized to delete post {}",userId,postId);
            throw new AuthorizationException("You don't have the privilege to delete this post");
        }
        postRepository.delete(post);
        return true;
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
    public List<Post> findWithPagination(Integer page) {
        log.info("From service currently fetching data....");
        return postRepository.findAllPagination(PageRequest.of(page, 7));
    }

    @Override
    public Post findPostById(Long id) throws DoesNotExistException {
        log.info("currently in services fetching post by an id");
        return postRepository.findById(id).orElseThrow(() -> new DoesNotExistException("There's no post with this id"));
    }
    public boolean upVote(Long userId, Long PostId){

        return true;
    }
}
