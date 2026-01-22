package com.example.library.management.mapper;

import com.example.library.management.domain.Loan;
import com.example.library.management.dto.LoanResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanResponseDTO toResponse(Loan loan) {
        return LoanResponseDTO.builder()
                .id(loan.getId())
                .loanDate(loan.getLoanDate())
                .expectedReturnDate(loan.getExpectedReturnDate())
                .actualReturnDate(loan.getActualReturnDate())

                .userId(loan.getUser().getId())
                .userName(loan.getUser().getName())
                .userEmail(loan.getUser().getEmail())

                .bookId(loan.getBook().getId())
                .bookTitle(loan.getBook().getTitle())
                .isbn(loan.getBook().getIsbn())
                .build();
    }
}
