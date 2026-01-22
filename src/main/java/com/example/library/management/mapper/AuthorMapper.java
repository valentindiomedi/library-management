package com.example.library.management.mapper;

import com.example.library.management.domain.Author;
import com.example.library.management.dto.AuthorRequestDTO;
import com.example.library.management.dto.AuthorResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequestDTO dto) {
        return Author.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .nationality(dto.getNationality())
                .biography(dto.getBiography())
                .build();
    }

    public AuthorResponseDTO toResponse(Author author) {
        return AuthorResponseDTO.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .nationality(author.getNationality())
                .biography(author.getBiography())
                .build();
    }
}
