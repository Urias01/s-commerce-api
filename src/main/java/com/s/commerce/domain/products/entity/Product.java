package com.s.commerce.domain.products.entity;

import java.util.UUID;

import com.s.commerce.domain.common.Auditable;
import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.products.valueObject.Money;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "products")
@Getter
public class Product extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;
  private String description;
  @Embedded
  private Money price;
  @Enumerated(value = EnumType.STRING)
  private ProductCategory category;

  protected Product() {
  }

  public Product(String name, String description, Money price, ProductCategory category) {
    super();
    validateName(name);

    this.name = name;
    this.description = description;
    this.price = price;
    this.category = category;
  }

  private void validateName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
  }
}
