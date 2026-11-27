package com.galen.ordertracker.item;

import com.galen.ordertracker.business.Business;
import com.galen.ordertracker.business.BusinessRepository;
import com.galen.ordertracker.common.exception.ConflictException;
import com.galen.ordertracker.common.exception.ResourceNotFoundException;
import com.galen.ordertracker.item.dto.AddItemRequest;
import com.galen.ordertracker.item.dto.EditItemRequest;
import com.galen.ordertracker.item.dto.ItemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final BusinessRepository businessRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository repository;
    private final ItemMapper mapper;

    public ItemService(
            BusinessRepository businessRepository,
            CategoryRepository categoryRepository,
            ItemRepository repository,
            ItemMapper mapper
    ) {
        this.categoryRepository = categoryRepository;
        this.businessRepository = businessRepository;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ItemResponse addItem(AddItemRequest req) {
        Business business = requireBusiness(req.businessId());
        Category category = requireCategory(req.categoryId());
        ensureCategoryBelongsTo(category, business.getId());

        Item item = this.repository.save(this.mapper.toEntity(req, business, category));
        return this.mapper.toResponse(item);
    }

    public ItemResponse getItemById(UUID id) {
        return this.mapper.toResponse(requireItem(id));
    }

    public List<ItemResponse> listItems() {
        return this.repository.findAllByDeletedAtIsNullAndBusinessDeregisteredAtIsNull()
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    @Transactional
    public ItemResponse editItem(UUID id, EditItemRequest req) {
        Item item = requireItem(id);

        if (req.categoryId() != null) {
            Category category = requireCategory(req.categoryId());
            ensureCategoryBelongsTo(category, item.getBusiness().getId());
            this.mapper.applyEdit(req, item, category);
        } else {
            this.mapper.applyEdit(req, item);
        }

        return this.mapper.toResponse(item);
    }

    @Transactional
    public void removeItem(UUID id) {
        requireItem(id).delete();
    }

    private Item requireItem(UUID id) {
        return this.repository.findByIdAndDeletedAtIsNullAndBusinessDeregisteredAtIsNull(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Item", id));
    }

    private Category requireCategory(UUID id) {
        return this.categoryRepository.findByIdAndBusinessDeregisteredAtIsNull(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Category", id));
    }

    private Business requireBusiness(UUID id) {
        return this.businessRepository.findByIdAndDeregisteredAtIsNull(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Business", id));
    }

    private void ensureCategoryBelongsTo(Category category, UUID businessId) {
        if (!category.getBusiness().getId().equals(businessId)) {
            throw new ConflictException("Category " + category.getId() + " does not belong to this item's business");
        }
    }
}
