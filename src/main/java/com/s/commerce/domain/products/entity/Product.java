package com.s.commerce.domain.products.entity;

import com.s.commerce.domain.common.Auditable;
import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.common.valueObject.Money;

import com.s.commerce.domain.products.exceptions.InvalidProductDataException;
import com.s.commerce.domain.products.valueObject.ProductId;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "products")
@Getter
public class Product extends Auditable {

    @EmbeddedId
    private ProductId id;

    private String name;
    private String description;
    @Embedded
    private Money price;
    @Enumerated(value = EnumType.STRING)
    private ProductCategory category;

    protected Product() { }

    public Product(String name, String description, Money price, ProductCategory category) {
        super();
        validateName(name);

        this.id = ProductId.newId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductDataException("Name cannot be null or empty");
        }
    }

    public void changeName(String name) {
        validateName(name);

        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changePrice(Money newPrice) {
        if (newPrice == null) {
            throw new InvalidProductDataException("Price cannot be null");
        }

        if (this.price.equals(newPrice)) {
            return;
        }

        this.price = newPrice;
    }

    public void changeCategory(ProductCategory newCategory) {
        if (newCategory == null) {
            throw new InvalidProductDataException("Category cannot be null");
        }

        if (this.category == newCategory) {
            return;
        }

        this.category = newCategory;
    }
}
