package com.galen.ordertracker.customer;

import com.galen.ordertracker.customer.dto.AddressResponse;
import com.galen.ordertracker.customer.dto.CreateAddressRequest;
import com.galen.ordertracker.customer.dto.UpdateAddressRequest;
import com.galen.ordertracker.customer.dto.UpdateCustomerRequest;
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

    public Address toEntity(CreateAddressRequest req, Customer customer) {
        return new Address(
                req.name(),
                customer,
                req.street(),
                req.city(),
                req.latitude(),
                req.longitude()
        );
    }

    public void updateEntity(UpdateAddressRequest req, Address address) {
        if (req.name() != null) address.setName(req.name());
        if (req.street() != null) address.setStreet(req.street());
        if (req.city() != null) address.setCity(req.city());
        if (req.postalCode() != null) address.setPostalCode(req.postalCode());
        if (req.latitude() != null) address.setLatitude(req.latitude());
        if (req.longitude() != null) address.setLongitude(req.longitude());
        if (req.name() != null) address.setName(req.name());
    }

    public void updateEntity(UpdateAddressRequest req, Address address, Customer customer) {
        updateEntity(req, address);
        if (req.customerId() != null) address.setCustomer(customer);
    }

}
