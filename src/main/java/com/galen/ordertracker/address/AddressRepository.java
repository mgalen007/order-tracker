package com.galen.ordertracker.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Lookups only see addresses that are not deleted and whose owner's account is still open. */
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findByIdAndDeletedAtIsNullAndCustomerClosedAtIsNull(UUID id);

    List<Address> findAllByDeletedAtIsNullAndCustomerClosedAtIsNull();

    List<Address> findAllByNameAndDeletedAtIsNullAndCustomerClosedAtIsNull(String name);
}
