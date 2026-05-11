package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;
import com.example.restaurant.model.base.RootEntity;

public interface IRestRestaurantTableController {

    public RootEntity<RestaurantTableResponseDTO> saveRestaurantTable(CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO);

}
