package com.example.productcatalog.event;

import com.example.productcatalog.model.ProductEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventPublisher {
    private final KafkaTemplate<String, Object> kafka;
    private final String topic = "products";

    public ProductEventPublisher(KafkaTemplate<String, Object> kafka) { this.kafka = kafka; }

    public void publishProductCreated(ProductEntity p) {
        var evt = new ProductEvent(p.getId(), p.getTenantId(), "CREATED");
        kafka.send(topic, p.getTenantId() + ":" + p.getId(), evt);
    }

    public void publishProductUpdated(ProductEntity p) {
        var evt = new ProductEvent(p.getId(), p.getTenantId(), "UPDATED");
        kafka.send(topic, p.getTenantId() + ":" + p.getId(), evt);
    }
}

record ProductEvent(java.util.UUID productId, String tenantId, String action) {}
