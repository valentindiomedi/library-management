package com.example.library.management.service;

import com.example.library.management.domain.Author;
import com.example.library.management.dto.AuthorRequestDTO;
import com.example.library.management.dto.AuthorResponseDTO;
import com.example.library.management.mapper.AuthorMapper;
import com.example.library.management.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.library.management.dto.AuthorPatchDTO;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponseDTO create(AuthorRequestDTO request) {
        Author author = authorMapper.toEntity(request);
        return authorMapper.toResponse(authorRepository.save(author));
    }

    public AuthorResponseDTO getById(UUID id) {
        return authorRepository.findById(id)
                .map(authorMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
    }

    public List<AuthorResponseDTO> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    public void delete(UUID id) {
        if (!authorRepository.existsById(id)) {
            throw new IllegalArgumentException("Author not found");
        }
        authorRepository.deleteById(id);
    }

    @Transactional
    public AuthorResponseDTO patch(UUID id, AuthorPatchDTO request) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

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
