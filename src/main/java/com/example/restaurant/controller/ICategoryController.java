package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CreateCategoryRequestDTO;
import com.example.restaurant.dto.response.CategoryResponseDTO;
import com.example.restaurant.model.base.RootEntity;

import java.util.List;

public interface ICategoryController {

   RootEntity<CategoryResponseDTO> saveCategory(CreateCategoryRequestDTO createCategoryRequestDTO);

   RootEntity<List<CategoryResponseDTO>> getAllCategories();

   RootEntity<CategoryResponseDTO> getCategoryById(Long id);

   RootEntity<CategoryResponseDTO> updateCategory(Long id, CreateCategoryRequestDTO createCategoryRequestDTO);

   RootEntity<Void> deleteCategory(Long id);
}