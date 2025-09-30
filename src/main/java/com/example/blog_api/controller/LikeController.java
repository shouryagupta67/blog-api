package com.example.blog_api.controller;

import com.example.blog_api.model.Like;
import com.example.blog_api.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // CREATE a like
    @PostMapping
    public ResponseEntity<Like> addLike(@RequestParam Long postId,
                                        @RequestParam Long userId) {
        return ResponseEntity.ok(likeService.addLike(postId, userId));
    }

    // GET all likes for a post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Like>> getLikesByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.getLikesByPost(postId));
    }

    // GET a single like by ID (optional)
    @GetMapping("/{id}")
    public ResponseEntity<Like> getLikeById(@PathVariable Long id) {
        return likeService.getLikeById(id);
    }

    // DELETE a like
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLike(@PathVariable Long id) {

      return likeService.deleteLike(id);
    }
}
