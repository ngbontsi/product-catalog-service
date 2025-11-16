package com.example.productcatalog.repository;

import com.example.productcatalog.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findByIdAndTenantId(UUID id, String tenantId);
}
