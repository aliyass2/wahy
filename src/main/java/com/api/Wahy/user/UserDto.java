package com.api.Wahy.user;

import java.time.Instant;
import java.util.UUID;

public record UserDto(UUID id, String username, String email, Instant createdAt) {}
