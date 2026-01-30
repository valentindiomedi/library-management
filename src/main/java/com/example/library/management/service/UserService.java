package com.example.library.management.service;

import com.example.library.management.domain.User;
import com.example.library.management.domain.UserStatus;
import com.example.library.management.dto.UserPatchDTO;
import com.example.library.management.dto.UserRequestDTO;
import com.example.library.management.dto.UserResponseDTO;
import com.example.library.management.exception.UserNotFoundException;
import com.example.library.management.mapper.UserMapper;
import com.example.library.management.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // ========================= CREATE =========================
    public UserResponseDTO create(UserRequestDTO request) {
        User user = userMapper.toEntity(request);
        user.setStatus(UserStatus.ACTIVE);
        return userMapper.toResponse(userRepository.save(user));
    }

    // ========================= GET BY ID =========================
    public UserResponseDTO getById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    // ========================= GET ALL =========================
    public Page<UserResponseDTO> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }

    // ========================= DELETE =========================
    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    // ========================= PATCH =========================
    @Transactional
    public UserResponseDTO patch(UUID id, UserPatchDTO request) {

        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }

        return userMapper.toResponse(user);
    }

    // ========================= FILTERED SEARCH =========================
    public Page<UserResponseDTO> search(
            UserStatus status,
            String name,
            Pageable pageable
    ) {

        if (status != null) {
            return userRepository
                    .findByStatus(status, pageable)
                    .map(userMapper::toResponse);
        }

        if (name != null) {
            return userRepository
                    .findByNameContainingIgnoreCase(name, pageable)
                    .map(userMapper::toResponse);
        }

        return userRepository
                .findAll(pageable)
                .map(userMapper::toResponse);
    }
}
