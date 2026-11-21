package com.galen.ordertracker.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Lookups only see addresses whose owner's account is still open. */
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findByIdAndCustomerClosedAtIsNull(UUID id);

    List<Address> findAllByCustomerClosedAtIsNull();

    List<Address> findAllByNameAndCustomerClosedAtIsNull(String name);
}
