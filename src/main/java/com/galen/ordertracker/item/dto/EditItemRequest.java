package com.galen.ordertracker.item.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record EditItemRequest(
        @Size(min = 2, max = NAME_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
        String name,

        @Size(max = DESCRIPTION_MAX)
        String description,

        UUID categoryId,

        @PositiveOrZero
        @Digits(integer = PRICE_INTEGER_DIGITS, fraction = PRICE_FRACTION_DIGITS)
        BigDecimal price,

        Boolean available
) {}
