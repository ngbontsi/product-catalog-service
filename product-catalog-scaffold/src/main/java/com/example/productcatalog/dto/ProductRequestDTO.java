package com.example.productcatalog.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductRequestDTO {
    @NotBlank
    private String sku;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    private String currency = "ZAR";
    private boolean available = true;
    private String metadata;
}
