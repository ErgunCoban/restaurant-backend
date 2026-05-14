package com.example.restaurant.service;

import com.example.restaurant.dto.request.CreateCategoryRequestDTO;
import com.example.restaurant.dto.response.CategoryResponseDTO;

import java.util.List;

public interface ICategoryService {

    CategoryResponseDTO saveCategory(CreateCategoryRequestDTO createCategoryRequestDTO);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO getCategoryById(Long id);

    CategoryResponseDTO updateCategory(Long id, CreateCategoryRequestDTO createCategoryRequestDTO);

    void deleteCategory(Long id);
}