package com.example.library.management.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDTO {

    private UUID id;
    private String title;

    // Info del autor ya resuelta
    private UUID authorId;
    private String authorFullName;

    private String isbn;
    private String category;

    private Integer totalQuantity;
    private Integer availableQuantity;

    private String location;
}
