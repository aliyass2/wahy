package com.api.Wahy.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserPatchDto(
    @Size(max = 100) String username,
    @Email @Size(max = 200) String email
) {}
