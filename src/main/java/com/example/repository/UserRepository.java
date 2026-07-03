package com.example.repository;

import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {


    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
