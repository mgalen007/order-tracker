package com.galen.ordertracker.business;

import com.galen.ordertracker.business.dto.BusinessResponse;
import com.galen.ordertracker.business.dto.CreateBusinessRequest;

public class BusinessMapper {
    public BusinessResponse toResponse(Business business) {
        return new BusinessResponse(
                business.getId(),
                business.getBusinessType(),
                business.getName(),
                business.getDescription(),
                business.getPhone(),
                business.getStreet(),
                business.getCity(),
                business.getLatitude(),
                business.getLongitude()
        );
    }

    public Business toEntity(CreateBusinessRequest req) {
        Business business = new Business(
                req.businessType(),
                req.name(),
                req.phone(),
                req.street(),
                req.city()
        );

        business.setDescription(req.description());
        business.setLatitude(req.latitude());
        business.setLongitude(req.longitude());

        return business;
    }
}
