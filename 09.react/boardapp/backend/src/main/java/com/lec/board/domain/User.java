package com.lec.board.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles; // ROLE_USER, ROLE_ADMIN
}