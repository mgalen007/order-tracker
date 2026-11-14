package com.galen.ordertracker.health;

public record HealthResponse(
   HealthStatus status,
   String message
) {}
