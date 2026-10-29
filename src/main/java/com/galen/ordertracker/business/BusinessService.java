package com.galen.ordertracker.business;

import com.galen.ordertracker.business.dto.BusinessResponse;
import com.galen.ordertracker.business.dto.CreateBusinessRequest;
import com.galen.ordertracker.business.dto.UpdateBusinessRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class BusinessService {
    private final BusinessRepository repository;
    private final BusinessMapper mapper;

    public BusinessService(BusinessRepository repository, BusinessMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public BusinessResponse createBusiness(CreateBusinessRequest req) {
        Business business = this.mapper.toEntity(req);
        Business savedBusiness = this.repository.save(business);

        return this.mapper.toResponse(savedBusiness);
    }

    public BusinessResponse findBusinessById(UUID id) {
        Business business = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Business with ID " + id + " not found"
                ));

        return this.mapper.toResponse(business);
    }

    public List<BusinessResponse> findBusinessesByName(String name) {
        return this.repository.findByName(name)
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    public List<BusinessResponse> findBusinessesByType(BusinessType businessType) {
        return this.repository.findByBusinessType(businessType)
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    public List<BusinessResponse> findByStreet(String street) {
        return this.repository.findByStreet(street)
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    public List<BusinessResponse> findByCity(String city) {
        return this.repository.findByCity(city)
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    @Transactional
    public BusinessResponse updateBusiness(UUID id, UpdateBusinessRequest req) {
        Business business = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Business with ID " + id + " not found"
                ));

        this.mapper.updateEntity(req, business);

        return this.mapper.toResponse(business);
    }

    @Transactional
    public void deleteBusiness(UUID id) {
        Business business = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Business with ID " + id + " not found"
                ));

        this.repository.deleteById(id);
    }
}
