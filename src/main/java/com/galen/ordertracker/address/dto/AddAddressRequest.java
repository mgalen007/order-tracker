package com.galen.ordertracker.address.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record AddAddressRequest(

        @NotBlank
        @Size(min = 3, max = 100)
        String name,

        @NotNull
        UUID customerId,

        @NotBlank
        @Size(min = 7, max = 12)
        String street,

        @NotBlank
        @Size(min = 1, max = 30)
        String city,

        @Size(min = 5, max = 5)
        String postalCode,

        @NotNull
        @DecimalMin("-90.0")
        @DecimalMax("90.0")
        Double latitude,

        @NotNull
        @DecimalMin("-180.0")
        @DecimalMax("180.0")
        Double longitude

) {}
