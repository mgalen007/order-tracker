package com.galen.ordertracker.item.dto;

import jakarta.validation.constraints.Size;

import java.util.UUID;

import static com.galen.ordertracker.common.validation.ValidationRules.NAME_MAX;

public record EditCategoryRequest(
        UUID businessId,

        @Size(max = NAME_MAX)
        String name
) {}