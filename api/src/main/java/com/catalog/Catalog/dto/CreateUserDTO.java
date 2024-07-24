package com.catalog.Catalog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email String email,
        String password) {

}
