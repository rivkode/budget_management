package com.example.budget_management.domain.user;

import com.example.budget_management.system.common.entity.embedded.Address;
import com.example.budget_management.system.common.entity.embedded.CreatedAtEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "user_table")
@Getter
public class User extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Embedded
    private Address address;

    @Builder
    public User(String email, String username, String password, Address address) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
    }
}
