package com.galen.ordertracker.customer;

import com.galen.ordertracker.customer.dto.AddressResponse;
import com.galen.ordertracker.customer.dto.CreateAddressRequest;
import com.galen.ordertracker.customer.dto.UpdateAddressRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    private final CustomerRepository customerRepository;
    private final AddressRepository repository;
    private final AddressMapper mapper;

    public AddressService(CustomerRepository customerRepository, AddressRepository repository, AddressMapper mapper) {
        this.customerRepository = customerRepository;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public AddressResponse createAddress(CreateAddressRequest req) {
        Customer customer = this.customerRepository.findById(UUID.fromString(String.valueOf(req.customerId())))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer with ID " + req.customerId() + " not found"
                ));

        Address address = this.mapper.toEntity(req, customer);
        Address savedAddress = this.repository.save(address);

        return this.mapper.toResponse(savedAddress);
    }

    public List<AddressResponse> findAddressesByName(String name) {
        return this.repository.findAllByName(name)
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    public AddressResponse findAddressById(UUID id) {
        Address address = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Address with ID " + id + " not found"
                ));

        return this.mapper.toResponse(address);
    }

    @Transactional
    public AddressResponse updateAddress(UUID id, UpdateAddressRequest req) {
        Address address = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Address with ID " + id + " not found"
                ));
        this.mapper.updateEntity(req, address);

        return this.mapper.toResponse(address);
    }

    public AddressResponse deleteAddress(UUID id) {
        Address address = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Address with ID " + id + " not found"
                ));
        this.repository.deleteById(id);

        return this.mapper.toResponse(address);
    }

}
