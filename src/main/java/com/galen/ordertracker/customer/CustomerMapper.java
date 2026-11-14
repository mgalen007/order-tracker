package com.galen.ordertracker.customer;

import com.galen.ordertracker.customer.dto.CreateCustomerRequest;
import com.galen.ordertracker.customer.dto.CustomerResponse;
import com.galen.ordertracker.customer.dto.UpdateCustomerRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt()
        );
    }

    public Customer toEntity(CreateCustomerRequest req) {
        return new Customer(
            req.name(),
            req.email(),
            req.phone()
        );
    }

    public void updateEntity(UpdateCustomerRequest req, Customer customer) {
        if (req.name() != null) customer.setName(req.name());
        if (req.email() != null) customer.setEmail(req.email());
        if (req.phone() != null) customer.setPhone(req.phone());
    }

}
