package com.galen.ordertracker.business;

import com.galen.ordertracker.business.dto.BusinessResponse;
import com.galen.ordertracker.business.dto.CreateBusinessRequest;
import com.galen.ordertracker.business.dto.UpdateBusinessRequest;
import org.springframework.stereotype.Component;

@Component
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

    public void updateEntity(UpdateBusinessRequest req, Business business) {
        if (req.businessType() != null) business.setBusinessType(req.businessType());
        if (req.city() != null) business.setCity(req.city());
        if (req.description() != null) business.setDescription(req.description());
        if (req.name() != null) business.setName(req.name());
        if (req.latitude() != null) business.setLatitude(req.latitude());
        if (req.longitude() != null) business.setLongitude(req.longitude());
        if (req.phone() != null) business.setPhone(req.phone());
        if (req.street() != null) business.setStreet(req.street());
    }
}
