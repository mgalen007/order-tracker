package com.galen.ordertracker.item;

import com.galen.ordertracker.business.Business;
import com.galen.ordertracker.business.BusinessRepository;
import com.galen.ordertracker.common.exception.ResourceNotFoundException;
import com.galen.ordertracker.item.dto.AddItemRequest;
import com.galen.ordertracker.item.dto.EditItemRequest;
import com.galen.ordertracker.item.dto.ItemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    // addItem, editItem, removeItem, getItemById, listItems
    private final BusinessRepository businessRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository repository;
    private final ItemMapper mapper;

    public ItemService(BusinessRepository businessRepository, CategoryRepository categoryRepository, ItemRepository repository, ItemMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.businessRepository = businessRepository;
        this.repository = repository;
        this.mapper = mapper;
    }

    public ItemResponse addItem(AddItemRequest req) {
        Item item = this.mapper.toEntity(req, requireBusiness(req.businessId()), requireCategory(req.categoryId()));
        Item savedItem = this.repository.save(item);

        return this.mapper.toResponse(savedItem);
    }

    public ItemResponse getItemById(UUID id) {
        Item item = requireItem(id);

        return this.mapper.toResponse(item);
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
            this.mapper.applyEdit(req, item, requireCategory(req.categoryId()));
        } else {
            this.mapper.applyEdit(req, item);
        }

        return this.mapper.toResponse(item);
    }

    @Transactional
    public void removeItem(UUID id) {
        requireItem(id).delete();
    }

    public Item requireItem(UUID id) {
        return this.repository.findByIdAndDeletedAtIsNullAndBusinessDeregisteredAtIsNull(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Item", id));
    }

    public Category requireCategory(UUID id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Category", id));
    }

    public Business requireBusiness(UUID id) {
        return this.businessRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Business", id));
    }
}
