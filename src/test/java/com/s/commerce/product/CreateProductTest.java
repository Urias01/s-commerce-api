package com.s.commerce.product;

import com.s.commerce.application.products.create.CreateProductRequest;
import com.s.commerce.application.products.create.CreateProductResponse;
import com.s.commerce.application.products.create.CreateProductUseCase;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateProductTest {

    @InjectMocks
    private CreateProductUseCase createProductUseCase;

    @Mock
    private IProductRepository productRepository;

    @Test
    @DisplayName("Should be able to create successfully a product")
    public void shouldBeAbleToCreateSuccessfullyAProduct() {
        Money price = new Money(BigDecimal.valueOf(95.9), Currency.getInstance("BRL"));

        CreateProductRequest request = new CreateProductRequest("Strawberry Cake", "A wonderful strawberry cake", price, ProductCategory.CAKE);

        when(productRepository.create(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateProductResponse response = createProductUseCase.execute(request);

        assertNotNull(response);

        assertNotNull(response);
        assertNotNull(response.id());

        verify(productRepository).create(argThat(product ->
                product.getName().equals("Strawberry Cake") &&
                        product.getCategory().equals(ProductCategory.CAKE)
        ));
    }

}
