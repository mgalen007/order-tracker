package com.galen.ordertracker.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Lookups only see customers whose account is still open. */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByIdAndClosedAtIsNull(UUID id);

    Optional<Customer> findByEmailAndClosedAtIsNull(String email);

    Optional<Customer> findByPhoneAndClosedAtIsNull(String phone);

    List<Customer> findAllByClosedAtIsNull();

    List<Customer> findAllByNameAndClosedAtIsNull(String name);

    boolean existsByEmailAndClosedAtIsNull(String email);

    boolean existsByPhoneAndClosedAtIsNull(String phone);
}
