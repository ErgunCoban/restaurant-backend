package com.example.restaurant.dto.request;

import com.example.restaurant.model.RestaurantTable;
import jakarta.validation.constraints.NotEmpty;

public class CreateOrderRequestDTO {

    @NotEmpty
    private RestaurantTable table;


}
