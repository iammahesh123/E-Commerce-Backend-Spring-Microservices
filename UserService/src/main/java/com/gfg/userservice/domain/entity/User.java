package com.gfg.userservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfg.userservice.audit.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;


import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"addresses", "credential"})
@Data
@Builder
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false, updatable = false)
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "image_url")
    private String imageUrl;

    @Email(message = "*Input must be in Email format!**")
    private String email;
    private String phone;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Address> addresses;


    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Credential credential;


    public <T> User(String username, String password, Boolean isEnabled, Boolean isAccountNonExpired, Boolean isCredentialsNonExpired, Boolean isAccountNonLocked, List<T> ts) {
        super();
    }
}
