package com.example.library.management.service;

import com.example.library.management.domain.Author;
import com.example.library.management.dto.AuthorPatchDTO;
import com.example.library.management.dto.AuthorRequestDTO;
import com.example.library.management.dto.AuthorResponseDTO;
import com.example.library.management.exception.ResourceNotFoundException;
import com.example.library.management.mapper.AuthorMapper;
import com.example.library.management.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    // ========================= CREATE =========================
    public AuthorResponseDTO create(AuthorRequestDTO request) {
        Author author = authorMapper.toEntity(request);
        return authorMapper.toResponse(authorRepository.save(author));
    }

    // ========================= GET BY ID =========================
    public AuthorResponseDTO getById(UUID id) {
        return authorRepository.findById(id)
                .map(authorMapper::toResponse)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Author not found")
                );
    }

    // ========================= GET ALL =========================
    public Page<AuthorResponseDTO> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(authorMapper::toResponse);
    }

    // ========================= DELETE =========================
    public void delete(UUID id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found");
        }
        authorRepository.deleteById(id);
    }

    // ========================= PATCH =========================
    @Transactional
    public AuthorResponseDTO patch(UUID id, AuthorPatchDTO request) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Author not found")
                );

        if (request.getFirstName() != null) {
            author.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            author.setLastName(request.getLastName());
        }

        if (request.getNationality() != null) {
            author.setNationality(request.getNationality());
        }

        if (request.getBiography() != null) {
            author.setBiography(request.getBiography());
        }

        return authorMapper.toResponse(author);
    }
}
