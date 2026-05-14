package com.example.restaurant.mapper;

import com.example.restaurant.dto.request.CreateMenuItemRequestDTO;
import com.example.restaurant.dto.request.UpdateMenuItemRequestDTO;
import com.example.restaurant.dto.response.MenuItemResponseDTO;
import com.example.restaurant.dto.response.MenuItemSummaryDTO;
import com.example.restaurant.model.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenuItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    MenuItem toEntity(CreateMenuItemRequestDTO createMenuItemRequestDTO);

    MenuItemResponseDTO toResponse(MenuItem menuItem);

    List<MenuItemResponseDTO> toResponses(List<MenuItem> menuItems);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    MenuItem updateEntityFromDTO(UpdateMenuItemRequestDTO updateMenuItemRequestDTO, @MappingTarget MenuItem menuItem);
    //@MappingTarget ile var olan nesnenin güncellenmesi sağlandı.

    MenuItemSummaryDTO toSummaryDTO(MenuItem menuItem);

}
