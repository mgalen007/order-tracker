package com.galen.ordertracker.customer.dto;

import jakarta.validation.constraints.Size;

public record UpdateCustomerRequest (

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    String name,

    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    String email,

    @Size(min = 10, max = 13, message = "Phone must be between 10 and 13 characters")
    String phone

) {}
