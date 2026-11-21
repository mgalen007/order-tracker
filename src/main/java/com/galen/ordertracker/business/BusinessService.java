package com.galen.ordertracker.business;

import com.galen.ordertracker.business.dto.BusinessResponse;
import com.galen.ordertracker.business.dto.RegisterBusinessRequest;
import com.galen.ordertracker.business.dto.UpdateBusinessProfileRequest;
import com.galen.ordertracker.common.exception.ConflictException;
import com.galen.ordertracker.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class BusinessService {

    private final BusinessRepository repository;
    private final BusinessMapper mapper;

    public BusinessService(BusinessRepository repository, BusinessMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public BusinessResponse registerBusiness(RegisterBusinessRequest req) {
        ensurePhoneAvailable(req.phone());

        Business business = this.repository.save(this.mapper.toEntity(req));
        return this.mapper.toResponse(business);
    }

    public BusinessResponse getBusiness(UUID id) {
        return this.mapper.toResponse(requireBusiness(id));
    }

    public List<BusinessResponse> listBusinesses() {
        return this.repository.findAllByDeregisteredAtIsNull()
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    @Transactional
    public BusinessResponse updateBusinessProfile(UUID id, UpdateBusinessProfileRequest req) {
        Business business = requireBusiness(id);

        if (req.phone() != null && !req.phone().equals(business.getPhone())) {
            ensurePhoneAvailable(req.phone());
        }

        this.mapper.applyProfileUpdate(req, business);
        return this.mapper.toResponse(business);
    }

    /** Starts accepting orders. */
    @Transactional
    public BusinessResponse openBusiness(UUID id) {
        Business business = requireBusiness(id);
        business.setOpen(true);
        return this.mapper.toResponse(business);
    }

    /** Stops accepting orders. */
    @Transactional
    public BusinessResponse closeBusiness(UUID id) {
        Business business = requireBusiness(id);
        business.setOpen(false);
        return this.mapper.toResponse(business);
    }

    /** Removes the business from the platform; the record is kept for order history. */
    @Transactional
    public void deregisterBusiness(UUID id) {
        requireBusiness(id).deregister();
    }

    private Business requireBusiness(UUID id) {
        return this.repository.findByIdAndDeregisteredAtIsNull(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Business", id));
    }

    private void ensurePhoneAvailable(String phone) {
        if (this.repository.existsByPhoneAndDeregisteredAtIsNull(phone)) {
            throw new ConflictException("A business with the phone number " + phone + " already exists");
        }
    }
}
