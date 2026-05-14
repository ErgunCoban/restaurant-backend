package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.IMenuItemController;
import com.example.restaurant.controller.base.RestBaseController;
import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.request.UpdateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;
import com.example.restaurant.model.base.RootEntity;
import com.example.restaurant.service.IMenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant/menu")
public class MenuItemControllerImpl extends RestBaseController implements IMenuItemController {

    private final IMenuItemService menuItemService;

    @PostMapping("/save")
    @Override
    public RootEntity<MenuItemResponseDTO> saveMenuItem(@Valid @RequestBody CreateMenuItemRequestDTO createMenuItemRequestDTO) {
        return ok(menuItemService.saveMenuItem(createMenuItemRequestDTO));
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<MenuItemResponseDTO> getMenuItemById(@PathVariable(name = "id") Long id) {
        return ok(menuItemService.getMenuItemById(id));
    }

    @GetMapping("/category/{categoryId}")
    @Override
    public RootEntity<List<MenuItemResponseDTO>> getMenuByCategory(@PathVariable(name = "categoryId") Long categoryId) {
        return ok(menuItemService.getMenuByCategory(categoryId));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<Void> deleteMenuItemById(@PathVariable(name = "id") Long id) {
        menuItemService.deleteMenuItem(id);
        return ok(null);
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<MenuItemResponseDTO> updateMenuItemById(@PathVariable(name = "id") Long id, @RequestBody UpdateMenuItemRequestDTO updateMenuItemRequestDTO) {
        return ok(menuItemService.updateMenuItemById(id, updateMenuItemRequestDTO));
    }

}
