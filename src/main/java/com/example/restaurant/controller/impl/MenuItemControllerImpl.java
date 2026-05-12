package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.IMenuItemController;
import com.example.restaurant.controller.base.RestBaseController;
import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;
import com.example.restaurant.model.base.RootEntity;
import com.example.restaurant.service.IMenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/restaurant/menu")
public class MenuItemControllerImpl extends RestBaseController implements IMenuItemController {

    private final IMenuItemService menuItemService;

    @PostMapping("/save")
    @Override
    public RootEntity<MenuItemResponseDTO> saveMenuItem(CreateMenuItemRequestDTO createMenuItemRequestDTO) {
        return ok(menuItemService.saveMenuItem(createMenuItemRequestDTO));
    }
}
