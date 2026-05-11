package com.example.restaurant.mapper;

import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;
import com.example.restaurant.dto.response.MenuItemSummaryDTO;
import com.example.restaurant.model.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    MenuItem toEntity(CreateMenuItemRequestDTO createMenuItemRequestDTO);


    MenuItemResponseDTO toResponse(MenuItem menuItem);

    MenuItemSummaryDTO toSummaryDTO(MenuItem menuItem);

}
