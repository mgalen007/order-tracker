package com.galen.ordertracker.business.dto;

import com.galen.ordertracker.business.BusinessType;

import java.util.UUID;

public record BusinessResponse(
        UUID id,
        BusinessType businessType,
        String name,
        String description,
        String phone,
        String street,
        String city,
        Double latitude,
        Double longitude
) {}
