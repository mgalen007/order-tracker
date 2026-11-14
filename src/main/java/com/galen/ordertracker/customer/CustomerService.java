package com.galen.ordertracker.customer;

import com.galen.ordertracker.customer.dto.CreateCustomerRequest;
import com.galen.ordertracker.customer.dto.CustomerResponse;
import com.galen.ordertracker.customer.dto.UpdateCustomerRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerService {

    private final CustomerMapper mapper;
    private final CustomerRepository repository;

    public CustomerService(CustomerMapper mapper, CustomerRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CustomerResponse createCustomer(CreateCustomerRequest req) {
        Customer customer = this.mapper.toEntity(req);
        Customer savedCustomer = this.repository.save(customer);

        return this.mapper.toResponse(savedCustomer);
    }

    public CustomerResponse findCustomerById(UUID id) {
        Customer customer = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer with ID " + id + " not found"
                ));

        return this.mapper.toResponse(customer);
    }

    public CustomerResponse findCustomerByEmail(String email) {
        Customer customer = this.repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer with email " + email + " not found"
                ));

        return this.mapper.toResponse(customer);
    }

    public CustomerResponse findByPhone(String phone) {
        Customer customer = this.repository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer with the phone number " + phone + " not found"
                ));

        return this.mapper.toResponse(customer);
    }

    public List<CustomerResponse> findCustomersByName(String name) {
        return this.repository.findByName(name)
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    @Transactional
    public CustomerResponse updateCustomer(UUID id, UpdateCustomerRequest req) {
        Customer customer = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer with ID " + id + " not found"
                ));
        this.mapper.updateEntity(req, customer);

        return this.mapper.toResponse(customer);
    }

    public CustomerResponse deleteCustomer(UUID id) {
        Customer customer = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer with ID " + id + " not found"
                ));
        this.repository.deleteById(id);

        return this.mapper.toResponse(customer);
    }

}
