package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;
import com.example.restaurant.mapper.MenuItemMapper;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.service.IMenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements IMenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    @Override
    public MenuItemResponseDTO saveMenuItem(CreateMenuItemRequestDTO createMenuItemRequestDTO) {
        MenuItem menuItem = menuItemRepository.save(menuItemMapper.toEntity(createMenuItemRequestDTO));
        return menuItemMapper.toResponse(menuItem);
    }

}
