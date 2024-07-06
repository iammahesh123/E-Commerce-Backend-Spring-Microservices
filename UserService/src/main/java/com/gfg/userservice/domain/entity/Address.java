package com.gfg.userservice.domain.entity;

import com.gfg.userservice.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
@Builder
public class Address extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", unique = true, nullable = false, updatable = false)
    private Integer addressId;

    @Column(name = "full_address", unique = true)
    private String fullAddress;

    @Column(name = "postal_code")
    private String postalCode;

    private String  city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
