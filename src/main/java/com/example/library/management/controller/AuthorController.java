package com.example.library.management.controller;

import com.example.library.management.dto.AuthorRequestDTO;
import com.example.library.management.dto.AuthorResponseDTO;
import com.example.library.management.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> create(@RequestBody AuthorRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(authorService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
