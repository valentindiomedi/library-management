package com.example.library.management.service;

import com.example.library.management.domain.Author;
import com.example.library.management.domain.Book;
import com.example.library.management.dto.BookRequestDTO;
import com.example.library.management.dto.BookResponseDTO;
import com.example.library.management.mapper.BookMapper;
import com.example.library.management.repository.AuthorRepository;
import com.example.library.management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.library.management.dto.BookPatchDTO;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookResponseDTO create(BookRequestDTO request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        Book book = bookMapper.toEntity(request, author);
        return bookMapper.toResponse(bookRepository.save(book));
    }

    public BookResponseDTO getById(UUID id) {
        return bookRepository.findById(id)
                .map(bookMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    public List<BookResponseDTO> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    public BookResponseDTO getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    public BookResponseDTO update(UUID id, BookRequestDTO request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        book.setTitle(request.getTitle());
        book.setAuthor(author);
        book.setIsbn(request.getIsbn());
        book.setCategory(request.getCategory());
        book.setTotalQuantity(request.getTotalQuantity());
        book.setAvailableQuantity(request.getAvailableQuantity());
        book.setLocation(request.getLocation());

        return bookMapper.toResponse(bookRepository.save(book));
    }




    @Transactional
    public BookResponseDTO patch(UUID id, BookPatchDTO request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }

        if (request.getCategory() != null) {
            book.setCategory(request.getCategory());
        }

        if (request.getIsbn() != null) {
            book.setIsbn(request.getIsbn());
        }

        if (request.getLocation() != null) {
            book.setLocation(request.getLocation());
        }

        if (request.getTotalQuantity() != null) {
            book.setTotalQuantity(request.getTotalQuantity());
        }

        if (request.getAvailableQuantity() != null) {
            book.setAvailableQuantity(request.getAvailableQuantity());
        }

        if (request.getAuthorId() != null) {
            Author author = authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("Author not found"));
            book.setAuthor(author);
        }

        return bookMapper.toResponse(bookRepository.save(book));
    }


}
