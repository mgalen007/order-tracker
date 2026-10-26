package com.galen.ordertracker.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
    List<Business> findByBusinessType(BusinessType businessType);

    Optional<Business> findByName(String name);

    Optional<Business> findByPhone(String phone);

    List<Business> findByStreet(String street);

    List<Business> findByCity(String city);
}
