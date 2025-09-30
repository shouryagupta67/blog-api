package com.example.blog_api.repository;

import com.example.blog_api.model.Comment;
import com.example.blog_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);

    Optional<Comment> findByIdAndPost(Long commentId, Post post);

    List<Comment> findByPost(Post post);
}
