package com.catalog.Catalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.catalog.Catalog.enties.Product;
import com.catalog.Catalog.services.exceptions.ResourceNotFoundException;
import com.catalog.Catalog.tests.ProductFactory;

@DataJpaTest
public class ProductRepositoryTests {

  @Autowired
  private ProductRepository repository;

  private long existingId;
  private long nonexistentId;
  private Product product;
  private long totalProducts;

  @BeforeEach
  void setUp() throws Exception {
    existingId = 1L;
    nonexistentId = 999L;
    product = ProductFactory.createProduct();
    totalProducts = repository.count();
  }

  @Test
  public void SaveShouldPersistWithAutoIcrementWhenIdIsNull() {
    product.setId(null);
    product = repository.save(product);

    Assertions.assertNotNull(product.getId());
    Assertions.assertEquals(totalProducts + 1, product.getId());
  }

  @Test
  public void deleteShouldDeleteObjectWhenIdExists() {
    repository.deleteById(existingId);
    Optional<Product> result = repository.findById(existingId);
    Assertions.assertFalse(result.isPresent());
  }

  @Test
  public void deleteShoudNotWhenIdNotExists() {
    long totalProductsBeforeDelete = repository.count();

    repository.deleteById(nonexistentId);
    long totalProductsAfterDelete = repository.count();

    Assertions.assertEquals(totalProductsBeforeDelete, totalProductsAfterDelete);
  }

  @Test
  public void FindByIdShouldReturnProductWhenIdExists() {
    long idExisting = 1L;
    Optional<Product> objFound = repository.findById(idExisting);
    Product entity = objFound.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    Assertions.assertEquals(idExisting, entity.getId());
  }

  @Test
  public void FindByIdShouldThrowResourceNotFoundExceptionWhenIdNotExist() {
    Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      nonexistentId = totalProducts + 1;
      Optional<Product> objFound = repository.findById(nonexistentId);
      product = objFound.orElseThrow(() -> new ResourceNotFoundException(""));
    });

  }
}
