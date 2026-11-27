package com.galen.ordertracker.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record UpdateCustomerProfileRequest(

        @Size(min = 2, max = NAME_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
        String name,

        @Email
        @Size(max = EMAIL_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
        String email,

        @Pattern(regexp = PHONE_PATTERN)
        String phone

) {}
