package com.example.blog_api.controller;

import com.example.blog_api.model.Post;
import com.example.blog_api.model.User;
import com.example.blog_api.service.CustomUserDetailsService;
import com.example.blog_api.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CustomUserDetailsService userDetailsService;

    public PostController(PostService postService, CustomUserDetailsService userDetailsService) {
        this.postService = postService;
        this.userDetailsService = userDetailsService;
    }

    // GET all posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return postService.getAllPosts();
    }

    // GET post by ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User author = userDetailsService.loadUserByUsernameAsUser(userDetails.getUsername());

        post.setAuthor(author);
       return postService.createPost(post);
    }

    // UPDATE post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(id, post);
        return ResponseEntity.ok(updatedPost);
    }


    // DELETE post
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(post -> {
                    postService.deletePost(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
