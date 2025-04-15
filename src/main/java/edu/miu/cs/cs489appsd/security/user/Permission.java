package edu.miu.cs.cs489appsd.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    ADMIN_WRITE("admin:write"),
    ADMIN_READ("admin:read"),
    MEMBER_WRITE("member:write"),
    MEMBER_READ("member:read");

    private final String permission;
}
