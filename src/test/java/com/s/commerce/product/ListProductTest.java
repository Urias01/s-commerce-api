package com.s.commerce.product;

import com.s.commerce.application.commons.request.BasePagination;
import com.s.commerce.application.commons.response.PageResponse;
import com.s.commerce.application.products.list.ListProductQuery;
import com.s.commerce.application.products.list.ListProductResponse;
import com.s.commerce.application.products.list.ListProductUseCase;
import com.s.commerce.domain.common.PageResult;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListProductTest {

    @InjectMocks
    private ListProductUseCase listProductUseCase;

    @Mock
    private IProductRepository productRepository;

    @Test
    @DisplayName("Should be able to get product list")
    public void shouldBeAbleToGetProductList() {
        Money price = new Money(BigDecimal.valueOf(60.5));
        Product product = new Product("Cake", "description cake", price, ProductCategory.CAKE);

        PageResult<Product> pageResult = new PageResult<>(List.of(product), 1, 0, 10, 1);

        ListProductQuery query = new ListProductQuery("", ProductCategory.CAKE, new BasePagination(0, 10, "", "asc"));

        when(productRepository.findAll(query))
                .thenReturn(pageResult);

        PageResponse<ListProductResponse> response = listProductUseCase.execute(query);

        verify(productRepository).findAll(query);

        assertEquals("Cake", response.items().getFirst().name());
        assertEquals(1, response.items().size());
        assertEquals(1, response.totalItems());
        assertEquals(0, response.page());
        assertEquals(10, response.size());
        assertEquals(1, response.totalPages());
    }

}
