package com.galen.ordertracker.customer;

import com.galen.ordertracker.customer.dto.CustomerResponse;
import com.galen.ordertracker.customer.dto.RegisterCustomerRequest;
import com.galen.ordertracker.customer.dto.UpdateCustomerProfileRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse registerCustomer(@Valid @RequestBody RegisterCustomerRequest req) {
        return this.service.registerCustomer(req);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable UUID id) {
        return this.service.getCustomer(id);
    }

    @GetMapping
    public List<CustomerResponse> listCustomers(@RequestParam(required = false) String name) {
        if (name != null) return this.service.searchCustomersByName(name);
        return this.service.listCustomers();
    }

    @PatchMapping("/{id}")
    public CustomerResponse updateCustomerProfile(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCustomerProfileRequest req
    ) {
        return this.service.updateCustomerProfile(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeCustomerAccount(@PathVariable UUID id) {
        this.service.closeCustomerAccount(id);
    }
}
