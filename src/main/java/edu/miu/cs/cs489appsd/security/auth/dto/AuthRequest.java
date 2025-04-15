package edu.miu.cs.cs489appsd.security.auth.dto;

public record AuthRequest(
        String username,
        String password
) {
}
