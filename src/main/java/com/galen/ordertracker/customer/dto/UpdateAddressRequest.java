package com.galen.ordertracker.customer.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;


public record UpdateAddressRequest (

        @Size(min = 3, max = 100)
        String name,

        @Size(min = 36, max = 36)
        String customerId,

        @Size(min = 7, max = 12)
        String street,

        @Size(min = 1, max = 30)
        String city,

        @Size(min = 5, max = 5)
        String postalCode,

        @DecimalMin("-90.0")
        @DecimalMax("90.0")
        Double latitude,

        @DecimalMin("-180.0")
        @DecimalMax("180.0")
        Double longitude

)
{}
