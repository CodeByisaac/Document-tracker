package com.victor.documenttracker.model;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> user;

    public Role() {}

    public Role(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
