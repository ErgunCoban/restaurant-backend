package com.example.restaurant.service;

import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.request.UpdateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;

import java.util.List;

public interface IMenuItemService {

    MenuItemResponseDTO saveMenuItem(CreateMenuItemRequestDTO createMenuItemRequestDTO);

    MenuItemResponseDTO getMenuItemById(Long id);

    List<MenuItemResponseDTO> getMenuByCategory(Long categoryId);

    void deleteMenuItem(Long id);

    MenuItemResponseDTO updateMenuItemById(Long id, UpdateMenuItemRequestDTO updateMenuItemRequestDTO);

}