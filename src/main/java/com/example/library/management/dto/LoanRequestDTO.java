package com.example.library.management.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequestDTO {

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Book ID is required")
    private UUID bookId;

    @NotNull(message = "Expected return date is required")
    @Future(message = "Expected return date must be in the future")
    private LocalDate expectedReturnDate;
}
