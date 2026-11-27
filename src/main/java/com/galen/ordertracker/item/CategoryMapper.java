package com.galen.ordertracker.item;

import com.galen.ordertracker.business.Business;
import com.galen.ordertracker.item.dto.CategoryResponse;
import com.galen.ordertracker.item.dto.CreateCategoryRequest;
import com.galen.ordertracker.item.dto.EditCategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getBusiness().getId(),
                category.getName()
        );
    }

    public Category toEntity(CreateCategoryRequest req, Business business) {
        return new Category(
                business,
                req.name().trim()
        );
    }

    public void applyEdit(EditCategoryRequest req, Category category) {
        if (req.name() != null) category.setName(req.name().trim());
    }
}
