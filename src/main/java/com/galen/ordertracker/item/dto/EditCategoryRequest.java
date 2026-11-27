package com.galen.ordertracker.item.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

import static com.galen.ordertracker.common.validation.ValidationRules.*;

public record EditCategoryRequest(
        UUID businessId,

        @Size(max = NAME_MAX)
        @Pattern(regexp = NOT_BLANK_PATTERN, message = NOT_BLANK_MESSAGE)
        String name
) {}
