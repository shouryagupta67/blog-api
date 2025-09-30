package com.example.blog_api.exception;

public class CommentNotFoundException  extends RuntimeException{
    public CommentNotFoundException(String message){
        super(message);
    }
}
