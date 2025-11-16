package com.example.productcatalog.service;

import com.example.productcatalog.event.ProductEventPublisher;
import com.example.productcatalog.model.ProductEntity;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.tenant.TenantContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repo;
    private final ProductEventPublisher publisher;

    public ProductService(ProductRepository repo, ProductEventPublisher publisher) {
        this.repo = repo;
        this.publisher = publisher;
    }

    @Transactional
    public ProductEntity create(ProductEntity product) {
        if (product.getId() == null) product.setId(UUID.randomUUID());
        product.setTenantId(TenantContext.getCurrentTenant());
        ProductEntity saved = repo.save(product);
        publisher.publishProductCreated(saved);
        return saved;
    }

    @Cacheable(value = "product", key = "#root.target.getCacheKey(#id)")
    public ProductEntity getById(UUID id) {
        String tenant = TenantContext.getCurrentTenant();
        return repo.findByIdAndTenantId(id, tenant).orElse(null);
    }

    public String getCacheKey(java.util.UUID id) { return TenantContext.getCurrentTenant() + ":" + id; }

    @Transactional
    @CacheEvict(value = "product", key = "#root.target.getCacheKey(#product.id)")
    public ProductEntity update(ProductEntity product) {
        product.setTenantId(TenantContext.getCurrentTenant());
        ProductEntity saved = repo.save(product);
        publisher.publishProductUpdated(saved);
        return saved;
    }
}
