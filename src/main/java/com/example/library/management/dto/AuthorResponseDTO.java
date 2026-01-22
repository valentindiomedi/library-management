package com.example.library.management.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponseDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String biography;
}
