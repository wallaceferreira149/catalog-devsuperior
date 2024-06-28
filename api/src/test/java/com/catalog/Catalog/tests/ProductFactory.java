package com.catalog.Catalog.tests;

import java.time.Instant;

import com.catalog.Catalog.dto.ProductDTO;
import com.catalog.Catalog.enties.Category;
import com.catalog.Catalog.enties.Product;

public class ProductFactory {

  public static Product createProduct() {
    Product product = new Product(
        1L,
        "The Lord of the Rings",
        "Historia de fantasia",
        90.5,
        "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg",
        Instant.now());
    product.getCategories().add(new Category(1L, "Qualque categoria"));
    return product;
  }

  public static ProductDTO createProductDTO() {
    Product product = createProduct();
    return new ProductDTO(product);
  }
}
