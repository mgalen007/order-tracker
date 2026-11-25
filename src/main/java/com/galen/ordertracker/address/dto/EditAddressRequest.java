package com.galen.ordertracker.address.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record EditAddressRequest(

        @Size(min = 2, max = NAME_MAX)
        String name,

        @Size(min = 3, max = STREET_MAX)
        String street,

        @Size(min = 2, max = CITY_MAX)
        String city,

        @Size(max = POSTAL_CODE_MAX)
        String postalCode,

        @DecimalMin("-90.0")
        @DecimalMax("90.0")
        Double latitude,

        @DecimalMin("-180.0")
        @DecimalMax("180.0")
        Double longitude

) {}
