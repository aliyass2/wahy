package com.api.Wahy.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UserUpdateDto(
    @NotBlank @Size(max = 100) String username,
    @NotBlank @Email @Size(max = 200) String email
) {}