package com.example.library.management.controller;

import com.example.library.management.dto.LoanRequestDTO;
import com.example.library.management.dto.LoanResponseDTO;
import com.example.library.management.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        LoanResponseDTO response = loanService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(loanService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAll() {
        return ResponseEntity.ok(loanService.getAll());
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnBook(@PathVariable UUID id) {
        return ResponseEntity.ok(loanService.returnBook(id));
    }
}
