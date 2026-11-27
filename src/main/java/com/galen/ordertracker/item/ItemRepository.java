package com.galen.ordertracker.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findByIdAndDeletedAtIsNullAndBusinessDeregisteredAtIsNull(UUID id);

    List<Item> findAllByDeletedAtIsNullAndBusinessDeregisteredAtIsNull();

    List<Item> findByBusinessId(UUID businessId);

    List<Item> findByCategoryId(UUID categoryId);

    boolean existsByCategoryId(UUID categoryId);

    List<Item> findByAvailableTrue();

    List<Item> findByPrice(BigDecimal price);
}
