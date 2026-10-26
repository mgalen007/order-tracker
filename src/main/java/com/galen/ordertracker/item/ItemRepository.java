package com.galen.ordertracker.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByBusinessId(UUID businessId);

    List<Item> findByCategory(String category);

    List<Item> findByAvailableTrue();

    List<Item> findByPrice(BigDecimal price);
}
