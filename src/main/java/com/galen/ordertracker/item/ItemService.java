package com.galen.ordertracker.item;

import com.galen.ordertracker.business.Business;
import com.galen.ordertracker.business.BusinessRepository;
import com.galen.ordertracker.item.dto.AddItemRequest;
import com.galen.ordertracker.item.dto.EditItemRequest;
import com.galen.ordertracker.item.dto.ItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.rmi.server.UID;
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

    public ItemResponse addItem(AddItemRequest req, UUID businessId, UUID categoryId) {
        Item item = this.mapper.toEntity(req, requireBusiness(businessId), requireCategory(categoryId));
        Item savedItem = this.repository.save(item);

        return this.mapper.toResponse(savedItem);
    }

    public ItemResponse getItemById(UUID id) {
        Item item = requireItem(id);

        return this.mapper.toResponse(item);
    }

    public List<ItemResponse> listItems() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    @Transactional
    public ItemResponse editItem(UUID id, EditItemRequest req) {
        Item item = requireItem(id);
        this.mapper.applyEdit(req, item);

        return this.mapper.toResponse(item);
    }

    @Transactional
    public ItemResponse editItemCategory(UUID id, EditItemRequest req, UUID categoryId) {
        Item item = requireItem(id);
        this.mapper.applyEdit(req, item, requireCategory(categoryId));

        return this.mapper.toResponse(item);
    }

    @Transactional
    public void removeItem(UUID id) {
        Item item = requireItem(id);
        this.repository.delete(item);
    }

    public Item requireItem(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Item with ID " + id + " not found"
                ));
    }

    public Category requireCategory(UUID id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category with ID " + id + " not found"
                ));
    }

    public Business requireBusiness(UUID id) {
        return this.businessRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Business with ID " + id + " not found"
                ));
    }
}
