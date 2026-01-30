package com.example.library.management.repository;

import com.example.library.management.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    // ========================= FILTERS (PAGINATED) =========================

    Page<Author> findByLastNameContainingIgnoreCase(
            String lastName,
            Pageable pageable
    );

    Page<Author> findByNationalityIgnoreCase(
            String nationality,
            Pageable pageable
    );

    Page<Author> findByLastNameContainingIgnoreCaseAndNationalityIgnoreCase(
            String lastName,
            String nationality,
            Pageable pageable
    );
}
