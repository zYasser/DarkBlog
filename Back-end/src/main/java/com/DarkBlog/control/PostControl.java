package com.DarkBlog.control;

import com.DarkBlog.entity.Post;
import com.DarkBlog.error.AuthorizationException;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.error.GenericException;
import com.DarkBlog.form.PostForm;
import com.DarkBlog.form.UpVoteForm;
import com.DarkBlog.service.PostService;
import com.DarkBlog.service.UpVoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
@Slf4j
public class PostControl {

    @Autowired
    private PostService postService;
    @Autowired
    private UpVoteService upVoteService;


    @GetMapping("/post")
    private ResponseEntity<Post> getPostById(@RequestParam("id") Long id) throws DoesNotExistException {
        var result = postService.findPostById(id);
        return ResponseEntity.ok(result);

    }


    @PostMapping("/create-post")
    private ResponseEntity<Post> addPost(
            @RequestBody PostForm postForm) throws GenericException, DoesNotExistException {
        log.info("received the request");
        Optional<Post> post = Optional.ofNullable(postService.createPost(postForm));
        if (post.isEmpty())
            throw new GenericException("Something went wrong, Please Try Again");
        return ResponseEntity.ok(post.get());

    }


    @GetMapping("/admin/posts")
    private ResponseEntity<List<Post>> posts() {
        List<Post> list = postService.findAllPost();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/posts")
    private ResponseEntity<List<Post>> postPagination(@RequestParam Integer page) {
        log.info("server receives a request to fetch posts");
        List<Post> list = postService.findWithPagination(page);
        if (list.isEmpty()) {
            log.error("no data left");
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(list);
    }


    @PostMapping("/up-vote")
    private ResponseEntity<Boolean> upvote(@RequestBody UpVoteForm form) throws DoesNotExistException {
        log.info("receive request from up-vote end-point");
        upVoteService.vote(form.getUserId(), form.getPostId(), form.getPoint());
        return ResponseEntity.ok(true);
    }


    @DeleteMapping("/delete")
    private ResponseEntity<Boolean> deletePost(@RequestParam Long post,
            Authentication authentication) throws DoesNotExistException, AuthorizationException {
        log.info("received request from {} to delete post with id {}", authentication.getName(), post);
        return (postService.deletePost(post,
                authentication.getName())) ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);

    }
}
