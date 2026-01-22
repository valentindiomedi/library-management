package com.example.library.management.controller;

import com.example.library.management.dto.BookRequestDTO;
import com.example.library.management.dto.BookResponseDTO;
import com.example.library.management.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> create(
            @Valid @RequestBody BookRequestDTO request
    ) {
        return new ResponseEntity<>(
                bookService.create(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponseDTO> getByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getByIsbn(isbn));
    }
}
