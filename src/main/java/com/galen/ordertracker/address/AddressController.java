package com.galen.ordertracker.address;

import com.galen.ordertracker.address.dto.AddAddressRequest;
import com.galen.ordertracker.address.dto.AddressResponse;
import com.galen.ordertracker.address.dto.EditAddressRequest;
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
    public AddressResponse addAddress(@Valid @RequestBody AddAddressRequest req) {
        return this.service.addAddress(req);
    }

    @GetMapping
    public List<AddressResponse> listAddresses(@RequestParam(required = false) String name) {
        if (name != null) return this.service.searchAddressesByName(name);
        return this.service.listAddresses();
    }

    @GetMapping("/{id}")
    public AddressResponse getAddress(@PathVariable UUID id) {
        return this.service.getAddress(id);
    }

    @PatchMapping("/{id}")
    public AddressResponse editAddress(
            @PathVariable UUID id,
            @Valid @RequestBody EditAddressRequest req
    ) {
        return this.service.editAddress(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAddress(@PathVariable UUID id) {
        this.service.removeAddress(id);
    }
}
