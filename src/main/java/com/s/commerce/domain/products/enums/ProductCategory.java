package com.s.commerce.domain.products.enums;

import lombok.Getter;

@Getter
public enum ProductCategory {

  CAKE("bolo"),
  CUPCAKE("cupcake"),
  SWEET("sweet");

  private final String description;

  ProductCategory(String description) {
    this.description = description;
  }

}