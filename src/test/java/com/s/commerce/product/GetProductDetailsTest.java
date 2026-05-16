package com.s.commerce.product;

import com.s.commerce.application.products.getById.GetProductDetailsResponse;
import com.s.commerce.application.products.getById.GetProductDetailsUseCase;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.products.repositories.IProductRepository;
import com.s.commerce.domain.products.valueObject.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductDetailsTest {

    @InjectMocks
    private GetProductDetailsUseCase getProductDetailsUseCase;

    @Mock
    private IProductRepository productRepository;

    @Test
    @DisplayName("Should be able to get product details")
    public void shouldBeAbleToGetProductDetails() {
        Money price = new Money(BigDecimal.valueOf(95.9), Currency.getInstance("BRL"));
        Product product = new Product("Lemon Cake", "An incredible lemon cake", price, ProductCategory.CAKE);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        GetProductDetailsResponse response = getProductDetailsUseCase.execute(product.getId());

        verify(productRepository).findById(product.getId());
        assertEquals(product.getId(), response.id());
        assertEquals(product.getName(), response.name());
        assertEquals(product.getDescription(), response.description());
        assertEquals(product.getCategory(), response.category());
        assertEquals(product.getPrice(), response.price());
    }
}
