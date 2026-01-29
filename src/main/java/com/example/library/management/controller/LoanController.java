package com.example.library.management.controller;

import com.example.library.management.dto.LoanRequestDTO;
import com.example.library.management.dto.LoanResponseDTO;
import com.example.library.management.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.library.management.domain.LoanStatus;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> create(
            @Valid @RequestBody LoanRequestDTO request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loanService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(loanService.getById(id));
    }

    // ========================= GET ALL (PAGINADO) =========================
    @GetMapping
    public ResponseEntity<Page<LoanResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(loanService.getAll(pageable));
    }

    // ========================= GET ACTIVE (PAGINADO) =========================
    @GetMapping("/active")
    public ResponseEntity<Page<LoanResponseDTO>> getActiveLoans(Pageable pageable) {
        return ResponseEntity.ok(loanService.getActiveLoans(pageable));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnBook(@PathVariable UUID id) {
        return ResponseEntity.ok(loanService.returnBook(id));
    }


    // ========================= GET BY USER =========================
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<LoanResponseDTO>> getByUser(
            @PathVariable UUID userId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                loanService.getByUser(userId, pageable)
        );
    }

    // ========================= GET BY BOOK =========================
    @GetMapping("/book/{bookId}")
    public ResponseEntity<Page<LoanResponseDTO>> getByBook(
            @PathVariable UUID bookId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                loanService.getByBook(bookId, pageable)
        );
    }

    // ========================= GET BY USER + STATUS =========================
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<Page<LoanResponseDTO>> getByUserAndStatus(
            @PathVariable UUID userId,
            @PathVariable LoanStatus status,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                loanService.getByUserAndStatus(userId, status, pageable)
        );
    }

}