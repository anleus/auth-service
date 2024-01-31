package com.anleus.authservice.repository;

import com.anleus.authservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
