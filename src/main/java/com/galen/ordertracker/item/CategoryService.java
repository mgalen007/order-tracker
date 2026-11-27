package com.galen.ordertracker.item;

import com.galen.ordertracker.business.Business;
import com.galen.ordertracker.business.BusinessRepository;
import com.galen.ordertracker.common.exception.ConflictException;
import com.galen.ordertracker.common.exception.ResourceNotFoundException;
import com.galen.ordertracker.item.dto.CategoryResponse;
import com.galen.ordertracker.item.dto.CreateCategoryRequest;
import com.galen.ordertracker.item.dto.EditCategoryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryMapper mapper;
    private final CategoryRepository repository;
    private final BusinessRepository businessRepository;
    private final ItemRepository itemRepository;

    public CategoryService(
            CategoryMapper mapper,
            CategoryRepository repository,
            BusinessRepository businessRepository,
            ItemRepository itemRepository
    ) {
        this.mapper = mapper;
        this.repository = repository;
        this.businessRepository = businessRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public CategoryResponse createCategory(CreateCategoryRequest req) {
        Business business = requireBusiness(req.businessId());
        String name = req.name().trim();
        ensureNameAvailable(business.getId(), name);

        Category category = this.repository.save(this.mapper.toEntity(req, business));
        return this.mapper.toResponse(category);
    }

    public CategoryResponse getCategory(UUID id) {
        return this.mapper.toResponse(requireCategory(id));
    }

    public List<CategoryResponse> listCategories(UUID businessId) {
        List<Category> categories = businessId == null
                ? this.repository.findAllByBusinessDeregisteredAtIsNull()
                : this.repository.findAllByBusinessIdAndBusinessDeregisteredAtIsNull(businessId);

        return categories.stream()
                .map(this.mapper::toResponse)
                .toList();
    }

    @Transactional
    public CategoryResponse editCategory(UUID id, EditCategoryRequest req) {
        Category category = requireCategory(id);

        Business business = category.getBusiness();
        boolean moving = req.businessId() != null && !req.businessId().equals(business.getId());
        if (moving) {
            ensureHasNoItems(id, "moved to another business");
            business = requireBusiness(req.businessId());
        }

        String name = req.name() != null ? req.name().trim() : category.getName();
        if (moving || !name.equalsIgnoreCase(category.getName())) {
            ensureNameAvailable(business.getId(), name, id);
        }

        this.mapper.applyEdit(req, category);
        if (moving) category.setBusiness(business);

        return this.mapper.toResponse(category);
    }

    @Transactional
    public void removeCategory(UUID id) {
        Category category = requireCategory(id);
        ensureHasNoItems(id, "deleted");

        this.repository.delete(category);
    }

    private Category requireCategory(UUID id) {
        return this.repository.findByIdAndBusinessDeregisteredAtIsNull(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Category", id));
    }

    private Business requireBusiness(UUID id) {
        return this.businessRepository.findByIdAndDeregisteredAtIsNull(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Business", id));
    }

    private void ensureNameAvailable(UUID businessId, String name) {
        if (this.repository.existsByBusinessIdAndNameIgnoreCase(businessId, name)) {
            throw duplicateName(name);
        }
    }

    private void ensureNameAvailable(UUID businessId, String name, UUID excludingId) {
        if (this.repository.existsByBusinessIdAndNameIgnoreCaseAndIdNot(businessId, name, excludingId)) {
            throw duplicateName(name);
        }
    }

    private ConflictException duplicateName(String name) {
        return new ConflictException("This business already has a category named \"" + name + "\"");
    }

    private void ensureHasNoItems(UUID categoryId, String action) {
        if (this.itemRepository.existsByCategoryId(categoryId)) {
            throw new ConflictException("A category that still has items cannot be " + action);
        }
    }
}
