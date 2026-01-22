package com.example.library.management.dto;

import com.example.library.management.domain.UserStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private UserStatus status;
}
