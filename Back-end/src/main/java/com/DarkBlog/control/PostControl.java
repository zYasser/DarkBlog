package com.DarkBlog.control;

import com.DarkBlog.entity.Post;
import com.DarkBlog.error.GenericException;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.form.PostForm;
import com.DarkBlog.form.PostPaginationForm;
import com.DarkBlog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
@Slf4j
public class PostControl {
    @Autowired
    private PostService postService;
    @PostMapping("/create-post")
    private ResponseEntity<Post> addPost(@RequestBody PostForm postForm) throws GenericException, DoesNotExistException {
      log.info("received the request");
     Optional<Post> post


             = Optional.ofNullable(postService.createPost(postForm));
     if(post.isEmpty())
         throw new GenericException("Something went wrong, Please Try Again");
     return ResponseEntity.ok(post.get());

    }
    @GetMapping("/admin/posts")
    private ResponseEntity<List<Post>> posts(){
        List<Post> list =postService.findAllPost();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/post")
    private ResponseEntity<List<Post>> postPagination(@RequestParam Integer page){
        log.info("server receives a request to fetch posts");
        List<Post> list =postService.findWithPagination(page);
        return ResponseEntity.ok(list);
    }
}
