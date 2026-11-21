package com.galen.ordertracker.business.dto;

import com.galen.ordertracker.business.BusinessType;
import jakarta.validation.constraints.*;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record RegisterBusinessRequest(

        @NotNull
        BusinessType businessType,

        @NotBlank
        @Size(min = 2, max = NAME_MAX)
        String name,

        @Size(max = DESCRIPTION_MAX)
        String description,

        @NotBlank
        @Pattern(regexp = PHONE_PATTERN)
        String phone,

        @NotBlank
        @Size(min = 3, max = STREET_MAX)
        String street,

        @NotBlank
        @Size(min = 2, max = CITY_MAX)
        String city,

        @DecimalMin("-90.0")
        @DecimalMax("90.0")
        Double latitude,

        @DecimalMin("-180.0")
        @DecimalMax("180.0")
        Double longitude

) {}
