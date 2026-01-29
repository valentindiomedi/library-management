package com.example.library.management.repository;

import com.example.library.management.domain.Book;
import com.example.library.management.domain.Loan;
import com.example.library.management.domain.LoanStatus;
import com.example.library.management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {

    Page<Loan> findByStatus(LoanStatus status, Pageable pageable);

    Page<Loan> findByUser(User user, Pageable pageable);

    Page<Loan> findByBook(Book book, Pageable pageable);

    Page<Loan> findByUserAndStatus(
            User user,
            LoanStatus status,
            Pageable pageable
    );

    boolean existsByBookAndStatus(Book book, LoanStatus status);
}
