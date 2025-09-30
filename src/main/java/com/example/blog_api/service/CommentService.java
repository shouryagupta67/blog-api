package com.example.blog_api.service;

import com.example.blog_api.exception.PostNotFoundException;
import com.example.blog_api.model.Comment;
import com.example.blog_api.model.Post;
import com.example.blog_api.model.User;
import com.example.blog_api.repository.CommentRepository;
import com.example.blog_api.repository.PostRepository;
import com.example.blog_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // CREATE comment
    public Comment addComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id " + postId));
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    // GET all comments by post
    public List<Comment> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id " + postId));
        return commentRepository.findByPost(post);
    }

    // GET single comment by ID
    public Optional<Comment> getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id " + postId));
        return commentRepository.findByIdAndPost(commentId, post);
    }

    // UPDATE comment
    public Optional<Comment> updateComment(Long postId, Long commentId, Comment updatedComment) {
        return getCommentById(postId, commentId).map(existingComment -> {
            existingComment.setContent(updatedComment.getContent());
            return commentRepository.save(existingComment);
        });
    }

    // DELETE comment
    public boolean deleteComment(Long postId, Long commentId) {
        return getCommentById(postId, commentId).map(comment -> {
            commentRepository.delete(comment);
            return true;
        }).orElse(false);
    }
}
