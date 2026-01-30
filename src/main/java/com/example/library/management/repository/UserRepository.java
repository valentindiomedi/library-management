package com.example.library.management.repository;

import com.example.library.management.domain.User;
import com.example.library.management.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndStatus(String email, UserStatus status);

    // ========================= FILTERS =========================
    Page<User> findByStatus(UserStatus status, Pageable pageable);

    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
