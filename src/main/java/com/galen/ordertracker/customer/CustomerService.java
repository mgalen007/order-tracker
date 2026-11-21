package com.galen.ordertracker.customer;

import com.galen.ordertracker.common.exception.ConflictException;
import com.galen.ordertracker.common.exception.ResourceNotFoundException;
import com.galen.ordertracker.customer.dto.CustomerResponse;
import com.galen.ordertracker.customer.dto.RegisterCustomerRequest;
import com.galen.ordertracker.customer.dto.UpdateCustomerProfileRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CustomerResponse registerCustomer(RegisterCustomerRequest req) {
        String email = normalizeEmail(req.email());
        ensureEmailAvailable(email);
        ensurePhoneAvailable(req.phone());

        Customer customer = this.mapper.toEntity(req);
        customer.setEmail(email);

        return this.mapper.toResponse(this.repository.saveAndFlush(customer));
    }

    public CustomerResponse getCustomer(UUID id) {
        return this.mapper.toResponse(requireCustomer(id));
    }

    public CustomerResponse getCustomerByEmail(String email) {
        return this.repository.findByEmailAndClosedAtIsNull(normalizeEmail(email))
                .map(this.mapper::toResponse)
                .orElseThrow(() -> ResourceNotFoundException.of("Customer", "email", email));
    }

    public CustomerResponse getCustomerByPhone(String phone) {
        return this.repository.findByPhoneAndClosedAtIsNull(phone)
                .map(this.mapper::toResponse)
                .orElseThrow(() -> ResourceNotFoundException.of("Customer", "phone number", phone));
    }

    public List<CustomerResponse> listCustomers() {
        return this.repository.findAllByClosedAtIsNull()
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    public List<CustomerResponse> searchCustomersByName(String name) {
        return this.repository.findAllByNameAndClosedAtIsNull(name)
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    @Transactional
    public CustomerResponse updateCustomerProfile(UUID id, UpdateCustomerProfileRequest req) {
        Customer customer = requireCustomer(id);

        String email = req.email() == null ? null : normalizeEmail(req.email());
        if (email != null && !email.equals(customer.getEmail())) {
            ensureEmailAvailable(email);
        }
        if (req.phone() != null && !req.phone().equals(customer.getPhone())) {
            ensurePhoneAvailable(req.phone());
        }

        this.mapper.applyProfileUpdate(req, customer);
        if (email != null) customer.setEmail(email);

        return this.mapper.toResponse(customer);
    }

    /** Closes the account; the record is kept so past orders still point to a customer. */
    @Transactional
    public void closeCustomerAccount(UUID id) {
        requireCustomer(id).close();
    }

    private Customer requireCustomer(UUID id) {
        return this.repository.findByIdAndClosedAtIsNull(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Customer", id));
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }

    private void ensureEmailAvailable(String email) {
        if (this.repository.existsByEmailAndClosedAtIsNull(email)) {
            throw new ConflictException("A customer with the email " + email + " already exists");
        }
    }

    private void ensurePhoneAvailable(String phone) {
        if (this.repository.existsByPhoneAndClosedAtIsNull(phone)) {
            throw new ConflictException("A customer with the phone number " + phone + " already exists");
        }
    }
}
