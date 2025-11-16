package com.example.productcatalog.controller;

import com.example.productcatalog.dto.ProductRequestDTO;
import com.example.productcatalog.dto.ProductResponseDTO;
import com.example.productcatalog.mapper.ProductMapper;
import com.example.productcatalog.model.ProductEntity;
import com.example.productcatalog.service.ProductService;
import com.example.productcatalog.tenant.TenantContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private final ProductService service;
    private final ProductMapper mapper;

    public ProductRestController(ProductService service, ProductMapper mapper) { this.service = service; this.mapper = mapper; }

    @Operation(summary = "Create Product")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Created") })
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Validated ProductRequestDTO dto) {
        ProductEntity entity = mapper.toEntity(dto);
        ProductEntity created = service.create(entity);
        return ResponseEntity.ok(mapper.toDto(created));
    }

    @Operation(summary = "Get Product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> get(@PathVariable UUID id) {
        ProductEntity found = service.getById(id);
        if (found == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDto(found));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable UUID id, @RequestBody ProductRequestDTO dto) {
        ProductEntity entity = mapper.toEntity(dto);
        entity.setId(id);
        ProductEntity updated = service.update(entity);
        return ResponseEntity.ok(mapper.toDto(updated));
    }
}
