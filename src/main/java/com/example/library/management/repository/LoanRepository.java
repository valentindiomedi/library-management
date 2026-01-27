package com.example.library.management.repository;

import com.example.library.management.domain.Book;
import com.example.library.management.domain.Loan;
import com.example.library.management.domain.LoanStatus;
import com.example.library.management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {

    List<Loan> findByUser(User user);

    List<Loan> findByBook(Book book);

    List<Loan> findByStatus(LoanStatus status);

    List<Loan> findByUserAndStatus(User user, LoanStatus status);

    boolean existsByBookAndStatus(Book book, LoanStatus status);

    Page<Loan> findByStatus(LoanStatus status, Pageable pageable);

}
