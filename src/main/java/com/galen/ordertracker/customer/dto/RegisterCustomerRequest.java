package com.galen.ordertracker.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterCustomerRequest(

        @NotBlank
        @Size(min = 3, max = 50)
        String name,

        @NotBlank
        @Email
        @Size(min = 5, max = 50)
        String email,

        @NotBlank
        @Size(min = 10, max = 13)
        String phone

) {}
