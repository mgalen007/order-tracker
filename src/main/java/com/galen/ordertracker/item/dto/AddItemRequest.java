package com.galen.ordertracker.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record AddItemRequest(
        @NotNull
        UUID businessId,

        @NotBlank
        @Size(min = 3, max = 40)
        String name,

        @Size(min = 10, max = 200)
        String description,

        @NotNull
        UUID categoryId,

        @NotNull
        BigDecimal price,

        @NotNull
        boolean available
) {}
