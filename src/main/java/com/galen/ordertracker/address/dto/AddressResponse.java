package com.galen.ordertracker.address.dto;

import java.util.UUID;

public record AddressResponse(
        UUID id,
        String name,
        UUID customerId,
        String street,
        String city,
        String postalCode,
        Double latitude,
        Double longitude
) {
}
