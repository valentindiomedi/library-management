package com.example.library.management.service;

import com.example.library.management.domain.Book;
import com.example.library.management.domain.BookStatus;
import com.example.library.management.domain.Loan;
import com.example.library.management.domain.LoanStatus;
import com.example.library.management.domain.User;
import com.example.library.management.dto.LoanRequestDTO;
import com.example.library.management.dto.LoanResponseDTO;
import com.example.library.management.exception.BadRequestException;
import com.example.library.management.exception.BookNotFoundException;
import com.example.library.management.exception.LoanNotFoundException;
import com.example.library.management.exception.UserNotFoundException;
import com.example.library.management.mapper.LoanMapper;
import com.example.library.management.repository.BookRepository;
import com.example.library.management.repository.LoanRepository;
import com.example.library.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanMapper loanMapper;

    // ========================= CREATE LOAN =========================
    @Transactional
    public LoanResponseDTO create(LoanRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(BookNotFoundException::new);

        // Validación por estado
        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new BadRequestException("Book is not available for loan");
        }

        // Validación por stock
        if (book.getAvailableQuantity() <= 0) {
            throw new BadRequestException("No available copies for this book");
        }

        // Se presta un ejemplar
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);

        // Si se quedó sin stock, cambia el estado
        if (book.getAvailableQuantity() == 0) {
            book.setStatus(BookStatus.BORROWED);
        }

        bookRepository.save(book);

        Loan loan = Loan.builder()
                .user(user)
                .book(book)
                .loanDate(LocalDateTime.now())
                .expectedReturnDate(request.getExpectedReturnDate())
                .status(LoanStatus.ACTIVE)
                .build();

        Loan savedLoan = loanRepository.save(loan);

        return loanMapper.toResponse(savedLoan);
    }

    // ========================= GET BY ID =========================
    public LoanResponseDTO getById(UUID id) {
        return loanRepository.findById(id)
                .map(loanMapper::toResponse)
                .orElseThrow(LoanNotFoundException::new);
    }

    // ========================= GET ALL (PAGINADO) =========================
    public Page<LoanResponseDTO> getAll(Pageable pageable) {
        return loanRepository
                .findAll(pageable)
                .map(loanMapper::toResponse);
    }

    // ========================= GET ACTIVE LOANS (PAGINADO) =========================
    public Page<LoanResponseDTO> getActiveLoans(Pageable pageable) {
        return loanRepository
                .findByStatus(LoanStatus.ACTIVE, pageable)
                .map(loanMapper::toResponse);
    }

    // ========================= VALIDATIONS =========================
    public boolean hasActiveLoans(Book book) {
        return loanRepository.existsByBookAndStatus(book, LoanStatus.ACTIVE);
    }

    // ========================= RETURN BOOK =========================
    @Transactional
    public LoanResponseDTO returnBook(UUID id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(LoanNotFoundException::new);

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new BadRequestException("Loan already returned");
        }

        loan.setStatus(LoanStatus.RETURNED);
        loan.setActualReturnDate(LocalDateTime.now());

        Book book = loan.getBook();

        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        book.setStatus(BookStatus.AVAILABLE);

        bookRepository.save(book);
        loanRepository.save(loan);

        return loanMapper.toResponse(loan);
    }
}
