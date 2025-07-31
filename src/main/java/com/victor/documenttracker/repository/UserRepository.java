package com.victor.documenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.documenttracker.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    //Finds a user by username
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
