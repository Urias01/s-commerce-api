package com.s.commerce.product;

import com.s.commerce.application.products.edit.EditProductRequest;
import com.s.commerce.application.products.edit.EditProductResponse;
import com.s.commerce.application.products.edit.EditProductUseCase;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.products.repositories.IProductRepository;
import com.s.commerce.domain.common.valueObject.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditProductTest {

    @InjectMocks
    private EditProductUseCase editProductUseCase;

    @Mock
    private IProductRepository productRepository;

    @Test
    @DisplayName("Should be able to edit successfully a product")
    public void shouldBeAbleToEditSuccessfullyAProduct() {
        Money price = new Money(BigDecimal.valueOf(95.9));
        Product product = new Product("Chocolate Cake", "A wonderful chocolate cake", price, ProductCategory.CAKE);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        when(productRepository.update(product))
                .thenReturn(product);

        EditProductRequest request = new EditProductRequest("Strawberry Cake", "A wonderful strawberry cake", price, ProductCategory.CAKE);

        EditProductResponse response = editProductUseCase.execute(product.getId(), request);

        verify(productRepository).update(product);
        assertEquals(product.getId(), response.id());
        assertEquals("Strawberry Cake", product.getName());
    }

}
