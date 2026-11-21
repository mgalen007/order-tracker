package com.galen.ordertracker.address;

import com.galen.ordertracker.address.dto.AddAddressRequest;
import com.galen.ordertracker.address.dto.AddressResponse;
import com.galen.ordertracker.address.dto.EditAddressRequest;
import com.galen.ordertracker.common.exception.ResourceNotFoundException;
import com.galen.ordertracker.customer.Customer;
import com.galen.ordertracker.customer.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class AddressService {

    private final AddressRepository repository;
    private final CustomerRepository customerRepository;
    private final AddressMapper mapper;

    public AddressService(
            AddressRepository repository,
            CustomerRepository customerRepository,
            AddressMapper mapper
    ) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Transactional
    public AddressResponse addAddress(AddAddressRequest req) {
        Customer customer = this.customerRepository.findById(req.customerId())
                .orElseThrow(() -> ResourceNotFoundException.of("Customer", req.customerId()));

        Address address = this.repository.save(this.mapper.toEntity(req, customer));
        return this.mapper.toResponse(address);
    }

    public AddressResponse getAddress(UUID id) {
        return this.mapper.toResponse(requireAddress(id));
    }

    public List<AddressResponse> listAddresses() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    public List<AddressResponse> searchAddressesByName(String name) {
        return this.repository.findAllByName(name)
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    @Transactional
    public AddressResponse editAddress(UUID id, EditAddressRequest req) {
        Address address = requireAddress(id);

        this.mapper.applyEdit(req, address);
        return this.mapper.toResponse(address);
    }

    @Transactional
    public void removeAddress(UUID id) {
        this.repository.delete(requireAddress(id));
    }

    private Address requireAddress(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Address", id));
    }
}
