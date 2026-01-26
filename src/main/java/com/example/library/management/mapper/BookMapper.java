package com.example.library.management.mapper;

import com.example.library.management.domain.Author;
import com.example.library.management.domain.Book;
import com.example.library.management.dto.BookRequestDTO;
import com.example.library.management.dto.BookResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequestDTO dto, Author author) {
        return Book.builder()
                .title(dto.getTitle())
                .isbn(dto.getIsbn())
                .category(dto.getCategory())
                .totalQuantity(dto.getTotalQuantity())
                .availableQuantity(dto.getAvailableQuantity())
                .location(dto.getLocation())
                .author(author)
                .build();
    }

    public BookResponseDTO toResponse(Book book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .category(book.getCategory())
                .totalQuantity(book.getTotalQuantity())
                .availableQuantity(book.getAvailableQuantity())
                .location(book.getLocation())
                .status(book.getStatus())
                .authorId(book.getAuthor().getId())
                .authorFullName(
                        book.getAuthor().getFirstName() + " " +
                                book.getAuthor().getLastName()
                )
                .build();
    }
}
