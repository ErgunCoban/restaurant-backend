package com.example.restaurant.dto.request;

import com.example.restaurant.model.RestaurantTable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequestDTO {

    @NotNull
    private Long restaurantTableId;

    @NotEmpty
    private List<CreateOrderItemRequestDTO> orderItems;

}