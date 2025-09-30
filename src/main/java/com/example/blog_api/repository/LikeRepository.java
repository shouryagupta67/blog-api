package com.example.blog_api.repository;

import com.example.blog_api.model.Like;
import com.example.blog_api.model.Post;
import com.example.blog_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByPostId(Long postId);
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);

    List<Like> findByPost(Post post);

    Optional<Like> findByPostAndUser(Post post, User user);
}
