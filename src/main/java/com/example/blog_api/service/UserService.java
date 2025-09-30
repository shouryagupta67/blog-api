package com.example.blog_api.service;
import com.example.blog_api.model.Role;

import com.example.blog_api.model.User;
import com.example.blog_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User registerUser(User u) {
        if (repo.existsByUsername(u.getUsername())) throw new RuntimeException("Username taken");
        if (repo.existsByEmail(u.getEmail())) throw new RuntimeException("Email taken");
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }
}

