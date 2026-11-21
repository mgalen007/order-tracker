package com.galen.ordertracker.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateCustomerProfileRequest(

        @Size(min = 3, max = 50)
        String name,

        @Email
        @Size(min = 5, max = 50)
        String email,

        @Size(min = 10, max = 13)
        String phone

) {}
