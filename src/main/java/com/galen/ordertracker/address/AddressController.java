package com.galen.ordertracker.customer;

import com.galen.ordertracker.customer.dto.AddressResponse;
import com.galen.ordertracker.customer.dto.CreateAddressRequest;
import com.galen.ordertracker.customer.dto.UpdateAddressRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse createAddress(
            @Valid @RequestBody CreateAddressRequest req
    ) {
        return this.service.createAddress(req);
    }

    @GetMapping
    public List<AddressResponse> findAddresses(
            @RequestParam String name
    ) {
        if (name != null) return this.service.findAddressesByName(name);
        return this.service.findAllAddresses();
    }

    @GetMapping("/{id}")
    public AddressResponse findAddressById(
            @PathVariable UUID id
    ) {
        return this.service.findAddressById(id);
    }

    @PatchMapping("/{id}")
    public AddressResponse updateAddress(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateAddressRequest req
    ) {
        return this.service.updateAddress(id, req);
    }

    @DeleteMapping("/{id}")
    public AddressResponse deleteAddress(
            @PathVariable UUID id
    ) {
        return this.service.deleteAddress(id);
    }

}
