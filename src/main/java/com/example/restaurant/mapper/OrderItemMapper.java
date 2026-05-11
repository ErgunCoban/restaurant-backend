package com.example.restaurant.mapper;

import com.example.restaurant.dto.request.CreateOrderItemRequestDTO;
import com.example.restaurant.dto.response.OrderItemResponseDTO;
import com.example.restaurant.model.OrderItem;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring", uses = {MenuItemMapper.class})
public interface OrderItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "menuItem", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(CreateOrderItemRequestDTO dto);

    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "menuItem", source = "menuItem")
    OrderItemResponseDTO toResponseDTO(OrderItem orderItem);

}
