package com.s.commerce.domain.products.enums;

public enum ProductCategory {

  CAKE("bolo"),
  CUPCAKE("cupcake"),
  CANDY("brigadeiros");

  private final String description;

  ProductCategory(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}