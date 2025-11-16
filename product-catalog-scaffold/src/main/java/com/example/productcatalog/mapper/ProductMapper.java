package com.example.productcatalog.mapper;

import com.example.productcatalog.dto.ProductRequestDTO;
import com.example.productcatalog.dto.ProductResponseDTO;
import com.example.productcatalog.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toEntity(ProductRequestDTO dto);
    ProductResponseDTO toDto(ProductEntity entity);
}
