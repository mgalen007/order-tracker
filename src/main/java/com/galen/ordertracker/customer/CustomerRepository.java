package com.galen.ordertracker.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findByName(String name);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);
}
