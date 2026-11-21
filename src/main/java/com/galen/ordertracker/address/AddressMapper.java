package com.galen.ordertracker.address;

import com.galen.ordertracker.address.dto.AddAddressRequest;
import com.galen.ordertracker.address.dto.AddressResponse;
import com.galen.ordertracker.address.dto.EditAddressRequest;
import com.galen.ordertracker.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressResponse toResponse(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getName(),
                address.getCustomer().getId(),
                address.getStreet(),
                address.getCity(),
                address.getPostalCode(),
                address.getLatitude(),
                address.getLongitude()
        );
    }

    public Address toEntity(AddAddressRequest req, Customer customer) {
        Address address = new Address(
                req.name(),
                customer,
                req.street(),
                req.city(),
                req.latitude(),
                req.longitude()
        );

        if (req.postalCode() != null) address.setPostalCode(req.postalCode());

        return address;
    }

    public void applyEdit(EditAddressRequest req, Address address) {
        if (req.name() != null) address.setName(req.name());
        if (req.street() != null) address.setStreet(req.street());
        if (req.city() != null) address.setCity(req.city());
        if (req.postalCode() != null) address.setPostalCode(req.postalCode());
        if (req.latitude() != null) address.setLatitude(req.latitude());
        if (req.longitude() != null) address.setLongitude(req.longitude());
    }
}
