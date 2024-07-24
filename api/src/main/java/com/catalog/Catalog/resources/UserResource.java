package com.catalog.Catalog.resources;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.catalog.Catalog.dto.CreateUserDTO;
import com.catalog.Catalog.dto.UpdateUserDTO;
import com.catalog.Catalog.dto.UserDTO;
import com.catalog.Catalog.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserResource {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<Page<UserDTO>> findAllPaged(Pageable pageable) {
    Page<UserDTO> list = userService.findAllPaged(pageable);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDTO> findById(@PathVariable Long userId) {
    UserDTO dto = userService.findById(userId);
    return ResponseEntity.ok().body(dto);
  }

  @PostMapping
  public ResponseEntity<UserDTO> create(
      @Valid @RequestBody CreateUserDTO dto) {
    UserDTO user = userService.create(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(user.id()).toUri();
    return ResponseEntity.created(uri).body(user);
  }

  @PutMapping(value = "/{userId}")
  public ResponseEntity<UserDTO> update(
      @Valid @RequestBody UpdateUserDTO dto,
      @PathVariable Long userId) {
    UserDTO user = userService.update(dto, userId);
    return ResponseEntity.ok().body(user);
  }

  @DeleteMapping(value = "/{userId}")
  public ResponseEntity<Void> delete(@PathVariable Long userId) {

    userService.delete(userId);
    return ResponseEntity.noContent().build();
  }

}
