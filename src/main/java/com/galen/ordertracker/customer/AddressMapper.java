package com.galen.ordertracker.customer;

import com.galen.ordertracker.customer.dto.AddressResponse;
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

}
