package com.example.library.management.repository;

import com.example.library.management.domain.User;
import com.example.library.management.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndStatus(String email, UserStatus status);
}
