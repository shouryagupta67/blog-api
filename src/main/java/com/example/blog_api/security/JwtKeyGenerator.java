package com.example.blog_api.security;
// JwtKeyGenerator.java - run as a small main program

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // strong key for HS512
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Your HS512 Key: " + base64Key);
    }
}

