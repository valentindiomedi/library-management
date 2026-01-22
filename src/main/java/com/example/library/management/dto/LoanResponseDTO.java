package com.example.library.management.dto;

import com.example.library.management.domain.LoanStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponseDTO {

    private UUID id;

    private UUID userId;
    private String userName;
    private String userEmail;


    private UUID bookId;
    private String bookTitle;
    private String isbn;

    private LocalDateTime loanDate;
    private LocalDate expectedReturnDate;
    private LocalDateTime actualReturnDate;

    private LoanStatus status;
}
