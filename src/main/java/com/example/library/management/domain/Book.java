package com.example.library.management.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
        name = "books",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "isbn")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String category;

    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status;

}
