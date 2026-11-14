package com.galen.ordertracker.customer;

import com.galen.ordertracker.business.dto.UpdateBusinessRequest;
import com.galen.ordertracker.customer.dto.CreateCustomerRequest;
import com.galen.ordertracker.customer.dto.CustomerResponse;
import com.galen.ordertracker.customer.dto.UpdateCustomerRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerResponse createCustomer(
            @Valid @RequestBody CreateCustomerRequest req
    ) {
        return this.service.createCustomer(req);
    }

    @GetMapping("/{id}")
    public CustomerResponse findCustomerById(
            @PathVariable UUID id
    ) {
        return this.service.findCustomerById(id);
    }

    @GetMapping
    public List<CustomerResponse> findCustomersByName(
            @RequestParam String name
    ) {
        return this.service.findCustomersByName(name);
    }

    @PatchMapping("/{id}")
    public CustomerResponse updateCustomer(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCustomerRequest req
    ) {
        return this.service.updateCustomer(id, req);
    }

    @DeleteMapping("/{id}")
    public CustomerResponse deleteCustomer(
            @PathVariable UUID id
    ) {
        return this.service.deleteCustomer(id);
    }

}
