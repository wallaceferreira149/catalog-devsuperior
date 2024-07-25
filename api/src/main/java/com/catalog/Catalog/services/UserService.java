package com.catalog.Catalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.catalog.Catalog.dto.CreateUserDTO;
import com.catalog.Catalog.dto.UpdateUserDTO;
import com.catalog.Catalog.dto.UserDTO;
import com.catalog.Catalog.enties.User;
import com.catalog.Catalog.repositories.UserRepository;
import com.catalog.Catalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public Page<UserDTO> findAllPaged(Pageable pageable) {
    Page<User> list = userRepository.findAll(pageable);
    return list.map(UserDTO::new);
  }

  public UserDTO findById(Long userId) {
    Optional<User> user = userRepository.findById(userId);
    User entity = user.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    return new UserDTO(entity);
  }

  public UserDTO create(CreateUserDTO dto) {
    User entity = new User(dto);
    entity.setPassword(passwordEncoder.encode(dto.password()));
    entity = userRepository.save(entity);

    return new UserDTO(entity);
  }

  public UserDTO update(UpdateUserDTO dto, Long userId) {
    try {
      Optional<User> entity = userRepository.findById(userId);

      // if (entity.isPresent()) {
      entity.get().setFirstName(dto.firstName());
      entity.get().setLastName(dto.lastName());
      entity.get().setEmail(dto.email());
      User user = userRepository.save(entity.get());
      // }
      return new UserDTO(user);

    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Usuário não econtrado.");
    }
  }

  public void delete(Long userId) {
    if (userRepository.existsById(userId)) {
      userRepository.deleteById(userId);
    } else {
      throw new ResourceNotFoundException("Usuário não encontrado");
    }
  }

}
