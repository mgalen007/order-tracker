package com.galen.ordertracker.item.dto;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record EditItemRequest(
        @Size(min = 3, max = 40)
        String name,

        @Size(min = 10, max = 200)
        String description,

        UUID categoryId,

        BigDecimal price,

        boolean available
) {}
