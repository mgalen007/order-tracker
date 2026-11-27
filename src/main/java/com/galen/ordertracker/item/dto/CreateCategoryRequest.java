package com.galen.ordertracker.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

import static com.galen.ordertracker.common.validation.ValidationRules.NAME_MAX;

public record CreateCategoryRequest(
        @NotNull
        UUID businessId,

        @NotBlank
        @Size(max = NAME_MAX)
        String name
) {}
