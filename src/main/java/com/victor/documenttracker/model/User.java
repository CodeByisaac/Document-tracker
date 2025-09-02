//maps to the user table in postgresql and stores login and role
package com.victor.documenttracker.model;

import jakarta.persistence.*;
import java.util.Set;

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
    private Role role;

    //constructors
    public User() {}

    public User(String username, String password, String email, Role role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
