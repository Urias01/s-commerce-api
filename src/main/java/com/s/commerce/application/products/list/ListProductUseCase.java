package com.s.commerce.application.products.list;

import com.s.commerce.application.commons.response.PageResponse;
import com.s.commerce.application.products.mappers.ProductMapper;
import com.s.commerce.domain.common.PageResult;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.repositories.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ListProductUseCase {

    private final IProductRepository productRepository;

    public ListProductUseCase(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PageResponse<ListProductResponse> execute(ListProductQuery query) {
      log.info("Starting processing to get a list of products");
        PageResult<Product> result = this.productRepository.findAll(query);

        List<ListProductResponse> items =
                result.items()
                        .stream()
                        .map(ProductMapper::toListItemResponse)
                        .toList();

      log.info("Get a list of products successful");
        return new PageResponse<>(items,
                result.totalItems(),
                result.page(),
                result.size(),
                result.totalPages());
    }
}
