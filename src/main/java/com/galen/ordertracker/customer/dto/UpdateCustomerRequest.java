package com.galen.ordertracker.customer.dto;

import jakarta.validation.constraints.Size;

public record UpdateCustomerRequest (

    @Size(min = 3, max = 50)
    String name,

    @Size(min = 5, max = 50)
    String email,

    @Size(min = 10, max = 13)
    String phone

) {}
