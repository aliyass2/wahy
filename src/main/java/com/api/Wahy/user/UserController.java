package com.api.Wahy.user;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserCreateDto request) {
        UserDto created = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable UUID id, @Valid @RequestBody UserUpdateDto request) {
        return userService.update(id, request);
    }

    @PatchMapping("/{id}")
    public UserDto patch(@PathVariable UUID id, @RequestBody UserPatchDto request) {
        return userService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
