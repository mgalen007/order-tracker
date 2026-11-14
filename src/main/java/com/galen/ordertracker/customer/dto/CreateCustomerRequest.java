package com.galen.ordertracker.customer.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest (

    @NotNull(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    String name,

    @NotNull(message = "Email cannot be empty")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    String email,

    @NotNull(message = "Phone cannot be empty")
    @Size(min = 10, max = 13, message = "Phone must be between 10 and 13 characters")
    String phone

) {}
