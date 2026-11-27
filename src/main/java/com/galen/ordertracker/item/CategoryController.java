package com.galen.ordertracker.item;

import com.galen.ordertracker.item.dto.CategoryResponse;
import com.galen.ordertracker.item.dto.CreateCategoryRequest;
import com.galen.ordertracker.item.dto.EditCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest req) {
        return this.service.createCategory(req);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable UUID id) {
        return this.service.getCategory(id);
    }

    @GetMapping
    public List<CategoryResponse> listCategories(@RequestParam(required = false) UUID businessId) {
        return this.service.listCategories(businessId);
    }

    @PatchMapping("/{id}")
    public CategoryResponse editCategory(
            @PathVariable UUID id,
            @Valid @RequestBody EditCategoryRequest req
    ) {
        return this.service.editCategory(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCategory(@PathVariable UUID id) {
        this.service.removeCategory(id);
    }
}
