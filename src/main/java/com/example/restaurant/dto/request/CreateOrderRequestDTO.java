package com.example.restaurant.dto.request;

import com.example.restaurant.model.RestaurantTable;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateOrderRequestDTO {

    @NotEmpty
    private Long restaurantTableId;

    @NotEmpty
    private List<CreateOrderItemRequestDTO> orderItems;

}