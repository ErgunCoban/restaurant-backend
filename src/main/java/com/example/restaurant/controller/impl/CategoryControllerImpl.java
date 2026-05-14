package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.ICategoryController;
import com.example.restaurant.controller.base.RestBaseController;
import com.example.restaurant.dto.request.CreateCategoryRequestDTO;
import com.example.restaurant.dto.response.CategoryResponseDTO;
import com.example.restaurant.model.base.RootEntity;
import com.example.restaurant.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant/category")
public class CategoryControllerImpl extends RestBaseController implements ICategoryController {

    private final ICategoryService categoryService;

    @PostMapping("/save")
    @Override
    public RootEntity<CategoryResponseDTO> saveCategory(@Valid @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO) {
        return ok(categoryService.saveCategory(createCategoryRequestDTO));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        return ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id,
                                                          @Valid @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO) {
        return ok(categoryService.updateCategory(id, createCategoryRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ok(null);
    }
}