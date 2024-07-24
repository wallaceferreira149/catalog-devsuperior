package com.catalog.Catalog.dto;

import com.catalog.Catalog.services.validation.UserUpdateValid;

@UserUpdateValid
public record UpdateUserDTO(
    String firstName,
    String lastName,
    String email) {

}
