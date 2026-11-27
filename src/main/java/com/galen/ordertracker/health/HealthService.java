package com.galen.ordertracker.health;

import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public HealthResponse healthCheck() {
        return new HealthResponse(
          HealthStatus.SERVICE_HEALTHY,
          "Order Tracker API service up"
        );
    }

}
