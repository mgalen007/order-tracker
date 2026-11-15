package com.galen.ordertracker.customer.dto;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponse (
        UUID id,
        String name,
        String email,
        String phone,
        Instant createdAt
) {}
