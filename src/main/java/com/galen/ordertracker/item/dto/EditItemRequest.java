package com.galen.ordertracker.item.dto;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

import static com.galen.ordertracker.common.validation.ValidationRules.DESCRIPTION_MAX;
import static com.galen.ordertracker.common.validation.ValidationRules.NAME_MAX;

public record EditItemRequest(
        @Size(min = 2, max = NAME_MAX)
        String name,

        @Size(max = DESCRIPTION_MAX)
        String description,

        UUID categoryId,

        BigDecimal price,

        boolean available
) {}
