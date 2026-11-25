package com.galen.ordertracker.item.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemResponse(
   UUID id,
   UUID businessId,
   String name,
   String description,
   UUID categoryId,
   BigDecimal price,
   boolean available
) {}
