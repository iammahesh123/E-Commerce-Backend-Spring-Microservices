package com.gfg.userservice.domain.enums;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleBasedAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    @Column(name = "role")
    private final String role;
}
