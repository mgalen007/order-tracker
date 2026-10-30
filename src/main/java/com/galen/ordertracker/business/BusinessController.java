package com.galen.ordertracker.business;

import com.galen.ordertracker.business.dto.BusinessResponse;
import com.galen.ordertracker.business.dto.CreateBusinessRequest;
import com.galen.ordertracker.business.dto.UpdateBusinessRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/businesses")
public class BusinessController {
    private final BusinessService service;

    public BusinessController(BusinessService service) {
        this.service = service;
    }

    @PostMapping
    public BusinessResponse createBusiness(
            @Valid @RequestBody CreateBusinessRequest req
    ) {
        return this.service.createBusiness(req);
    }

    @GetMapping("/{id}")
    public BusinessResponse findBusinessById(
            @PathVariable UUID id
    ) {
        return this.service.findBusinessById(id);
    }

    @GetMapping
    public List<BusinessResponse> findAllBusiness() {
        return this.service.findAllBusinesses();
    }

    @PatchMapping("/{id}")
    public BusinessResponse updateBusiness(
            @Valid @RequestBody UpdateBusinessRequest req,
            @PathVariable UUID id
    ) {
        return this.service.updateBusiness(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteBusiness(
            @PathVariable UUID id
    ) {
        this.service.deleteBusiness(id);
    }

}
