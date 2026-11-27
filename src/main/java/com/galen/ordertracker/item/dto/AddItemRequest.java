package com.galen.ordertracker.item.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record AddItemRequest(
        @NotNull
        UUID businessId,

        @NotBlank
        @Size(min = 2, max = NAME_MAX)
        String name,

        @Size(max = DESCRIPTION_MAX)
        String description,

        @NotNull
        UUID categoryId,

        @NotNull
        @PositiveOrZero
        @Digits(integer = PRICE_INTEGER_DIGITS, fraction = PRICE_FRACTION_DIGITS)
        BigDecimal price,

        @NotNull
        Boolean available
) {}
