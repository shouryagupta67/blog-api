package com.example.blog_api.service;

import com.example.blog_api.exception.PostNotFoundException;
import com.example.blog_api.model.Post;
import com.example.blog_api.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<Post> createPost(Post post) {
       Post post1 = postRepository.save(post);
        if(post1 != null){
            return  new ResponseEntity<>(post,HttpStatus.CREATED);
        }
        throw new RuntimeException("Post Not created");
    }

    public ResponseEntity<List<Post>> getAllPosts() {
       List<Post> posts = postRepository.findAll();
       if(posts.isEmpty()){
           throw new PostNotFoundException("No Post found");

       }
        return new ResponseEntity<>(posts,HttpStatus.FOUND);
    }

    public Optional<Post> getPostById(Long id) {
        return Optional.ofNullable(postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException("Post Not Found!")));
    }

    public void deletePost(Long id) {

            Post post = postRepository.findById(id)
                    .orElseThrow(()->new PostNotFoundException("post not found!"));
        postRepository.deleteById(id);
    }

    public Post updatePost(Long postId, Post updatedPost) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        // Update fields
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());

        // Add any other fields you have

        return postRepository.save(existingPost);
    }
}
