package com.galen.ordertracker.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Lookups only see businesses that have not been deregistered. */
public interface BusinessRepository extends JpaRepository<Business, UUID> {

    Optional<Business> findByIdAndDeregisteredAtIsNull(UUID id);

    List<Business> findAllByDeregisteredAtIsNull();

    boolean existsByPhoneAndDeregisteredAtIsNull(String phone);
}
