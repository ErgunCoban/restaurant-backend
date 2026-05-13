package com.example.restaurant.service;

import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;

public interface IMenuItemService {

    MenuItemResponseDTO saveMenuItem(CreateMenuItemRequestDTO createMenuItemRequestDTO);

}