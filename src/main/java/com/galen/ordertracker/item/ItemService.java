package com.galen.ordertracker.item;

import com.galen.ordertracker.business.Business;
import com.galen.ordertracker.item.dto.AddItemRequest;
import com.galen.ordertracker.item.dto.EditItemRequest;
import com.galen.ordertracker.item.dto.ItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    // addItem, editItem, removeItem, getItemById, listItems
    private final ItemRepository repository;
    private final ItemMapper mapper;

    public ItemService(ItemRepository repository, ItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ItemResponse addItem(AddItemRequest req, Business business, Category category) {
        Item item = this.mapper.toEntity(req, business, category);
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

    public ItemResponse editItem(UUID id, EditItemRequest req) {
        Item item = requireItem(id);
        this.mapper.applyEdit(req, item);

        return this.mapper.toResponse(item);
    }

    public ItemResponse editItemCategory(UUID id, EditItemRequest req, Category category) {
        Item item = requireItem(id);
        this.mapper.applyEdit(req, item, category);

        return this.mapper.toResponse(item);
    }

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
}
