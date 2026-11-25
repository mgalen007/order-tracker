package com.galen.ordertracker.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record UpdateCustomerProfileRequest(

        @Size(min = 2, max = NAME_MAX)
        String name,

        @Email
        @Size(max = EMAIL_MAX)
        String email,

        @Pattern(regexp = PHONE_PATTERN)
        String phone

) {}
