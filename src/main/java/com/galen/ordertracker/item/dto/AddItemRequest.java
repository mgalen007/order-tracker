package com.galen.ordertracker.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record AddItemRequest(
        @NotNull
        UUID businessId,

        @NotBlank
        @Size(min = 3, max = NAME_MAX)
        String name,

        @Size(max = DESCRIPTION_MAX)
        String description,

        @NotNull
        UUID categoryId,

        @NotNull
        BigDecimal price,

        @NotNull
        boolean available
) {}
