package com.example.library.management.controller;

import com.example.library.management.dto.UserRequestDTO;
import com.example.library.management.dto.UserResponseDTO;
import com.example.library.management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.library.management.dto.UserPatchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.library.management.domain.UserStatus;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
            @Valid @RequestBody UserRequestDTO request
    ) {
        return new ResponseEntity<>(
                userService.create(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> patch(
            @PathVariable UUID id,
            @Valid @RequestBody UserPatchDTO request
    ) {
        return ResponseEntity.ok(userService.patch(id, request));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserResponseDTO>> search(
            @RequestParam(required = false) UserStatus status,
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                userService.search(status, name, pageable)
        );
    }

}
