package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;
import com.example.restaurant.model.base.RootEntity;

public interface IMenuItemController {

    RootEntity<MenuItemResponseDTO> saveMenuItem(CreateMenuItemRequestDTO createMenuItemRequestDTO);

}