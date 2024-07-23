package com.catalog.Catalog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.catalog.Catalog.dto.ProductDTO;
import com.catalog.Catalog.enties.Product;
import com.catalog.Catalog.repositories.ProductRepository;
import com.catalog.Catalog.tests.ProductFactory;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @InjectMocks
  private ProductService service;

  @Mock
  private ProductRepository repository;

  private Product product;
  private ProductDTO productDTO;

  @BeforeEach
  public void setUp() {
    product = ProductFactory.createProduct();
    productDTO = ProductFactory.createProductDTO();

  }

  @Test
  @DisplayName("Should create a new product when given data is correct")
  public void createProductSucess() {
    Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);
    ProductDTO newProduct = service.create(productDTO);
    Assertions.assertEquals(productDTO.getId(), newProduct.getId());
  }

}
