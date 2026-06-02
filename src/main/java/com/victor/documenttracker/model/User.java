package com.victor.documenttracker.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) //must be unique
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable=false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "uploadedBy")
    private Set<Document> documents;

    //constructors
    public User() {}

    public User(String username, String password, String email, Role role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
