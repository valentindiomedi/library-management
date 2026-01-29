package com.example.library.management.repository;

import com.example.library.management.domain.Author;
import com.example.library.management.domain.Book;
import com.example.library.management.domain.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    // ========================= FILTERS (PAGINATED) =========================

    Page<Book> findByAuthor(Author author, Pageable pageable);

    Page<Book> findByCategory(String category, Pageable pageable);

    Page<Book> findByStatus(BookStatus status, Pageable pageable);

    Page<Book> findByAuthorAndStatus(
            Author author,
            BookStatus status,
            Pageable pageable
    );

    Page<Book> findByCategoryAndStatus(
            String category,
            BookStatus status,
            Pageable pageable
    );
}
