package com.galen.ordertracker.customer.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest (

    @NotNull
    @Size(min = 3, max = 50)
    String name,

    @NotNull
    @Size(min = 5, max = 50)
    String email,

    @NotNull
    @Size(min = 10, max = 13)
    String phone

) {}
