package com.s.commerce.infrastructure.persistence.product;

import com.s.commerce.application.products.list.ListProductQuery;
import com.s.commerce.domain.common.PageResult;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.valueObject.ProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.s.commerce.domain.products.repositories.IProductRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements IProductRepository {

    private final ProductRepository productRepository;

    public ProductRepositoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public PageResult<Product> findAll(ListProductQuery query) {
        Specification<Product> specification =
                ProductSpecification.byQuery(query);

        var pagination = query.pagination();

        var direction =
                Sort.Direction.fromString(pagination.getDirection());

        PageRequest pageRequest = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                direction,
                pagination.getField()
        );

        Page<Product> page =
                productRepository.findAll(specification, pageRequest);

        List<Product> items =
                page.getContent();

        return new PageResult<>(
                items,
                page.getTotalElements(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages()
        );
    }

    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return this.productRepository.save(product);
    }
}
