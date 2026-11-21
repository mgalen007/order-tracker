package com.galen.ordertracker.customer;

import com.galen.ordertracker.address.AddressRepository;
import com.galen.ordertracker.common.exception.ConflictException;
import com.galen.ordertracker.common.exception.ResourceNotFoundException;
import com.galen.ordertracker.customer.dto.CustomerResponse;
import com.galen.ordertracker.customer.dto.RegisterCustomerRequest;
import com.galen.ordertracker.customer.dto.UpdateCustomerProfileRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository repository;
    private final AddressRepository addressRepository;
    private final CustomerMapper mapper;

    public CustomerService(
            CustomerRepository repository,
            AddressRepository addressRepository,
            CustomerMapper mapper
    ) {
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Transactional
    public CustomerResponse registerCustomer(RegisterCustomerRequest req) {
        ensureEmailAvailable(req.email());
        ensurePhoneAvailable(req.phone());

        Customer customer = this.repository.save(this.mapper.toEntity(req));
        return this.mapper.toResponse(customer);
    }

    public CustomerResponse getCustomer(UUID id) {
        return this.mapper.toResponse(requireCustomer(id));
    }

    public CustomerResponse getCustomerByEmail(String email) {
        return this.repository.findByEmail(email)
                .map(this.mapper::toResponse)
                .orElseThrow(() -> ResourceNotFoundException.of("Customer", "email", email));
    }

    public CustomerResponse getCustomerByPhone(String phone) {
        return this.repository.findByPhone(phone)
                .map(this.mapper::toResponse)
                .orElseThrow(() -> ResourceNotFoundException.of("Customer", "phone number", phone));
    }

    public List<CustomerResponse> listCustomers() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    public List<CustomerResponse> searchCustomersByName(String name) {
        return this.repository.findByName(name)
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    @Transactional
    public CustomerResponse updateCustomerProfile(UUID id, UpdateCustomerProfileRequest req) {
        Customer customer = requireCustomer(id);

        if (req.email() != null && !req.email().equals(customer.getEmail())) {
            ensureEmailAvailable(req.email());
        }
        if (req.phone() != null && !req.phone().equals(customer.getPhone())) {
            ensurePhoneAvailable(req.phone());
        }

        this.mapper.applyProfileUpdate(req, customer);
        return this.mapper.toResponse(customer);
    }

    /** Closes the account together with the customer's saved addresses. */
    @Transactional
    public void closeCustomerAccount(UUID id) {
        Customer customer = requireCustomer(id);

        this.addressRepository.deleteAllByCustomerId(id);
        this.repository.delete(customer);
    }

    private Customer requireCustomer(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Customer", id));
    }

    private void ensureEmailAvailable(String email) {
        if (this.repository.existsByEmail(email)) {
            throw new ConflictException("A customer with the email " + email + " already exists");
        }
    }

    private void ensurePhoneAvailable(String phone) {
        if (this.repository.existsByPhone(phone)) {
            throw new ConflictException("A customer with the phone number " + phone + " already exists");
        }
    }
}
