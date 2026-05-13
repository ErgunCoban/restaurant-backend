package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.CreateCategoryRequestDTO;
import com.example.restaurant.dto.response.CategoryResponseDTO;
import com.example.restaurant.mapper.CategoryMapper;
import com.example.restaurant.model.Category;
import com.example.restaurant.repository.CategoryRepository;
import com.example.restaurant.rules.CategoryRules;
import com.example.restaurant.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryRules categoryRules;

    @Override
    public CategoryResponseDTO saveCategory(CreateCategoryRequestDTO createCategoryRequestDTO) {
        categoryRules.checkIfCategoryAlreadyExists(createCategoryRequestDTO.getName());

        Category category = categoryMapper.toEntity(createCategoryRequestDTO);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponseDTO(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toResponseDTOList(categories);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        categoryRules.checkIfCategoryExistsById(id);

        Category category = categoryRepository.findById(id).get();
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CreateCategoryRequestDTO createCategoryRequestDTO) {
        categoryRules.checkIfCategoryExistsById(id);

        Category category = categoryRepository.findById(id).get();
        category.setName(createCategoryRequestDTO.getName());

        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRules.checkIfCategoryExistsById(id);

        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
    }
}