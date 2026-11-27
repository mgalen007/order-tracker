package com.galen.ordertracker.business.dto;

import com.galen.ordertracker.business.BusinessType;
import jakarta.validation.constraints.*;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record UpdateBusinessProfileRequest(

        BusinessType businessType,

        @Size(min = 2, max = NAME_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
        String name,

        @Size(max = DESCRIPTION_MAX)
        String description,

        @Pattern(regexp = PHONE_PATTERN)
        String phone,

        @Size(min = 3, max = STREET_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
        String street,

        @Size(min = 2, max = CITY_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
        String city,

        @DecimalMin("-90.0")
        @DecimalMax("90.0")
        Double latitude,

        @DecimalMin("-180.0")
        @DecimalMax("180.0")
        Double longitude

) {}
