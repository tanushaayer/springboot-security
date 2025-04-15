package edu.miu.cs.cs489appsd.security.auth.dto;

import edu.miu.cs.cs489appsd.security.user.Role;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Role role
) {
}
