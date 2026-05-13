package com.example.restaurant.mapper;

import com.example.restaurant.dto.request.CreateOrderRequestDTO;
import com.example.restaurant.dto.response.OrderResponseDTO;
import com.example.restaurant.enums.OrderStatus;
import com.example.restaurant.model.Order;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(CreateOrderRequestDTO createOrderRequestDTO);

    @AfterMapping
    default void setDefaultStatus(@MappingTarget Order order){
        order.setStatus(OrderStatus.PREPARING);
    }

    @Mapping(source = "table", target = "table")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderResponseDTO toResponse(Order order);

}