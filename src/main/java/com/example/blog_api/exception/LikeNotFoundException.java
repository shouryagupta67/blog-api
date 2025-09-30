package com.example.blog_api.exception;

public class LikeNotFoundException extends RuntimeException{
    public LikeNotFoundException(String message){
        super(message);
    }
}
