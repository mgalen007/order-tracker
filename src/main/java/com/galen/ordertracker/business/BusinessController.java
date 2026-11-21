package com.galen.ordertracker.business;

import com.galen.ordertracker.business.dto.BusinessResponse;
import com.galen.ordertracker.business.dto.RegisterBusinessRequest;
import com.galen.ordertracker.business.dto.UpdateBusinessProfileRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessResponse registerBusiness(@Valid @RequestBody RegisterBusinessRequest req) {
        return this.service.registerBusiness(req);
    }

    @GetMapping("/{id}")
    public BusinessResponse getBusiness(@PathVariable UUID id) {
        return this.service.getBusiness(id);
    }

    @GetMapping
    public List<BusinessResponse> listBusinesses() {
        return this.service.listBusinesses();
    }

    @PatchMapping("/{id}")
    public BusinessResponse updateBusinessProfile(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateBusinessProfileRequest req
    ) {
        return this.service.updateBusinessProfile(id, req);
    }

    @PostMapping("/{id}/open")
    public BusinessResponse openBusiness(@PathVariable UUID id) {
        return this.service.openBusiness(id);
    }

    @PostMapping("/{id}/close")
    public BusinessResponse closeBusiness(@PathVariable UUID id) {
        return this.service.closeBusiness(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deregisterBusiness(@PathVariable UUID id) {
        this.service.deregisterBusiness(id);
    }
}
