package com.catalog.Catalog.dto;

import com.catalog.Catalog.enties.Role;

public record RoleDTO(
    Long id,
    String authority) {

  public RoleDTO(Role entity) {
    this(entity.getId(), entity.getAuthority());
  }
}
