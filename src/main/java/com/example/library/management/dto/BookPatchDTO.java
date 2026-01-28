package com.example.library.management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookPatchDTO {

    private String title;

    private UUID authorId;

    private String isbn;

    private String category;


    private Integer totalQuantity;

    private Integer availableQuantity;

    private String location;
}
