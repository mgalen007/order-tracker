package com.galen.ordertracker.business.dto;

import com.galen.ordertracker.business.BusinessType;
import jakarta.validation.constraints.*;

public record CreateBusinessRequest(
    @NotNull
    BusinessType businessType,

    @NotBlank
    @Size(min = 2, max = 50)
    String name,

    @Size(min = 10, max = 500)
    String description,

    @NotBlank
    @Size(min = 10, max = 17)
    String phone,

    @NotBlank
    @Size(min = 5, max = 20)
    String street,

    @NotBlank
    @Size(min = 3, max = 35)
    String city,

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    Double latitude,

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    Double longitude
) {}
