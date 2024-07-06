package com.gfg.userservice.security;


public interface JwtUtil {
    String generateToken(String subject);
    String extractUsername(String token);
    boolean validateToken(String token, String username);
}

