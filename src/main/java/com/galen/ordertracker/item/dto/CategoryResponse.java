package com.galen.ordertracker.item.dto;

import java.util.UUID;

public record CategoryResponse(
   UUID id,
   UUID businessId,
   String name
) {}
