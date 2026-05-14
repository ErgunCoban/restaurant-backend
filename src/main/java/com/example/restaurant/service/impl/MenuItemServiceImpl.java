package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.request.UpdateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;
import com.example.restaurant.exception.BaseException;
import com.example.restaurant.exception.ErrorMessage;
import com.example.restaurant.exception.MessageType;
import com.example.restaurant.mapper.MenuItemMapper;
import com.example.restaurant.model.Category;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.repository.CategoryRepository;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.rules.CategoryRules;
import com.example.restaurant.rules.MenuItemRules;
import com.example.restaurant.service.IMenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements IMenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final MenuItemMapper menuItemMapper;
    private final MenuItemRules menuItemRules;
    private final CategoryRules categoryRules;

    @Override
    public MenuItemResponseDTO saveMenuItem(CreateMenuItemRequestDTO createMenuItemRequestDTO) {

        categoryRules.checkIfCategoryExistsById(createMenuItemRequestDTO.getCategoryId());
        menuItemRules.checkIfMenuItemExistsByName(createMenuItemRequestDTO.getName());
        Category category = categoryRepository.findById(createMenuItemRequestDTO.getCategoryId()).get();

        MenuItem menuItem = menuItemMapper.toEntity(createMenuItemRequestDTO);
        menuItem.setCategory(category);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        return menuItemMapper.toResponse(savedMenuItem);
    }

    @Override
    public MenuItemResponseDTO getMenuItemById(Long id) {
        menuItemRules.checkIfMenuItemExistsById(id);
        Optional<MenuItem> menuItem = menuItemRepository.findById(id);
        return menuItemMapper.toResponse(menuItem.get());
    }

    @Override
    public List<MenuItemResponseDTO> getMenuByCategory(Long categoryId) {
        List<MenuItem> menuItems = menuItemRepository.findByCategoryId(categoryId);
        return menuItemMapper.toResponses(menuItems);
    }

    @Override
    public void deleteMenuItem(Long id) {
        menuItemRules.checkIfMenuItemExistsById(id);
        menuItemRepository.deleteById(id);
    }

    @Override
    public MenuItemResponseDTO updateMenuItemById(Long id, UpdateMenuItemRequestDTO updateMenuItemRequestDTO) {
        menuItemRules.checkIfMenuItemExistsById(id);

        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(id);
        menuItemMapper.updateEntityFromDTO(updateMenuItemRequestDTO, optionalMenuItem.get());
        MenuItem updatedMenuItem = menuItemRepository.save(optionalMenuItem.get());

        return menuItemMapper.toResponse(updatedMenuItem);
    }

}