package com.catalog.Catalog.dto;

import com.catalog.Catalog.services.validation.UserInsertValid;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@UserInsertValid
public record CreateUserDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email String email,
        String password) {

}
