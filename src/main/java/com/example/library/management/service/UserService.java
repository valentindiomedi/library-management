package com.example.library.management.service;

import com.example.library.management.domain.User;
import com.example.library.management.domain.UserStatus;
import com.example.library.management.dto.UserRequestDTO;
import com.example.library.management.dto.UserResponseDTO;
import com.example.library.management.mapper.UserMapper;
import com.example.library.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO create(UserRequestDTO request) {
        User user = userMapper.toEntity(request);
        user.setStatus(UserStatus.ACTIVE);
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponseDTO getById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }
}
