package com.example.blog_api.exception;

public class PostNotFoundException  extends RuntimeException{
    public PostNotFoundException(String message){
        super(message);
    }
}