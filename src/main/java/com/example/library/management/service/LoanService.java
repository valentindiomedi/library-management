package com.example.library.management.service;

import com.example.library.management.domain.Book;
import com.example.library.management.domain.Loan;
import com.example.library.management.domain.LoanStatus;
import com.example.library.management.domain.User;
import com.example.library.management.dto.LoanRequestDTO;
import com.example.library.management.dto.LoanResponseDTO;
import com.example.library.management.mapper.LoanMapper;
import com.example.library.management.repository.BookRepository;
import com.example.library.management.repository.LoanRepository;
import com.example.library.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanMapper loanMapper;

    @Transactional
    public LoanResponseDTO create(LoanRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (book.getAvailableQuantity() <= 0) {
            throw new IllegalArgumentException("No available copies for this book");
        }

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);

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

    public LoanResponseDTO getById(UUID id) {
        return loanRepository.findById(id)
                .map(loanMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
    }

    public List<LoanResponseDTO> getAll() {
        return loanRepository.findAll()
                .stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    @Transactional
    public LoanResponseDTO returnBook(UUID id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new IllegalArgumentException("Loan already returned");
        }

        loan.setStatus(LoanStatus.RETURNED);
        loan.setActualReturnDate(LocalDateTime.now());

        Book book = loan.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);

        return loanMapper.toResponse(loan);
    }
}
