package com.catalog.Catalog.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.catalog.Catalog.enties.User;

public record UserDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    Set<RoleDTO> roles) {

  public UserDTO(User entity) {
    this(
        entity.getId(),
        entity.getFirstName(),
        entity.getLastName(),
        entity.getEmail(),
        entity.getRoles().stream().map(role -> new RoleDTO(role)).collect(Collectors.toSet()));
  }

  // public UserDTO(User entity, Set<Role> roles) {
  // this(entity);
  // roles.forEach(role -> this.roles.add(new RoleDTO(role)));
  // }

}
