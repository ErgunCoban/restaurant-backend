package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.request.UpdateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;
import com.example.restaurant.model.base.RootEntity;

import java.util.List;

public interface IMenuItemController {

    RootEntity<MenuItemResponseDTO> saveMenuItem(CreateMenuItemRequestDTO createMenuItemRequestDTO);

    RootEntity<MenuItemResponseDTO> getMenuItemById(Long id);

    RootEntity<List<MenuItemResponseDTO>> getMenuByCategory(Long categoryId);

    RootEntity<Void> deleteMenuItemById(Long id);

    RootEntity<MenuItemResponseDTO> updateMenuItemById(Long id, UpdateMenuItemRequestDTO updateMenuItemRequestDTO);

}