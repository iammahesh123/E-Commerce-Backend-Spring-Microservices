package com.gfg.userservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleBasesAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String role;
}
