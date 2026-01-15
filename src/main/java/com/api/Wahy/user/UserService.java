package com.api.Wahy.user;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
            .map(this::toDto)
            .toList();
    }

    public UserDto getById(UUID id) {
        return toDto(getEntity(id));
    }

    public UserDto create(UserCreateDto request) {
        User user = new User(request.username(), request.email());
        User saved = userRepository.save(user);
        return toDto(saved);
    }

    @Transactional
    public UserDto update(UUID id, UserUpdateDto request) {
        User user = getEntity(id);
        user.setUsername(request.username());
        user.setEmail(request.email());
        return toDto(user); // dirty checking will persist on commit
    }

    @Transactional
    public UserDto patch(UUID id, UserPatchDto request) {
        User user = getEntity(id);

        if (request.username() != null) user.setUsername(request.username());
        if (request.email() != null) user.setEmail(request.email());

        return toDto(user);
    }

    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
    }

    private User getEntity(UUID id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }
}
