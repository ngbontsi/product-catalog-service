package com.example.productcatalog.graphql;

import com.example.productcatalog.mapper.ProductMapper;
import com.example.productcatalog.model.ProductEntity;
import com.example.productcatalog.service.ProductService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ProductGraphqlController {
    private final ProductService service;
    private final ProductMapper mapper;

    public ProductGraphqlController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @QueryMapping
    public com.example.productcatalog.dto.ProductResponseDTO product(String id) {
        ProductEntity p = service.getById(UUID.fromString(id));
        return p == null ? null : mapper.toDto(p);
    }

    // search resolver left as TODO - implement paging and filtering using repository
}
