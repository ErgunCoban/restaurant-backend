package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;
import com.example.restaurant.model.base.RootEntity;


import java.util.List;

public interface IRestaurantTableController {

    RootEntity<RestaurantTableResponseDTO> saveRestaurantTable(
            CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO
    );

    RootEntity<List<RestaurantTableResponseDTO>> getAllRestaurantTables();

    RootEntity<RestaurantTableResponseDTO> updateRestaurantTable(
            Long id,
            CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO
    );

    RootEntity<Void> deleteRestaurantTable(Long id);

    RootEntity<RestaurantTableResponseDTO> getRestaurantTableById(Long id);

}