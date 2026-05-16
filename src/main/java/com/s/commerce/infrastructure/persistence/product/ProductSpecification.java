package com.s.commerce.infrastructure.persistence.product;

import com.s.commerce.application.products.list.ListProductQuery;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.enums.ProductCategory;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> byQuery(
            ListProductQuery query
    ) {

        return Specification.allOf(
                nameLike(query.name()),
                categoryEquals(query.category())
        );
    }

    private static Specification<Product> nameLike(
            String name
    ) {

        return (root, query, cb) -> {

            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    private static Specification<Product> categoryEquals(
            ProductCategory category
    ) {

        return (root, query, cb) -> {

            if (category == null) {
                return cb.conjunction();
            }

            return cb.equal(root.get("category"), category);
        };
    }

}
