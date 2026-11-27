package com.galen.ordertracker.item;

import com.galen.ordertracker.item.dto.AddItemRequest;
import com.galen.ordertracker.item.dto.EditItemRequest;
import com.galen.ordertracker.item.dto.ItemResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse addItem(
            @Valid @RequestBody AddItemRequest req
    ) {
        return this.service.addItem(req);
    }

    @GetMapping("/{id}")
    public ItemResponse getItemById(
            @PathVariable UUID id
    ) {
        return this.service.getItemById(id);
    }

    @GetMapping
    public List<ItemResponse> listItems() {
        return this.service.listItems();
    }

    @PatchMapping("/{id}")
    public ItemResponse editItem(
            @PathVariable UUID id,
            @Valid @RequestBody EditItemRequest req
    ) {
        return this.service.editItem(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(
            @PathVariable UUID id
    ) {
        this.service.removeItem(id);
    }
}
