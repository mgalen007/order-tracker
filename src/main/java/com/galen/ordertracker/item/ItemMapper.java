package com.galen.ordertracker.item;

import com.galen.ordertracker.business.Business;
import com.galen.ordertracker.item.dto.AddItemRequest;
import com.galen.ordertracker.item.dto.ItemResponse;
import com.galen.ordertracker.item.dto.EditItemRequest;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toEntity(AddItemRequest req, Business business, Category category) {
        return new Item(
          business, req.name(), req.description(),
          category, req.price(), req.available()
        );
    }

    public ItemResponse toResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getBusiness().getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory().getId(),
                item.getPrice(),
                item.isAvailable()
        );
    }

    public void applyEdit(EditItemRequest req, Item item) {
        if (req.name() != null) item.setName(req.name());
        if (req.description() != null) item.setDescription(req.description());
        if (req.price() != null) item.setPrice(req.price());
        if (req.available() != item.isAvailable()) item.setAvailable(req.available());
    }

    public void applyEdit(EditItemRequest req, Item item, Category category) {
        applyEdit(req, item);
        item.setCategory(category);
    }

}
