package com.galen.ordertracker.address.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record EditAddressRequest(

        @Size(min = 2, max = NAME_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
        String name,

        @Size(min = 3, max = STREET_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
        String street,

        @Size(min = 2, max = CITY_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
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
