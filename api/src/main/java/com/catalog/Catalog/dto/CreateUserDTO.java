package com.catalog.Catalog.dto;

public record CreateUserDTO(
    String firstName,
    String lastName,
    String email,
    String password) {

}
