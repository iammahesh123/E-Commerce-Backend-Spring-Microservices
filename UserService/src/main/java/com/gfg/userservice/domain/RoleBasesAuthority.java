package com.gfg.userservice.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleBasesAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    @Column(name = "role")
    private final String role;
}
