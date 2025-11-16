package com.example.productcatalog.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductResponseDTO {
    private UUID id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private String currency;
    private boolean available;
    private String metadata;
}
