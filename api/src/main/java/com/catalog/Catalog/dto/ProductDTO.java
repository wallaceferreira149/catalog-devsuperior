package com.catalog.Catalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.catalog.Catalog.enties.Category;
import com.catalog.Catalog.enties.Product;

public class ProductDTO implements Serializable {

  private Long id;
  private String name;
  private String description;
  private Double price;
  private String imgUrl;
  private Instant date;
  private List<CategoryDTO> categories = new ArrayList<>();

  public ProductDTO() {
  }

  public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date,
      List<CategoryDTO> categories) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
    this.date = date;
  }

  public ProductDTO(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
    this.price = product.getPrice();
    this.imgUrl = product.getImgUrl();
    this.date = product.getDate();
  }

  public ProductDTO(Product product, Set<Category> categories) {
    this(product);
    categories.forEach(category -> this.categories.add(new CategoryDTO(category)));
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getImgUrl() {
    return this.imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public Instant getDate() {
    return this.date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public List<CategoryDTO> getCategories() {
    return this.categories;
  }

  public void setCategories(List<CategoryDTO> categories) {
    this.categories = categories;
  }
}
