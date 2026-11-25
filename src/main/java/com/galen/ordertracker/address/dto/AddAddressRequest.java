package com.galen.ordertracker.address.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record AddAddressRequest(

        @NotBlank
        @Size(min = 2, max = NAME_MAX)
        String name,

        @NotNull
        UUID customerId,

        @NotBlank
        @Size(min = 3, max = STREET_MAX)
        String street,

        @NotBlank
        @Size(min = 2, max = CITY_MAX)
        String city,

        @Size(max = POSTAL_CODE_MAX)
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
