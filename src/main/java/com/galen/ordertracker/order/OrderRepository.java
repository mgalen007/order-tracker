package com.galen.ordertracker.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByCustomerId(UUID customerId);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByAddressId(UUID addressId);

    List<Order> findByBusinessId(UUID businessId);
}
