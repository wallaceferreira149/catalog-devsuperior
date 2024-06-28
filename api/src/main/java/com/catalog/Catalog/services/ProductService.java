package com.catalog.Catalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.catalog.Catalog.dto.CategoryDTO;
import com.catalog.Catalog.dto.ProductDTO;
import com.catalog.Catalog.enties.Category;
import com.catalog.Catalog.enties.Product;
import com.catalog.Catalog.repositories.CategoryRepository;
import com.catalog.Catalog.repositories.ProductRepository;
import com.catalog.Catalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional
  public Page<ProductDTO> findAllPaged(Pageable pageable) {
    Page<Product> list = productRepository.findAll(pageable);
    return list.map(item -> new ProductDTO(item));
  }

  @Transactional
  public ProductDTO findById(Long id) {
    Optional<Product> obj = productRepository.findById(id);
    Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    return new ProductDTO(entity, entity.getCategories());
  }

  @Transactional
  public ProductDTO create(ProductDTO dto) {
    Product entity = new Product();
    copyDtoToEntity(dto, entity);
    entity = productRepository.save(entity);

    return new ProductDTO(entity, entity.getCategories());
  }

  @Transactional
  public ProductDTO update(ProductDTO dto, Long id) {
    try {
      Product entity = productRepository.getReferenceById(id);
      copyDtoToEntity(dto, entity);
      entity = productRepository.save(entity);
      return new ProductDTO(entity, entity.getCategories());

    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Produto não encontrado");
    }
  }

  public void delete(Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
    } else {
      throw new ResourceNotFoundException("Produto não encontrado");
    }
    // try {
    // productRepository.deleteById(id);
    // } catch (EmptyResultDataAccessException e) {
    // throw new ResourceNotFoundException("Produto não encontrado.");
    // } catch (DataIntegrityViolationException e) {
    // throw new DatabaseException("Não foi possível excluir o produto.");
    // } catch (RuntimeException e) {
    // throw new Error(e);
    // }
  }

  private void copyDtoToEntity(ProductDTO dto, Product entity) {
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setDate(dto.getDate());
    entity.setImgUrl(dto.getImgUrl());
    entity.setPrice(dto.getPrice());

    entity.getCategories().clear();
    for (CategoryDTO categoryDTO : dto.getCategories()) {
      Category category = categoryRepository.getReferenceById(categoryDTO.getId());
      entity.getCategories().add(category);
    }
  }

}
