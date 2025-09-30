package com.example.blog_api.service;

import com.example.blog_api.exception.LikeNotFoundException;
import com.example.blog_api.exception.PostNotFoundException;
import com.example.blog_api.exception.UserNotFoundException;
import com.example.blog_api.model.Like;
import com.example.blog_api.model.Post;
import com.example.blog_api.model.User;
import com.example.blog_api.repository.LikeRepository;
import com.example.blog_api.repository.PostRepository;
import com.example.blog_api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository,
                       PostRepository postRepository,
                       UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // CREATE like
    public Like addLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id " + postId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

        // Prevent duplicate likes by the same user
        Optional<Like> existingLike = likeRepository.findByPostAndUser(post, user);
        if (existingLike.isPresent()) return existingLike.get();

        Like like = new Like();
        like.setPost(post);
        like.setUser(user);
        return likeRepository.save(like);
    }

    // GET all likes for a post
    public List<Like> getLikesByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id " + postId));
        return likeRepository.findByPost(post);
    }

    // GET like by ID
    public ResponseEntity<Like> getLikeById(Long likeId) {
      Like like = likeRepository.findById(likeId)
              .orElseThrow(()->new LikeNotFoundException("like not found!"));
      return new ResponseEntity<>(like, HttpStatus.FOUND);

    }

    // DELETE like
    public ResponseEntity<String> deleteLike(Long likeId) {
        Like like = likeRepository.findById(likeId).orElseThrow(()->new LikeNotFoundException("Like Not found!"));
        return new ResponseEntity<>("Deleted",HttpStatus.OK);

    }
}
