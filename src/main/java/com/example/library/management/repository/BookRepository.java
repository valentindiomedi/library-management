package com.example.library.management.repository;

import com.example.library.management.domain.Author;
import com.example.library.management.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library.management.domain.LoanStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    List<Book> findByAuthor(Author author);

    List<Book> findByCategory(String category);

    boolean existsByBookAndStatus(Book book, LoanStatus status);

}
