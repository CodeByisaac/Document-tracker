//maps to the user table in postgresql and stores login and role
package com.victor.documenttracker.model;

//jpa persistence annotations
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="users") //names table as user in db

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //unique identifier for user

    @Column(nullable = false, unique = true) //must be unique
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable=false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( //bridge table users and roles
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    //constructors
    public User(){}

    public User(String username, String password, String email, Set<Role> roles){
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password =password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public Set<Role> getRoles(){
        return roles;
    }

}
