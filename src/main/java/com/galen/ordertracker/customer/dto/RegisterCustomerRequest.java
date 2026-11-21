package com.galen.ordertracker.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record RegisterCustomerRequest(

        @NotBlank
        @Size(min = 2, max = NAME_MAX)
        String name,

        @NotBlank
        @Email
        @Size(max = EMAIL_MAX)
        String email,

        @NotBlank
        @Pattern(regexp = PHONE_PATTERN)
        String phone

) {}
